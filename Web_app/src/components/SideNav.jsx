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
import arrowIcon from "../assets/images/arrow.svg";
import { fetchData } from "../api/getData";

const SideNav = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <StyledNavBar>
      <StyledHeader>SenseHat</StyledHeader>
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
            <span
              onClick={() => {
                fetchData();
              }}
            >
              Charts
            </span>
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
    </StyledMenu>
  );
};

const NavBarLink = styled(Link)`
  display: flex;
  align-items: center;
  gap: 0.25rem;
  color: white;
  text-decoration: none;
  margin: 0.5rem 0;
`;

const StyledHeader = styled.h1`
  margin-left: 1rem;
`;

const StyledNavBar = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #1e3549;
  width: 20vw;
  height: 100vh;
  box-sizing: border-box;
  width: 200px;
`;

const StyledList = styled.div`
  display: flex;
  flex-direction: column;
  white-space: nowrap;
  width: 100%;
  margin-left: 1rem;
  margin-top: 1rem;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const StyledMenu = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 2rem;
  display: ${props => (props.isOpen ? "block" : "none")};
`;

const StyledIcon = styled.img`
  width: 25px;
`;

const StyledArrow = styled(StyledIcon)`
  transition: 0.3s;
  rotate: ${props => (props.isOpen ? "0deg" : "-90deg")};
`;

const StyledItem = styled.div`
  display: flex;
  flex-direction: row;
`;

export { SideNav };
