import styles from "./Navigation.module.css";

const Navigation = () => {
  return (
    <nav className={styles.container}>
      <div className={styles.link}>
        <span className={styles.number}>01.</span>
        <span>About</span>
      </div>
      <div className={styles.link}>
        <span className={styles.number}>02.</span>
        <span>Projects</span>
      </div>
      <div className={styles.link}>
        <span className={styles.number}>03.</span>
        <span>Skills</span>
      </div>
    </nav>
  );
};

export default Navigation;
