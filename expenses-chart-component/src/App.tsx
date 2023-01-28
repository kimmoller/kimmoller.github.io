import React from "react";
import "./App.css";
import Content from "./components/Content/Content";
import Header from "./components/Header/Header";

function App() {
  return (
    <div className='App'>
      <Header total='200'></Header>
      <Content></Content>
    </div>
  );
}

export default App;
