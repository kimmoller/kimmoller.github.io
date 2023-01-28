import React from "react";
import styles from "./content.module.css";

const Content = () => {
  return (
    <div className={styles.content}>
      <div>
        <span className={styles.header}>Spending - Last 7 days</span>
      </div>
      <div>
        <span>chart</span>
      </div>
      <hr />
      <div>
        <span>total sums</span>
      </div>
    </div>
  );
};

export default Content;
