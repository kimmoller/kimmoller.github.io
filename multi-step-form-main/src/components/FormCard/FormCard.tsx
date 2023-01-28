import React from "react";
import "./FormCard.css";

type Props = {
  id: string;
  image: string;
  name: string;
  price: string;
  selectPlan: (name: string, price: string) => void;
};

const FormCard = (props: Props) => {
  const onEnterPress = (e: React.KeyboardEvent) => {
    if (e.key === "Enter") {
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
        onChange={() => props.selectPlan(props.name, props.price)}
      ></input>
      <label
        className='formCardLabel'
        onClick={() => props.selectPlan(props.name, props.price)}
        htmlFor={props.id}
        onKeyUp={(e) => onEnterPress(e)}
        tabIndex={1}
        role='Plan selector'
      >
        <img className='cardImage' src={`./images/${props.image}`}></img>
        <div className='textContainer'>
          <span className='cardName'>{props.name}</span>
          <span className='cardPrice'>{props.price}</span>
        </div>
      </label>
    </div>
  );
};

export default FormCard;
