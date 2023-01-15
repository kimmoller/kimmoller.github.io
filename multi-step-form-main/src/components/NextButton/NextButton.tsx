import React from "react";
import FormButton from "../FormButton/FormButton";

type Props = {
  onClick: () => void;
};

const NextButton = (props: Props) => {
  return <FormButton text='Next Step' onClick={() => props.onClick()} variety='primary'></FormButton>;
};

export default NextButton;
