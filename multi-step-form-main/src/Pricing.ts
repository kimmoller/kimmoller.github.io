import { PlanPricing } from "./components/Form/Form";

export const getPricingLabel = (pricing: PlanPricing) => {
  return pricing === PlanPricing.MONTH ? "month" : "year";
};
