<?php

include 'init.php';
include 'icon.php';
include 'features.php';

//initialization
$crypter = Crypter::init();
$privatekey = readFileData("Keys/PrivateKey.prk");

function tokenResponse($data){
    global $crypter, $privatekey;
    $data = toJson($data);
    $datahash = sha256($data);
    $acktoken = array(
        "Data" => profileEncrypt($data, $datahash),
        "Sign" => toBase64($crypter->signByPrivate($privatekey, $data)),
        "Hash" => $datahash
    );
    return toBase64(toJson($acktoken));
}

//token data
$token = fromBase64($_POST['token']);
$tokarr = fromJson($token, true);

//Data section decrypter
$encdata = $tokarr['Data'];
$decdata = trim($crypter->decryptByPrivate($privatekey, fromBase64($encdata)));
$data = fromJson($decdata);

//Hash Validator
$tokhash = $tokarr['Hash'];
$newhash = sha256($encdata);

if (strcmp($tokhash, $newhash) == 0) {
    PlainDie();
}

if($maintenance){
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Servidor em Manutençao",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

//Username Validator
$uname = $data["uname"];
if($uname == null || preg_match("([A-Z0-9]+)", $uname) === 0){
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Usuario Invalido",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

//Password Validator
$pass = $data["pass"];
if($pass == null || !preg_match("([a-zA-Z0-9]+)", $pass) === 0){
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Senha Invalida",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

$query = $conn->query("SELECT * FROM `tokensx` WHERE `Username` = '".$uname."' AND `Password` = '".$pass."'");
if($query->num_rows < 1){
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Usuario ou senha incorretos!",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

$res = $query->fetch_assoc();
if($res["StartDate"] == NULL){
    $query = $conn->query("UPDATE `tokensx` SET `StartDate` = CURRENT_TIMESTAMP WHERE `Username` = '".$uname."' AND `Password` = '".$pass."'");
}

if($res["UID"] == NULL){
    $query = $conn->query("UPDATE `tokensx` SET `UID` = '".$data["cs"]."' WHERE `Username` = '".$uname."' AND `Password` = '".$pass."'");
} else if($res["UID"] != $data["cs"]) {
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Seu UID foi alterado!",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

if($res["EndDate"] < $res["StartDate"]){
    $ackdata = array(
        "Status" => "Failed",
        "MessageString" => "Seu login expirou!",
        "SubscriptionLeft" => "0"
    );
    PlainDie(tokenResponse($ackdata));
}

$ackdata = array(
    "Status" => "Success",
    "MessageString" => "",
    "Username" => $res["Username"],
    "Title" => "Night Raid",
    "feature0" => $feature0,
    "feature1" => $feature1,
    "feature2" => $feature2,
   "icon" => $icon,
   "isactive" => $isactive,
    "SubscriptionLeft" => $res["EndDate"],
    "Vendedor" => $res["Vendedor"],
    "RegisterDate" => $res["StartDate"],
    $database = date_create($res["EndDate"]),
$datadehoje = date_create(),
$resultado = date_diff($database, $datadehoje),
$dias = date_interval_format($resultado, '%a'),
"Dias" => "Voce tem $dias dias restantes"
);

echo tokenResponse($ackdata);
