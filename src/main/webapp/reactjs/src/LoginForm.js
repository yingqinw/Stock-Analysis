import React from 'react';
import './App.css';
import {Title, SelectedTitle, FormWrapper, Button, Arrow} from './Modals';

export default function(props) {
  return (
    <form id="login-form" onSubmit={props.handleSubmit}>
      <div className="selectWrapper">
        <SelectedTitle onClick={()=>{props.setSelectLogin(true)}}>Login</SelectedTitle>
        <Title onClick={()=>{props.setSelectLogin(false)}}>Signup</Title>
      </div>
      <div className="alertWrapper">
      	<p>{props.alertText}</p>
      </div>
      <FormWrapper>
        <div className="fields">
          <input type="text" placeholder="Username" onChange={(e) => {
            props.setUsername(e.target.value);
          }}
            style={{borderColor: props.validUserName ? 'green':'red'}}  
          />
          <input type="password" placeholder="Password" onChange={(e) => {
            props.setPassword(e.target.value);
          }}
            style={{borderColor: props.validPass ? 'green':'red'}}
          />
        </div>
        <Button>
          login
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
    </form> 
  );
}
