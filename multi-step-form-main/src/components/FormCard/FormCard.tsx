import React from "react";
import { getPricingLabel } from "../../Pricing";
import { PlanPricing } from "../Form/Form";
import "./FormCard.css";

type Props = {
  id: string;
  image: string;
  name: string;
  price: number;
  pricing: PlanPricing;
  isChecked: (name: string) => boolean;
  selectPlan: (name: string, price: number) => void;
};

const FormCard = (props: Props) => {
  const onKeyUp = (e: React.KeyboardEvent) => {
    if (e.code === "Enter" || e.code === "Space") {
      (document.getElementById(props.id) as HTMLInputElement).checked = true;
      props.selectPlan(props.name, props.price);
    }
  };
  return (
    <div className='formCard'>
      <input
        type='radio'
        id={props.id}
        value={props.name}
        name='planSelection'
        checked={props.isChecked(props.name)}
        onChange={() => props.selectPlan(props.name, props.price)}
      ></input>
      <label
        className='formCardLabel'
        onClick={() => props.selectPlan(props.name, props.price)}
        htmlFor={props.id}
        onKeyUp={(e) => onKeyUp(e)}
        tabIndex={1}
        role='Plan selector'
      >
        <img className='cardImage' src={`./images/${props.image}`}></img>
        <div className='textContainer'>
          <span className='cardName'>{props.name}</span>
          <span className='cardPrice'>{`$${props.price} / ${getPricingLabel(props.pricing)}`}</span>
        </div>
      </label>
    </div>
  );
};

export default FormCard;
