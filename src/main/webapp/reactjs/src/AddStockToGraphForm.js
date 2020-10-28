import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';


export default function(props) {
  return (
    <form id="addStockToGraph-form" onSubmit={props.handleAddToGraph}>
      <div className="selectWrapper2">
        <FormTitle>View stock</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowAddStockGraph(false)
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
        <Button onClick={()=>{props.resetLogoutTimer()}}>
          VIEW STOCK
          <Arrow className="arrow"></Arrow>
        </Button>
		<Button style={{margin:20 }} onClick={()=>{
          props.setShowAddStockGraph(false);
          props.resetLogoutTimer()}}>
          CANCEL
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{props.alertText}</p>
      </div>
	</form>
  );
}