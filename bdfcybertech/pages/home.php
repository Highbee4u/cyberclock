<div id="login_page">
<?php
include ("common/banner.php");   //here is where i included my site banner

if(isset($_POST['page'])){
$user_id= strip_tags($_REQUEST['user_id']);
$password= strip_tags($_REQUEST['psw']);

$stat= logIn($user_id, $password);
}
?>
<div style="clear: both; ">
<p style="font-size: 79%; font-weight: bold; text-align: left; margin: 0 0 0 80px ">Pls Enter Login Details Below : </p>
<form method="POST" action="index.php" target="_self" style="margin: 5px 0 ;">
<label for="" style="color: red; font-size: 71%; margin: -10px 0 0 0" ><?php echo $stat;?> </label> <br />
<label for="user_id" class="label">USER ID :</label> 
<input type="text" name="user_id" id="email_id" class="input"/> <br />
<label for="psw" class="label">PASSWORD :</label>
<input type="password" name="psw" id="psw" class="input"/> <br />
<input type="hidden" name="page" value="home" />
<input type="submit" value="ACCESS ADMIN PANEL" style="font-size: 80%; color: green" /> 
</form>
</div>
</div>