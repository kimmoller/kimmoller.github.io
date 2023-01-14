import React from "react";
import Form from "../Form/Form";
import SideBar from "../SideBar/SideBar";
import "./Container.css";

const Container = () => {
  return (
    <div className='container'>
      <SideBar></SideBar>
      <Form></Form>
    </div>
  );
};

export default Container;
