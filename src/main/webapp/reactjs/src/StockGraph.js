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
			verticalAlign: 'middle',
			enabled: true,
		},

		plotOptions: {
			series: {
				label: {
					connectorAllowed: false
				},
			}
		},
		
		rangeSelector: {
			allButtonsEnabled: true,
			buttons: [{
				type: 'month',
				count: 1,
				text: '1m'
			}, {
					type: 'month',
					count: 3,
					text: '3m'
			}, {
					type: 'month',
					count: 6,
					text: '6m'
			}, {
					type: 'ytd',
					text: 'YTD'
			}, {
					type: 'year',
					count: 1,
					text: '1y'
			}, {
					type: 'all',
					text: 'All'
			}],
			buttonTheme: {
					width: 60
			},
			selected: 5,
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
					},
					rangeSelector: {
						inputEnabled: false
					}
				}
			}]
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
