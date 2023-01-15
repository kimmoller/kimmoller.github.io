import React, { useState } from "react";
import "./FormInput.css";

type Props = {
  title: string;
  placeholder: string;
  required: boolean;
};

const FormInput = (props: Props) => {
  const isFilled = useState(false);

  return (
    <div className='formInput'>
      <div className='titleContainer'>
        <span className='title'>{props.title}</span>
        <span className='error'>This field is required</span>
      </div>
      <input className='input' placeholder={props.placeholder}></input>
    </div>
  );
};

export default FormInput;
