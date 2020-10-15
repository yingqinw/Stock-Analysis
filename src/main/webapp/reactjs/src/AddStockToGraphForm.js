import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';


export default function(props) {
  return (
    <form id="addStockToGraph-form" onSubmit={props.handleAddToGraph}>
      <div className="selectWrapper2">
        <FormTitle>Add stock</FormTitle>
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
          add stock to graph
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{props.alertText}</p>
      </div>
	</form>
  );
}