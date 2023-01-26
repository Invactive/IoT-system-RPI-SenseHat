import React, { useState, useEffect } from "react";
import { SideNav } from "../components/SideNav";
import styled from "styled-components";
import { MatrixButton } from "../components/MatrixButton";
import { fetchLeds } from "../api/getLeds";
import { ChromePicker } from "react-color";
import { resetLED } from "../api/resetLeds";

const Matrix = ({ server }) => {
  const [maciora, setMaciora] = useState([]);
  const [isFetched, setIsFetched] = useState(false);
  const [blockPickerColor, setBlockPickerColor] = useState({
    r: 255,
    g: 0,
    b: 0
  });

  const matrixArr = [
    [0, 0, maciora[0]],
    [0, 1, maciora[1]],
    [0, 2, maciora[2]],
    [0, 3, maciora[3]],
    [0, 4, maciora[4]],
    [0, 5, maciora[5]],
    [0, 6, maciora[6]],
    [0, 7, maciora[7]],
    [1, 0, maciora[8]],
    [1, 1, maciora[9]],
    [1, 2, maciora[10]],
    [1, 3, maciora[11]],
    [1, 4, maciora[12]],
    [1, 5, maciora[13]],
    [1, 6, maciora[14]],
    [1, 7, maciora[15]],
    [2, 0, maciora[16]],
    [2, 1, maciora[17]],
    [2, 2, maciora[18]],
    [2, 3, maciora[19]],
    [2, 4, maciora[20]],
    [2, 5, maciora[21]],
    [2, 6, maciora[22]],
    [2, 7, maciora[23]],
    [3, 0, maciora[24]],
    [3, 1, maciora[25]],
    [3, 2, maciora[26]],
    [3, 3, maciora[27]],
    [3, 4, maciora[28]],
    [3, 5, maciora[29]],
    [3, 6, maciora[30]],
    [3, 7, maciora[31]],
    [4, 0, maciora[32]],
    [4, 1, maciora[33]],
    [4, 2, maciora[34]],
    [4, 3, maciora[35]],
    [4, 4, maciora[36]],
    [4, 5, maciora[37]],
    [4, 6, maciora[38]],
    [4, 7, maciora[39]],
    [5, 0, maciora[40]],
    [5, 1, maciora[41]],
    [5, 2, maciora[42]],
    [5, 3, maciora[43]],
    [5, 4, maciora[44]],
    [5, 5, maciora[45]],
    [5, 6, maciora[46]],
    [5, 7, maciora[47]],
    [6, 0, maciora[48]],
    [6, 1, maciora[49]],
    [6, 2, maciora[50]],
    [6, 3, maciora[51]],
    [6, 4, maciora[52]],
    [6, 5, maciora[53]],
    [6, 6, maciora[54]],
    [6, 7, maciora[55]],
    [7, 0, maciora[56]],
    [7, 1, maciora[57]],
    [7, 2, maciora[58]],
    [7, 3, maciora[59]],
    [7, 4, maciora[60]],
    [7, 5, maciora[61]],
    [7, 6, maciora[62]],
    [7, 7, maciora[63]]
  ];

  const fetching = async () => {
    const macier = await fetchLeds(server);
    setMaciora(await macier);
    setIsFetched(true);
  };

  function refreshPage() {
    window.location.reload(false);
  }

  useEffect(() => {
    fetching();
    return () => fetching();
  }, []);

  return (
    <StyledWrapper>
      <SideNav />
      <StyledMain>
        <StyledMatrix onClick={() => {}}>
          {isFetched
            ? matrixArr.map(el => {
                return <MatrixButton el={el} color={blockPickerColor} />;
              })
            : "loading..."}
        </StyledMatrix>
        <StyledContainer>
          <h1>Pick your Color</h1>
          <StyledPicker
            color={blockPickerColor}
            onChange={color => {
              setBlockPickerColor(color.rgb);
            }}
          />
          <StyledReset
            onClick={async () => {
              await resetLED();
              setTimeout(refreshPage, 500);
            }}
          >
            Reset LEDs
          </StyledReset>
        </StyledContainer>
      </StyledMain>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const StyledMain = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 5vw;
  width: 100vw;
  margin: 1vw;
  @media only screen and (max-width: 1528px) {
    flex-direction: column;
  }
`;

const StyledMatrix = styled.div`
  margin: auto;
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  grid-template-rows: repeat(8, 1fr);
  grid-column-gap: 1vw;
  grid-row-gap: 1vw;
  margin: 1vw;
`;

const StyledContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin-bottom: 2vw;
`;

const StyledPicker = styled(ChromePicker)`
  @media only screen and (max-width: 1528px) {
    scale: 0.8;
  }
`;

const StyledReset = styled.button`
  width: 14rem;
  background-color: white;
  border: 1px solid grey;
  box-shadow: 0.5vw 0.25vw 2vw;
  @media only screen and (max-width: 1528px) {
    width: 10rem;
  }
`;

export { Matrix };
