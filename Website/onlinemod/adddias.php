<html lang="pt-br" >
<head>
<meta charset="UTF-8">
<title>Adicionado dias...</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/index.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'>
<style>
    body {
    background: url("wallpaper.jpg");
	display: table;
    width: 100%;
  height: 100vh;
  padding: 100px 0;
  
  

  background-position: 30% 45%;
  background-size: cover;
	}
	
	h2 {
	    color: red;
	}
	p {
	    color: red;
	    font-size: 20px;
	}
	
	td {
	    background-color: white;
	}
	}
	h8 {
	    font-size: 80px;
	    color: red;
	}
	a {
	    font-size: 40px;
	    color: red;
	}
    .container {
        width: 100%;
    }
    input[type=number] {
width: 100%;
height: 40px;
padding: 10x;
color: red;
font-size:18px;
font-weight: bold;
    }
    //input[name="adicionardias"] {
  //background: red;
//}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>
<body>
  <div id="registration-form">
	<div class='fieldset'>
		<form action="" method="post" data-validate="parsley">
			<div class='row'>
			<!--	<label for='firstname'>Username</label> -->
				<input type="text" placeholder="Usuario" name='nome' id='firstname' data-required="true" data-error-message="Digite o nome!" required>
			</div>
			<div class='row'>
			<!--	<label for="lastname">Password</label> -->
				<input type="Number" placeholder="Numero de Dias"  name='dias' data-required="true" data-type="email" data-error-message="Digite os dias!" required>
			</div>
			<input type="submit" class="btn btn-success" value="Adicionar dias" name="adicionardias">
		</form>
	</div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/parsley.js/1.2.2/parsley.min.js'></script>
 
<?php   // this code is use to insert the form details and register and expiration date
include '/storage/ssd4/290/15501290/public_html/DB.php';
include 'Global.php';

$hora = date("h/m/s");
if(isset($_POST['adicionardias'])){
$Username = $_POST['nome'];
$dias  = $_POST['dias'];
$sql = "SELECT * FROM tokensx WHERE Username = '$Username'";
	$resultado = mysqli_query($conn, $sql);
	$dados = mysqli_fetch_array($resultado);
   $date2 = $dados['EndDate'];


$mod_date = strtotime($date2."+ $dias days");
$adicionardias = date("Y/m/d h:m",$mod_date);
//echo $adicionardias;
$conn->query("UPDATE tokensx SET EndDate='$adicionardias' WHERE `Username` = '$Username'");   
   //echo "UPDATE tokens SET EndDate='$adicionardias' WHERE `Username` = '$Username'";   
     
?>
<script type="text/javascript">
	alert("Adicionado com Sucesso!");
	window.location.href='painel2.php';
</script>
<?php
}
?>