<?php

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: *");
    header("Access-Control-Allow-Headers: *");

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "PUT" || $method == "OPTIONS"){
        $command = escapeshellcmd('python3 ../src/python/reset_leds.py');
        $output = shell_exec($command);
        http_response_code(200);
    }
    else{
        http_response_code(400);
    }
    
?>
