<?php
session_start();
?>


<?php
include ("common/banner.php");   //here is where i included my site banner


if($x == "Good"){
print<<<end
<div style="text-align:center; padding: 3px; clear: both; margin: 5px 0;">
<div id="user_admin">
<iframe id="u_admin" src="admin_frame.php" >
</iframe>
</div>

<div id="tickets">
<iframe id="tick" src="ticket_frame.php" >
</iframe>
</div>
<div id="down">
<p>
<form style="float:left" onSubmit="createSoloTime(); return false;">
<span style="font-size:80%; font-family:tahoma;">Create Solo PIN : </span>
<input type="text" id="solo" title="create solo time pin"/>
<input type="submit" value="Create Solo Time" /> ||||||||||
</form>
<form style="float:right" onSubmit="deleteTicket(); return false;">
<input type="text" id="tick_id" title="enter ticket id you want to remove" />
<input type="submit" value="Delete" />
</form>
<p id="alert" style="clear:both; width:100%; margin: 3px; color:green; font-size:76%; background:#E1F3FA; height:70px; border:1px solid #73C8EB;"></p>
</p>
<form style="float:left" onSubmit="makeOffline(); return false;">
<span style="font-size:80%; font-family:tahoma;">Remove User Online : </span>
<input type="text" id="t_id" title="enter ticket id you want to remove online" />
<input type="submit" value="Remove Online" /> ||||||||
</form>
<form style="float:right;" onSubmit="track(); return false;">
<input type="text" id="t_id_pin" title="enter ticket id or pin you want to track" />
<input type="submit" value="Track" />
</form>
</div>
<div id="counter">
<p style="clear:both; text-decoration:underline; padding: 3px 0;">TODAY's Ticket COUNTER</p>

<iframe id="count" src="common/counter.php" >
</iframe>

</div>
<p><a href="index.php?page=logout">logout</a></p>
</div>
end;

}
?>
