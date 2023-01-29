import React from "react";
import { getPricingLabel } from "../../Pricing";
import { PlanPricing } from "../Form/Form";
import styles from "./addon.module.css";

type Props = {
  title: string;
  price: number;
  pricing: PlanPricing;
};

const AddOn = (props: Props) => {
  return (
    <div className={styles.addon}>
      <span className={styles.title}>{props.title}</span>
      <span className={styles.price}>{`+$${props.price} / ${getPricingLabel(props.pricing)}`}</span>
    </div>
  );
};

export default AddOn;
