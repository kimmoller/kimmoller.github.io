import React from "react";
import FormButton from "../FormButton/FormButton";
import FormInput from "../FormInput/FormInput";
import FormTitle from "../FormTitle/FormTitle";
import "./Form.css";

const Form = () => {
  return (
    <div className='form'>
      <FormTitle
        mainText='Personal Info'
        secondaryText='Please provide your name, email address, and phone number'
      ></FormTitle>
      <FormInput title='Name' placeholder='eg. Firstname Lastname'></FormInput>
      <FormInput title='Email Address' placeholder='eg. firstname.lastname@example.net'></FormInput>
      <FormInput title='Phone number' placeholder='eg. +358401234567'></FormInput>
      <div className='buttonContainer'>
        <FormButton text='Next Step'></FormButton>
      </div>
    </div>
  );
};

export default Form;
