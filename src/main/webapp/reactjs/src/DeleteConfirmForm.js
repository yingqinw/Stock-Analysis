import React from 'react';
import './App.css';
import {FormWrapper, Button} from './Modals';


export default function(props) {
  return (
    <form id="addStock-form" onSubmit={(e)=>{e.preventDefault()}}>
      <FormWrapper>
        <div className="selectWrapper2">
          <p className ="deleteConTitle"> Do you want to delete ticker {props.ticker} ?</p>
        </div>
        <Button style={{margin:20 }} onClick={()=>{          
          props.resetLogoutTimer()
          props.removeStocks(props.ticker);
          props.fetchStockData('RemoveStock', props.ticker,props.startDate,props.endDate);
        }}>
          DELETE STOCK
        </Button>
        <Button style={{margin:20 }} onClick={()=>{
          props.setShowDeleteConfirmForm(false);
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
