import React from "react";
import "./App.css";
import Form from "./components/Form/Form";
import SideBar from "./components/SideBar/SideBar";

const App = () => {
  return (
    <div className='app'>
      <SideBar></SideBar>
      <Form></Form>
    </div>
  );
};

export default App;
