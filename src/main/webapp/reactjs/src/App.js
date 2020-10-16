import React from 'react';
import {useEffect, useState} from 'react';
import './App.css';
import styled from 'styled-components';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import HomePage from './HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import {jsonToArray} from './HomePage';

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

var logoutinterval;

export const useLocalStorage = (defaultValue, key) => {
  const [value, setValue] = React.useState(() => {
    const stickyValue = window.localStorage.getItem(key);
    return stickyValue !== null
      ? JSON.parse(stickyValue)
      : defaultValue;
  });
  React.useEffect(() => {
    window.localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);
  return [value, setValue];
}

export default function() {
  const [alertText, setAlertText] = useState("");
  const [selectLogin, setSelectLogin] = useState(true);
  const [loggedIn, setLoggedIn] = useLocalStorage(false, "loggedIn");
  const [username, setUsername] = useLocalStorage("", "username");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validPass, setValidPass] = useState(false);
  const [validUserName, setValidUserName] = useState(false);
  const [timer, setTimer] = useState(0);
  const [stocks, setStocks] = useState([]);
  
  const fetchStockData = (execute = false) => {
    if(loggedIn || execute) {
      fetch(`http://localhost:8080/UpdatePrices?username=${username}`, {
        method: 'POST'
      })
      .then(response =>  response.json().then(data => {
        setStocks(jsonToArray(data));
      }))
    }
  }

  window.onload = function() {
    fetchStockData();
  }

  useEffect(() => {
    setValidPass(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{1,20}$/.test(password));
  }, [password]);
  useEffect(() => {
    setValidUserName(/^[0-9a-zA-Z_.-]+$/.test(username) && username.length >= 5);
  }, [username]);

  useEffect(() => {
    if(timer<0){
      setLoggedIn(false)
      clearInterval(logoutinterval)
      setTimer(5)
    }
  }, [timer, setLoggedIn]);

  const timerProgress = () => {
	  setTimer(prevTimer => prevTimer - 1);
  }
  
  const resetLogoutTimer = () =>{
    setTimer(300);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
    if(!validUserName) {
      alertMessage.push("Username can only contain alphanumeric characters and longer than 5 characters. ");
    }
    if(!validPass) {
      alertMessage.push("Password should contain uppercase, lowercase and numeric character.\n");
    }
    if(alertMessage.length !== 0) {
      alertMessage.join('\n');
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      const route = selectLogin ? 'Login' : 'Register';
      fetch(`http://localhost:8080/${route}?username=${username}&password=${password}&confirmPassword=${confirmPassword}`, {
        method: 'POST'
      })
      .then(response =>  response.json().then(data => {
        const error = selectLogin ? data.loginerr: data.registererr;
        if(error) {
          setAlertText("");
          setAlertText(error);
          setLoggedIn(false);
        }
        else {
          setLoggedIn(true);
          setTimer(300);
		      clearInterval(logoutinterval);
          logoutinterval = setInterval(timerProgress,1000);
          fetchStockData(true);
        }
      }))
    }
  }

  return (
    <div className="App">
      <div className="App-header">
        {
          loggedIn ? 
            <HomePage 
              loggedIn={loggedIn}
              username={username}
              setLoggedIn={setLoggedIn}
              resetLogoutTimer={resetLogoutTimer}
              stocks={stocks}
              setStocks={setStocks}
            /> :
            <Wrapper>
              {selectLogin ? 
              <LoginForm 
                setSelectLogin={setSelectLogin}
                setUsername={setUsername}
                setPassword={setPassword}
                username={username}
                validUserName={validUserName}
                validPass={validPass}
                handleSubmit={handleSubmit}
                alertText = {alertText}
                setAlertText = {setAlertText} 
              /> : 
              <SignupForm
                setSelectLogin={setSelectLogin}
                setUsername={setUsername}
                setPassword={setPassword}
                setConfirmPassword={setConfirmPassword}
                validUserName={validUserName}
                validPass={validPass}
                password={password}
                handleSubmit={handleSubmit}
                alertText = {alertText}
                setAlertText = {setAlertText} 
              />}
            </Wrapper>
          }
      </div>
    </div>
  );
}
