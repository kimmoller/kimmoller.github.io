import React from "react";
import styles from "./header.module.css";

type Props = {
  total: string;
};

const Header = (props: Props) => {
  return (
    <div className={styles.header}>
      <div className={styles.content}>
        <span className={styles.title}>My Balance</span>
        <span className={styles.total}>${props.total}</span>
      </div>
      <div className={styles.logo}>
        <img src='/logo.svg'></img>
      </div>
    </div>
  );
};

export default Header;
