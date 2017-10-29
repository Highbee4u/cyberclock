<style type="text/css">
h3{
letter-spacing: 0.1em; 
font-family: ARIAL; 
font-size: 80%;
}
table{
border: 3px solid black; 
text-align: center; 
width: 100%; 
margin: -11px auto; 
border-collapse: collapse;
}
tr{
border: 1px solid green;
}
th{
border: 1px solid black;
color: #8A70E5;
text-align: center;
font-family: verdana;
font-weight: bold;
letter-spacing: 0.1em;
font-size: 72%;
line-height: 1.6em;
}
td{
font-family: tahoma;
text-align: center;
font-size: 80%;
color: #06A1E1;
border: 1px solid #FFCE7D;
}
</style>
<?php
require_once("common/functions.php");

print<<<end

<div id="ad" style="text-align:center; padding: -5px 1px;">
<h3>SYSTEMS ONLINE</h3>
<table>
<tr>
<th>ID</th>
<th>System Name</th>
<th>Timer</th>
</tr>
end;

readUsers();

print<<<down
</table>
</div>
down;
?>