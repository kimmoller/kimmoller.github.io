import React from "react";
import { getPricingLabel } from "../../Pricing";
import { PlanPricing } from "../Form/Form";
import "./SelectBox.css";

type Props = {
  mainText: string;
  secondaryText: string;
  price: number;
  pricing: PlanPricing;
  selected: boolean;
  onSelect: (name: string, price: number) => void;
  onDeselect: (name: string) => void;
};

const SelectBox = (props: Props) => {
  const onChange = () => {
    if (!props.selected) {
      props.onSelect(props.mainText, props.price);
    } else {
      props.onDeselect(props.mainText);
    }
  };

  return (
    <div className='selectBox' onClick={() => onChange()}>
      <input className='checkbox' type='checkbox' onChange={() => onChange()} checked={props.selected}></input>
      <div className='description'>
        <span className='mainText'>{props.mainText}</span>
        <span className='secondaryText'>{props.secondaryText}</span>
      </div>
      <span className='price'>{`$${props.price} / ${getPricingLabel(props.pricing)}`}</span>
    </div>
  );
};

export default SelectBox;
