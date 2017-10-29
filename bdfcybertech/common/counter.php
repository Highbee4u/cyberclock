
<?php
require_once("functions.php");

$tick = new TicketHandler();
$solo = $tick->getSoloTime();
print<<<top
<span style="font-size:80%; font-family:tahoma; clear:both; width:100%; margin: 3px; background:#E1F3FA; height:70px; border:1px solid #73C8EB;"><span style="font-size:80%; font-family:tahoma;">SOLO :</span> $solo || 
top;
$tick->displaySummary();
print<<<down
</span>
down;
?>