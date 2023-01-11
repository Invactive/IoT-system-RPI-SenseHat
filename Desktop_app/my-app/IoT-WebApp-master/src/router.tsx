import { BrowserRouter, Routes, Route } from 'react-router-dom'
import React from 'react'
import { Dashboard } from './pages/Dashboard'

const Router: React.FC = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Dashboard />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export { Router }
