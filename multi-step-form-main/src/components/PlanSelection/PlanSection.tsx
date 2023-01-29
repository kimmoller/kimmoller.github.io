import React, { useContext } from "react";
import BackButton from "../BackButton/BackButton";
import {
  FormContext,
  FormContextType,
  FormDataActionType,
  PlanPricing,
  PlanPricingContext,
  PlanPricingContextType,
} from "../Form/Form";
import FormCard from "../FormCard/FormCard";
import FormTitle from "../FormTitle/FormTitle";
import NextButton from "../NextButton/NextButton";
import SubscriptionTypeSelector from "../SubscriptionTypeSelector/SubscriptionTypeSelector";

type Props = {
  onClickNextStep: () => void;
  onClickGoBack: () => void;
};

const PlanSection = (props: Props) => {
  const { data, updateData } = useContext(FormContext) as FormContextType;
  const { pricing, setPricing } = useContext(PlanPricingContext) as PlanPricingContextType;

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
          price={pricing === PlanPricing.MONTH ? 9 : 90}
          pricing={pricing}
          selectPlan={(name, price) =>
            updateData({ type: FormDataActionType.ADD, data: { ...data, plan: { name: name, price: price } } })
          }
        ></FormCard>
        <FormCard
          id='Advanced'
          image='icon-advanced.svg'
          name='Advanced'
          price={pricing === PlanPricing.MONTH ? 12 : 120}
          pricing={pricing}
          selectPlan={(name, price) =>
            updateData({ type: FormDataActionType.ADD, data: { ...data, plan: { name: name, price: price } } })
          }
        ></FormCard>
        <FormCard
          id='Pro'
          image='icon-pro.svg'
          name='Pro'
          price={pricing === PlanPricing.MONTH ? 15 : 150}
          pricing={pricing}
          selectPlan={(name, price) =>
            updateData({ type: FormDataActionType.ADD, data: { ...data, plan: { name: name, price: price } } })
          }
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

export default PlanSection;
