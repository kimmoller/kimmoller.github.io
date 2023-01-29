import React from "react";
import "./SideBarItem.css";

type Props = {
  number: string;
  step: string;
  topic: string;
  isCurrentStep: boolean;
};

const SideBarItem = (props: Props) => {
  return (
    <div className='sidebarItem'>
      <div className={`number ${props.isCurrentStep ? "selectedNumber" : null}`}>{props.number}</div>
      <div className='text'>
        <span className='step'>{props.step}</span>
        <span className='topic'>{props.topic}</span>
      </div>
    </div>
  );
};

export default SideBarItem;
