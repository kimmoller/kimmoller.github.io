import React, { useState } from "react";
import BackButton from "../BackButton/BackButton";
import FormCard from "../FormCard/FormCard";
import FormInput from "../FormInput/FormInput";
import FormTitle from "../FormTitle/FormTitle";
import NextButton from "../NextButton/NextButton";
import SubscriptionTypeSelector from "../SubscriptionTypeSelector/SubscriptionTypeSelector";
import "./Form.css";

type FirstStepProps = {
  onClickNextStep: () => void;
};

type StepProps = {
  onClickNextStep: () => void;
  onClickGoBack: () => void;
};

const FirstStep = (props: FirstStepProps) => {
  return (
    <div className='form'>
      <FormTitle
        mainText='Personal Info'
        secondaryText='Please provide your name, email address, and phone number'
      ></FormTitle>
      <FormInput title='Name' placeholder='eg. Firstname Lastname' required={true}></FormInput>
      <FormInput title='Email Address' placeholder='eg. firstname.lastname@example.net' required={true}></FormInput>
      <FormInput title='Phone number' placeholder='eg. +358401234567' required={true}></FormInput>
      <div className='singleButtonContainer'>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

const SecondStep = (props: StepProps) => {
  return (
    <div className='form'>
      <FormTitle
        mainText='Select your plan'
        secondaryText='You have the option of a monthly or yearly subscription'
      ></FormTitle>
      <div className='cardContainer'>
        <FormCard image='icon-arcade.svg' name='Arcade' price='$9 / month'></FormCard>
        <FormCard image='icon-advanced.svg' name='Advanced' price='$12 / month'></FormCard>
        <FormCard image='icon-pro.svg' name='Pro' price='$15 / month'></FormCard>
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

const ThirdStep = () => {
  return <div>step 3</div>;
};

const LastStep = () => {
  return <div>step 4</div>;
};

const Form = () => {
  const [currentStep, setCurrentStep] = useState(2);

  const goToNextStep = () => {
    const nextStep = currentStep + 1;
    setCurrentStep(nextStep);
  };

  const returnToPreviousStep = () => {
    const nextStep = currentStep - 1;
    setCurrentStep(nextStep);
  };

  return (
    <>
      {currentStep === 1 ? <FirstStep onClickNextStep={() => goToNextStep()}></FirstStep> : null}
      {currentStep === 2 ? (
        <SecondStep onClickNextStep={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></SecondStep>
      ) : null}
      {currentStep === 3 ? <ThirdStep></ThirdStep> : null}
      {currentStep === 4 ? <LastStep></LastStep> : null}
    </>
  );
};

export default Form;
