import React from "react";
import BackButton from "../BackButton/BackButton";
import ConfirmButton from "../CofirmButton/ConfirmButton";
import FormTitle from "../FormTitle/FormTitle";
import Selections from "../Selections/Selections";

type Props = {
  onConfirm: () => void;
  onClickGoBack: () => void;
};

const ConfirmSection = (props: Props) => {
  return (
    <div className='form'>
      <FormTitle mainText='Finishing up' secondaryText='Double-check everythin looks OK before confirming.'></FormTitle>
      <Selections></Selections>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <ConfirmButton onClick={() => props.onConfirm()}></ConfirmButton>
      </div>
    </div>
  );
};

export default ConfirmSection;
