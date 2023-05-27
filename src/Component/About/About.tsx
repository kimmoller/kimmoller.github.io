import cvPhoto from "../../assets/cvPhoto.jpg";
import styles from "./About.module.css";
import { ReactComponent as ReactIcon } from "devicon/icons/react/react-original-wordmark.svg";
import { ReactComponent as JSIcon } from "devicon/icons/javascript/javascript-original.svg";
import { ReactComponent as TSIcon } from "devicon/icons/typescript/typescript-original.svg";
import { ReactComponent as JavaIcon } from "devicon/icons/java/java-original-wordmark.svg";
import { ReactComponent as MySQLIcon } from "devicon/icons/mysql/mysql-original-wordmark.svg";
import { ReactComponent as PostgresIcon } from "devicon/icons/postgresql/postgresql-original-wordmark.svg";
import { ReactComponent as Spring } from "devicon/icons/spring/spring-original-wordmark.svg";
import { ReactComponent as DockerIcon } from "devicon/icons/docker/docker-original-wordmark.svg";
import { ReactComponent as AwsIcon } from "devicon/icons/amazonwebservices/amazonwebservices-original-wordmark.svg";
import { ReactComponent as NodeJsIcon } from "devicon/icons/nodejs/nodejs-original-wordmark.svg";
import { ReactComponent as PythonIcon } from "devicon/icons/python/python-original-wordmark.svg";
import { ReactComponent as ReduxIcon } from "devicon/icons/redux/redux-original.svg";
import { ReactComponent as HtmlIcon } from "devicon/icons/html5/html5-original-wordmark.svg";
import { ReactComponent as JestIcon } from "devicon/icons/jest/jest-plain.svg";
import { ReactComponent as WebpackIcon } from "devicon/icons/webpack/webpack-original-wordmark.svg";
import { ReactComponent as SqLite } from "devicon/icons/sqlite/sqlite-original-wordmark.svg";

const About = () => {
  return (
    <div className={styles.container}>
      <div className={styles.aboutContainer}>
        <div className={styles.textContainer}>
          <h1>Hey! It's me, Kim Möller</h1>
          <span className={styles.description}>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat.
          </span>
        </div>
        <div className={styles.image}>
          <img src={cvPhoto}></img>
        </div>
      </div>
      <div className={styles.skillsContainer}>
        <JSIcon className={styles.icon} />
        <TSIcon className={styles.icon} />
        <HtmlIcon className={styles.icon} />
        <ReactIcon className={styles.icon} />
        <ReduxIcon className={styles.icon} />
        <WebpackIcon className={styles.icon} />
        <JestIcon className={styles.icon} />
        <NodeJsIcon className={styles.icon} />
        <JavaIcon className={styles.icon} />
        <Spring className={styles.icon} />
        <PostgresIcon className={styles.icon} />
        <MySQLIcon className={styles.icon} />
        <SqLite className={styles.icon} />
        <PythonIcon className={styles.icon} />
        <DockerIcon className={styles.icon} />
        <AwsIcon className={styles.icon} />
      </div>
    </div>
  );
};

export default About;
