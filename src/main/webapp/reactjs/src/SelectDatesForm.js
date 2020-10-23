import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';


export default function(props) {

  return (
    <form id="addStock-form" onSubmit={(e) => {
      props.handleAddToGraph(e, "ChangeDateGraph");
    }}>
      <div className="selectWrapper2">
        <FormTitle>Select Dates</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowSelectDatesForm(false);
          props.setAlertText("");
        }}></i>
      </div>
      <FormWrapper>
        <div className="fields">
          <h4>Start Date</h4>
          <input type="date" placeholder="start date"
            onChange={(e) => {
              props.setStartDate(e.target.value);
            }}
            style={{borderColor: props.validStart? 'green':'red'}} 
          />
          <h4>End Date</h4>
          <input type="date" placeholder="end date"
            onChange={(e) => {
              props.setEndDate(e.target.value);
            }}
            style={{borderColor: props.validEnd? 'green':'red'}} 
          />
        </div>
        <Button onClick={()=>{props.resetLogoutTimer()}}>
          Confirm Dates
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{props.alertText}</p>
      </div>
	</form>
  );
}
