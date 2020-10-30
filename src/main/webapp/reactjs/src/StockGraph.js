import React from "react";
import Highcharts from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";
import {useEffect, useState} from 'react';

export default function(props) {
	const [data, setData] = useState([]);
	useEffect(()=> {
		setData([]);
		const newData = [];
		props.graphTickers.forEach((ticker,i) => {
			newData.push({
				name: ticker,
				data: props.graphPrices[i]
			})
		})
		setData(newData);
	}, [props.graphTickers, props.graphPrices])

	const options = {
		title: {
			text: 'USC CS310 Stock Management Chart'
		},

		yAxis: {
			title: {
				text: 'Ticker prices in dollar'
			}
		},

		xAxis: {
			categories: props.graphLabels,
		},

		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle'
		},

		plotOptions: {
			series: {
				label: {
					connectorAllowed: false
				},
			}
		},

		series: data,

		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						layout: 'horizontal',
						align: 'center',
						verticalAlign: 'bottom'
					}
				}
			}]
		},

		rangeSelector: {
      selected: 1
    },
	}
		
	return (
		<div>
			<HighchartsReact
				constructorType={"stockChart"}
				highcharts={Highcharts}
				options={options}
			/>
		</div>
	)
};
