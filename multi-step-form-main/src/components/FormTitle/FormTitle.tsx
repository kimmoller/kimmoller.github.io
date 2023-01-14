import React from "react";
import "./FormTitle.css";

type Props = {
  mainText: string;
  secondaryText: string;
};

const FormTitle = (props: Props) => {
  return (
    <div className='formTitle'>
      <span className='mainText'>{props.mainText}</span>
      <span className='secondaryText'>{props.secondaryText}</span>
    </div>
  );
};

export default FormTitle;
