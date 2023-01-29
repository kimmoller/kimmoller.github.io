import React, { SetStateAction, useMemo, useState } from "react";
import "./App.css";
import Form from "./components/Form/Form";
import SideBar from "./components/SideBar/SideBar";

export type StepContextType = {
  currentStep: number;
  setCurrentStep: React.Dispatch<SetStateAction<number>>;
};

export const StepContext = React.createContext<StepContextType | null>(null);

const App = () => {
  const [currentStep, setCurrentStep] = useState(1);

  const planContextValue = useMemo(() => {
    return { currentStep, setCurrentStep };
  }, [currentStep, setCurrentStep]);

  return (
    <StepContext.Provider value={planContextValue}>
      <div className='app'>
        <SideBar />
        <Form />
      </div>
    </StepContext.Provider>
  );
};

export default App;
