import React, { useEffect, useState } from "react";
import logo from "./logo.svg";
import "./App.css";
// import Chart from "./components/chart";
import DynamicChart from "./components/DynamicChart";
const axios = require("axios");

const App = () => {
  const [orderbooks, setOrderbooks] = useState({});
  const [currency, setCurrency] = useState("XBT/USD");
  const [offerType, setOfferType] = useState("asks");
  const [loading, setLoading] = useState(false);

  let fetchOrderbook = () => {
    axios.get("api/").then((response) => {
      console.log(response.data);
      setOrderbooks(response.data);
      setLoading(false);
    });
  };

  useEffect(() => {
    setLoading(true);

    axios.get("api/book").then((response) => {
      console.log(response);
    });

    setInterval(function () {
      fetchOrderbook();
    }, 1);
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  let tableData = [];
  if (
    orderbooks[currency] !== undefined &&
    orderbooks[currency][offerType] !== undefined
  ) {
    const offers = orderbooks[currency][offerType]["offers"];

    tableData = Object.keys(offers).map((timestamp, index) => {
      let values = offers[timestamp].values
        .map((value) => value[0] + "\n" + value[1])
        .toString();
      if (values.length > 30) {
        values = values.substring(0, 30) + "...";
      }
      return (
        <tr>
          <th scope="row">{timestamp}</th>
          <td>{offers[timestamp].best}</td>
          <td>{values}</td>
        </tr>
      );
    });
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div class="menu">
          <select
            value={currency}
            onChange={(e) => {
              setCurrency(e.target.value);
            }}
            class="form-select"
            aria-label="Select crypto currency"
          >
            <option selected>Select crypto currency</option>
            <option value="XBT/USD">XBT/USD</option>
            <option value="ETH/USD">ETH/USD</option>
          </select>
          <select
            value={offerType}
            onChange={(e) => {
              setOfferType(e.target.value);
            }}
            class="form-select"
            aria-label="Select offer type"
          >
            <option selected>Select offer type</option>
            <option value="asks">asks</option>
            <option value="bids">bids</option>
          </select>
          <button
            type="button"
            class="btn btn-primary"
            onClick={fetchOrderbook}
          >
            Refresh
          </button>
        </div>

        <div className="App-intro">
          <h2>Order Book</h2>
          {/* {groups.map((group) => (
            <div>{group}</div>
          ))} */}
        </div>
        <div>
          <div class="bookTable">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Date</th>
                  <th scope="col">Best</th>
                  <th scope="col">Values</th>
                </tr>
              </thead>
              <tbody>{tableData}</tbody>
            </table>
          </div>

          <DynamicChart
            currency={currency}
            offerType={offerType}
            orderbooks={orderbooks}
          />
        </div>
      </header>
    </div>
  );
};

export default App;
