<form name="form" action="<?php echo $_SERVER['PHP_SELF']?>" method="GET">
<div style="width:100%; height :50%;  background-color:Lime;">
    <div style="width:34%; height:100%; background-color:Lightblue; float:left;">
        Input options to show results<br>
        -t C or -t F<br>
        -p hPa or -p mmHg<br>
        -h % or -h [value 0-1]<br>
        <input type="text" name="pole1ARG"> <br>
        <?php
            $zad1ARG = $_GET["pole1ARG"];
            $command = escapeshellcmd('python3 zadanie1.py '.$zad1ARG);
            $outputZAD1 = shell_exec($command);
            $jsonZAD1 = json_encode(json_decode($outputZAD1));
            echo $jsonZAD1;
        ?>
    </div>
    <div style="width:33%; height:100%; background-color:Gray; float:left;">
        Input options to show results<br>
        -u deg or rad<br>
        -r<br>
        -p<br>
        -y<br>
        <input type="text" name="pole2ARG"> <br>
        <?php
            echo "<br>";
            $zad2ARG = $_GET["pole2ARG"];
            $command = escapeshellcmd('python3 zadanie2.py '.$zad2ARG);
            $outputZAD2 = shell_exec($command);
            $jsonZAD2 = json_encode(json_decode($outputZAD2));
            echo $jsonZAD2;
        ?>
    </div>
    <div style="width:33%; height:100%; background-color:Lightblue; float:left;">
        Joystick.dat
        <?php
            echo "<br>";
            $resZAD4 = file_get_contents('./joystick.dat', true);
            $jsonZAD4 = json_encode(json_decode($resZAD4));
            echo $jsonZAD4;
        ?>
    </div>    
</div>
    <input type="submit" name="submit" value="submit"> <br>
</form>

<?php
    echo "<hr>";
    echo json_encode(array_merge(json_decode($jsonZAD1, true), json_decode($jsonZAD2, true), json_decode($jsonZAD4, true)));
?>
