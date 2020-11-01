import React from "react";
import Highcharts from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";
import {useEffect, useState} from 'react';

export default function(props) {
	const [options, setOptions] = useState({});

	useEffect(()=> {
		let newOptions = {
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
	
			series: props.graphTickers.map((ticker,i) => {
				return {
					name: ticker,
					data: props.graphPrices[i]
				}
			}),
	
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
		setOptions(newOptions);
		console.log('updated')
		console.log(props.graphPrices)
	}, [props.graphTickers, props.graphPrices, props.graphLabels])
	// console.log('here',props.graphPrices)
		
	return (
		<div>
			<HighchartsReact
				constructorType={"stockChart"}
				highcharts={Highcharts}
				options={options}
				oneToOne={true}
				allowChartUpdate={true}
				updateArgs={[true, true, true]}
			/>
		</div>
	)
};
