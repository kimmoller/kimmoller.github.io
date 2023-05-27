import { useState } from "react";
import { PersonIcon, RocketIcon, RulerSquareIcon } from "@radix-ui/react-icons";
import Career from "../../Component/Button/Career/Career";
import About from "../../Component/About/About";
import Introduction from "../../Component/Introduction/Introduction";
import Projects from "../../Component/Projects/Projects";
import Button from "../../Component/Button/Button";
import styles from "./Content.module.css";

enum Phase {
  INTRODUCTION,
  ABOUT,
  CAREER,
  PROJECTS,
}

const Content = () => {
  const [phase, setPhase] = useState(Phase.INTRODUCTION);

  const onClick = (phase: Phase) => {
    setPhase(phase);
  };

  return (
    <section className={styles.container}>
      <div className={styles.content}>
        {phase === Phase.INTRODUCTION ? <Introduction /> : null}
        {phase === Phase.ABOUT ? <About /> : null}
        {phase === Phase.CAREER ? <Career /> : null}
        {phase === Phase.PROJECTS ? <Projects /> : null}
      </div>
      <div className={styles.buttonContainer}>
        <Button value='About' onClick={() => onClick(Phase.ABOUT)} iconLeft={<PersonIcon />} />
        <Button value='Career' onClick={() => onClick(Phase.CAREER)} iconLeft={<RocketIcon />} />
        <Button value='Projects' onClick={() => onClick(Phase.PROJECTS)} iconLeft={<RulerSquareIcon />} />
      </div>
    </section>
  );
};

export default Content;
