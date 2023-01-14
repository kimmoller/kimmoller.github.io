import React from "react";
import SideBarItem from "../SideBarItem/SideBarItem";
import "./SideBar.css";

const SideBar = () => {
  return (
    <div className='sidebar'>
      <SideBarItem number='1' step='STEP 1' topic='YOUR INFO'></SideBarItem>
      <SideBarItem number='2' step='STEP 2' topic='SELECT PLAN'></SideBarItem>
      <SideBarItem number='3' step='STEP 3' topic='ADD-ONS'></SideBarItem>
      <SideBarItem number='4' step='STEP 4' topic='SUMMARY'></SideBarItem>
    </div>
  );
};

export default SideBar;
