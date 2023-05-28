import { ReactElement } from "react";
import styles from "./IconButton.module.css";

type Props = {
  icon: ReactElement;
  onClick: () => void;
};

const IconButton = (props: Props) => {
  return (
    <button type='button' className={styles.button} onClick={() => props.onClick()}>
      {props.icon}
    </button>
  );
};

export default IconButton;
