<!DOCTYPE html>
<html lang="en-US">

<head>
    <link rel='stylesheet' href='style.css'/>
    <script src="script.js" type='text/JavaScript' defer></script>
    <link rel="shortcut icon" href="#">
</head>

<body>
<div class="wrapper">
    <div class='grid'>

    </div>
    <form name="form" action="<?php echo $_SERVER['PHP_SELF']?>" method="POST">
        <input type="submit" name="submit" value="submit"> <br>
    </form>
</div> 

</body>
 
</html>

<?php
    echo "<hr>";

    $clickedBtns = $_COOKIE['json'];
    $clickedBtnsJSON = json_decode($clickedBtns, true);
    $keys = array_keys($clickedBtnsJSON);
    echo "LED's you chose: <br>";
    foreach($clickedBtnsJSON as $key => $val){
        $keyLin = (int) filter_var($key, FILTER_SANITIZE_NUMBER_INT);
        $RGBargs = explode(",", $val);
        foreach($RGBargs as $arg){
            $RGBints[] = intval($arg);
        }
        $argX = $keyLin % 8;
        $argY = ($keyLin - $argX) / 8;
        $command = escapeshellcmd('python3 zadanie3.py -x '.$argX.' -y '.$argY.' -r '.$RGBints[0].' -g '.$RGBints[1].' -b '.$RGBints[2]);
        $runPY = shell_exec($command);
        echo "X: $argX, Y: $argY -> R: $RGBints[0], G: $RGBints[1], B: $RGBints[2]<br>";
        unset($RGBints);
    }

    $cmd = escapeshellcmd('python3 get_LEDS.py');
    $LED_state = shell_exec($cmd);
    setcookie("LEDstate", $LED_state);
    
?>