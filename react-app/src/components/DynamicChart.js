import React from "react";
import { Line } from "react-chartjs-2";
import { CDBContainer } from "cdbreact";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

const DynamicChart = (props) => {
  const currency = props.currency;
  const offerType = props.offerType;
  const orderbooks = props.orderbooks;
  let offers;
  if (
    orderbooks !== undefined &&
    orderbooks[currency] !== undefined &&
    orderbooks[currency][offerType] !== undefined &&
    orderbooks[currency][offerType]["offers"] !== undefined
  ) {
    offers = orderbooks[currency][offerType]["offers"];
  }

  let data = {
    labels: [
      "01/20/1970 07:05:57:579",
      "01/20/1970 07:05:57:581",
      "01/20/1970 07:05:57:580",
    ],
    datasets: [
      {
        label: currency,
        fill: false,
        lineTension: 0.1,
        backgroundColor: "rgba(194, 116, 161, 0.5)",
        borderColor: "rgb(194, 116, 161)",
        borderCapStyle: "butt",
        borderDash: [],
        borderDashOffset: 0.0,
        borderJoinStyle: "miter",
        pointBorderColor: "rgba(75,192,192,1)",
        pointBackgroundColor: "#fff",
        pointBorderWidth: 1,
        pointHoverRadius: 5,
        pointHoverBackgroundColor: "rgba(71, 225, 167, 0.5)",
        pointHoverBorderColor: "rgb(71, 225, 167)",
        pointHoverBorderWidth: 2,
        pointRadius: 1,
        pointHitRadius: 10,
        data: [24055.7, 24055.0, 24053.8],
      },
    ],
  };
  if (offers !== undefined) {
    let timestamps = Object.keys(offers).sort();
    let values = timestamps.map((timastamp) => offers[timastamp].best);

    data = {
      labels: timestamps,
      datasets: [
        {
          label: currency,
          fill: false,
          lineTension: 0.1,
          backgroundColor: "rgba(194, 116, 161, 0.5)",
          borderColor: "rgb(194, 116, 161)",
          borderCapStyle: "butt",
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: "miter",
          pointBorderColor: "rgba(75,192,192,1)",
          pointBackgroundColor: "#fff",
          pointBorderWidth: 1,
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(71, 225, 167, 0.5)",
          pointHoverBorderColor: "rgb(71, 225, 167)",
          pointHoverBorderWidth: 2,
          pointRadius: 1,
          pointHitRadius: 10,
          data: values,
        },
      ],
    };
  }

  return (
    <CDBContainer>
      <h3 className="mt-5">
        Best {offerType} for {currency}.
      </h3>
      <Line data={data} options={{ responsive: true }} />
    </CDBContainer>
  );
};

export default DynamicChart;
