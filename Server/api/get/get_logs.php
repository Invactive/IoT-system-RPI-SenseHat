<?php
<<<<<<< HEAD
    $http_origin = $_SERVER['HTTP_ORIGIN'];

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
=======

    header("Content-type: application/json; charset=UTF-8");

    $parts = explode('/', $_SERVER['REQUEST_URI']);
    $argTime;

    foreach($parts as $i){
        if(strpos($i, "time") !== false){
            $argTime = (float) filter_var($i, FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION);
        }
    }
>>>>>>> Mobile_app

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "GET"){
        $output = file_get_contents("../src/data/logs.json");
        http_response_code(200);
        echo $output;
    }
<<<<<<< HEAD
=======
    else if($method == "PUT"){
        file_put_contents("../src/data/interval.txt", $argTime);
        http_response_code(200);
    }
>>>>>>> Mobile_app
    else{
        http_response_code(400);
    }
    
?>