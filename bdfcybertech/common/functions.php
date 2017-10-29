<?php
require_once("dbconnect.php");

function logIn($user_id, $password){
//double protect sql injecttion
$good_user= mysql_real_escape_string($user_id);
$good_pass= mysql_real_escape_string($password);
//query that gets users in
$sql= "SELECT * FROM clerk where username='$good_user' and password='$good_pass'";
$result= mysql_query($sql) or die(mysql_error());
$validate= mysql_num_rows($result);
$set= mysql_fetch_array($result);
//fetch out the arrays from the dbase
$user= $set['username'];
$id= $set['id'];

if($validate == 1){
$_SESSION['id'] = $id;
$_SESSION['user']= $user;

//check to send email to email addresses


print<<<end
<script>
location.href="index.php?page=admin";
</script>
end;
}
else{
$_SESSION['id'] = "";
$_SESSION['user']= "";
return "Invalid Credentials .... pls try again or contact service!";
}
}


function authenticate($pass){
$good_pass= mysql_real_escape_string($pass);

$sql= "SELECT * FROM clerk where password='$good_pass'";
$result= mysql_query($sql) or die(mysql_error());
$validate= mysql_num_rows($result);
$set= mysql_fetch_array($result);

if($validate == 1){
return true;
}
else{
return false;
}
}


class Ticket{

private $num_to_print;
private $minutes;

public function Ticket($print, $min){
$this->num_to_print = $print;
$this->minutes = $min;

}

private function ticketGen(){
$counter = "";
$pin_gen = "";
$letters = Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'M', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
$numbers = Array('2', '3', '4', '5', '6', '7', '8', '9');
$let_num = array_merge($letters, $numbers);
$range = 10;
$surf = Array(mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)),
mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)), mt_rand(0, count($let_num)));

for($j = 0; $j < $range; $j++){
$pin_gen .= $let_num[$surf[$j]];
}
return $pin_gen;

}//function that generates password for user here


private function checkEachPins($p){
$sql = "SELECT * FROM unused_tickets WHERE pin='$p'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count > 0){
//that means ticket already exists
return "";
}
else{
//good ticket gen
return $p;
}
}

private function downloadPin($p, $min){
$sql = "INSERT INTO unused_tickets(pin, mins_rem) VALUES ('$p','$min')";
$result = mysql_query($sql) or die(mysql_error());
//$tick = new ticketHandler();
//$tick->passHandler($min, $this->num_to_print);
//$tick->performStore();
}

public function printPins(){
$p = Array();

print<<<top
<div id="ticket" style="text-align:center; font-family: arial, sans-serif; line-height:0.6em; clear:both; width: 100%; margin: 2px auto;">
top;

for($print = 0; $print < $this->num_to_print; $print++){
$check = $this->checkEachPins($this->ticketGen());
if($check != ""){
$p[$print] = $check;
$this->downloadPin($p[$print], $this->minutes);
print<<<mid
<div style="border: 2px solid black; width: 170px; float:left">
<p style="font-weight:bold">BDF .NET</p>
<span>$this->minutes minutes</span>
<p><span style="font-weight:bold">PIN </span>: $p[$print]</p>
</div>
mid;
}
}

print<<<down
</div>

down;
}

}//end of class here

function readUsers(){
$sql = "SELECT * FROM using_tickets";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
for($i = 0; $i < $count; $i++){
$id = mysql_result($result, $i, "id");
$name = mysql_result($result, $i, "user");
$time = mysql_result($result, $i, "mins_rem");

print<<<end
<tr>
<td>$id</td>
<td>$name</td>
<td>$time</td>
</tr>
end;
}
} //end of function that reads user here


class TicketHandler{

private $mins;
private $qty;
private $date;

public function ticketHandler(){
$this->date = date("d / m / Y");
}

public function passHandler($m, $q){
$this->mins = $m;
$this->qty = $q;
}

public function makeSolo(){
$this->mins = "SOLO";
$sql = "INSERT INTO ticketting (time,qty,date) VALUES ('$this->mins', 1, '$this->date')";
$result = mysql_query($sql) or die(mysql_error());
}

public function getSoloTime(){
$sql = "SELECT time FROM ticketting WHERE time='SOLO' and date='$this->date'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
return "<span style='color:black'>$count</span>";
}

public function performStore($min, $qty){
$sql = "SELECT time, qty FROM ticketting WHERE time='$min' and date='$this->date'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count == 0){
//insert new record
$sql2 = "INSERT INTO ticketting (time, qty, date) VALUES ('$min', '$qty', '$this->date')";
$result2 = mysql_query($sql2) or die(mysql_error());
}
else{
//then update the table
$data = mysql_fetch_array($result);
$get_qty = $data['qty'];
$quant = ($qty + $get_qty);
//now insert it
$sql3 = "UPDATE ticketting SET qty='$quant', date='$this->date' WHERE time='$min'";
$result3 = mysql_query($sql3) or die(mysql_error());
}
}

public function displaySummary(){
$sql = "SELECT * FROM ticketting WHERE time <> 'SOLO' and date='$this->date'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count > 0){
for($i= 0; $i < $count; $i++){
$t = mysql_result($result, $i, "time");
$q = mysql_result($result, $i, "qty");
print<<<end
$t <span style="font-size:80%; font-family:tahoma;">Minutes : Printed</span> $q |||
end;
}

}
else{
//zero
}
}//display sumary function ends here

public function displaySummaryToSend(){
$sql = "SELECT * FROM ticketting WHERE time <> 'SOLO' and date='$this->date'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count > 0){
for($i= 0; $i < $count; $i++){
$t = mysql_result($result, $i, "time");
$q = mysql_result($result, $i, "qty");
print<<<end
$t <span style="font-size:80%; font-family:tahoma;">Minutes : Printed</span> $q || <br />
end;
}

}
else{
//zero
}
}//display sumary function ends here

}//end of ticket handler's class ends here


function sendSummary(){
$tick = new TicketHandler();
$solo = $tick->getSoloTime();

$header = 'From: support@technoglobalprogrammers.net' . "\r\n" .
    'Reply-To: support@technoglobalprogrammers.net' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();

$msg = "
      SUMMARY,
##########################################################
      This Message contains the TIMER Summary of BDF Cyber Tech.
****************************************************************
****************************************************************
      SOLO TIMER : $solo
      ".
      $tick->displaySummaryToSend().
      "
****************************************************************
****************************************************************
#########################################################
      Techno Global World Of Programmers,
      Admin & General Developer.
      08025481373, 08066131861
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
";

$sql = "SELECT * FROM email";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
for($i = 0; $i < $count; $i++){
$res = mysql_result($result, $i, "email");
if(mail($res, "BDF CYBER TECH - ".date("d / m / Y"), $msg, $header)){
return true;
}
else{
return false;
}
}
}//end of function that send summary ends here

function sender(){
$today = date("d / m / Y");

$sql = "SELECT * FROM ticketting WHERE date <> '$today'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count > 0){
//then , send the mail
if(sendSummary()){
//after sending ...... 
$sql2 = "DELETE FROM ticketting WHERE date <> '$today'";
$result2 = mysql_query($sql2) or die(mysql_error());
}
else{
//do nothing
}
}
else{
//do nothing
}
}//sender function ends here

?>
