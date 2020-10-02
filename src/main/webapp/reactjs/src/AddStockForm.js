import React from 'react';
import './App.css';
import {FormWrapper, Button, Arrow} from './Modals';


export default function(props) {
  return (
    <form id="addStock-form" onSubmit={props.handleSubmit}>
	  <FormWrapper>
        <div className="fields">
          <input type="text" placeholder="Ticker" onChange={(e) => {
            props.setTicker(e.target.value);
          }}
            style={{borderColor: props.validTicker ? 'green':'red'}}  
          />
          <input type="text" placeholder="Quantity" onChange={(e) => {
            props.setQuantity(e.target.value);
          }}
            style={{borderColor: props.validQuantity ? 'green':'red'}} 
          />
		  <h1>Start Date</h1>
          <input type="date" placeholder="start date"
            onChange={(e) => {
              props.setStartDate(e.target.value);
            }}
            style={{borderColor: props.validStart? 'green':'red'}} 
          />
		  <h1>End Date (optional)</h1>
		  <input type="date" placeholder="end date"
            onChange={(e) => {
              props.setEndDate(e.target.value);
            }}
            style={{borderColor: props.validEnd? 'green':'red'}} 
          />
        </div>
        <Button>
          add stock
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
      
      <div className="alertWrapper">
      	<p>{props.alertText}</p>
      </div>

	</form>
  );
}