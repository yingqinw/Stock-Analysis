import React from 'react';
import {useEffect, useState} from 'react';
import './App.css';
import styled from 'styled-components';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import HomePage from './HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import {jsonToArray, jsDateConverter} from './HomePage';

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
var lockoutinterval;

export function useLocalStorage(initialValue, key) {
  // State to store our value
  // Pass initial state function to useState so logic is only executed once
  const [storedValue, setStoredValue] = useState(() => {
      try {
          // Get from local storage by key
          const item = window.localStorage.getItem(key);
          // Parse stored json or if none return initialValue
          return item ? JSON.parse(item) : initialValue;
      } catch (error) {
          // If error also return initialValue
          console.log(error);
          return initialValue;
      }
  });

  // Return a wrapped version of useState's setter function that ...
  // ... persists the new value to localStorage.
  const setValue = (value) => {
      try {
          // Allow value to be a function so we have same API as useState
          const valueToStore =
              value instanceof Function ? value(storedValue) : value;
          // Save state
          setStoredValue(valueToStore);
          // Save to local storage
          window.localStorage.setItem(key, JSON.stringify(valueToStore));
      } catch (error) {
          console.log(error);
      }
  };

  return [storedValue, setValue];
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
  const [unSelectedTickers, setUnSelectedTickers] = useLocalStorage([], "unSelectedTickers");
  const [portfolioDates, setPortfolioDates] = useState([]);
  const [portfolioPrices, setPortfolioPrices] = useState([]);
  const [spyPrices, setSpyPrices] = useState([]);
  const [loginLock, setLoginLock] = useState(false);
  const [loginLockTimer, setLoginLockTimer] = useState(0);
  const [portfolioValue, setPortfolioValue] = useState(0);
  const [prevPortfolioValue, setPrevPortfolioValue] = useState(0);
  
  const fetchStockData = (execute = false) => {
    if(loggedIn || execute) {
      let threeMonthsAgo = new Date();
      threeMonthsAgo.setDate(threeMonthsAgo.getDate() - 90);
      fetch(`http://localhost:8080/UpdatePrices?username=${username}&startdate_graph=${jsDateConverter(threeMonthsAgo)}&enddate_graph=${jsDateConverter(new Date())}`, {
        method: 'POST'
      })
      .then(response =>  response.json().then(data => {
        let dates = data.date.myArrayList;
        let prices = data.price.myArrayList;
        let spyPrices = data.SPV.myArrayList;
        setSpyPrices(spyPrices);
        setPortfolioDates(dates);
        setPortfolioPrices(prices);
        setStocks(jsonToArray(data.update.map));
        setPortfolioValue(parseFloat(data.currentPortfolioValue));
        setPrevPortfolioValue(parseFloat(data.prevPortfolioValue));
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
      setTimer(300)
      window.localStorage.clear();
    }
	//console.log(timer);
  }, [timer, setLoggedIn]);

  useEffect(() => {
    if(loginLockTimer<0){
      setLoginLock(false);
	  clearInterval(lockoutinterval);
      setLoginLockTimer(300);
    }
	console.log(loginLockTimer);
  }, [loginLockTimer, setLoginLock]);



  const timerProgress = () => {
	  setTimer(prevTimer => prevTimer - 1);
  }

  const lockTimerProgress = () =>{
	setLoginLockTimer(prevLoginLockTimer => prevLoginLockTimer - 1);
  }
  
  const resetLogoutTimer = () =>{
    setTimer(120);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
	if(loginLock){
	  alertMessage.push("You are locked out due to 3 failed attempts, please wait " + loginLockTimer + " more seconds");
	}
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
		  if(error.includes("locked")){
			setLoginLock(true);
			setLoginLockTimer(180);
			clearInterval(lockoutinterval);
            lockoutinterval = setInterval(lockTimerProgress,1000);
		  }
        }
        else {
          setLoggedIn(true);
		  resetLogoutTimer();
          //setTimer(300);
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
              spyPrices={spyPrices}
              stocks={stocks}
              setStocks={setStocks}
              portfolioValue={portfolioValue}
              unSelectedTickers={unSelectedTickers}
              setUnSelectedTickers={setUnSelectedTickers}
              portfolioDates={portfolioDates}
              portfolioPrices={portfolioPrices}
              prevPortfolioValue={prevPortfolioValue}
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
