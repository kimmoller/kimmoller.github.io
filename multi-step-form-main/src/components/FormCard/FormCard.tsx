import React from "react";
import "./FormCard.css";

type Props = {
  image: string;
  name: string;
  price: string;
};

const FormCard = (props: Props) => {
  return (
    <div className='formCard'>
      <img className='cardImage' src={`./images/${props.image}`}></img>
      <div className='textContainer'>
        <span className='cardName'>{props.name}</span>
        <span className='cardPrice'>{props.price}</span>
      </div>
    </div>
  );
};

export default FormCard;
