import React from 'react';
import './App.css';
import {FormWrapper, Button} from './Modals';


export default function(props) {
	const deleteStock = () => {
    //e.preventDefault();
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
    window.localStorage.setItem("graphTickers", JSON.stringify(props.graphTickers));
    window.localStorage.setItem("graphPrices", JSON.stringify(props.graphPrices));
    if(props.graphTickers.length === 0) {
      window.localStorage.setItem("graphLabels", JSON.stringify([]));
      props.setGraphLabels([]);
    }
    props.setShowRemoveConfirmForm(false)
  }
	
  return (
    <form id="addStock-form" onSubmit={(e)=>{e.preventDefault()}}>
      <FormWrapper>
        <div className="selectWrapper2">
          <p className ="deleteConTitle"> Do you want to remove ticker {props.ticker}?</p>
        </div>
        <Button style={{margin:20 }} onClick={()=>{          
          props.resetLogoutTimer()
		  deleteStock()
          //props.removeStocks(props.ticker);
          //props.fetchStockData('RemoveStock', props.ticker,props.startDate,props.endDate);
        }}>
          Remove Stock
        </Button>
        <Button style={{margin:20 }} onClick={()=>{
          props.setShowRemoveConfirmForm(false);
		  props.setAlertText("");
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