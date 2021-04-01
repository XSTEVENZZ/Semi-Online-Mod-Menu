<?php
include '/storage/ssd4/290/15501290/public_html/DB.php';
include 'Global.php';

if($maintenance == false){
     $conn->query("DELETE FROM `tokensx` WHERE `Username` = '".$_GET['no']."'");   
}

?>

<script type="text/javascript">
	alert("Producto Eliminado exitosamente");
	window.location.href='painel2.php';
</script>