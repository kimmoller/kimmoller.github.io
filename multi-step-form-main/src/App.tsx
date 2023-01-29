import React, { useState } from "react";
import "./App.css";
import Form from "./components/Form/Form";
import SideBar from "./components/SideBar/SideBar";

const App = () => {
  const [currentStep, setCurrentStep] = useState(1);
  return (
    <div className='app'>
      <SideBar currentStep={currentStep}></SideBar>
      <Form currentStep={currentStep} setCurrentStep={(step) => setCurrentStep(step)}></Form>
    </div>
  );
};

export default App;
