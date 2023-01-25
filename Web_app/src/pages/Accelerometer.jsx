import React from "react";
import { useState, useEffect } from "react";
import { fetchAccelerometer } from "../api/getAcceloremeter";
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

const Accelerometer = ({ chartInterval, server }) => {
  //ROLL
  const [tempArrR, setTempArrR] = useState([]);
  //PITCH
  const [tempArrP, setTempArrP] = useState([]);
  //YAW
  const [tempArrY, setTempArrY] = useState([]);
  //TIME
  const [timeArr, setTimeArr] = useState([]);

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
      const RPYObj = await fetchAccelerometer(server);
      const currRoll = await RPYObj.roll;
      const currPitch = await RPYObj.pitch;
      const currYaw = await RPYObj.yaw;
      const currTime = RPYObj.timestamp;
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
  });

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <StyledItem>
          <h1>Orientation</h1>
          <StyledChart data={dataRPY} height={300} width={800}>
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
  box-sizing: border-box;
`;

export { Accelerometer };
