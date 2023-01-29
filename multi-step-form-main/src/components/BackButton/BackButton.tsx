import React from "react";
import FormButton from "../FormButton/FormButton";

type Props = {
  onClick: () => void;
};

const BackButton = (props: Props) => {
  return <FormButton text='Go Back' onClick={() => props.onClick()} variety='light'></FormButton>;
};

export default BackButton;
