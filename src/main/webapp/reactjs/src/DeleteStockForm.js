import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';

export default function(props) {
  


  return (
    <form id="addStock-form" /*onSubmit={deleteStock}*/>
      <div className="selectWrapper2">
        <FormTitle width={100}>Remove stock</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowDeleteStockForm(false)
          props.setAlertText("");
        }}></i>
      </div>
      <FormWrapper>
        <div className="fields">
          <input type="text" placeholder="Ticker" onChange={(e) => {
            props.setTicker(e.target.value);
          }}
            style={{borderColor: props.validTicker ? 'green':'red'}}  
          />
        </div>
        <Button onClick={()=>{props.resetLogoutTimer()
		  props.setShowRemoveConfirmForm(true)
		  props.setShowDeleteStockForm(false)}}>
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
