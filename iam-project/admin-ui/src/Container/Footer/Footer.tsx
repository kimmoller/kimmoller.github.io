import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={styles.container}>
      <h3 className={styles.title}>Kim Möller</h3>
      <a className={styles.link} href='https://github.com/kimmoller'>
        GitHub
      </a>
    </div>
  );
};

export default Footer;
