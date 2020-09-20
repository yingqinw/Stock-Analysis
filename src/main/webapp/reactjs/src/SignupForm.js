import React from 'react';
import {useEffect, useState} from 'react';
import './App.css';
import {Title, SelectedTitle, FormWrapper, Button, Arrow} from './Modals';

export default function(props) {
  return (
    <form id="signup-form">
      <div className="selectWrapper">
        <Title onClick={()=>{props.setSelectLogin(true)}}>Login</Title>
        <SelectedTitle onClick={()=>{props.setSelectLogin(false)}}>Signup</SelectedTitle>
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
          <input type="password" placeholder="Retype Password"
            style={{borderColor: props.validPass ? 'green':'red'}} 
          />
          <input type="email" placeholder="Email" onChange={(e) => {
            props.setEmail(e.target.value);
          }}/>
        </div>
        <Button>
          signup
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
    </form>
  );
}
