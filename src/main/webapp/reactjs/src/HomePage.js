import React from 'react';
import './App.css';
import AddStockForm from './AddStockForm';
import AddStockToGraphForm from './AddStockToGraphForm';
import StockGraph from './StockGraph';
import DeleteConfirmForm from './DeleteConfirmForm';
import DeleteStockForm from './DeleteStockForm';
import SelectDatesForm from './SelectDatesForm';
import RemoveConfirmForm from './RemoveConfirmForm';
import {useEffect, useState} from 'react';
import { Navbar } from 'react-bootstrap';
import {Button, Arrow} from './Modals';
//import createActivityDetector from 'activity-detector';
import {useLocalStorage} from './App';
//import {deleteStock} from './DeleteStockForm';

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

export const jsonToArray2 = (data) => {
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

export const isEmpty = (data) => {
  return Object.keys(data).length === 0 && data.constructor === Object;
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
  const [showAddStockGraph, setShowAddStockGraph] = useState(false);
  const [showDeleteConfirmForm, setShowDeleteConfirmForm] = useState(false);
  const [showRemoveConfirmForm, setShowRemoveConfirmForm] = useState(false);
  const [showDeleteStockForm, setShowDeleteStockForm] = useState(false);
  const [graphTickers, setGraphTickers] = useLocalStorage([], "graphTickers");
  const [graphLabels, setGraphLabels] = useLocalStorage([], "graphLabels");
  const [graphPrices, setGraphPrices] = useLocalStorage([], "graphPrices");
  const [showSelectDatesForm, setShowSelectDatesForm] = useState(false);
  const [validBuy, setValidBuy] = useState(false);
  const [validSell, setValidSell] = useState(false);
  const [buyDate, setBuyDate] = useState("");
  const [sellDate, setSellDate] = useState("");

/*  function useIdle(options){
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
*/
//  useIdle({timeToIdle: 1000})

  const dateConverter = (date) => {
    if(date.indexOf('-') > -1) {
      const dateArr = date.split('-');
      return `${dateArr[1]}/${dateArr[2]}/${dateArr[0]}`;
    }
    else {
      return date;
    }
  }

  const jsDateConverter = (date) => {
    const year = date.getFullYear();
    let month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    let day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return month + '/' + day + '/' + year;
  }

  const fetchStockData = (route, removedTicker = null, startDateGraph=startDate.indexOf('-') > -1? dateConverter(startDate): startDate, endDateGraph=endDate.indexOf('-') > -1? dateConverter(endDate): endDate) => {
    if(props.loggedIn) {
      const realStartDateGraph = startDateGraph.indexOf('-') > -1? dateConverter(startDateGraph) : startDateGraph;
      const realEndDateGraph = endDateGraph.indexOf('-') > -1? dateConverter(endDateGraph) : endDateGraph;
      fetch(`http://localhost:8080/${route}?username=${props.username}&ticker=${removedTicker??ticker}&quantity=${quantity}&startdate=${dateConverter(buyDate)}&enddate=${dateConverter(sellDate)}&startdate_graph=${realStartDateGraph}&enddate_graph=${realEndDateGraph}`, {
        method: route === 'UpdatePrices'? 'POST': 'GET'
      })
      .then(response =>  response.json().then(data => {
		//console.log("here")
		//console.log(data)
        const error = data.AddStockerr;
        if(error) {
          setAlertText("");
          setAlertText(error);
        }
        else {
          if(route === 'AddStock') {
            setShowAddStockForm(false);
          }
          if(route === 'RemoveStock') {
            setShowDeleteConfirmForm(false);
          }
		  //console.log(data);
		  //console.log(jsonToArray2(data.update.map));
		  if(!isEmpty(data.update)){
			props.setStocks(jsonToArray2(data.update.map));
		  }else{
			console.log("empty")
		  }
		  
		   let removeIndex = -1;
  	       let newGraphPrices = graphPrices;
  	       let newGraphTickers = graphTickers;
  	       if(newGraphTickers.includes('portfolio')) {
      	   // find the removal index
       		newGraphTickers.forEach((item,i) => {
         		if(item === 'portfolio') {
           			removeIndex = i;
        		}
       		})
       			// replace with new array
       		newGraphPrices[removeIndex] = data.price.myArrayList;
     		}
     		else {
       			// push portfolio values to end of graph array
       			setGraphTickers(newGraphTickers.concat('portfolio'));
       			newGraphPrices.push(data.price.myArrayList)
       			setGraphPrices(newGraphPrices);
     		}
     		setGraphLabels(data.date.myArrayList);
          
        }
      }))
    }
  }

  const fetchGraphData = (route, ticker, startDateGraph=startDate.indexOf('-') > -1? dateConverter(startDate): startDate, endDateGraph=endDate.indexOf('-') > -1? dateConverter(endDate): endDate) => {
    if(props.loggedIn) {
      const tickerParameter = route === 'AddStockGraph'? 'ticker_graph': 'tickers_graph';
      fetch(`http://localhost:8080/${route}?${tickerParameter}=${ticker}&startdate_graph=${startDateGraph}&enddate_graph=${endDateGraph}&username=${props.username}`, {
        method: 'POST'
      })
      .then(response =>  response.json().then(data => {
        const error = data.AddStockerr;
        if(error) {
          setAlertText("");
          setAlertText(error);
        }
        else {
          if(route === 'AddStockGraph') {
            setShowAddStockGraph(false);
            if(!graphTickers.includes(ticker)){
              setGraphTickers(graphTickers.concat(ticker));
              var labelsGraph = (jsonToArray(data.date))[0].price; //I have no idea why the json is structured like this
              var pricesGraph = (jsonToArray(data.price))[0].price;
              setGraphLabels(labelsGraph);
              var tempArray = [pricesGraph];
              setGraphPrices(graphPrices.concat(tempArray));
            }
          }
          else {
            let tickerArray = [];
            let priceArray = [];
            const tickerValues = data.prices.map;
            for (let tickerName in tickerValues) {
              if (tickerValues.hasOwnProperty(tickerName)) {
                var pricesJson = tickerValues[tickerName];
                tickerArray.push(tickerName);
                priceArray.push(pricesJson.myArrayList);
              }
            }
            setGraphLabels(data.date.myArrayList);
            setGraphTickers(tickerArray);
            setGraphPrices(priceArray);
            setShowSelectDatesForm(false);
          }
        }
      }))
    }
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
    setValidEnd(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/.test(endDate) && endDate.localeCompare(startDate)===1);
  }, [endDate,startDate]);
  useEffect(() => {
    setValidBuy(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/.test(buyDate));
  }, [buyDate]);
  useEffect(() => {
    setValidSell(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/.test(sellDate) && sellDate.localeCompare(buyDate)===1);
  }, [sellDate,buyDate]);

  const handleAddToGraph = (e, route='AddStockGraph') => {
	  e.preventDefault();
    const alertMessage = [];
    if(!validTicker && route === 'AddStockGraph') {
      alertMessage.push("Ticker should have at least 1 uppercase letters.");
    }
    if(!validStart && route !== 'AddStockGraph') {
      alertMessage.push("Please enter a start date.")	
    }
    if(!validEnd && route !== 'AddStockGraph') {
      alertMessage.push("Please choose an end date that is after start date.")	
    }
	if(alertMessage.length === 0 && route !== 'AddStockGraph'){
		let sDate = new Date(startDate)
		let eDate = new Date(endDate)
		let sDay = sDate.getDay()
		let eDay = eDate.getDay()
		let sIsWeekend = (sDay === 6) || (sDay === 0)
		let eIsWeekend = (eDay === 6) || (eDay === 0)
		let msDay = 60*60*24*1000
		let isClose = ((Math.floor((eDate - sDate) / msDay) <= 3))
		if(sIsWeekend && eIsWeekend && isClose){
			alertMessage.push("Please do not choose two days from the same weekend.")
		}
	}
	
    if(endDate.length === 0) {
      setEndDate(jsDateConverter(new Date()));
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      let realGraphTickers = graphTickers;
      realGraphTickers = realGraphTickers.filter(ticker => ticker !== "portfolio");
      const tickerArray = realGraphTickers.map(ticker => `"${ticker}"`).join(',');
      const tickerString = route === 'AddStockGraph' ? ticker : "[" + tickerArray + "]";
      if(startDate.length === 0 && endDate.length === 0) {
        let sevenDaysAgo = new Date();
        sevenDaysAgo.setDate(sevenDaysAgo.getDate() - 90);
        fetchGraphData(route, tickerString, jsDateConverter(sevenDaysAgo), jsDateConverter(new Date()));
        setStartDate(jsDateConverter(sevenDaysAgo))
        setEndDate(jsDateConverter(new Date()))
      }
      else {
        fetchGraphData(route, tickerString);
      }
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const alertMessage = [];
    if(!validTicker) {
      alertMessage.push("Ticker should have at least 1 uppercase letters.");
    }
    if(!validQuantity) {
      alertMessage.push("Quantity should be a number greater than 0.");
    }
    if(!validBuy) {
      alertMessage.push("Please enter a start date.")	
    }
    if(!validSell){
      alertMessage.push("Please choose an end date that is after start date.")	
    }
    if(alertMessage.length !== 0) {
      alertMessage.join('\n');
    }
    if(sellDate.length === 0) {
      setSellDate(jsDateConverter(new Date()));
    }
    setAlertText(alertMessage);
    if(alertMessage.length === 0) {
      if(startDate.length === 0 && endDate.length === 0) {
        let thirtyDaysAgo = new Date();
        thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 90);
        fetchStockData('AddStock', null, jsDateConverter(thirtyDaysAgo), jsDateConverter(new Date()));
        setStartDate(jsDateConverter(thirtyDaysAgo))
        setEndDate(jsDateConverter(new Date()))
      }
      else {
        fetchStockData('AddStock');
      }
    }
  }
  
  const removeStocks = (removed) => {
    const newStocks = props.stocks.filter(stock => stock.ticker !== removed);
    props.setStocks(newStocks)
  }

  return (
    <div className="homepageWrapper">
      <Navbar expand="lg" className="text-uppercase mb-3">
        <Navbar.Brand className="nav_brand" style={{color: '#f0f3fa', fontSize: (window.innerWidth) === 414 ? 12.5 : 19}} href="/">USC CS310 Stock Portfolio Management</Navbar.Brand>
        <Navbar.Toggle className="justify-content-end" aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse className="justify-content-end" id="responsive-navbar-nav">
          <Navbar.Text>
              <Button className="my-auto" onClick={()=>{
                props.setLoggedIn(false);
                setGraphLabels([]);
                setGraphTickers([]);
                setGraphPrices([]);
                window.localStorage.clear();
              }}>
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
				  props.resetLogoutTimer();
                }}>Add stock</Button>
              </div>
              <div className="tab-content"> 
                <div className="tab-pane fade show active" id="BTC" role="tabpanel">
                  <table className="table">
                    <thead>
                      <tr>
                        <th>Tickers</th>
                        <th>Last Price</th>
                        <th>Action</th>
                        <th>Option</th>
                      </tr>
                    </thead>
                    <tbody>
                      {
                        props.stocks.map((stock, i) => {
                          return <tr key={i}>
                            <td>{stock.ticker}</td>
                            <td>{stock.price}</td>
                            <td><div className="" onClick={()=>{
							                props.resetLogoutTimer();
                              setTicker(stock.ticker);
                              setShowDeleteConfirmForm(true);
                            }}>Delete</div></td> 
                            <td><div className='custom-control custom-switch'>
                              <input
                                type='checkbox'
                                className='custom-control-input'
                                id={stock.ticker}
                                readOnly
                                defaultChecked
                              />
                              <label className='custom-control-label' htmlFor={stock.ticker} />
                            </div></td>
                          </tr>
                        })
                      }
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-9 market-pairs2">
            <StockGraph 
              graphTickers={graphTickers}
              graphLabels={graphLabels}
              graphPrices={graphPrices}
            />
            <div className="graphButton">
              <Button onClick={()=>{
                  props.resetLogoutTimer();
                  setShowAddStockGraph(true);
              }}>VIEW STOCK</Button>
              <Button onClick={()=>{
                props.resetLogoutTimer();
                setShowDeleteStockForm(true);
              }}>Remove Stock From Graph</Button>
              <Button onClick={()=>{
                props.resetLogoutTimer();
                setShowSelectDatesForm(true);
              }}>Select Dates</Button>
            </div>
          </div>
        </div>
      </div>
	  {showAddStockForm ? 
      <div className="addFormBackground">
        <div className="addFormWrapper px-3">
          <AddStockForm
            resetLogoutTimer={props.resetLogoutTimer}
            handleSubmit={handleSubmit}
            setTicker={setTicker}
            setQuantity={setQuantity}
            setStartDate={setBuyDate}
            setEndDate={setSellDate}
            alertText = {alertText}
            setAlertText={setAlertText}
            validTicker={validTicker}
            validStart={validBuy}
            validQuantity={validQuantity}
            validEnd={validSell}
            setShowAddStockForm={setShowAddStockForm}
          />
        </div>
      </div> : <></>}
	
	  {showAddStockGraph ? 
      <div className="addFormBackground">
        <div className="deleteFormWrapper px-3">
          <AddStockToGraphForm
            resetLogoutTimer={props.resetLogoutTimer}
            handleAddToGraph={handleAddToGraph}
            setTicker={setTicker}
            alertText = {alertText}
            setAlertText={setAlertText}
            validTicker={validTicker}
            setShowAddStockGraph={setShowAddStockGraph}
          />
        </div>
      </div> : <></>}

    {showDeleteConfirmForm ? 
      <div className="addFormBackground">
        <div className="deleteFormWrapper px-3">
          <DeleteConfirmForm
            ticker={ticker}
            removeStocks={removeStocks}
            fetchStockData={fetchStockData}
            resetLogoutTimer={props.resetLogoutTimer}
            setShowDeleteConfirmForm={setShowDeleteConfirmForm}
			startDate={startDate}
			endDate={endDate}
          />
        </div>
      </div> : <></>}

	{showRemoveConfirmForm ? 
      <div className="addFormBackground">
        <div className="deleteFormWrapper px-3">
          <RemoveConfirmForm
            ticker={ticker}
            fetchStockData={fetchStockData}
            resetLogoutTimer={props.resetLogoutTimer}
            setShowRemoveConfirmForm={setShowRemoveConfirmForm}
			setTicker={setTicker}
            graphTickers={graphTickers}
            setGraphTickers={setGraphTickers}
            setGraphLabels={setGraphLabels}
            graphPrices={graphPrices}
            setGraphPrices={setGraphPrices}
            alertText = {alertText}
            setAlertText={setAlertText}
          />
        </div>
      </div> : <></>}  

    {showDeleteStockForm ? 
      <div className="addFormBackground">
        <div className="deleteFormWrapper px-3">
          <DeleteStockForm
            ticker={ticker}
            setTicker={setTicker}
            graphTickers={graphTickers}
            setGraphTickers={setGraphTickers}
            setGraphLabels={setGraphLabels}
            graphPrices={graphPrices}
            setGraphPrices={setGraphPrices}
            setShowDeleteStockForm={setShowDeleteStockForm}
			setShowRemoveConfirmForm={setShowRemoveConfirmForm}
            alertText = {alertText}
            setAlertText={setAlertText}
            validTicker={validTicker}
            resetLogoutTimer={props.resetLogoutTimer}
          />
        </div>
      </div> : <></>}  

      {
        showSelectDatesForm ? 
        <div className="addFormBackground">
          <div className="addFormWrapper px-3">
            <SelectDatesForm
              setShowSelectDatesForm={setShowSelectDatesForm}
              setEndDate={setEndDate}
              setStartDate={setStartDate}
              alertText = {alertText}
              setAlertText={setAlertText}
              handleAddToGraph={handleAddToGraph}
              validStart={validStart}
              validEnd={validEnd}
              resetLogoutTimer={props.resetLogoutTimer}
            />
          </div>
        </div> : <></>
      }

    </div>
  );
}
