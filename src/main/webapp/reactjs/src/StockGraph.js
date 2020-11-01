import React from "react";
import Highcharts from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";

export default function(props) {

	return (
		<div>
			<HighchartsReact
				constructorType={"stockChart"}
				highcharts={Highcharts}
				options={props.options}
				oneToOne={true}
				allowChartUpdate={true}
				updateArgs={[true, true, true]}
			/>
		</div>
	)
};
