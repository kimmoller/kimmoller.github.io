import React from "react";
import styles from "./planview.module.css";

type Props = {
  title: string;
  price: string;
};

const PlanView = (props: Props) => {
  return (
    <div className={styles.planContainer}>
      <div className={styles.plan}>
        <span className={styles.title}>{props.title}</span>
        <a className={styles.change}>Change</a>
      </div>
      <span className={styles.price}>{props.price}</span>
    </div>
  );
};

export default PlanView;
