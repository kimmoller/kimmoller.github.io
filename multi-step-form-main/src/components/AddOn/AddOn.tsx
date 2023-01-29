import React from "react";
import styles from "./addon.module.css";

type Props = {
  title: string;
  price: string;
};

const AddOn = (props: Props) => {
  return (
    <div className={styles.addon}>
      <span className={styles.title}>{props.title}</span>
      <span className={styles.price}>{props.price}</span>
    </div>
  );
};

export default AddOn;
