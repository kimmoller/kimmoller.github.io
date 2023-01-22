import React from "react";
import "./Header.css";

type Props = {
  total: string;
};

const Header = (props: Props) => {
  return (
    <div className='header'>
      <div className='content'>
        <span className='title'>My Balance</span>
        <span className='total'>${props.total}</span>
      </div>
      <div className='logo'>
        <img src='/logo.svg'></img>
      </div>
    </div>
  );
};

export default Header;
