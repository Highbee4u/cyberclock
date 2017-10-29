<?php
session_start();
require_once("functions.php");

$check = $_SESSION['id'];

$solo = $_REQUEST['solo'];
$delete = $_REQUEST['delete'];
$offline = $_REQUEST['offline'];
$track = $_REQUEST['track'];

if($check != ""){

if($solo != ""){
$sql = "SELECT * FROM unused_tickets WHERE pin='$solo'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count > 0){
//that means ticket already exists
print<<<end
ERORR 101
end;
return "";
}
else{
//good ticket gen
$sql2 = "INSERT INTO unused_tickets(pin, mins_rem) VALUES ('$solo','20')";
$result2 = mysql_query($sql2) or die(mysql_error());
$tick = new ticketHandler();
$tick->passHandler('20', '');
$tick->makeSolo();
print<<<end
SUCCESS !!!
end;
}

}
elseif($delete != ""){
$sql = "SELECT * FROM unused_tickets WHERE id='$delete'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count < 1){
//that means ticket already exists
print<<<end
No Such Ticket
end;
return "";
}
else{
//good ticket gen
$sql = "DELETE FROM unused_tickets WHERE id='$delete'";
$sql2 = "DELETE FROM using_tickets WHERE id='$delete'";
$result = mysql_query($sql) or die(mysql_error());
$result2 = mysql_query($sql2) or die(mysql_error());
print<<<end
SUCCESS !!!
end;
}
}
elseif($offline != ""){
$sql = "SELECT * FROM using_tickets WHERE id='$offline'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count < 1){
//that means ticket already exists
print<<<end
No Such USER
end;
return "";
}
else{
//good ticket gen
$sql = "UPDATE unused_tickets SET active=0 WHERE id='$offline'";
$result = mysql_query($sql) or die(mysql_error());

$sql2 = "DELETE FROM using_tickets WHERE id='$offline'";
$result2 = mysql_query($sql2) or die(mysql_error());
print<<<end
SUCCESS !!!
end;
}
}


elseif($track != ""){
$sql = "SELECT * FROM unused_tickets WHERE id='$track'";
$result = mysql_query($sql) or die(mysql_error());
$count = mysql_num_rows($result);
if($count < 1){
//that means ticket already exists
$sql1 = "SELECT * FROM unused_tickets WHERE pin='$track'";
$result1 = mysql_query($sql1) or die(mysql_error());
$count1 = mysql_num_rows($result1);
if($count1 < 1){
print<<<end
No Such TICKET
end;
return "";
}
else{
$data = mysql_fetch_array($result1);
$id = $data['id'];
$pin = $data['pin'];
if($data['active'] != 0){
$stat = "Active";
}
else{
$stat = "Not Active";
}
$time = $data['mins_rem'];

print<<<end
<div style="font-family:tahoma; font-size:76%; padding: 5px; line-height:1.5em">
Ticket ID : <span style="color:black">$id</span><br />
PIN : <span style="color:black">$pin</span><br />
STATUS : <span style="color:black">$stat</span><br />
TIME REM : <span style="color:black">$time</span><br />
</div>
end;
}
}
else{
//good ticket gen
$data = mysql_fetch_array($result);
$id = $data['id'];
$pin = $data['pin'];
if($a = $data['active'] != 0){
$stat = "Active";
}
else{
$stat = "Not Active";
}
$time = $data['mins_rem'];

print<<<end
<div style="font-family:tahoma; font-size:76%; padding: 5px; line-height:1.5em">
Ticket ID : <span style="color:black">$id</span><br />
PIN : <span style="color:black">$pin</span><br />
STATUS : <span style="color:black">$stat</span><br />
TIME REM : <span style="color:black">$time</span><br />
</div>
end;
}
}


else{
//do nothing
print<<<e
Contact Service 1
e;
}

}//
else{
print<<<e
Contact Service 2
e;
}

?>