<?php
$_SESSION['id'] = "";
$_SESSION['user']= "";
print<<<end
<script>
location.href="index.php";
</script>
end;
?>