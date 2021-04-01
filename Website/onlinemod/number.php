<?php
// Sessão
session_start();
// Verificação
if(!isset($_SESSION['logadox'])):
  header('Location: verification.php');
endif;
?>
<html>
<html lang="pt-br" >
 
<head>
<meta charset="UTF-8">
<title>BHA TEAM</title>
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
  
  .sair {
      text-align: center;
      padding: 10px;
  }
  
  a {
      font-size: 40px;
      color: red;
  }
    .container {
        width: 100%;
    }
    input[type=number] {
width: 50px;
padding: 1px;
    }
    
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/parsley.js/1.2.2/parsley.min.js'></script>
</head>
<body>
<?php
include '/storage/ssd4/290/15501290/public_html/DB.php';
?>
<div class="container"><?php $key2=$_GET["number"];?>
  <h2>Delimitado para: <?php echo $key2;?> </h2>    
  
  <table class="table table-hover">
    <thead>
      <tr class="info">
        <th>Usuario</th>
        <th>Senha</th>
        <th>Vendedor</th>
        <th>UID</th>
    <th>Data de Registro</th>
        <th>Validade</th>
    <th>Status</th>
        <th>Eliminar</th>
        <th>Resetar</th>
        <th>Adicionar dias</th>
        <th>Dias</th>
      </tr>
    </thead>
    <tbody>
<?php
if(isset($_GET['number']))
{
$key=$_GET["number"];  //key=pattern to be searched
$nome="Hyupai";
echo $key;
// Check connection
$result2=mysqli_query($conn,"select * from `tokensx` LIMIT $key"); 

while($row=mysqli_fetch_assoc($result2))
{
?>
     <tr>
        <td><?php echo $row['Username'];?></td>
        <td><?php echo $row['Password'];?></td>
        <td><?php echo $row['Vendedor'];?></td>
    <td><?php if ($row['UID'] == NULL) {
    echo "0/1";
 } else {
    echo "1/1";
} 
        ?></td>
        <td><?php echo $date1 = $row['StartDate'];?></td>
    <td><?php echo $date2 = $row['EndDate'];?></td>
    <td><?php if(strtotime(date("Y/m/d")) < strtotime($date2)) echo "Active"; else { echo "Expired";} ?></td>
    <?php
      {
         echo "<td> <a href='mrz.php?no=".$row['Username']."'><button type='button' class='btn btn-danger'>Eliminar</button></a> </td>";
      }

      ?>
      <?php
      {
         echo "<td> <button type='button' class='btn btn-danger'>Resetar</button> </td>";
      }


      ?>
 <?php
 
    echo "<td> <a href='adddias.php'><button type='submit' name='adicionardias' class='btn btn-danger'>Adicionar dias</button></a> </td>";
    

    ?>
        <?php 
$database = date_create($date2);
$datadehoje = date_create();
$resultado = date_diff($database, $datadehoje);
echo "<td>";
echo date_interval_format($resultado, '%a');
echo "</td>";
        ?>
       
       
        <?php
    
  if(isset($_POST['adicionardiass'])){
$dias = $_POST['dias'];
$mod_date = strtotime($date2."+ 1 days");
$adicionardias = date("Y/m/d h:m",$mod_date);
$nome = $row['Username'];
echo $nome;

//$adicionardias = date('Y/m/d h:m', strtotime('+$dias days', strtotime($date2)));;
  } 
  ?>

      </tr>

      
      <?php }  ?>
    </tbody>
  </table>

</div>
<script>
function myFunction($lol) {
<?php
$delete = "SELECT * FROM `tokens1`";
}
?>
    
</script>
</body>
</html>
