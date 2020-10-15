import React from 'react';
import {Line} from 'react-chartjs-2';

var colors = ['rgba(75,72,73,1)','rgba(200,72,73,1)','rgba(75,200,73,1)','rgba(75,72,200,1)','rgba(100,72,73,1)','rgba(75,100,73,1)','rgba(75,72,100,1)']

export default function(props) {
	/*
	var datasetexample = [
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
     */

	var dataset1 = [];
	var i;
	for(i = 0; i<props.graphTickers.length;i++){
		var data1 = [];
		var j;
		for(j=0; j<props.graphLabels.length;j++){
			data1.push(props.graphPrices[i*props.graphLabels.length + j]);
		}
		var color = colors[i%7];
		dataset1.push({
			label: props.graphTickers[i],
			fill: false,
			lineTension: 0,
			backgroundColor: color,
			borderColor: color,
			borderWidth: 2,
			data: data1
		})
	}
	
	
	const state = {
  	//labels: ['10-7', '10-8', '10-9','10-10', '10-11'],
	labels: props.graphLabels,
	datasets: dataset1
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