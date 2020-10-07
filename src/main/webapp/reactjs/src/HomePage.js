import React from 'react';
import './App.css';
import AddStockForm from './AddStockForm';
import {useEffect, useState, useRef} from 'react';
import { Navbar } from 'react-bootstrap';
import {Button, Arrow} from './Modals';
import createActivityDetector from 'activity-detector';

export const jsonToArray = (data) => {
  let result = []
  for (let key in data)
  {
    if (data.hasOwnProperty(key))
    {
      result.push({
        ticker: key,
        price: data[key]
      })
    }
  }
  return result;
}

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
  
  function useIdle(options){
	const [isIdle, setIsIdle] = React.useState(false)
	React.useEffect( () => {
		const activityDetector = createActivityDetector(options)
		activityDetector.on('idle', () => setIsIdle(true))
		activityDetector.on('active', () => setIsIdle(false))
		if(!isIdle){
			props.resetLogoutTimer()
		}
		return () => activityDetector.stop()
	})
  }
  

  useIdle({timeToIdle: 1000})

  const dateConverter = (date) => {
    const dateArr = date.split('-');
    return `${dateArr[1]}\\${dateArr[2]}\\${dateArr[0]}`;
  }

  const jsDateConverter = () => {
    const date = new Date();
    const year = date.getFullYear();
    let month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    let day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return month + '/' + day + '/' + year;
  }

  const fetchStockData = (route) => {
    if(props.loggedIn) {
      fetch(`http://localhost:8080/${route}?username=${props.username}&ticker=${ticker}&quantity=${quantity}&startdate=${dateConverter(startDate)}&enddate=${dateConverter(endDate)}`, {
        method: route === 'UpdatePrices'? 'POST': 'GET'
      })
      .then(response =>  response.json().then(data => {
        const error = data.AddStockerr;
        if(error) {
          setAlertText("");
          setAlertText(error);
        }
        else {
          props.setStocks(jsonToArray(data));
        }
      }))
    }
  }

  const useInterval = (callback, delay) => {
    const savedCallback = useRef();
  
    // Remember the latest callback.
    useEffect(() => {
      savedCallback.current = callback;
    }, [callback]);
  
    // Set up the interval.
    useEffect(() => {
      function tick() {
        savedCallback.current();
      }
      if (delay !== null) {
        let id = setInterval(tick, delay);
        return () => clearInterval(id);
      }
    }, [delay]);
  }

  useEffect(() => {
    setValidTicker(/^[A-Z]{1,}$/.test(ticker) && ticker.length >= 1 && ticker.length <= 5);
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
  useInterval(function(){fetchStockData('UpdatePrices')}, 60 * 1000);

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
    if(!validTicker) {
      alertMessage.push("Ticker should have at least 1 uppercase letters.");
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
    if(endDate.length === 0) {
      setEndDate(jsDateConverter());
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      fetchStockData('AddStock');
    }
  }
  
  const removeStocks = (removed) => {
    const newStocks = props.stocks.filter(stock => stock.ticker !== removed);
    props.setStocks(newStocks)
  }
  
  return (
    <div className="homepageWrapper">
      <Navbar bg="light" expand="lg" className="text-uppercase mb-3">
        <Navbar.Brand className="nav_brand" href="/">Stockanalysis</Navbar.Brand>
        <Navbar.Toggle className="justify-content-end" aria-controls="responsive-navbar-nav" />
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
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      {
                        props.stocks.map((stock, i) => {
                          return <tr key={i}>
                            <td>{stock.ticker}</td>
                            <td>{stock.price}</td>
                            <td><div className="" onClick={()=>{
                              setTicker(stock.ticker);
                              removeStocks(stock.ticker);
                              fetchStockData('RemoveStock');
                            }}>Delete</div></td>
                          </tr>
                        })
                      }
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
