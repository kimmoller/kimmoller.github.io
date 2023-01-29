import React, { useContext } from "react";
import BackButton from "../BackButton/BackButton";
import ConfirmButton from "../CofirmButton/ConfirmButton";
import { FormContext, FormContextType } from "../Form/Form";
import FormTitle from "../FormTitle/FormTitle";

type Props = {
  onConfirm: () => void;
  onClickGoBack: () => void;
};

const ConfirmSection = (props: Props) => {
  const { data, updateData } = useContext(FormContext) as FormContextType;

  return (
    <div className='form'>
      <FormTitle mainText='Finishing up' secondaryText='Double-check everythin looks OK before confirming.'></FormTitle>
      <div>
        <span>{data.plan.name}</span>
        <span>{data.plan.price}</span>
      </div>
      <div>
        {data.addOns.map((item) => {
          return (
            <div>
              <span>{item.name}</span>
              <span>{item.price}</span>
            </div>
          );
        })}
      </div>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <ConfirmButton onClick={() => props.onConfirm()}></ConfirmButton>
      </div>
    </div>
  );
};

export default ConfirmSection;
