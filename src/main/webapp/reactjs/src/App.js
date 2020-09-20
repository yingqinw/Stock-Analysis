import React from 'react';
import {useState} from 'react';
import './App.css';
import styled from 'styled-components';
import {ButtonGroup} from 'react-bootstrap';

const Wrapper = styled.div`
  width: 40%;
  background-color: white;
  padding: 2%;
  box-shadow:5px 10px black;
`;
const Row = styled.div`
  font-family: 'Roboto', sans-serif;
  color: black;
  margin-top: 2%;
`;
const Button = styled.button`
  margin-top: 10%;
  font-family: 'PT Sans', sans-serif;
  background-color: black;
  color: white;
  font-weight: bold;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  border: none;
  padding: 3% 5% 3% 5%;
  font-size: 70%;
  position: relative;
  width: 40%;
`
const Arrow = styled.div`
  border: solid white;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  position: absolute;
  right: 10%;
  top: calc(50% - 6px);
  font-size: 80%;
`

function App() {
  const [login, setLogin] = useState(true);
  return (
    <div className="App-header">
        <Wrapper Wrapper>
        <ButtonGroup toggle={true}>
          <Button variant="secondary" onClick={()=>{setLogin(true)}}>Log in</Button>
          <Button variant="secondary"onClick={()=>{setLogin(false)}}>Sign up</Button>
        </ButtonGroup>
        {
          login ? <>
          <Row>Username</Row>
          <Row>Password</Row>
          <Button>
            login
            <Arrow></Arrow>
          </Button></> :
          <><Row>Username</Row>
          <Row>Password</Row>
          <Row>Retype Password</Row>
          <Row>Email</Row>
          <Button>
            login
            <Arrow></Arrow>
          </Button></>
        }
        </Wrapper>
    </div>
  );
}

export default App;
