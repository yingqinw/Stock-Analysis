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
        <Button onClick={()=>{          
          props.resetLogoutTimer()
          props.removeStocks(props.ticker);
          props.fetchStockData('RemoveStock', props.ticker);
        }}>
          Yes
        </Button>
        <Button onClick={()=>{
          props.setShowDeleteConfirmForm(false);
          props.resetLogoutTimer()}}>
          No
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{props.alertText}</p>
      </div>
	</form>
  );
}
