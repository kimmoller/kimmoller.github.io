import styles from "./Introduction.module.css";

const Introduction = () => {
  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <h1>Building enjoyable web experiences</h1>
        <span className={styles.description}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat.
        </span>
      </div>
      <div className={styles.image}></div>
    </div>
  );
};

export default Introduction;
