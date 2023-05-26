import { GitHubLogoIcon, LinkedInLogoIcon } from "@radix-ui/react-icons";
import styles from "./SideBar.module.css";

const SideBar = () => {
  return (
    <section className={styles.container}>
      <a href='https://github.com/kimmoller'>
        <GitHubLogoIcon className={styles.icon} />
      </a>
      <a href='https://www.linkedin.com/in/mollerkim/'>
        <LinkedInLogoIcon className={styles.icon} />
      </a>
    </section>
  );
};

export default SideBar;
