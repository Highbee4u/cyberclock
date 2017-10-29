<?php
session_start();

require_once("functions.php");

$auth = $_REQUEST['validate'];
$copy = $_REQUEST['cop'];
$mins = $_REQUEST['min'];

$val = authenticate($auth);
if($val){

$_SESSION['auth'] = $auth;

print<<<end
<img src='ajax-loader3.gif' alt='' style='text-align: center; margin-top: 30px;' onLoad="window.open('ticket.php?copy=$copy&mins=$mins', 'BDF CYBER TECH TICKETS - Please print', 'width=780, height=1000,menubar=no,status=yes,toolbar=no,scrollbars=yes');"/><br /><p style='text-align:center; color:red; font-size: 89%; font-family: tahoma'> generating pins . . . .</p>
<span><a href="#" onClick='history.back();'/>click this to go back after printing</a></span>
end;
}

else{
$_SESSION['auth'] = "";
print<<<h
<p style="text-align:center; color:red; font-family:georgia; font-size:90%;">AUTHENTICATION FAILED</p>
<span><a href="#" onClick='history.back();'/>click this to go back and try again</a></span>
h;

}
?>