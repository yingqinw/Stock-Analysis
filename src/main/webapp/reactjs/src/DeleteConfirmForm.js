import React from 'react';
import './App.css';
import {FormWrapper, Button} from './Modals';


export default function(props) {
  return (
    <form id="addStock-form" onSubmit={props.handleSubmit}>
      <div className="selectWrapper2">
        <p className ="deleteConTitle"> Are you sure?</p>
        
      </div>
      <FormWrapper>
        <Button onClick={()=>{
          props.setConfirmMsg(true);
          props.resetLogoutTimer()}}>
          Yes
        </Button>
        <Button onClick={()=>{
          props.setConfirmMsg(false);
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
