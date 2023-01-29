import React, { useContext, useState } from "react";
import { PlanPricing, PlanPricingContext, PlanPricingContextType } from "../Form/Form";
import Switch from "../Switch/Switch";
import "./SubscriptionTypeSelector.css";

const LABLE_SELECTED = "lableSelected";
const LABLE = "lable";

const SubscriptionTypeSelector = () => {
  const { pricing, setPricing } = useContext(PlanPricingContext) as PlanPricingContextType;

  const onToggleChange = () => {
    if (pricing === PlanPricing.MONTH) {
      setElementAsSelected(document.getElementById("yearly"));
      setElementNotSelected(document.getElementById("monthly"));
      setPricing(PlanPricing.YEAR);
    } else {
      setElementAsSelected(document.getElementById("monthly"));
      setElementNotSelected(document.getElementById("yearly"));
      setPricing(PlanPricing.MONTH);
    }
  };

  const setElementAsSelected = (element: HTMLElement | null) => {
    element?.classList.remove(LABLE);
    element?.classList.add(LABLE_SELECTED);
  };

  const setElementNotSelected = (element: HTMLElement | null) => {
    element?.classList.remove(LABLE_SELECTED);
    element?.classList.add(LABLE);
  };

  return (
    <div className='typeSelector'>
      <span className={LABLE_SELECTED} id='monthly'>
        Monthly
      </span>
      <Switch onChange={() => onToggleChange()}></Switch>
      <span className={LABLE} id='yearly'>
        Yearly
      </span>
    </div>
  );
};

export default SubscriptionTypeSelector;
