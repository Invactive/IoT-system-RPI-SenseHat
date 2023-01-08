
<?php
    $command = escapeshellcmd('python3 zadanie1.py -t C');
    $outputZAD1 = shell_exec($command);
    $jsonZAD1 = json_encode(json_decode($outputZAD1));
    echo $jsonZAD1;
?>