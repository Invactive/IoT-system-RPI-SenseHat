<?php

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: *");
    header("Access-Control-Allow-Headers: *");

    $method = $_SERVER['REQUEST_METHOD'];
<<<<<<< HEAD
    if ($method == "PUT" || $method == "OPTIONS"){
=======
    if ($method == "PUT"){
>>>>>>> Mobile_app
        $command = escapeshellcmd('python3 ../src/python/reset_leds.py');
        $output = shell_exec($command);
        http_response_code(200);
    }
    else{
        http_response_code(400);
    }
    
?>