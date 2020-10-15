import React from 'react';
import {Line} from 'react-chartjs-2';

var labels1 = ['label'];
var i;
for(i=0;i<4;i++){
	labels1.push('label')
}

export default function(props) {
	
	//labels: 
	
	const state = {
  	labels: ['10-7', '10-8', '10-9','10-10', '10-11'],
  	datasets: [
      {
      label: 'AAPL',
      fill: false,
      lineTension: 0,
      backgroundColor: 'rgba(75,192,192,1)',
      borderColor: 'rgba(75,192,192,1)',
      borderWidth: 2,
      data: [65, 59, 80, 81, 56]
      },
	  {
      label: 'IBM',
      fill: false,
      lineTension: 0,
      backgroundColor: 'rgba(75,35,37,1)',
      borderColor: 'rgba(75,35,37,1)',
      borderWidth: 2,
      data: [56, 23, 77, 51, 32]
      } 
  	]
	}
	
	return (
		<div>
        <Line
          data={state}
          options={{
            title:{
              display:true,
              text:'Stock Price',
              fontSize:20
            },
            legend:{
              display:true,
              position:'right'
            }
          }}
        />
      </div>
	)
}