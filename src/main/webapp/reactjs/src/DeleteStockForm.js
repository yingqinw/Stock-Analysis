import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';

export default function(props) {
  
  const deleteStock = (e) => {
    e.preventDefault();
    let removeIndex = -1;
    props.graphTickers.forEach((tickerName, i) => {
      if(tickerName === props.ticker) {
        removeIndex = i;
      }
    })
    // not found in existing tickers
    if(removeIndex === -1) {
      props.setAlertText("");
      props.setAlertText("Ticker does not exist in the graph");
      return;
    }
    props.graphTickers.splice(removeIndex, 1);
    props.graphPrices.splice(removeIndex, 1);
    props.setGraphTickers(props.graphTickers);
    props.setGraphPrices(props.graphPrices);
    if(props.graphTickers.length === 0) {
      props.setGraphLabels([]);
    }
    props.setShowDeleteStockForm(false)
  }

  return (
    <form id="addStock-form" onSubmit={deleteStock}>
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
