import React, { useContext } from "react";
import { StepContext, StepContextType } from "../../App";
import AddOn from "../AddOn/AddOn";
import { FormContext, FormContextType, PlanPricingContext, PlanPricingContextType } from "../Form/Form";
import PlanView from "../PlanView/PlanView";
import styles from "./selections.module.css";

const Selections = () => {
  const { data, updateData } = useContext(FormContext) as FormContextType;
  const { pricing, setPricing } = useContext(PlanPricingContext) as PlanPricingContextType;
  const { currentStep, setCurrentStep } = useContext(StepContext) as StepContextType;

  return (
    <div className={styles.container}>
      <PlanView
        title={data.plan.name}
        price={data.plan.price}
        pricing={pricing}
        setCurrentStep={(step) => setCurrentStep(step)}
      ></PlanView>
      <hr />
      <div className={styles.addOnContainer}>
        {data.addOns.map((addOn) => {
          return <AddOn title={addOn.name} price={addOn.price} pricing={pricing}></AddOn>;
        })}
      </div>
    </div>
  );
};

export default Selections;
