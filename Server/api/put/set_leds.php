<?php

    header("Content-type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: *");
    header("Access-Control-Allow-Headers: *");

    $keys_arr = ['x', 'y', 'r', 'g', 'b'];
    $arg_dict = array();

    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "PUT" || $method == "OPTIONS"){
        $put_data = json_decode(file_get_contents("php://input"), true);
        for($i=0; $i<count($keys_arr); $i++){
            if(array_key_exists($keys_arr[$i], $put_data)){
                $arg_dict[$keys_arr[$i]] = $put_data[$keys_arr[$i]];
            }
            else{
                $arg_dict[$keys_arr[$i]] = 0;
            }
        }

        if ($arg_dict['x'] >= 0 && $arg_dict['x'] < 8 &&
            $arg_dict['y'] >= 0 && $arg_dict['y'] < 8 &&
            $arg_dict['r'] >= 0 && $arg_dict['r'] < 256 &&
            $arg_dict['g'] >= 0 && $arg_dict['g'] < 256 &&
            $arg_dict['b'] >= 0 && $arg_dict['b']< 256){
                $command = escapeshellcmd('python3 ../src/python/set_leds.py -x '.$arg_dict['x'].' -y '.$arg_dict['y'].' -r '.$arg_dict['r'].' -g '.$arg_dict['g'].' -b '.$arg_dict['b']);
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
