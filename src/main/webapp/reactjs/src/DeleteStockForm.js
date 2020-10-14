import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';


export default function(props) {
  return (
    <form id="addStock-form" onSubmit={(e)=>{e.preventDefault()}}>
      <div className="selectWrapper2">
        <FormTitle width={100}>Remove stock</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowDeleteStockForm(false)
        }}></i>
      </div>
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
          <h4>Start Date</h4>
          <input type="date" placeholder="start date"
            onChange={(e) => {
              props.setStartDate(e.target.value);
            }}
            style={{borderColor: props.validStart? 'green':'red'}} 
          />
          <h4>End Date (optional)</h4>
          <input type="date" placeholder="end date"
            onChange={(e) => {
              props.setEndDate(e.target.value);
            }}
            style={{borderColor: props.validEnd? 'green':'red'}} 
          />
        </div>
        <Button onClick={()=>{props.resetLogoutTimer()}}>
          remove stock
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{props.alertText}</p>
      </div>
	</form>
  );
}
