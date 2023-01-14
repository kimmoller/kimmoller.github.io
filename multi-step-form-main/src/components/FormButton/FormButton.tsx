import React from "react";
import "./FormButton.css";

type Props = {
  text: string;
};

const FormButton = (props: Props) => {
  return <button className='formButton'>{props.text}</button>;
};

export default FormButton;
