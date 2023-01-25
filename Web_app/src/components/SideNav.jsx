import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { useState } from "react";
import homeIcon from "../assets/images/home.svg";
import matrixIcon from "../assets/images/matrix.svg";
import chartIcon from "../assets/images/chart.svg";
import settingsIcon from "../assets/images/settings.svg";
import thermometerIcon from "../assets/images/thermometer.svg";
import humidityIcon from "../assets/images/humidity.svg";
import pressureIcon from "../assets/images/pressure.svg";
import dataIcon from "../assets/images/data.svg";
import accelerometerIcon from "../assets/images/accelerometer.svg";
import arrowIcon from "../assets/images/arrow.svg";

const SideNav = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <StyledNavBar>
      <StyledHeader>SenseHat.</StyledHeader>
      <StyledList>
        <NavBarLink to="/">
          <StyledIcon src={homeIcon} alt="home icons" />
          <span>Home</span>
        </NavBarLink>
        <NavBarLink to="/matrix">
          <StyledIcon src={matrixIcon} alt="matrix icons" />
          <span>Matrix</span>
        </NavBarLink>
        <StyledItem>
          <NavBarLink to="/charts">
            <StyledIcon src={chartIcon} alt="charts icons" />
            Charts
          </NavBarLink>
          <StyledArrow
            isOpen={isOpen}
            src={arrowIcon}
            alt="arrow icon"
            onClick={() => {
              setIsOpen(!isOpen);
            }}
          />
        </StyledItem>

        <CollapsableMenu isOpen={isOpen} />
        <NavBarLink to="/data">
          <StyledIcon src={dataIcon} alt="data icons" />
          <span>Data</span>
        </NavBarLink>
        <NavBarLink to="/settings">
          <StyledIcon src={settingsIcon} alt="settings icons" />
          <span>Settings</span>
        </NavBarLink>
      </StyledList>
    </StyledNavBar>
  );
};

const CollapsableMenu = ({ isOpen }) => {
  return (
    <StyledMenu isOpen={isOpen}>
      <NavBarLink to="/temperature">
        <StyledIcon src={thermometerIcon} alt="temp icons" />
        Temperature
      </NavBarLink>
      <NavBarLink to="/humidity">
        <StyledIcon src={humidityIcon} alt="humidity icons" />
        Humidity
      </NavBarLink>
      <NavBarLink to="/pressure">
        <StyledIcon src={pressureIcon} alt="pressure icons" />
        Pressure
      </NavBarLink>
      <NavBarLink to="/joystick">
        <StyledIcon src={accelerometerIcon} alt="pressure icons" />
        Accelerometer
      </NavBarLink>
    </StyledMenu>
  );
};

const NavBarLink = styled(Link)`
  display: flex;
  align-items: center;
  gap: 0.25rem;
  color: white;
  text-decoration: none;
  margin: 0.75rem 0;
  width: fit-content;
  @media only screen and (max-width: 1528px) {
    width: 4rem;
    color: #1e3549;
  }
`;

const StyledHeader = styled.h1`
  margin-left: 1rem;
  @media only screen and (max-width: 1528px) {
    visibility: hidden;
  }
`;

const StyledNavBar = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #1e3549;
  width: 300px;
  max-width: 300px;
  min-width: 300px;
  color: white;
  font-size: 1.25vw;
  @media only screen and (max-width: 1528px) {
    width: 10rem;
    max-width: 10rem;
    min-width: 10rem;
    position: fixed;
    top: 0;
  }
`;

const StyledList = styled.div`
  display: flex;
  flex-direction: column;
  white-space: nowrap;
  margin-left: 1.5rem;
  margin-top: 0.5rem;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const StyledMenu = styled.div`
  display: flex;
  flex-direction: column;
  margin: 1rem;
  font-size: 1vw;
  display: ${props => (props.isOpen ? "block" : "none")};
`;

const StyledIcon = styled.img`
  width: 2rem;
  @media only screen and (max-width: 1528px) {
    width: 4rem;
  }
`;

const StyledArrow = styled(StyledIcon)`
  //transition: 0.3s;
  width: 1.5rem;
  margin-left: 0.4rem;
  rotate: ${props => (props.isOpen ? "-90deg" : "-180deg")};
  @media only screen and (max-width: 1528px) {
    width: 3rem;
  }
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: row;
  width: fit-content;
`;

export { SideNav };
