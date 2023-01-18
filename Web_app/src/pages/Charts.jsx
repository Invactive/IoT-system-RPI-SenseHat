/*eslint-disable*/
import React, { useEffect } from "react";
import { SideNav } from "../components/SideNav";
import { fetchTemp } from "../api/getTemperature";
import { fetchPressure } from "../api/getPressure";
import { fetchHumidity } from "../api/getHumidity";
import { fetchAccelerometer } from "../api/getAcceloremeter";
import { useState } from "react";
import styled from "styled-components";
import {
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
} from "recharts";

const Charts = () => {
  //TEMPRATURE
  const [tempArrC, setTempArrC] = useState([]);
  //TEMPRATURE FROM HUMIDITY SENSOR
  const [tempArrCH, setTempArrCH] = useState([]);
  //TEMPRATURE FROM PRESSURE SENSOR
  const [tempArrCP, setTempArrCP] = useState([]);
  //PRESSURE IN hPa
  const [tempArrPH, setTempArrPH] = useState([]);
  //PRESSURE IN MNGH
  const [tempArrPM, setTempArrPM] = useState([]);
  //HUMIDITY
  const [tempArrH, setTempArrH] = useState([]);
  //ROLL
  const [tempArrR, setTempArrR] = useState([]);
  //PITCH
  const [tempArrP, setTempArrP] = useState([]);
  //YAW
  const [tempArrY, setTempArrY] = useState([]);

  //data for temperature plotting
  const dataTemp = [
    { name: "1", uv: tempArrC[0], pv: tempArrCH[0], cv: tempArrCP[0] },
    { name: "2", uv: tempArrC[1], pv: tempArrCH[1], cv: tempArrCP[1] },
    { name: "3", uv: tempArrC[2], pv: tempArrCH[2], cv: tempArrCP[2] },
    { name: "4", uv: tempArrC[3], pv: tempArrCH[3], cv: tempArrCP[3] },
    { name: "5", uv: tempArrC[4], pv: tempArrCH[4], cv: tempArrCP[4] },
    { name: "6", uv: tempArrC[5], pv: tempArrCH[5], cv: tempArrCP[5] },
    { name: "7", uv: tempArrC[6], pv: tempArrCH[6], cv: tempArrCP[6] },
    { name: "8", uv: tempArrC[7], pv: tempArrCH[7], cv: tempArrCP[7] },
    { name: "9", uv: tempArrC[8], pv: tempArrCH[8], cv: tempArrCP[8] },
    { name: "10", uv: tempArrC[9], pv: tempArrCH[9], cv: tempArrCP[9] },
  ];

  //data for pressure plotting
  const dataPress = [
    { name: "1", uv: tempArrPH[0], pv: tempArrPM[0] },
    { name: "2", uv: tempArrPH[1], pv: tempArrPM[1] },
    { name: "3", uv: tempArrPH[2], pv: tempArrPM[2] },
    { name: "4", uv: tempArrPH[3], pv: tempArrPM[3] },
    { name: "5", uv: tempArrPH[4], pv: tempArrPM[4] },
    { name: "6", uv: tempArrPH[5], pv: tempArrPM[5] },
    { name: "7", uv: tempArrPH[6], pv: tempArrPM[6] },
    { name: "8", uv: tempArrPH[7], pv: tempArrPM[7] },
    { name: "9", uv: tempArrPH[8], pv: tempArrPM[8] },
    { name: "10", uv: tempArrPH[9], pv: tempArrPM[9] },
  ];

  //data for humidity plotting
  const dataHum = [
    { name: "1", uv: tempArrH[0] },
    { name: "2", uv: tempArrH[1] },
    { name: "3", uv: tempArrH[2] },
    { name: "4", uv: tempArrH[3] },
    { name: "5", uv: tempArrH[4] },
    { name: "6", uv: tempArrH[5] },
    { name: "7", uv: tempArrH[6] },
    { name: "8", uv: tempArrH[7] },
    { name: "9", uv: tempArrH[8] },
    { name: "10", uv: tempArrH[9] },
  ];

  const dataRPY = [
    { name: "1", uv: tempArrR[0], pv: tempArrP[0], cv: tempArrY[0] },
    { name: "2", uv: tempArrR[1], pv: tempArrP[1], cv: tempArrY[1] },
    { name: "3", uv: tempArrR[2], pv: tempArrP[2], cv: tempArrY[2] },
    { name: "4", uv: tempArrR[3], pv: tempArrP[3], cv: tempArrY[3] },
    { name: "5", uv: tempArrR[4], pv: tempArrP[4], cv: tempArrY[4] },
    { name: "6", uv: tempArrR[5], pv: tempArrP[5], cv: tempArrY[5] },
    { name: "7", uv: tempArrR[6], pv: tempArrP[6], cv: tempArrY[6] },
    { name: "8", uv: tempArrR[7], pv: tempArrP[7], cv: tempArrY[7] },
    { name: "9", uv: tempArrR[8], pv: tempArrP[8], cv: tempArrY[8] },
    { name: "10", uv: tempArrR[9], pv: tempArrP[9], cv: tempArrY[9] },
  ];

  useEffect(() => {
    const interval = setInterval( async () => {
      const tempObj = await fetchTemp();
      const PressObj = await fetchPressure();
      const HumObj = await fetchHumidity();
      const RPYObj = await fetchAccelerometer();
      const currTempC = await tempObj.temp.tempC;
      const currTempCH = await tempObj.temp_humi.tempC;
      const currTempCP = await tempObj.temp_press.tempC;
      const currPressPH = await PressObj.press_hpa;
      const currPressPM = await PressObj.press_mmhg;
      const currHum = await HumObj.humidity;
      const currRoll = await RPYObj.roll;
      const currPitch = await RPYObj.pitch;
      const currYaw = await RPYObj.yaw;
      setTempArrC((prev) => {
        if (prev.length < 10) {
          return [...prev, currTempC] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currTempC) ;
        } else {
          return [] ;
        }
      });
      setTempArrCH((prev) => {
        if (prev.length < 10) {
          return [...prev, currTempCH] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currTempCH) ;
        } else {
          return [] ;
        }
      });
      setTempArrCP((prev) => {
        if (prev.length < 10) {
          return [...prev, currTempCP] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currTempCP) ;
        } else {
          return [] ;
        }
      });
      setTempArrPH((prev) => {
        if (prev.length < 10) {
          return [...prev, currPressPH] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currPressPH) ;
        } else {
          return [];
        }
      });
      setTempArrPM((prev) => {
        if (prev.length < 10) {
          return [...prev, currPressPM] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currPressPM);
        } else {
          return [];
        }
      });
      setTempArrH((prev) => {
        if (prev.length < 10) {
          return [...prev, currHum] ;
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currHum);
        } else {
          return [];
        }
      });
      setTempArrR((prev) => {
        if (prev.length < 10) {
          return [...prev, currRoll];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currRoll);
        } else {
          return [];
        }
      });
      setTempArrP((prev) => {
        if (prev.length < 10) {
          return [...prev, currPitch];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currPitch);
        } else {
          return [];
        }
      });
      setTempArrY((prev) => {
        if (prev.length < 10) {
          return [...prev, currYaw];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currYaw);
        } else {
          return [];
        }
      });
    }, 200);
    return () => clearInterval(interval);
  }, []);

  return (
    <StyledWrapper>
      <SideNav />
      <button
        onClick={() => {
          console.log(tempArrY);
        }}
      >
        log
      </button>
      <StyledCharts>
        <LineChart width={375} height={187.5} data={dataTemp}>
          <Tooltip />
          <Legend />
          <XAxis dataKey="name" />
          <YAxis domain={[-40, 110]} tickCount={20} />
          <Line
            type="monotone"
            dataKey="uv"
            stroke="#8884d8"
            activeDot={{ r: 8 }}
          />
          <Line type="monotone" dataKey="pv" stroke="#d67900" />
          <Line type="monotone" dataKey="cv" stroke="#6000fc" />
          <CartesianGrid stroke="#ccc" />
        </LineChart>
        <LineChart width={375} height={187.5} data={dataPress}>
          <Tooltip />
          <Legend />
          <XAxis dataKey="name" />
          <YAxis />
          <Line type="monotone" dataKey="uv" stroke="#8884d8" />
          <Line type="monotone" dataKey="pv" stroke="#d67900" />
          <CartesianGrid stroke="#ccc" />
        </LineChart>
        <LineChart width={375} height={187.5} data={dataHum}>
          <Tooltip />
          <Legend />
          <XAxis dataKey="name" />
          <YAxis domain={[-10, 110]} tickCount={20} />
          <Line
            type="monotone"
            dataKey="uv"
            stroke="#8884d8"
            activeDot={{ r: 8 }}
          />
          <CartesianGrid stroke="#ccc" />
        </LineChart>
        <LineChart width={375} height={187.5} data={dataRPY}>
          <Tooltip />
          <Legend />
          <XAxis dataKey="name" />
          <YAxis domain={[-10, 360]} tickCount={10} />
          <Line type="monotone" dataKey="uv" stroke="#8884d8" />
          <Line type="monotone" dataKey="pv" stroke="#d67900" />
          <Line type="monotone" dataKey="cv" stroke="#6000fc" />
          <CartesianGrid stroke="#ccc" />
        </LineChart>
      </StyledCharts>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
`;

const StyledCharts = styled.div`
  margin: auto;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(2, 1fr);
  grid-column-gap: 0.5rem;
  grid-row-gap: 0.5rem;
`;

export { Charts };
