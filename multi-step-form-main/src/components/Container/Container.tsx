import React from "react";
import SideBar from "../SideBar/SideBar";
import "./Container.css";

const Container = () => {
  return (
    <div className='container'>
      <SideBar></SideBar>
      <div className='content'></div>
    </div>
  );
};

export default Container;
