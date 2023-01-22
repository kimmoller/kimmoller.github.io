import React, { useState } from "react";
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

const FirstStep = (props: FirstStepProps) => {
  return (
    <div className='form'>
      <FormTitle
        mainText='Personal Info'
        secondaryText='Please provide your name, email address, and phone number.'
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
        secondaryText='You have the option of a monthly or yearly subscription.'
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

const Form = () => {
  const [currentStep, setCurrentStep] = useState(1);

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
      {currentStep === 3 ? (
        <ThirdStep onClickNextStep={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></ThirdStep>
      ) : null}
      {currentStep === 4 ? (
        <ConfirmStep onConfirm={() => goToNextStep()} onClickGoBack={() => returnToPreviousStep()}></ConfirmStep>
      ) : null}
      {currentStep === 5 ? <LastStep></LastStep> : null}
    </>
  );
};

export default Form;
