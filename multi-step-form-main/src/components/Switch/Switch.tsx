import React, { useState } from "react";
import "./Switch.css";

type Props = {
  onChange: () => void;
};

const Switch = (props: Props) => {
  return (
    <div>
      <label className='switch'>
        <input type='checkbox' onChange={() => props.onChange()} />
        <span className='slider round'></span>
      </label>
    </div>
  );
};

export default Switch;
