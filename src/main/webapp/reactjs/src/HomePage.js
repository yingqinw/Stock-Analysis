import React from 'react';
import './App.css';
import AddStockForm from './AddStockForm';
import {useEffect, useState} from 'react';
import { Navbar } from 'react-bootstrap';
import {Button, Arrow} from './Modals';

export default function(props) {
  const [alertText, setAlertText] = useState("");	
  const [validTicker, setValidTicker] = useState(false);
  const [validQuantity, setValidQuantity] = useState(false);
  const [validStart, setValidStart] = useState(false);
  const [validEnd, setValidEnd] = useState(true);
  const [ticker, setTicker] = useState("");
  const [quantity, setQuantity] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [showAddStockForm, setShowAddStockForm] = useState(false);
  
  useEffect(() => {
    setValidTicker(/^[A-Z]{1,5}$/.test(ticker) && ticker.length >= 1 && ticker.length <= 5);
  }, [ticker]);
  useEffect(() => {
    setValidQuantity(/^[0-9]{1,}$/.test(quantity) && quantity !== "0");
  }, [quantity]);
  useEffect(() => {
    setValidStart(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/.test(startDate));
  }, [startDate]);
  useEffect(() => {
    setValidEnd(endDate.localeCompare(startDate)===1 || !endDate.includes("-"));
  }, [endDate,startDate]);

  const dateConverter = (date) => {
    const dateArr = date.split('-');
    return `${dateArr[1]}\\${dateArr[2]}\\${dateArr[0]}`;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
    if(!validTicker) {
      alertMessage.push("Ticker should have 1 to 5 uppercase letters.");
    }
    if(!validQuantity) {
      alertMessage.push("Quantity should be a number greater than 0.");
    }
    if(!validStart) {
      alertMessage.push("Please enter a start date.")	
    }
    if(!validEnd){
      alertMessage.push("Please choose an end date that is after start date.")	
    }
    if(alertMessage.length !== 0) {
      alertMessage.join('\n');
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      const route = 'AddStock';
      fetch(`http://localhost:8080/${route}?username=${props.username}&ticker=${ticker}&quantity=${quantity}&startdate=${dateConverter(startDate)}&enddate=${dateConverter(endDate)}`, {
        method: 'POST'
      })
      .then(response =>  response.json().then(data => {
        const error = data.addstockerr;
        if(error) {
          setAlertText("");
          setAlertText(error);
        }
        else {
          
        }
      }))
    }
  }

	
  return (
    <div className="homepageWrapper">
      <Navbar bg="light" expand="lg" className="text-uppercase mb-3">
        <Navbar.Brand className="nav_brand" href="/">Stockanalysis</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse className="justify-content-end" id="responsive-navbar-nav">
          <Navbar.Text>
              <Button className="my-auto" onClick={()=>{props.setLoggedIn(false)}}>
                log out
              <Arrow className="arrow"></Arrow>
            </Button>
          </Navbar.Text>
        </Navbar.Collapse>
      </Navbar>
      <div className="container-fluid no-fluid">
        <div className="row sm-gutters px-2">
          <div className="col-md-3">
            <div className="market-pairs">
              <div className="header-wrap">
                <Button className="my-auto" onClick={()=>{
                  setShowAddStockForm(true)
                }}>Add stock</Button>
              </div>
              <div className="tab-content"> 
                <div className="tab-pane fade show active" id="BTC" role="tabpanel">
                  <table className="table">
                    <thead>
                      <tr>
                        <th>Pairs</th>
                        <th>Last Price</th>
                        <th>Change</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td> AAPL</td>
                        <td>116.98</td>
                        <td className="red">-2.58%</td>
                      </tr>
                      <tr>
                        <td> AMZN</td>
                        <td>3,195.55</td>
                        <td className="green">+5.6%</td>
                      </tr> 
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-9">
            Insert Graph Here
          </div>
        </div>
      </div>
	  {showAddStockForm ? 
    <div className="addFormBackground">
      <div className = "addFormWrapper px-3">
        <AddStockForm
          resetLogoutTimer={props.resetLogoutTimer}
          handleSubmit={handleSubmit}
          setTicker={setTicker}
          setQuantity={setQuantity}
          setStartDate={setStartDate}
          setEndDate={setEndDate}
          alertText = {alertText}
          setAlertText={setAlertText}
          validTicker={validTicker}
          validStart={validStart}
          validQuantity={validQuantity}
          validEnd={validEnd}
          setShowAddStockForm={setShowAddStockForm}
        />
    </div>
      </div> : <></>}
    </div>
	
  );
}
