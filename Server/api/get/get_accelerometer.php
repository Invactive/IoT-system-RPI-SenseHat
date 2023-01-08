<?php

    header("Content-type: application/json; charset=UTF-8");

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "GET"){
        $command = escapeshellcmd('python3 ../srcPY/get_accelerometer.py');
        $output = shell_exec($command);
        http_response_code(200);
        echo $output;
    }
    else{
        http_response_code(400);
    }
    
?>