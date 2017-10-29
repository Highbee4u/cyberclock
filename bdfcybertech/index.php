<?php
session_start();
include ("common/security.php");
include ("common/functions.php");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>::.BDF .NET::. </title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="admin.css" type="text/css" media="screen,projection" />
<script language="javascript" src="common/javascripts.js"></script>
</head>
<body onLoad="refreshUsers();">
<div id="wrapper">
<div id="content">
<?php //contents are coded here 

$page= $_REQUEST['page'];
if($page == ""){
$page= "home";
}
$link= "pages/$page.php";
if(!file_exists($link)){
$link="pages/home.php";
}
include ("$link");
?>
</div>
<?php
include ("common/footer.php"); //here is where i included my footer
?>
</div>
</body><!--body ends here-->
</html>