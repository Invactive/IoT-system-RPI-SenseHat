import React from "react";
import styled from "styled-components";
import { putLED } from "../api/setLeds";

const MatrixButton = ({ el, color }) => {
  function refreshPage() {
    window.location.reload(false);
  }

  return (
    <StyledButton
      onClick={() => {
        console.log(color);
        console.log(el);
        if (el[2][0] === 0 && el[2][1] === 0 && el[2][2] === 0) {
          putLED(el[1], el[0], color.r, color.g, color.b);
          console.log("turning led on");
        } else {
          putLED(el[1], el[0], 0, 0, 0);
          console.log("turning led off");
        }
        setTimeout(refreshPage, 500);
      }}
      r={el[2][0]}
      g={el[2][1]}
      b={el[2][2]}
    ></StyledButton>
  );
};

const StyledButton = styled.button`
  height: 4.5vw;
  width: 4.55vw;
  background-color: rgba(
    ${props => props.r},
    ${props => props.g},
    ${props => props.b},
    50%
  );
`;

export { MatrixButton };
