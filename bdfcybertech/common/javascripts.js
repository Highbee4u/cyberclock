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

function authenticateTicket(form){
with(form){
var auth = form.auth.value;
}

var display = document.getElementById("preview");
var the_page= the_file + "?generate_tickets.php?validate=" + auth + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
display.innerHTML = request.responseText;
}
else{
display.innerHTML = "<img src='images/ajax-loader3.gif' alt='' style='text-align: center; margin-top: 30px;'/><br /><p> please wait . . . .</p>";
}
}//asyncronous function ends here
request.send(null);
}


function createSoloTime(){

var pin = document.getElementById("solo").value;
if(pin == "" || pin.indexOf('1', 0) > 0 || pin.indexOf(' ', 0) > 0 || pin.indexOf('0', 0) > 0){
alert("cannot assign an empty pin or invalid characters error");
return false;
}

//var display = document.getElementById("preview");
var the_page= "common/admin_processes.php?solo=" + pin + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
alert("DONE");
document.getElementById("solo").disabled = "";
document.getElementById("alert").innerHTML = request.responseText;
document.getElementById("t_id").value = "";
document.getElementById("t_id_pin").value = "";
document.getElementById("tick_id").value = "";
document.getElementById("solo").value = "";
}
else{
document.getElementById("solo").disabled = "disabled";
document.getElementById("solo").value = "processing";
}
}//asyncronous function ends here
request.send(null);
}


function deleteTicket(){

var pin = document.getElementById("tick_id").value;

if(pin == ""){
alert("No Ticket ID to delete");
return false;
}

//var display = document.getElementById("preview");
var the_page= "common/admin_processes.php?delete=" + pin + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
alert("DONE");
document.getElementById("tick_id").disabled = "";
document.getElementById("alert").innerHTML = request.responseText;
document.getElementById("t_id").value = "";
document.getElementById("t_id_pin").value = "";
document.getElementById("tick_id").value = "";
document.getElementById("solo").value = "";
}
else{
document.getElementById("tick_id").disabled = "disabled";
document.getElementById("tick_id").value= "processing";
}
}//asyncronous function ends here
request.send(null);
}



function makeOffline(){

var pin = document.getElementById("t_id").value;

if(pin == ""){
alert("No Ticket ID to make Offline");
return false;
}

//var display = document.getElementById("preview");
var the_page= "common/admin_processes.php?offline=" + pin + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
alert("DONE");
document.getElementById("t_id").disabled = "";
document.getElementById("alert").innerHTML = request.responseText;
document.getElementById("t_id").value = "";
document.getElementById("t_id_pin").value = "";
document.getElementById("tick_id").value = "";
document.getElementById("solo").value = "";
}
else{
document.getElementById("t_id").disabled = "disabled";
document.getElementById("t_id").value= "processing";
}
}//asyncronous function ends here
request.send(null);
}


function track(){

var pin = document.getElementById("t_id_pin").value;

if(pin == ""){
alert("No Ticket ID to track");
return false;
}

//var display = document.getElementById("preview");
var the_page= "common/admin_processes.php?track=" + pin + "&surf=" + random_pager;

request.open("GET", the_page);
request.onreadystatechange = function(){
if(request.readyState == 4 && request.status == 200){
alert("DONE");
document.getElementById("t_id_pin").disabled = "";
document.getElementById("alert").innerHTML = request.responseText;
document.getElementById("t_id").value = "";
document.getElementById("t_id_pin").value = "";
document.getElementById("tick_id").value = "";
document.getElementById("solo").value = "";
}
else{
document.getElementById("t_id_pin").disabled = "disabled";
document.getElementById("t_id_pin").value= "processing";
}
}//asyncronous function ends here
request.send(null);
}



function refreshUsers(){
setInterval("document.getElementById('u_admin').contentWindow.location.reload();" ,30000);
setInterval("document.getElementById('count').contentWindow.location.reload();" ,30000);
}

-->