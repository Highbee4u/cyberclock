<?php
session_start();

require_once("functions.php");
$c = $_REQUEST['copy'];
$m = $_REQUEST['mins'];

if($_SESSION['auth'] != ""){

$handle = new ticketHandler();
$handle->performStore($m, $c);

$tick = new Ticket($c, $m);
$tick->printPins();

print<<<end
<div style="clear:both; text-align:center; margin: 5px auto;">
<span><a href="#" onClick="window.print(); return false;">PRINT</a></span>
</div>
end;

}
else{
print<<<p
<p style="color: red; text-align:center;">BAD REQUEST</p>
p;
}
?>

