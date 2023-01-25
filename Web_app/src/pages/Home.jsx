import React from "react";
import { SideNav } from "../components/SideNav";
import styled from "styled-components";

const Home = () => {
  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <StyledTexts>
          <h1>SenseHat Web App</h1>
          <span>
            This app is an user interface for controlling and supervising a
            SenseHat sensor module for RapsberryPi microcontroller.
          </span>
          <span>
            Matrix tab allows the user to set specified colors to each of the
            LEDs.
          </span>
          <span>
            Charts tab allows to see the sensors readings in real time. Its
            subtabs allow to view one chart at the time.
          </span>
          <span>
            Data tab allows to view a log of all of the sensors readings in a
            digestible format.
          </span>
          <span>Settings tab allows to adjust basic app options.</span>
          <span>
            Designed by Patryk Borowski, Szymon Smodlibowski and Jakub Grzesiak.
          </span>
        </StyledTexts>
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledTexts = styled.div`
  margin-top: 20vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  gap: 1rem;
  width: 40vw;
  font-size: 1vw;
`;

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const StyledMain = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  margin-inline: 1rem;
  @media only screen and (max-width: 1528px) {
    margin-left: 10vw;
  }
`;

export { Home };
