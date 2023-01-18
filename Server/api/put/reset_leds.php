<?php

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
    
    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "PUT"){
        $command = escapeshellcmd('python3 ../src/python/reset_leds.py');
        $output = shell_exec($command);
        http_response_code(200);
    }
    else{
        http_response_code(400);
    }
    
?>