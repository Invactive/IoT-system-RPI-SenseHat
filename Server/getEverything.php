<?php
    $command = escapeshellcmd('python3 zadanie1.py -t C -p hPa -h %');
    $outputPOM = shell_exec($command);

    $command = escapeshellcmd('python3 zadanie2.py -u deg -r -p -y');
    $outputORIENT = shell_exec($command);

    $command = escapeshellcmd('python3 get_LEDS.py');
    $outputLEDS = shell_exec($command);
    
    // $command = escapeshellcmd('sudo python3 zadanie4.py > /var/www/html/joystick.dat &');
    // $outputJoy = shell_exec($command);
    // $outputJoy = exec('python3 zadanie4.py > /dev/null 2>/dev/null &');
    // $outputJoy = system('sudo python3 zadanie4.py > /dev/null &');
    // echo 


    $joyF = file_get_contents('joystick.dat');

    $LEDS = array("LEDS"=>json_decode($outputLEDS));
    $LEDS2 = json_encode($LEDS);
    
    $result = json_encode(
        array_merge(
            json_decode($outputPOM, true), 
            json_decode($outputORIENT, true),
            json_decode($LEDS2, true),
            json_decode($joyF, true)
        )
        );
    echo $result;
    
?>