import React from "react";
import "./Balance.css";

type Props = {
  total: string;
};

const Balance = (props: Props) => {
  return (
    <div className='balance'>
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

export default Balance;
