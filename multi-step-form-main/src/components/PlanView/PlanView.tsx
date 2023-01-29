import React from "react";
import { getPricingLabel } from "../../Pricing";
import { PlanPricing } from "../Form/Form";
import styles from "./planview.module.css";

type Props = {
  title: string;
  price: number;
  pricing: PlanPricing;
  setCurrentStep: (step: number) => void;
};

const PlanView = (props: Props) => {
  return (
    <div className={styles.planContainer}>
      <div className={styles.plan}>
        <span className={styles.title}>{props.title}</span>
        <a onClick={() => props.setCurrentStep(2)} className={styles.change}>
          Change
        </a>
      </div>
      <span className={styles.price}>{`$${props.price} / ${getPricingLabel(props.pricing)}`}</span>
    </div>
  );
};

export default PlanView;
