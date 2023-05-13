import React from "react";
import styles from "./TextInput.module.css";

type Props = {
  id: string;
  name: string;
  label: string;
  placeholder?: string;
  value: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown: (event: React.KeyboardEvent<HTMLInputElement>) => void;
};

const TextInput = (props: Props) => {
  return (
    <div className={styles.container}>
      <label htmlFor={props.name}>{props.label}</label>
      <input
        type='text'
        id={props.id}
        name={props.name}
        placeholder={props.placeholder}
        value={props.value}
        onChange={(event) => props.onChange(event)}
        onKeyDown={(event) => props.onKeyDown(event)}
      ></input>
    </div>
  );
};

export default TextInput;
