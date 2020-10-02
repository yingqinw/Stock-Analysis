import React from 'react';
import './App.css';
import AddStockForm from './AddStockForm';
import {useEffect, useState} from 'react';

export default function() {
  const [alertText, setAlertText] = useState("");	
  const [validTicker, setValidTicker] = useState(false);
  const [validQuantity, setValidQuantity] = useState(false);
  const [validStart, setValidStart] = useState(false);
  const [validEnd, setValidEnd] = useState(true);
  const [ticker, setTicker] = useState("");
  const [quantity, setQuantity] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  
  useEffect(() => {
    setValidTicker(/^[A-Z]{1,5}$/.test(ticker) && ticker.length >= 1 && ticker.length <= 5);
  }, [ticker]);
  useEffect(() => {
    setValidQuantity(/^[0-9]{1,}$/.test(quantity) && quantity !== "0");
  }, [quantity]);
  useEffect(() => {
    setValidStart(/^[0-9]{4}-[0-9]{2}-[0-9][2]$/.test(startDate));
  }, [startDate]);
  useEffect(() => {
    setValidEnd(endDate.localeCompare(startDate)===1 || !endDate.includes("-"));
  }, [endDate]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
    if(!validTicker) {
      alertMessage.push("Ticker should have 1 to 5 uppercase letters.");
    }
    if(!validQuantity) {
      alertMessage.push("Quantity should be a number greater than 0.");
    }
	if(!validStart) {
	  alertMessage.push("Please enter a start date.")	
	}
	if(!validEnd){
	  alertMessage.push("Please choose an end date that is after start date.")	
	}
    if(alertMessage.length !== 0) {
      alertMessage.join('\n');
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      //success
    }
  }

	
  return (
    <div>
      Homepage
      <button>Sign out</button>
	  <div classname = "addform-wrapper">
	  	{<AddStockForm
		  handleSubmit={handleSubmit}
		  setTicker={setTicker}
		  setQuantity={setQuantity}
		  setStartDate={setStartDate}
		  setEndDate={setEndDate}
          alertText = {alertText}
          setAlertText={setAlertText}
		  validTicker={validTicker}
		  validStart={validStart}
		  validQuantity={validQuantity}
		  validEnd={validEnd}
		/>}
	  </div>
    </div>
	
  );
}
