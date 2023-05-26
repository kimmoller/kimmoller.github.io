import styles from "./Content.module.css";

const Content = () => {
  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <h1>Building enjoyable web experiences</h1>
        <span className={styles.description}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat.
        </span>
      </div>
      <div className={styles.image}></div>
    </section>
  );
};

// Divide into 4 parts
// Introduction
// About
// Projects / Career
// Skills

export default Content;
