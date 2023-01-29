import React, { useContext } from "react";
import AddOn from "../AddOn/AddOn";
import { FormContext, FormContextType } from "../Form/Form";
import PlanView from "../PlanView/PlanView";
import styles from "./selections.module.css";

const Selections = () => {
  const { data, updateData } = useContext(FormContext) as FormContextType;
  return (
    <div className={styles.container}>
      <PlanView title={data.plan.name} price={data.plan.price}></PlanView>
      <hr />
      <div className={styles.addOnContainer}>
        {data.addOns.map((addOn) => {
          return <AddOn title={addOn.name} price={addOn.price}></AddOn>;
        })}
      </div>
    </div>
  );
};

export default Selections;
