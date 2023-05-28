import { useReducer } from "react";
import Button from "../../Component/Button/Button";
import { MoonIcon, SunIcon } from "@radix-ui/react-icons";
import IconButton from "../../Component/IconButton/IconButton";

const ThemeSelector = () => {
  const themeMatcher = window.matchMedia("(prefers-color-scheme: dark)");
  const defaultDark = themeMatcher.matches;
  const [isDarkTheme, toggleDarkTheme] = useReducer((state) => {
    return !state;
  }, defaultDark);

  themeMatcher.addEventListener("change", () => {
    toggleDarkTheme();
  });

  console.log(isDarkTheme);

  const toggleTheme = () => {
    const element = document.body;
    if (defaultDark) {
      element.classList.toggle("light-mode");
      element.classList.remove("dark-mode");
    } else {
      element.classList.toggle("dark-mode");
      element.classList.remove("light-mode");
    }
    toggleDarkTheme();
  };

  return <IconButton icon={isDarkTheme ? <SunIcon /> : <MoonIcon />} onClick={() => toggleTheme()} />;
};

export default ThemeSelector;
