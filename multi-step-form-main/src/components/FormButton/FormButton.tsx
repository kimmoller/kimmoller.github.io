import React from "react";
import "./FormButton.css";

type Props = {
  text: string;
  onClick: () => void;
  variety: "primary" | "secondary";
};

const FormButton = (props: Props) => {
  return (
    <button className={`formButton ${props.variety}`} onClick={() => props.onClick()}>
      {props.text}
    </button>
  );
};

export default FormButton;
