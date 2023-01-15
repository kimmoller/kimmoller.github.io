import React, { useState } from "react";
import Switch from "../Switch/Switch";
import "./SubscriptionTypeSelector.css";

const LABLE_SELECTED = "lableSelected";
const LABLE = "lable";

const SubscriptionTypeSelector = () => {
  const [isSelected, setIsSelected] = useState("monthly");

  const onToggleChange = () => {
    if (isSelected === "monthly") {
      setElementAsSelected(document.getElementById("yearly"));
      setElementNotSelected(document.getElementById("monthly"));
      setIsSelected("yearly");
    } else {
      setElementAsSelected(document.getElementById("monthly"));
      setElementNotSelected(document.getElementById("yearly"));
      setIsSelected("monthly");
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
