<?php
session_start();
?>

<style type="text/css">
h3{
letter-spacing: 0.1em; 
font-family: ARIAL; 
font-size: 80%;
}
form{
width: 100%;

}
label{
float: left;
text-align: left;
margin: 7px 2px;
padding: 1px;
font-size: 88%;
width: 42%;
}
input, select{
float: right;
color: #06A1E1;
margin: 7px 2px;
padding: 1px;
width: 50%;
}

</style>
<?php
require_once("common/functions.php");

//$tick = new Ticket(9, 30);


$date = date("d / m / Y");
print<<<end

<div id="tic" style="text-align:center; padding: -5px 1px;">
<h3>TICKETS</h3>
<form action="common/p_tickets1.php" method="POST">
<label for="date">Production Date : </label><input type="text" name="date" value="$date" disabled="disabled" /><br />
<input type="hidden" value="$date" name="dat" />
<label for="mins" >Minutes : </label> <input type="text" name="mins" /> <br />
<label for="exp" >Expire : </label>
<select name="exp">
<option value="0" >Please Select</option>
<option value="1">1 day</option>
<option value="2">2 days</option>
<option value="3">3 days</option>
<option value="4">4 days</option>
<option value="5">5 days</option>
<option value="6">6 days</option>
<option value="7">7 days</option>
</select> <br />
<label for="qty">Quantity</label> <input type="text" name="qty" /><br />
<input type="submit" value="PROCESS" style="width:100%"/>

</form>
</div>

end;

//$tick->printPins();
?>