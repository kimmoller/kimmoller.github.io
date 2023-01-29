import React, { useContext } from "react";
import BackButton from "../BackButton/BackButton";
import { FormContext, FormContextType, FormDataActionType } from "../Form/Form";
import FormTitle from "../FormTitle/FormTitle";
import NextButton from "../NextButton/NextButton";
import SelectBox from "../SelectBox/SelectBox";

type Props = {
  onClickNextStep: () => void;
  onClickGoBack: () => void;
};

const AddOnsSection = (props: Props) => {
  const { data, updateData } = useContext(FormContext) as FormContextType;

  return (
    <div className='form'>
      <FormTitle mainText='Pick add-ons' secondaryText='Add-ons help you enhance your gaming experince.'></FormTitle>
      <div className='selectBoxContainer'>
        <SelectBox
          mainText='Online service'
          secondaryText='Access to multiplayer games'
          price='+$1/mo'
          onSelect={(name, price) =>
            updateData({
              type: FormDataActionType.ADD,
              data: { ...data, addOns: [...data.addOns, { name: name, price: price }] },
            })
          }
          onDeselect={(name) => updateData({ type: FormDataActionType.REMOVE, id: name, data: { ...data } })}
          selected={data.addOns.filter((item) => item.name === "Online service").length > 0}
        ></SelectBox>
        <SelectBox
          mainText='Larger storage'
          secondaryText='Extra 1TB of cloud save'
          price='+$2/mo'
          onSelect={(name, price) =>
            updateData({
              type: FormDataActionType.ADD,
              data: { ...data, addOns: [...data.addOns, { name: name, price: price }] },
            })
          }
          onDeselect={(name) => updateData({ type: FormDataActionType.REMOVE, id: name, data: { ...data } })}
          selected={data.addOns.filter((item) => item.name === "Larger storage").length > 0}
        ></SelectBox>
        <SelectBox
          mainText='Customizable profile'
          secondaryText='Custom theme on your profile'
          price='+$2/mo'
          onSelect={(name, price) =>
            updateData({
              type: FormDataActionType.ADD,
              data: { ...data, addOns: [...data.addOns, { name: name, price: price }] },
            })
          }
          onDeselect={(name) => updateData({ type: FormDataActionType.REMOVE, id: name, data: { ...data } })}
          selected={data.addOns.filter((item) => item.name === "Customizable profile").length > 0}
        ></SelectBox>
      </div>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

export default AddOnsSection;
