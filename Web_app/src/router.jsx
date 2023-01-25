import { HashRouter, Route, Routes } from "react-router-dom";
import React from "react";
import { Home } from "./pages/Home";
import { Matrix } from "./pages/Matrix";
import { Charts } from "./pages/Charts";
import { Settings } from "./pages/Settings";
import { Data } from "./pages/Data";
import GlobalStyle from "./globalStyles";
import { useState } from "react";
import { Temperature } from "./pages/Temperature";
import { Humidity } from "./pages/Humidity";
import { Pressure } from "./pages/Pressure";
import { Accelerometer } from "./pages/Accelerometer";

const Router = () => {
  const [chartInterval, setChartInterval] = useState(200);
  const [dataInterval, setDataInterval] = useState(5000);
  const [server, setServer] = useState("25.78.72.7");
  const [tempUnits, setTempUnits] = useState("Celsius");
  const [pressUnits, setPressUnits] = useState("hPa");

  return (
    <>
      <GlobalStyle />
      <HashRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/matrix" element={<Matrix server={server} />} />
          <Route
            path="/charts"
            element={<Charts chartInterval={chartInterval} server={server} />}
          />
          <Route
            path="/settings"
            element={
              <Settings
                chartInterval={chartInterval}
                setChartInterval={setChartInterval}
                dataInterval={dataInterval}
                setDataInterval={setDataInterval}
                server={server}
                setServer={setServer}
                tempUnits={tempUnits}
                setTempUnits={setTempUnits}
                pressUnits={pressUnits}
                setPressUnits={setPressUnits}
              />
            }
          />
          <Route
            path="/data"
            element={
              <Data
                dataInterval={dataInterval}
                tempUnits={tempUnits}
                pressUnits={pressUnits}
                server={server}
              />
            }
          />
          <Route
            path="/temperature"
            element={
              <Temperature chartInterval={chartInterval} server={server} />
            }
          />
          <Route
            path="/humidity"
            element={<Humidity chartInterval={chartInterval} server={server} />}
          />
          <Route
            path="/pressure"
            element={<Pressure chartInterval={chartInterval} server={server} />}
          />
          <Route
            path="/joystick"
            element={
              <Accelerometer chartInterval={chartInterval} server={server} />
            }
          />
        </Routes>
      </HashRouter>
    </>
  );
};

export { Router };
