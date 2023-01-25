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
  Legend
} from "recharts";

const Charts = ({ chartInterval, server }) => {
  //TEMPRATURE
  const [tempArrC, setTempArrC] = useState([]);
  //TEMPRATURE FROM HUMIDITY SENSOR
  const [tempArrCH, setTempArrCH] = useState([]);
  //TEMPRATURE FROM PRESSURE SENSOR
  const [tempArrCP, setTempArrCP] = useState([]);
  //PRESSURE IN hPa
  const [tempArrPH, setTempArrPH] = useState([]);
  //PRESSURE IN MMHG
  const [tempArrPM, setTempArrPM] = useState([]);
  //HUMIDITY
  const [tempArrH, setTempArrH] = useState([]);
  //ROLL
  const [tempArrR, setTempArrR] = useState([]);
  //PITCH
  const [tempArrP, setTempArrP] = useState([]);
  //YAW
  const [tempArrY, setTempArrY] = useState([]);
  //TIME
  const [timeArr, setTimeArr] = useState([]);

  //data for temperature plotting
  const dataTemp = [
    {
      name: timeArr[0],
      Standard: tempArrC[0],
      Humidity: tempArrCH[0],
      Pressure: tempArrCP[0]
    },
    {
      name: timeArr[1],
      Standard: tempArrC[1],
      Humidity: tempArrCH[1],
      Pressure: tempArrCP[1]
    },
    {
      name: timeArr[2],
      Standard: tempArrC[2],
      Humidity: tempArrCH[2],
      Pressure: tempArrCP[2]
    },
    {
      name: timeArr[3],
      Standard: tempArrC[3],
      Humidity: tempArrCH[3],
      Pressure: tempArrCP[3]
    },
    {
      name: timeArr[4],
      Standard: tempArrC[4],
      Humidity: tempArrCH[4],
      Pressure: tempArrCP[4]
    },
    {
      name: timeArr[5],
      Standard: tempArrC[5],
      Humidity: tempArrCH[5],
      Pressure: tempArrCP[5]
    },
    {
      name: timeArr[6],
      Standard: tempArrC[6],
      Humidity: tempArrCH[6],
      Pressure: tempArrCP[6]
    },
    {
      name: timeArr[7],
      Standard: tempArrC[7],
      Humidity: tempArrCH[7],
      Pressure: tempArrCP[7]
    },
    {
      name: timeArr[8],
      Standard: tempArrC[8],
      Humidity: tempArrCH[8],
      Pressure: tempArrCP[8]
    },
    {
      name: timeArr[9],
      Standard: tempArrC[9],
      Humidity: tempArrCH[9],
      Pressure: tempArrCP[9]
    }
  ];

  //data for pressure plotting
  const dataPress = [
    { name: timeArr[0], hPa: tempArrPH[0], MMHG: tempArrPM[0] },
    { name: timeArr[1], hPa: tempArrPH[1], MMHG: tempArrPM[1] },
    { name: timeArr[2], hPa: tempArrPH[2], MMHG: tempArrPM[2] },
    { name: timeArr[3], hPa: tempArrPH[3], MMHG: tempArrPM[3] },
    { name: timeArr[4], hPa: tempArrPH[4], MMHG: tempArrPM[4] },
    { name: timeArr[5], hPa: tempArrPH[5], MMHG: tempArrPM[5] },
    { name: timeArr[6], hPa: tempArrPH[6], MMHG: tempArrPM[6] },
    { name: timeArr[7], hPa: tempArrPH[7], MMHG: tempArrPM[7] },
    { name: timeArr[8], hPa: tempArrPH[8], MMHG: tempArrPM[8] },
    { name: timeArr[9], hPa: tempArrPH[9], MMHG: tempArrPM[9] }
  ];

  //data for humidity plotting
  const dataHum = [
    { name: timeArr[0], Humidity: tempArrH[0] },
    { name: timeArr[1], Humidity: tempArrH[1] },
    { name: timeArr[2], Humidity: tempArrH[2] },
    { name: timeArr[3], Humidity: tempArrH[3] },
    { name: timeArr[4], Humidity: tempArrH[4] },
    { name: timeArr[5], Humidity: tempArrH[5] },
    { name: timeArr[6], Humidity: tempArrH[6] },
    { name: timeArr[7], Humidity: tempArrH[7] },
    { name: timeArr[8], Humidity: tempArrH[8] },
    { name: timeArr[9], Humidity: tempArrH[9] }
  ];

  const dataRPY = [
    {
      name: timeArr[0],
      roll: tempArrR[0],
      pitch: tempArrP[0],
      yaw: tempArrY[0]
    },
    {
      name: timeArr[1],
      roll: tempArrR[1],
      pitch: tempArrP[1],
      yaw: tempArrY[1]
    },
    {
      name: timeArr[2],
      roll: tempArrR[2],
      pitch: tempArrP[2],
      yaw: tempArrY[2]
    },
    {
      name: timeArr[3],
      roll: tempArrR[3],
      pitch: tempArrP[3],
      yaw: tempArrY[3]
    },
    {
      name: timeArr[4],
      roll: tempArrR[4],
      pitch: tempArrP[4],
      yaw: tempArrY[4]
    },
    {
      name: timeArr[5],
      roll: tempArrR[5],
      pitch: tempArrP[5],
      yaw: tempArrY[5]
    },
    {
      name: timeArr[6],
      roll: tempArrR[6],
      pitch: tempArrP[6],
      yaw: tempArrY[6]
    },
    {
      name: timeArr[7],
      roll: tempArrR[7],
      pitch: tempArrP[7],
      yaw: tempArrY[7]
    },
    {
      name: timeArr[8],
      roll: tempArrR[8],
      pitch: tempArrP[8],
      yaw: tempArrY[8]
    },
    {
      name: timeArr[9],
      roll: tempArrR[9],
      pitch: tempArrP[9],
      yaw: tempArrY[9]
    }
  ];

  useEffect(() => {
    const interval = setInterval(async () => {
      const tempObj = await fetchTemp(server);
      const timeObj = await fetchTemp(server);
      const PressObj = await fetchPressure(server);
      const HumObj = await fetchHumidity(server);
      const RPYObj = await fetchAccelerometer(server);
      const currTempC = await tempObj.temp.tempC;
      const currTempCH = await tempObj.temp_humi.tempC;
      const currTempCP = await tempObj.temp_press.tempC;
      const currPressPH = await PressObj.press_hpa;
      const currPressPM = await PressObj.press_mmhg;
      const currHum = await HumObj.humidity;
      const currRoll = await RPYObj.roll;
      const currPitch = await RPYObj.pitch;
      const currYaw = await RPYObj.yaw;
      const currTime = timeObj.timestamp;
      setTempArrC(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currTempC)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currTempC));
        } else {
          return [];
        }
      });
      setTempArrCH(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currTempCH)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currTempCH));
        } else {
          return [];
        }
      });
      setTempArrCP(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currTempCP)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currTempCP));
        } else {
          return [];
        }
      });
      setTempArrPH(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currPressPH)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currPressPH));
        } else {
          return [];
        }
      });
      setTempArrPM(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currPressPM)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currPressPM));
        } else {
          return [];
        }
      });
      setTempArrH(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currHum)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currHum));
        } else {
          return [];
        }
      });
      setTempArrR(prev => {
        if (prev.length < 10) {
          return [...prev, currRoll];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currRoll);
        } else {
          return [];
        }
      });
      setTempArrP(prev => {
        if (prev.length < 10) {
          return [...prev, currPitch];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currPitch);
        } else {
          return [];
        }
      });
      setTempArrY(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currYaw)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currYaw));
        } else {
          return [];
        }
      });
      setTimeArr(prev => {
        if (prev.length < 10) {
          return [...prev, currTime.slice(11, 19)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(currTime.slice(11, 19));
        } else {
          return [];
        }
      });
    }, chartInterval);
    return () => clearInterval(interval);
  }, []);

  return (
    <StyledWrapper>
      <SideNav />
      <StyledCharts>
        <StyledItem>
          <h1>Temperature</h1>
          <StyledChart data={dataTemp} height={300} width={600}>
            <Tooltip />
            <Legend />
            <XAxis dataKey="name" />
            <YAxis domain={[-40, 110]} tickCount={20} />
            <Line
              type="monotone"
              dataKey="Standard"
              stroke="#751818"
              activeDot={{ r: 8 }}
            />
            <Line type="monotone" dataKey="Humidity" stroke="#251a88" />
            <Line type="monotone" dataKey="Pressure" stroke="#1e7017" />
            <CartesianGrid stroke="#ccc" />
          </StyledChart>
        </StyledItem>
        <StyledItem>
          <h1>Pressure</h1>
          <StyledChart data={dataPress} height={300} width={600}>
            <Tooltip />
            <Legend />
            <XAxis dataKey="name" />
            <YAxis />
            <Line type="monotone" dataKey="hPa" stroke="#751818" />
            <Line type="monotone" dataKey="MMHG" stroke="#251a88" />
            <CartesianGrid stroke="#ccc" />
          </StyledChart>
        </StyledItem>
        <StyledItem>
          <h1>Humidity</h1>
          <StyledChart data={dataHum} height={300} width={600}>
            <Tooltip />
            <Legend />
            <XAxis dataKey="name" />
            <YAxis domain={[-10, 110]} tickCount={20} />
            <Line
              type="monotone"
              dataKey="Humidity"
              stroke="#751818"
              activeDot={{ r: 8 }}
            />
            <CartesianGrid stroke="#ccc" />
          </StyledChart>
        </StyledItem>
        <StyledItem>
          <h1>Orientation</h1>
          <StyledChart data={dataRPY} height={300} width={600}>
            <Tooltip />
            <Legend />
            <XAxis dataKey="name" />
            <YAxis domain={[-10, 360]} tickCount={10} />
            <Line type="monotone" dataKey="roll" stroke="#751818" />
            <Line type="monotone" dataKey="pitch" stroke="#251a88" />
            <Line type="monotone" dataKey="yaw" stroke="#1e7017" />
            <CartesianGrid stroke="#ccc" />
          </StyledChart>
        </StyledItem>
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
  height: 100vh;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(2, 1fr);
  grid-column-gap: 1vh;
  grid-row-gap: 1vw;
  @media only screen and (max-width: 1524px) {
    grid-template-columns: repeat(1, 1fr);
    margin-left: 25vw;
  }
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const StyledChart = styled(LineChart)`
  @media only screen and (max-width: 1024px) {
    scale: 0.8;
  }
`;

export { Charts };
