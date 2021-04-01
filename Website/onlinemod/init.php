<?php
//api url filter
if(strpos($_SERVER['REQUEST_URI'],"init.php")){
    require_once 'Utils.php';
    PlainDie();
}

set_include_path(get_include_path() . PATH_SEPARATOR . 'phpseclib');

include 'Global.php';
include '/storage/ssd4/290/15501290/public_html/DB.php';
include 'Utils.php';
include 'Crypter.php';