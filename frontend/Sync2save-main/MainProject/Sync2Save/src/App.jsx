import { useState } from 'react'
import reactLogo from './assets/react.svg'
import logo from './assets/Logo.png'

import viteLogo from '/vite.svg'
import './App.css'
import ElectricLeaks from './components/ElectricLeaks'

function App() {
  

  return (
    <>
    <div className="banner">
    <h1>Sync2Save</h1>
    <img src={logo} alt="Company Logo" className="logo-banner" />
  </div>
  <div>
    <ElectricLeaks />
    </div>
    </>
  )
}

export default App
