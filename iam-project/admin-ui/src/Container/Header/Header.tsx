import styles from "./Header.module.css";

const Header = () => {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>IAM Admin UI</h1>
    </div>
  );
};

export default Header;
