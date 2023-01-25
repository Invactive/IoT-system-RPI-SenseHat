import React from "react";
import { useState, useEffect } from "react";
import { fetchHumidity } from "../api/getHumidity";
import styled from "styled-components";
import { SideNav } from "../components/SideNav";
import {
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend
} from "recharts";

const Humidity = ({ chartInterval, server }) => {
  //HUMIDITY
  const [humArr, setHumArr] = useState([]);
  //TIME
  const [timeArr, setTimeArr] = useState([]);

  const dataHum = [
    { name: timeArr[0], Humidity: humArr[0] },
    { name: timeArr[1], Humidity: humArr[1] },
    { name: timeArr[2], Humidity: humArr[2] },
    { name: timeArr[3], Humidity: humArr[3] },
    { name: timeArr[4], Humidity: humArr[4] },
    { name: timeArr[5], Humidity: humArr[5] },
    { name: timeArr[6], Humidity: humArr[6] },
    { name: timeArr[7], Humidity: humArr[7] },
    { name: timeArr[8], Humidity: humArr[8] },
    { name: timeArr[9], Humidity: humArr[9] }
  ];

  useEffect(() => {
    const interval = setInterval(async () => {
      const HumObj = await fetchHumidity(server);
      const currHum = await HumObj.humidity;
      const currTime = HumObj.timestamp;
      setHumArr(prev => {
        if (prev.length < 10) {
          return [...prev, Math.floor(currHum)];
        } else if (prev.length === 10) {
          return prev.slice(1).concat(Math.floor(currHum));
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
  margin: auto;
`;

export { Humidity };
