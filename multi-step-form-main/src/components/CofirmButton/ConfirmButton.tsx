import React from "react";
import FormButton from "../FormButton/FormButton";

type Props = {
  onClick: () => void;
};

const ConfirmButton = (props: Props) => {
  return <FormButton text='Confirm' variety='secondary' onClick={() => props.onClick()}></FormButton>;
};

export default ConfirmButton;
