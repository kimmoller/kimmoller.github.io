import React, { useContext, useMemo, useReducer, useState } from "react";
import BackButton from "../BackButton/BackButton";
import ConfirmButton from "../CofirmButton/ConfirmButton";
import FormCard from "../FormCard/FormCard";
import FormInput from "../FormInput/FormInput";
import FormTitle from "../FormTitle/FormTitle";
import NextButton from "../NextButton/NextButton";
import SelectBox from "../SelectBox/SelectBox";
import SubscriptionTypeSelector from "../SubscriptionTypeSelector/SubscriptionTypeSelector";
import ThankYouPage from "../ThankYouPage/ThankYouPage";
import "./Form.css";

type FirstStepProps = {
  onClickNextStep: () => void;
};

type StepProps = {
  onClickNextStep: () => void;
  onClickGoBack: () => void;
};

type ConfirmStepProps = {
  onConfirm: () => void;
  onClickGoBack: () => void;
};

interface PersonalInfo {
  name: string;
  email: string;
  phoneNumber: string;
}

interface Item {
  name: string;
  price: string;
}

interface FormData {
  personalInfo: PersonalInfo;
  plan: Item;
  addOns: Item[];
}

type FormContextType = {
  data: FormData;
  updateData: React.Dispatch<FormData>;
};

const FirstStep = (props: FirstStepProps) => {
  const { data, updateData } = useContext(FormContext) as FormContextType;

  return (
    <div className='form'>
      <FormTitle
        mainText='Personal Info'
        secondaryText='Please provide your name, email address, and phone number.'
      ></FormTitle>
      <FormInput
        title='Name'
        placeholder='eg. Firstname Lastname'
        required={true}
        value={data.personalInfo.name}
        updateValue={(value) => updateData({ ...data, personalInfo: { ...data.personalInfo, name: value } })}
      ></FormInput>
      <FormInput
        title='Email Address'
        placeholder='eg. firstname.lastname@example.net'
        required={true}
        value={data.personalInfo.email}
        updateValue={(value) => updateData({ ...data, personalInfo: { ...data.personalInfo, email: value } })}
      ></FormInput>
      <FormInput
        title='Phone number'
        placeholder='eg. +358401234567'
        required={true}
        value={data.personalInfo.phoneNumber}
        updateValue={(value) => updateData({ ...data, personalInfo: { ...data.personalInfo, phoneNumber: value } })}
      ></FormInput>
      <div className='singleButtonContainer'>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

const SecondStep = (props: StepProps) => {
  const { data, updateData } = useContext(FormContext) as FormContextType;

  return (
    <div className='form'>
      <FormTitle
        mainText='Select your plan'
        secondaryText='You have the option of a monthly or yearly subscription.'
      ></FormTitle>
      <div className='cardContainer'>
        <FormCard
          id='Arcade'
          image='icon-arcade.svg'
          name='Arcade'
          price='$9 / month'
          selectPlan={(name, price) => updateData({ ...data, plan: { name: name, price: price } })}
        ></FormCard>
        <FormCard
          id='Advanced'
          image='icon-advanced.svg'
          name='Advanced'
          price='$12 / month'
          selectPlan={(name, price) => updateData({ ...data, plan: { name: name, price: price } })}
        ></FormCard>
        <FormCard
          id='Pro'
          image='icon-pro.svg'
          name='Pro'
          price='$15 / month'
          selectPlan={(name, price) => updateData({ ...data, plan: { name: name, price: price } })}
        ></FormCard>
      </div>
      <div className='selectorContainer'>
        <SubscriptionTypeSelector></SubscriptionTypeSelector>
      </div>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

const ThirdStep = (props: StepProps) => {
  return (
    <div className='form'>
      <FormTitle mainText='Pick add-ons' secondaryText='Add-ons help you enhance your gaming experince.'></FormTitle>
      <div className='selectBoxContainer'>
        <SelectBox mainText='Online service' secondaryText='Access to multiplayer games' price='+$1/mo'></SelectBox>
        <SelectBox mainText='Larger storage' secondaryText='Extra 1TB of cloud save' price='+$2/mo'></SelectBox>
        <SelectBox
          mainText='Customizable profile'
          secondaryText='Custom theme on your profile'
          price='+$2/mo'
        ></SelectBox>
      </div>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

const ConfirmStep = (props: ConfirmStepProps) => {
  return (
    <div className='form'>
      <FormTitle mainText='Finishing up' secondaryText='Double-check everythin looks OK before confirming.'></FormTitle>
      <div className='multiButtonContainer'>
        <BackButton onClick={() => props.onClickGoBack()}></BackButton>
        <ConfirmButton onClick={() => props.onConfirm()}></ConfirmButton>
      </div>
    </div>
  );
};

const LastStep = () => {
  return (
    <div className='form'>
      <ThankYouPage></ThankYouPage>
    </div>
  );
};

export const FormContext = React.createContext<FormContextType | null>(null);

const Form = () => {
  const [currentStep, setCurrentStep] = useState(1);
  const [data, updateData] = useReducer(
    (prev: FormData, next: FormData) => {
      return { ...prev, ...next };
    },
    { personalInfo: { name: "", email: "", phoneNumber: "" }, plan: { name: "", price: "" }, addOns: [] }
  );

  const contextValue = useMemo(() => {
    return { data, updateData };
  }, [data, updateData]);

  const goToNextStep = () => {
    console.log(data);
    const nextStep = currentStep + 1;
    setCurrentStep(nextStep);
  };

  const returnToPreviousStep = () => {
    const nextStep = currentStep - 1;
    setCurrentStep(nextStep);
  };

  return (
    <>
      <FormContext.Provider value={contextValue}>
        {currentStep === 1 ? <FirstStep onClickNextStep={() => goToNextStep()}></FirstStep> : null}
        {currentStep === 2 ? (
          <SecondStep onClickNextStep={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></SecondStep>
        ) : null}
        {currentStep === 3 ? (
          <ThirdStep onClickNextStep={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></ThirdStep>
        ) : null}
        {currentStep === 4 ? (
          <ConfirmStep onConfirm={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></ConfirmStep>
        ) : null}
        {currentStep === 5 ? <LastStep></LastStep> : null}
      </FormContext.Provider>
    </>
  );
};

export default Form;
