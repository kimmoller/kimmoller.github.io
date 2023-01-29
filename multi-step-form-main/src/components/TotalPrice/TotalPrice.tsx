import React, { useContext } from "react";
import { getPricingLabel, getTotalPricingLabel } from "../../Pricing";
import { FormContext, FormContextType, PlanPricing, PlanPricingContext, PlanPricingContextType } from "../Form/Form";
import styles from "./totalPrice.module.css";

const TotalPrice = () => {
  const { data, updateData } = useContext(FormContext) as FormContextType;
  const { pricing, setPricing } = useContext(PlanPricingContext) as PlanPricingContextType;

  const getTotalPrice = () => {
    let totalPrice = data.plan.price;
    data.addOns.forEach((addOn) => {
      totalPrice += addOn.price;
    });
    return totalPrice;
  };

  return (
    <div className={styles.totalPriceContainer}>
      <span className={styles.title}>{`Total ${getTotalPricingLabel(pricing)}`}</span>
      <span className={styles.price}>{`+$${getTotalPrice()} / ${getPricingLabel(pricing)}`}</span>
    </div>
  );
};

export default TotalPrice;
