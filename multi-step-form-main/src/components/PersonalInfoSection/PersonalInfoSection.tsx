import React, { useContext } from "react";
import { FormContext, FormContextType, FormDataActionType } from "../Form/Form";
import FormInput from "../FormInput/FormInput";
import FormTitle from "../FormTitle/FormTitle";
import NextButton from "../NextButton/NextButton";

type Props = {
  onClickNextStep: () => void;
};

const PersonalInfoSection = (props: Props) => {
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
        updateValue={(value) =>
          updateData({
            type: FormDataActionType.ADD,
            data: { ...data, personalInfo: { ...data.personalInfo, name: value } },
          })
        }
      ></FormInput>
      <FormInput
        title='Email Address'
        placeholder='eg. firstname.lastname@example.net'
        required={true}
        value={data.personalInfo.email}
        updateValue={(value) =>
          updateData({
            type: FormDataActionType.ADD,
            data: { ...data, personalInfo: { ...data.personalInfo, email: value } },
          })
        }
      ></FormInput>
      <FormInput
        title='Phone number'
        placeholder='eg. +358401234567'
        required={true}
        value={data.personalInfo.phoneNumber}
        updateValue={(value) =>
          updateData({
            type: FormDataActionType.ADD,
            data: { ...data, personalInfo: { ...data.personalInfo, phoneNumber: value } },
          })
        }
      ></FormInput>
      <div className='singleButtonContainer'>
        <NextButton onClick={() => props.onClickNextStep()}></NextButton>
      </div>
    </div>
  );
};

export default PersonalInfoSection;
