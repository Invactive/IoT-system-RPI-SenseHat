<?php
    // Get
    $json = file_get_contents('php://input');
    
    // Save
    file_put_contents('led.json', $json);

    // Exec .py script using 'led.json'
    $command = escapeshellcmd('python3 setLED.py');
    $outputZAD1 = shell_exec($command);
    header('Content-Type: application/json; charset=utf-8');
    echo json_encode(json_decode($json));

    
?>