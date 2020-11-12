import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';
import {useState} from 'react';
import {jsonToArray2} from './HomePage';
import {isEmpty} from './HomePage';

export default function(props) {
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

  const [alertText, setAlertText] = useState("");
  const [selectedFile, setSelectedFile] = useState(null);

  const onFileChange = (event) => { 
    // Update the selected file state 
    setSelectedFile(event.target.files[0]);
  }; 

  const onFileUpdate = (e) => {
	
    e.preventDefault();
    // prevent null file
    if(selectedFile === null) {
      setAlertText("");
      setAlertText("Please select a file to upload");
      return;
    }

    //set default date
    let sDate = props.startDate;
    let eDate = props.endDate;
    if(sDate.indexOf('-') <= -1){
      console.log("date empty");
      let sevenDaysAgo = new Date();
      sevenDaysAgo.setDate(sevenDaysAgo.getDate() - 90);
      sDate = jsDateConverter(sevenDaysAgo);
      eDate = jsDateConverter(new Date());
      props.setStartDate(jsDateConverter(sevenDaysAgo))
      props.setEndDate(jsDateConverter(new Date()))
    }

    // Create an object of formData 
    const formData = new FormData(); 
    // Update the formData object 
    formData.append(
      "username",
      props.username
    )
    formData.append(
      "startDate",
      dateConverter(sDate)
    )
    formData.append(
      "endDate",
      dateConverter(eDate)
    )

    formData.append( 
      "portfolioFile", 
      selectedFile, 
      selectedFile.name,
    ); 

	
    // Details of the uploaded file 
    console.log(selectedFile);
    fetch("http://localhost:8080/GetForm", {
      method: 'POST',
        body: formData,
    })
    .then(response=>response.json().then(data => { 
	  console.log(data);
      const error = data.AddStockerr;
        if(error) {
          setAlertText("");
          setAlertText(error);
        }else{
			//setshowupload form to false
			setAlertText("");
			props.setShowUploadFileForm(false);
			
			if(!isEmpty(data.update)){
			props.setStocks(jsonToArray2(data.update.map));
		  }else{
			console.log("empty")
		  }
		  
		   let removeIndex = -1;
  	       let newGraphPrices = props.graphPrices;
  	       let newGraphTickers = props.graphTickers;
  	       if(newGraphTickers.includes('portfolio')) {
      	   // find the removal index
       		newGraphTickers.forEach((item,i) => {
         		if(item === 'portfolio') {
           			removeIndex = i;
        		}
       		})
       			// replace with new array
           newGraphPrices[removeIndex] = data.price.myArrayList;
           props.setGraphPrices(newGraphPrices);
     		}
     		else {
       			// push portfolio values to end of graph array
       			props.setGraphTickers(newGraphTickers.concat('portfolio'));
       			newGraphPrices.push(data.price.myArrayList)
       			props.setGraphPrices(newGraphPrices);
     		}
     		props.setGraphLabels(data.date.myArrayList);
        props.setCurrentPortfolioValue(parseFloat(data.currentPortfolioValue));
        props.setPrevPortfolioPercentage(parseFloat(data.prevPortfolioValue));
		}
		
		
    }))
  }

  return (
    <form id="addStock-form" onSubmit={(e) => {
      onFileUpdate(e);
    }}>
      <div className="selectWrapper2">
        <FormTitle width={100}>Upload file</FormTitle>
        <i className="fa fa-times closeIcon" onClick={()=>{
          props.setShowUploadFileForm(false);
          setAlertText("");
          props.resetLogoutTimer();
        }}></i>
      </div>
      <FormWrapper>
        <div className="fields">
          <h4>Upload File Here</h4>
          <input className="highInput" type="file" placeholder="upload a csv file"
            onChange={onFileChange}
            style={{borderColor: props.validStart? 'green':'red'}} 
          />
        </div>
        <Button onClick={()=>{
          props.resetLogoutTimer()
        }}>
          Upload File
          <Arrow className="arrow"></Arrow>
        </Button>
        <Button onClick={()=>{
          props.setShowUploadFileForm(false);
          props.resetLogoutTimer();
        }}>
          Cancel
          <Arrow className="arrow"></Arrow>
        </Button>
      </FormWrapper>
      <div className="alertWrapper">
        <p>{alertText}</p>
      </div>
	</form>
  );
}
