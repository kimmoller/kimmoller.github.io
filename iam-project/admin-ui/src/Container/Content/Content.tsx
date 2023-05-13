import IdentitySearch from "../IdentitySearch/IdentitySearch";
import styles from "./Content.module.css";

const Content = () => {
  return (
    <div className={styles.container}>
      <IdentitySearch />
    </div>
  );
};

export default Content;
