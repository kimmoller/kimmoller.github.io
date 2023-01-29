import React from "react";
import SideBarItem from "../SideBarItem/SideBarItem";
import "./SideBar.css";

type Props = {
  currentStep: number;
};

const SideBar = (props: Props) => {
  return (
    <div className='sidebar'>
      <SideBarItem number='1' step='STEP 1' topic='YOUR INFO' isCurrentStep={props.currentStep === 1}></SideBarItem>
      <SideBarItem number='2' step='STEP 2' topic='SELECT PLAN' isCurrentStep={props.currentStep === 2}></SideBarItem>
      <SideBarItem number='3' step='STEP 3' topic='ADD-ONS' isCurrentStep={props.currentStep === 3}></SideBarItem>
      <SideBarItem number='4' step='STEP 4' topic='SUMMARY' isCurrentStep={props.currentStep === 4}></SideBarItem>
    </div>
  );
};

export default SideBar;
