<?php
session_start();
require_once("functions.php");
?>

<script language="javascript">
<!--
//This code is coded and programmed by Mustafa Segun for 
//Techno Global World of Programmers
//in no reason must you re-use this (this is not a template) without the 
//company's concept ! be Warned....

var request = false;
//Check if we are using IE.
try {
//If the Javascript version is greater than 5.
request = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
//If not, then use the older active x object.
try {
//If we are using Internet Explorer.
request = new ActiveXObject("Microsoft.XMLHTTP");
} catch (E) {
//Else we must be using a non-IE browser.
request = false;
}
}
//If we are using a non-IE browser, create a javascript instance of the object.
if (!request && typeof XMLHttpRequest != 'undefined') {
request = new XMLHttpRequest();
}
var random_pager= Math.round(Math.random() * 99999999999999999999999999); //this generates random number

function authenticateTicket(){

var auth = document.getElementById("auth").value;

var copy = document.getElementById("qty").innerHTML;
var min  = document.getElementById("time").innerHTML;

var display = document.getElementById("preview");
var the_page= "generate_tickets.php?validate=" + auth + "&cop=" + copy + "&min=" + min + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
display.innerHTML = request.responseText;
}
else{
display.innerHTML = "<img src='ajax-loader3.gif' alt='' style='text-align: center; margin-top: 30px;'/><br /><p style='text-align:center; color:red; font-size: 89%; font-family: tahoma'> please wait . . . .</p>";
}
}//asyncronous function ends here
request.send(null);
}

</script>

<style type="text/css">
#preview{
text-align:center;
font-size: 90%;
}
.select{
color: #06A1E1;
font-family: Tahoma;
}
li{
text-align: left;
font-size: 80%;
}
p{
text-align: left;
font-family: verdana;
font-size: 88%;
}
input{
color: #06A1E1;
width: 70%;
margin: 2px;
}

</style>

<?php

$pro_date = $_REQUEST['dat'];
$minutes = strip_tags(addslashes($_REQUEST['mins']));
$expire = $_REQUEST['exp'];//in days
$quantity = strip_tags(addslashes($_REQUEST['qty']));

///////////////////////////////////////////////////////
sender();                      //////////////////////////
////////////////////////////////////////////////////////

print<<<end
<div id="preview">
<p>REF : <span class="select">$pro_date</span></p>
<p>Generate and print out : </p>
<ul>
<li><span class="select" id="qty">$quantity</span> Copies of <span class="select" id="time">$minutes</span> minutes Tickets.</li>
<li>Expires in <span class="select">$expire</span> days after first login.</li>
</ul>
<p>Enter Authorization Code : </p>
<form>
<input type="password" name="auth" id="auth"/> <br />
<input type="button" value="VALIDATE" style="width: 100%;" onClick="authenticateTicket(); return false;"/>
</form>
</div>
end;

?>