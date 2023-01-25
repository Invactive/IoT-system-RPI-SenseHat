import React from "react";
import { SideNav } from "../components/SideNav";
import styled from "styled-components";
import { putInterval } from "../api/setInterval";

const Settings = ({
  chartInterval,
  setChartInterval,
  dataInterval,
  setDataInterval,
  server,
  setServer,
  tempUnits,
  setTempUnits,
  pressUnits,
  setPressUnits
}) => {
  const handleChartInterval = e => {
    setChartInterval(e.target.value);
  };

  const handleDataInterval = e => {
    setDataInterval(e.target.value);
  };

  const handleSubmit = e => {
    e.preventDefault();
    console.log(chartInterval);
    console.log(dataInterval);
    console.log(server);
    console.log(tempUnits);
    console.log(pressUnits);
    putInterval(dataInterval);
  };

  const handleServer = e => {
    setServer(e.target.value);
  };

  const handleTempUnits = e => {
    setTempUnits(e.target.value);
  };

  const handlePressUnits = e => {
    setPressUnits(e.target.value);
  };

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <h1>User Settings</h1>
        <StyledForm
          onSubmit={e => {
            handleSubmit(e);
          }}
        >
          <StyledSettings>
            <StyledItem>
              <span>Temperature Units</span>
              <input
                type="text"
                list="tempUnits"
                onChange={handleTempUnits}
                placeholder={tempUnits}
              />
              <datalist id="tempUnits">
                <option>Celsius</option>
                <option>Farenheit</option>
              </datalist>
            </StyledItem>
            <StyledItem>
              <span>Humidity Units</span>
              <input
                type="text"
                list="pressUnits"
                onChange={handlePressUnits}
                placeholder={pressUnits}
              />
              <datalist id="pressUnits">
                <option>hPa</option>
                <option>MMHG</option>
              </datalist>
            </StyledItem>
            <StyledItem>
              <span>Charts Interval</span>
              <input
                onChange={handleChartInterval}
                placeholder={chartInterval}
              />
            </StyledItem>
            <StyledItem>
              <span>Logging Interval</span>
              <input onChange={handleDataInterval} placeholder={dataInterval} />
            </StyledItem>
            <StyledItem>
              <span>Server IP</span>
              <input onChange={handleServer} placeholder={server} />
            </StyledItem>
          </StyledSettings>
          <StyledSubmit type="submit" value="submit" />
        </StyledForm>
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
`;

const StyledMain = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100vw;
  height: 50vh;
  gap: 1rem;
  margin-inline: 1rem;
  @media only screen and (max-width: 1224px) {
    height: 100vh;
    margin-left: 10vw;
  }
`;

const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  gap: 1vh;
  align-items: center;
`;

const StyledSubmit = styled.input`
  padding: 1vw;
`;

const StyledSettings = styled.div`
  display: inline-flex;
  gap: 1vw;
  @media only screen and (max-width: 1224px) {
    display: flex;
    flex-direction: column;
  }
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  gap: 0.5vw;
`;

export { Settings };
