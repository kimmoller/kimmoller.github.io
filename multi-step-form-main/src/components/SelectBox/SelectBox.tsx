import React from "react";
import "./SelectBox.css";

type Props = {
  mainText: string;
  secondaryText: string;
  price: string;
  selected: boolean;
  onSelect: (name: string, price: string) => void;
  onDeselect: (name: string) => void;
};

const SelectBox = (props: Props) => {
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.currentTarget.checked) {
      props.onSelect(props.mainText, props.price);
    } else {
      props.onDeselect(props.mainText);
    }
  };
  return (
    <div className='selectBox'>
      <input className='checkbox' type='checkbox' onChange={(e) => onChange(e)} checked={props.selected}></input>
      <div className='description'>
        <span className='mainText'>{props.mainText}</span>
        <span className='secondaryText'>{props.secondaryText}</span>
      </div>
      <span className='price'>{props.price}</span>
    </div>
  );
};

export default SelectBox;
