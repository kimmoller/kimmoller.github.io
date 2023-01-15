import React from "react";
import "./SelectBox.css";

type Props = {
  mainText: string;
  secondaryText: string;
  price: string;
};

const SelectBox = (props: Props) => {
  return (
    <div className='selectBox'>
      <input className='checkbox' type='checkbox'></input>
      <div className='description'>
        <span className='mainText'>{props.mainText}</span>
        <span className='secondaryText'>{props.secondaryText}</span>
      </div>
      <span className='price'>{props.price}</span>
    </div>
  );
};

export default SelectBox;
