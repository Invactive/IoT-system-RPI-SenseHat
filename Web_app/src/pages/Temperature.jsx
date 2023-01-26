import React from "react";
import { useState, useEffect } from "react";
import { fetchTemp } from "../api/getTemperature";
import styled from "styled-components";
import { SideNav } from "../components/SideNav";
import {
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer
} from "recharts";

const Temperature = ({ chartInterval, server }) => {
  //TEMPRATURE
  const [tempArr, setTempArr] = useState([]);
  //TIME
  const [timeArr, setTimeArr] = useState([]);

  const dataTemp = [
    {
      name: timeArr[0],
      Standard: tempArr[0],
      Humidity: tempArr[0],
      Pressure: tempArr[0]
    },
    {
      name: timeArr[1],
      Standard: tempArr[1],
      Humidity: tempArr[1],
      Pressure: tempArr[1]
    },
    {
      name: timeArr[2],
      Standard: tempArr[2],
      Humidity: tempArr[2],
      Pressure: tempArr[2]
    },
    {
      name: timeArr[3],
      Standard: tempArr[3],
      Humidity: tempArr[3],
      Pressure: tempArr[3]
    },
    {
      name: timeArr[4],
      Standard: tempArr[4],
      Humidity: tempArr[4],
      Pressure: tempArr[4]
    },
    {
      name: timeArr[5],
      Standard: tempArr[5],
      Humidity: tempArr[5],
      Pressure: tempArr[5]
    },
    {
      name: timeArr[6],
      Standard: tempArr[6],
      Humidity: tempArr[6],
      Pressure: tempArr[6]
    },
    {
      name: timeArr[7],
      Standard: tempArr[7],
      Humidity: tempArr[7],
      Pressure: tempArr[7]
    },
    {
      name: timeArr[8],
      Standard: tempArr[8],
      Humidity: tempArr[8],
      Pressure: tempArr[8]
    },
    {
      name: timeArr[9],
      Standard: tempArr[9],
      Humidity: tempArr[9],
      Pressure: tempArr[9]
    }
  ];

  useEffect(() => {
    const interval = setInterval(async () => {
      const tempObj = await fetchTemp(server);
      const timeObj = await fetchTemp(server);
      const currTempC = await tempObj.temp.tempC;
      const currTime = timeObj.timestamp;
      setTempArr(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currTempC)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currTempC));
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
  });

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <StyledItem>
          <h1>Temperature</h1>
          <ResponsiveContainer height={300} width={800}>
            <StyledChart data={dataTemp}>
              <Tooltip />
              <Legend />
              <XAxis dataKey="name" />
              <YAxis domain={[-40, 110]} tickCount={10} />
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
          </ResponsiveContainer>
        </StyledItem>
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
  height: 100vh;
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const StyledChart = styled(LineChart)``;

const StyledMain = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
`;

export { Temperature };
