<?php 
include '/storage/ssd4/290/15501290/public_html/DB.php';

     $conn->query("UPDATE tokensx SET UID=NULL");   
?>
<script type="text/javascript">
	alert("RESETADO");
	window.location.href='painel2.php';
</script>