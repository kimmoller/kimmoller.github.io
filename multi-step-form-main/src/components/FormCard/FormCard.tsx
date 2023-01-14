import React from "react";

type Props = {
  image: string;
  name: string;
  price: string;
};

const FormCard = (props: Props) => {
  return (
    <div className='formCard'>
      <img src={props.image}></img>
      <span className='formCardName'>{props.name}</span>
      <span className='formCardPrice'>{props.price}</span>
    </div>
  );
};

export default FormCard;
