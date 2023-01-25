<?php
    $http_origin = $_SERVER['HTTP_ORIGIN'];

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "GET"){
        $output = file_get_contents("../src/data/logs.json");
        http_response_code(200);
        echo $output;
    }
    else{
        http_response_code(400);
    }
    
?>