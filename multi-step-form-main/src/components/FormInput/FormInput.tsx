import React, { useContext, useState } from "react";
import "./FormInput.css";

type Props = {
  title: string;
  placeholder: string;
  required: boolean;
  value?: string;
  updateValue: (value: string) => void;
};

const FormInput = (props: Props) => {
  return (
    <div className='formInput'>
      <div className='titleContainer'>
        <span className='title'>{props.title}</span>
        <span className='error'>This field is required</span>
      </div>
      <input
        className='input'
        placeholder={props.placeholder}
        value={props.value}
        onChange={(e) => props.updateValue(e.target.value)}
      ></input>
    </div>
  );
};

export default FormInput;
