import React from 'react';
import {useEffect, useState} from 'react';
import './App.css';
import styled from 'styled-components';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';

const Wrapper = styled.div`
  font-family: 'Open Sans', sans-serif;
  width: 400px;
  height: 490px;
  margin: auto;
  padding: 80px;
  position: relative;
  border: 1px solid #e2e2e2;
  background: #FFF;
  z-index: 1;
  box-shadow: -10px 10px 0;
  box-sizing: border-box;
`;

export default function() {
  const [selectLogin, setSelectLogin] = useState(true);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [validPass, setValidPass] = useState(false);
  const [validUserName, setValidUserName] = useState(false);
  
  useEffect(() => {
  
  }, [password]);

  return (
    <div className="App">
      <div className="App-header">
        <Wrapper>
          {
            selectLogin ? 
            <LoginForm 
              setSelectLogin={setSelectLogin}
              setUsername={setUsername}
              setPassword={setPassword}
              validUserName={validUserName}
            /> : 
            <SignupForm
              setSelectLogin={setSelectLogin}
              setUsername={setUsername}
              setPassword={setPassword}
              setEmail={setEmail}
              validUserName={validUserName}
              validPass={validPass}
            />
          }
        </Wrapper>
      </div>
    </div>
  );
}
