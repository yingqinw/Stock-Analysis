import React from 'react';
import './App.css';
import {FormTitle, FormWrapper, Button, Arrow} from './Modals';
import {useState} from 'react';

export default function(props) {

  const [alertText, setAlertText] = useState("");
  const [selectedFile, setSelectedFile] = useState(null);

  const onFileChange = (event) => { 
    // Update the selected file state 
    setSelectedFile(event.target.files[0]);
  }; 

  const onFileUpdate = (e) => {
    e.preventDefault();
    // Create an object of formData 
    const formData = new FormData(); 
    // Update the formData object 
    formData.append( 
      "portfolioFile", 
      selectedFile, 
      selectedFile.name 
    ); 
    // Details of the uploaded file 
    console.log(selectedFile);
    fetch("http://localhost:8080/GetForm", {
      method: 'POST',
        body: formData,
    })
    .then((response)=>{ 
      
    })
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
