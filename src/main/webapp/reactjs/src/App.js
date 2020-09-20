import React from 'react';
import {useState} from 'react';
import './App.css';
import styled from 'styled-components';

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
const FormWrapper = styled.div`
  margin-top: 10%;
`;
const Button = styled.button`
  cursor: pointer;
  background-color: #3e3e3e;
  color: #FFF;
  padding: 10px 25px 11px;
  margin: 5px 15px 0 0;
  height: 45px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.2em;
  transition: background-color .3s;
  font-size: 0.8em;
  margin-top: 10%;
  &:hover, &:focus {
    background-color: #b1b1b1;
    > .arrow{
      margin: 0px 0 4px 18px;
    }
  }
`
const Arrow = styled.div`
  position: relative;
  display: inline-block;
  width: 20px;
  vertical-align: middle;
  color: #FFF;
  margin: 0px 0 4px 8px;
  transition: margin .3s;
  &:before,&:after {
    content: "";
    border-radius: 2px;
    position: absolute;
  },
  &:before {
    right: 0;
    top: 0;
    width: 20px;
    height: 2px;
    transition: width .3s;
    transform-origin: right top;
    box-shadow: inset 0 0 0 32px;
  }
  &:after {
    transform: rotate(45deg);
    top: -5px;
    right: 0;
    width: 10px;
    height: 10px;
    border-width: 2px 2px 0 0;
    border-style: solid;
  }
`;
const Title = styled.div`
  cursor: pointer;
  padding: 0 40px 10px 0;
  margin: 0 -4px 0 0;
  height: 30px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.2em;
  font-size: 0.8em;
  color: #3e3e3e;
  border-bottom: 5px solid #e2e2e2;
  transition: color .2s, border-bottom .2s;
  display: inline-block;
  transition: color .2s, border-bottom .2s;
`;
const SelectedTitle = styled(Title)`
  color: #3e3e3e;
  border-bottom: 5px solid #3e3e3e;
`;

function App() {
  const [selectLogin, setSelectLogin] = useState(true);
  console.log(selectLogin);
  return (
    <div className="App">
      <div className="App-header">
        <Wrapper>
          {
            selectLogin ? 
            <form id="login-form">
              <div className="selectWrapper">
                <SelectedTitle onClick={()=>{setSelectLogin(true)}}>Login</SelectedTitle>
                <Title onClick={()=>{setSelectLogin(false)}}>Signup</Title>
              </div>
              <FormWrapper>
                <div className="fields">
                  <input type="text" placeholder="Username"/>
                  <input type="password" placeholder="Password"/>
                </div>
                <Button>
                  login
                  <Arrow className="arrow"></Arrow>
                </Button>
              </FormWrapper>
            </form> : 
            <form id="signup-form">
              <div className="selectWrapper">
                <Title onClick={()=>{setSelectLogin(true)}}>Login</Title>
                <SelectedTitle onClick={()=>{setSelectLogin(false)}}>Signup</SelectedTitle>
              </div>
              <FormWrapper>
                <div className="fields">
                  <input type="text" placeholder="Username"/>
                  <input type="password" placeholder="Password"/>
                  <input type="password" placeholder="Retype Password"/>
                  <input type="email" placeholder="Email"/>
                </div>
                <Button>
                  signup
                  <Arrow className="arrow"></Arrow>
                </Button>
              </FormWrapper>
            </form>
          }
        </Wrapper>
      </div>
    </div>
  );
}

export default App;
