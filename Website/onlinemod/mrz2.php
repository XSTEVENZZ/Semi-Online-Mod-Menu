<?php
include '/storage/ssd4/290/15501290/public_html/DB.php';
include 'Global.php';

if($maintenance == false){
     $conn->query("UPDATE tokensx SET UID=NULL WHERE `Username` = '".$_GET['no']."'");   
}

?>

<script type="text/javascript">
	alert("RESETADO");
	window.location.href='painel2.php';
</script>