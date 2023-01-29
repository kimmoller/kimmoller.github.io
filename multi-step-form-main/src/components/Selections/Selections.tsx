import React, { useContext } from "react";
import AddOn from "../AddOn/AddOn";
import { FormContext, FormContextType, PlanPricingContext, PlanPricingContextType } from "../Form/Form";
import PlanView from "../PlanView/PlanView";
import styles from "./selections.module.css";

const Selections = () => {
  const { data, updateData } = useContext(FormContext) as FormContextType;
  const { pricing, setPricing } = useContext(PlanPricingContext) as PlanPricingContextType;
  return (
    <div className={styles.container}>
      <PlanView title={data.plan.name} price={data.plan.price} pricing={pricing}></PlanView>
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
