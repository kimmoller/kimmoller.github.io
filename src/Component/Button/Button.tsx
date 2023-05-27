import { ReactElement } from "react";
import styles from "./Button.module.css";

type Props = {
  value: string | ReactElement;
  onClick: () => void;
  iconLeft?: ReactElement;
};

const Button = (props: Props) => {
  return (
    <button type='button' className={styles.button} onClick={() => props.onClick()}>
      {props.iconLeft}
      {props.value}
    </button>
  );
};

export default Button;
