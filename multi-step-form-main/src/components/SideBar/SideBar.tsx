import React, { useContext } from "react";
import { StepContext, StepContextType } from "../../App";
import SideBarItem from "../SideBarItem/SideBarItem";
import "./SideBar.css";

const SideBar = () => {
  const { currentStep, setCurrentStep } = useContext(StepContext) as StepContextType;
  return (
    <div className='sidebar'>
      <SideBarItem number='1' step='STEP 1' topic='YOUR INFO' isCurrentStep={currentStep === 1}></SideBarItem>
      <SideBarItem number='2' step='STEP 2' topic='SELECT PLAN' isCurrentStep={currentStep === 2}></SideBarItem>
      <SideBarItem number='3' step='STEP 3' topic='ADD-ONS' isCurrentStep={currentStep === 3}></SideBarItem>
      <SideBarItem number='4' step='STEP 4' topic='SUMMARY' isCurrentStep={currentStep === 4}></SideBarItem>
    </div>
  );
};

export default SideBar;
