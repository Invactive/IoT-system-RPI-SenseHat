<?php

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: *");
    header("Access-Control-Allow-Headers: *");
    
    $method = $_SERVER['REQUEST_METHOD'];
    if($method == "PUT" || $method == "OPTIONS"){
        $put_data = json_decode(file_get_contents("php://input"), true);
        if(isset($put_data['interval'])){
            file_put_contents("../src/data/interval.txt", $put_data['interval']);
            http_response_code(200);
        }
        else{
            // http_response_code(422);
            header('Content-Type: application/json');
            echo json_encode(['error' => 'Invalid body']);
        }
    }
    else{
        http_response_code(400);
    }
    
?>