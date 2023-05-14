import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={styles.container}>
      <h3 className={styles.title}>Kim Möller</h3>
      <a
        className={styles.link}
        target='_blank'
        href='https://github.com/kimmoller/kimmoller.github.io/tree/main/iam-project'
      >
        GitHub
      </a>
    </div>
  );
};

export default Footer;
