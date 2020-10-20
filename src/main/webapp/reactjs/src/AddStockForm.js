import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';


export default function(props) {
  return (
    <form id="addStock-form" onSubmit={props.handleSubmit}>
      <div className="selectWrapper2">
        <FormTitle>Add stock</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowAddStockForm(false)
          props.setAlertText("")
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
          <h4>Buy date</h4>
          <input type="date" placeholder="start date"
            onChange={(e) => {
              props.setStartDate(e.target.value);
            }}
            style={{borderColor: props.validStart? 'green':'red'}} 
          />
          <h4>Sell date</h4>
          <input type="date" placeholder="end date"
            onChange={(e) => {
              props.setEndDate(e.target.value);
            }}
            style={{borderColor: props.validEnd? 'green':'red'}} 
          />
        </div>
        <Button onClick={()=>{props.resetLogoutTimer()}}>
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
