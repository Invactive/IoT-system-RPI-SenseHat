import React from "react";
import styled from "styled-components";

const DataTable = ({
  temp,
  press,
  hum,
  acc,
  joy,
  time,
  tempUnits,
  pressUnits
}) => {
  return (
    <StyledWrapper>
      <StyledTable>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius" ? "Temperature[°C]" : "Temperature[°F]"}
          </StyledHead>
          <StyledHead>
            {pressUnits === "hPa" ? "Pressure[hPa]" : "Pressure[MMHG]"}
          </StyledHead>
          <StyledHead>Humidity[%]</StyledHead>
          <StyledHead>Roll[°]</StyledHead>
          <StyledHead>Pitch[°]</StyledHead>
          <StyledHead>Yaw[°]</StyledHead>
          <StyledHead>Joystick X</StyledHead>
          <StyledHead>Joystick Y</StyledHead>
          <StyledHead>Joystick Mid</StyledHead>
          <StyledHead>Timestamp</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][0].temp.tempC
              : temp[0][0].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][0].press_hpa}</StyledHead>
          <StyledHead>{hum[0][0].humidity}</StyledHead>
          <StyledHead>{acc[0][0].roll}</StyledHead>
          <StyledHead>{acc[0][0].pitch}</StyledHead>
          <StyledHead>{acc[0][0].yaw}</StyledHead>
          <StyledHead>{joy[0][0].X}</StyledHead>
          <StyledHead>{joy[0][0].Y}</StyledHead>
          <StyledHead>{joy[0][0].Mid}</StyledHead>
          <StyledHead>{time[0][0]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][1].temp.tempC
              : temp[0][1].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][1].press_hpa}</StyledHead>
          <StyledHead>{hum[0][1].humidity}</StyledHead>
          <StyledHead>{acc[0][1].roll}</StyledHead>
          <StyledHead>{acc[0][1].pitch}</StyledHead>
          <StyledHead>{acc[0][1].yaw}</StyledHead>
          <StyledHead>{joy[0][1].X}</StyledHead>
          <StyledHead>{joy[0][1].Y}</StyledHead>
          <StyledHead>{joy[0][1].Mid}</StyledHead>
          <StyledHead>{time[0][1]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][2].temp.tempC
              : temp[0][2].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][2].press_hpa}</StyledHead>
          <StyledHead>{hum[0][2].humidity}</StyledHead>
          <StyledHead>{acc[0][2].roll}</StyledHead>
          <StyledHead>{acc[0][2].pitch}</StyledHead>
          <StyledHead>{acc[0][2].yaw}</StyledHead>
          <StyledHead>{joy[0][2].X}</StyledHead>
          <StyledHead>{joy[0][2].Y}</StyledHead>
          <StyledHead>{joy[0][2].Mid}</StyledHead>
          <StyledHead>{time[0][2]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][3].temp.tempC
              : temp[0][3].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][3].press_hpa}</StyledHead>
          <StyledHead>{hum[0][3].humidity}</StyledHead>
          <StyledHead>{acc[0][3].roll}</StyledHead>
          <StyledHead>{acc[0][3].pitch}</StyledHead>
          <StyledHead>{acc[0][3].yaw}</StyledHead>
          <StyledHead>{joy[0][3].X}</StyledHead>
          <StyledHead>{joy[0][3].Y}</StyledHead>
          <StyledHead>{joy[0][3].Mid}</StyledHead>
          <StyledHead>{time[0][3]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][4].temp.tempC
              : temp[0][4].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][4].press_hpa}</StyledHead>
          <StyledHead>{hum[0][4].humidity}</StyledHead>
          <StyledHead>{acc[0][4].roll}</StyledHead>
          <StyledHead>{acc[0][4].pitch}</StyledHead>
          <StyledHead>{acc[0][4].yaw}</StyledHead>
          <StyledHead>{joy[0][4].X}</StyledHead>
          <StyledHead>{joy[0][4].Y}</StyledHead>
          <StyledHead>{joy[0][4].Mid}</StyledHead>
          <StyledHead>{time[0][4]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][5].temp.tempC
              : temp[0][5].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][5].press_hpa}</StyledHead>
          <StyledHead>{hum[0][5].humidity}</StyledHead>
          <StyledHead>{acc[0][5].roll}</StyledHead>
          <StyledHead>{acc[0][5].pitch}</StyledHead>
          <StyledHead>{acc[0][5].yaw}</StyledHead>
          <StyledHead>{joy[0][5].X}</StyledHead>
          <StyledHead>{joy[0][5].Y}</StyledHead>
          <StyledHead>{joy[0][5].Mid}</StyledHead>
          <StyledHead>{time[0][5]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][6].temp.tempC
              : temp[0][6].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][6].press_hpa}</StyledHead>
          <StyledHead>{hum[0][6].humidity}</StyledHead>
          <StyledHead>{acc[0][6].roll}</StyledHead>
          <StyledHead>{acc[0][6].pitch}</StyledHead>
          <StyledHead>{acc[0][6].yaw}</StyledHead>
          <StyledHead>{joy[0][6].X}</StyledHead>
          <StyledHead>{joy[0][6].Y}</StyledHead>
          <StyledHead>{joy[0][6].Mid}</StyledHead>
          <StyledHead>{time[0][6]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][7].temp.tempC
              : temp[0][7].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][7].press_hpa}</StyledHead>
          <StyledHead>{hum[0][7].humidity}</StyledHead>
          <StyledHead>{acc[0][7].roll}</StyledHead>
          <StyledHead>{acc[0][7].pitch}</StyledHead>
          <StyledHead>{acc[0][7].yaw}</StyledHead>
          <StyledHead>{joy[0][7].X}</StyledHead>
          <StyledHead>{joy[0][7].Y}</StyledHead>
          <StyledHead>{joy[0][7].Mid}</StyledHead>
          <StyledHead>{time[0][7]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][8].temp.tempC
              : temp[0][8].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][8].press_hpa}</StyledHead>
          <StyledHead>{hum[0][8].humidity}</StyledHead>
          <StyledHead>{acc[0][8].roll}</StyledHead>
          <StyledHead>{acc[0][8].pitch}</StyledHead>
          <StyledHead>{acc[0][8].yaw}</StyledHead>
          <StyledHead>{joy[0][8].X}</StyledHead>
          <StyledHead>{joy[0][8].Y}</StyledHead>
          <StyledHead>{joy[0][8].Mid}</StyledHead>
          <StyledHead>{time[0][8]}</StyledHead>
        </StyledRow>
        <StyledRow>
          <StyledHead>
            {tempUnits === "Celsius"
              ? temp[0][9].temp.tempC
              : temp[0][9].temp.tempF}
          </StyledHead>
          <StyledHead>{press[0][9].press_hpa}</StyledHead>
          <StyledHead>{hum[0][9].humidity}</StyledHead>
          <StyledHead>{acc[0][9].roll}</StyledHead>
          <StyledHead>{acc[0][9].pitch}</StyledHead>
          <StyledHead>{acc[0][9].yaw}</StyledHead>
          <StyledHead>{joy[0][9].X}</StyledHead>
          <StyledHead>{joy[0][9].Y}</StyledHead>
          <StyledHead>{joy[0][9].Mid}</StyledHead>
          <StyledHead>{time[0][9]}</StyledHead>
        </StyledRow>
      </StyledTable>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1vw;
  margin: 1vw;
`;

const StyledTable = styled.table`
  border: 1px solid;
`;

const StyledHead = styled.th`
  border: 1px solid;
  height: 2vw;
  width: 6vw;
  padding: 0.5vw 0.5vw;
`;

const StyledRow = styled.tr`
  border: 1px solid;
  width: 1vw;
`;

export { DataTable };
