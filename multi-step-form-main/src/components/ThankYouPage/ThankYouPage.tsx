import React from "react";
import "./ThankYouPage.css";

const ThankYouPage = () => {
  return (
    <div className='thankYou'>
      <img className='icon' src={`./images/icon-thank-you.svg`}></img>
      <span className='header'>Thank You!</span>
      {/* <span className='content'>
        Thank you for confirming your subscription! We hope you have fun using our platform. If you ever need support,
        please feel free to email us at support@loremgaming.com
      </span> */}
      <span className='content'>Thank you for confirming your subscription!</span>
    </div>
  );
};

export default ThankYouPage;
