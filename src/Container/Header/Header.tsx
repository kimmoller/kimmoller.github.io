import styles from "./Header.module.css";
import { ReactComponent as Logo } from "../../assets/logo.svg";
import ThemeSelector from "../ThemeSelector/ThemeSelector";
import { GitHubLogoIcon, LinkedInLogoIcon } from "@radix-ui/react-icons";

const Header = () => {
  return (
    <section className={styles.container}>
      <div className={styles.logoContainer}>
        <Logo />
      </div>
      <div className={styles.externalLinksContainer}>
        <a href='https://github.com/kimmoller'>
          <GitHubLogoIcon className={styles.icon} />
        </a>
        <a href='https://www.linkedin.com/in/mollerkim/'>
          <LinkedInLogoIcon className={styles.icon} />
        </a>
        <ThemeSelector />
      </div>
    </section>
  );
};

export default Header;
