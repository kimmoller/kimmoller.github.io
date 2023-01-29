import React, { SetStateAction, useContext, useMemo, useReducer, useState } from "react";
import { StepContext, StepContextType } from "../../App";
import AddOnsSection from "../AddOnsSection/AddOnsSection";
import ConfirmSection from "../ConfimSection/ConfirmSection";
import PersonalInfoSection from "../PersonalInfoSection/PersonalInfoSection";
import PlanSection from "../PlanSelection/PlanSection";
import ThankYouPage from "../ThankYouPage/ThankYouPage";
import "./Form.css";

export enum FormDataActionType {
  ADD = "ADD",
  REMOVE = "REMOVE",
}

export enum PlanPricing {
  MONTH = "MONTH",
  YEAR = "YEAR",
}

interface PersonalInfo {
  name: string;
  email: string;
  phoneNumber: string;
}

interface Item {
  name: string;
  price: number;
}

interface FormData {
  personalInfo: PersonalInfo;
  plan: Item;
  addOns: Item[];
}

interface FormDataAction {
  type: FormDataActionType;
  id?: string;
  data: FormData;
}

export type FormContextType = {
  data: FormData;
  updateData: React.Dispatch<FormDataAction>;
};

export type PlanPricingContextType = {
  pricing: PlanPricing;
  setPricing: React.Dispatch<SetStateAction<PlanPricing>>;
};

export const FormContext = React.createContext<FormContextType | null>(null);
export const PlanPricingContext = React.createContext<PlanPricingContextType | null>(null);

const Form = () => {
  const [pricing, setPricing] = useState(PlanPricing.MONTH);
  const { currentStep, setCurrentStep } = useContext(StepContext) as StepContextType;

  const [data, updateData] = useReducer(
    (state: FormData, action: FormDataAction) => {
      switch (action.type) {
        case "ADD":
          return { ...state, ...action.data };
        case "REMOVE":
          return { ...state, addOns: state.addOns.filter((item) => item.name !== action.id) };
        default:
          return { ...state, ...action.data };
      }
    },
    { personalInfo: { name: "", email: "", phoneNumber: "" }, plan: { name: "", price: 0 }, addOns: [] }
  );

  const contextValue = useMemo(() => {
    return { data, updateData };
  }, [data, updateData]);

  const planContextValue = useMemo(() => {
    return { pricing, setPricing };
  }, [pricing, setPricing]);

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
      <PlanPricingContext.Provider value={planContextValue}>
        <FormContext.Provider value={contextValue}>
          {currentStep === 1 ? (
            <PersonalInfoSection onClickNextStep={() => goToNextStep()}></PersonalInfoSection>
          ) : null}
          {currentStep === 2 ? (
            <PlanSection
              onClickNextStep={() => goToNextStep()}
              onClickGoBack={() => returnToPreviousStep()}
            ></PlanSection>
          ) : null}
          {currentStep === 3 ? (
            <AddOnsSection
              onClickNextStep={() => goToNextStep()}
              onClickGoBack={() => returnToPreviousStep()}
            ></AddOnsSection>
          ) : null}
          {currentStep === 4 ? (
            <ConfirmSection
              onConfirm={() => goToNextStep()}
              onClickGoBack={() => returnToPreviousStep()}
            ></ConfirmSection>
          ) : null}
          {currentStep === 5 ? <ThankYouPage /> : null}
        </FormContext.Provider>
      </PlanPricingContext.Provider>
    </>
  );
};

export default Form;
