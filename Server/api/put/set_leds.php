<?php

    $parts = explode('/', $_SERVER['REQUEST_URI']);

    $argX;
    $argY;
    $argR = 0;
    $argG = 0;
    $argB = 0;

    foreach($parts as $i){
        if(strpos($i, "x") !== false){
            $argX = (int) filter_var($i, FILTER_SANITIZE_NUMBER_INT);
        }
        if(strpos($i, "y") !== false){
            $argY = (int) filter_var($i, FILTER_SANITIZE_NUMBER_INT);
        }
        if(strpos($i, "r") !== false){
            $argR = (int) filter_var($i, FILTER_SANITIZE_NUMBER_INT);
        }
        if(strpos($i, "g") !== false){
            $argG = (int) filter_var($i, FILTER_SANITIZE_NUMBER_INT);
        }
        if(strpos($i, "b") !== false){
            $argB = (int) filter_var($i, FILTER_SANITIZE_NUMBER_INT);
        }
    }

    header("Content-type: application/json; charset=UTF-8");

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "PUT"){
        if ($argX >= 0 && $argX < 8 &&
            $argY >= 0 && $argY < 8 &&
            $argR >= 0 && $argR < 256 &&
            $argG >= 0 && $argG < 256 &&
            $argB >= 0 && $argB < 256){
                $command = escapeshellcmd('python3 ../src/python/set_leds.py -x '.$argX.' -y '.$argY.' -r '.$argR.' -g '.$argG.' -b '.$argB);
                echo $command;
                $output = shell_exec($command);
                http_response_code(200);
            }
            else{
                http_response_code(416);
            }
        
    }
    else{
        http_response_code(400);
    }
    
?>