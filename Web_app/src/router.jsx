import { BrowserRouter, Route, Routes } from "react-router-dom";
import React from "react";
import { Home } from "./pages/Home";
import { Matrix } from "./pages/Matrix";
import { Charts } from "./pages/Charts";
import { Settings } from "./pages/Settings";
import { Data } from "./pages/Data";
import GlobalStyle from "./globalStyles";

const Router = () => {
  return (
    <>
      <GlobalStyle />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/matrix" element={<Matrix />} />
          <Route path="/charts" element={<Charts />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/data" element={<Data />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export { Router };
