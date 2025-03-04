import React, { useState } from "react";
import "./ElectricLeaks.css";
import logo from '../assets/Logo.png'



import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from "recharts";

const months = [
  "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
];


function ElectricLeaks() {
  const [policyNumber, setPolicyNumber] = useState("");
  const [selectedMonth, setSelectedMonth] = useState("");
  const [customerName, setCustomerName] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [discount, setDiscount] = useState("");
  const [chartData, setChartData] = useState([]);
  const [dataFetched, setDataFetched] = useState(false);

  const handleGetReadings = async () => {
    if (!policyNumber || !selectedMonth) {
      alert("Please enter policy number and select a month");
      return;
    }

    try {
      const monthIndex = months.indexOf(selectedMonth) + 1;
      const response = await fetch(`http://localhost:8080/api/policies/${policyNumber}/readings?month=${monthIndex}`);
      const data = await response.json();

      setCustomerName(data.policy.customerName);
      setStartDate(data.policy.startDate);
      setEndDate(data.policy.endDate);
      setDiscount(data.policy.eligibleForDiscount ? "Yes" : "No");

      const formattedData = data.readings.map((reading) => ({
        date: new Date(reading.timestamp).getDate(),
        voltage: reading.value,
      }));
      setChartData(formattedData);
      setDataFetched(true);
    } catch (error) {
      console.error("Error fetching data:", error);
      alert("Failed to fetch data");
    }
  };

  const handleToggleDiscount = () => {
    setDiscount(prevDiscount => prevDiscount === "Yes" ? "No" : "Yes");
  };

  return (
  <div   className="container">
    Policy Number:&nbsp;
    <input type="text" value={policyNumber} onChange={(e) => setPolicyNumber(e.target.value)} />

    <p />
    Month:&nbsp;
    <select value={selectedMonth} onChange={(e) => setSelectedMonth(e.target.value)}>
      <option value="">Select Month</option>
      {months.map((month, index) => (
        <option key={index} value={month}>{month}</option>
      ))}
    </select>

    <p />
    <button onClick={handleGetReadings}>Get Readings</button>

    {dataFetched && (
      <>
        <p><span style={{ fontWeight: "bold" }}>Customer Name:</span> {customerName}</p>
        <p><span style={{ fontWeight: "bold" }}>Policy Start Date:</span> {startDate}</p>
        <p><span style={{ fontWeight: "bold" }}>Policy End Date:</span> {endDate}</p>
        <p><span style={{ fontWeight: "bold" }}>Eligible for Discount:</span> {discount}{" "}
        </p><p>
          <button onClick={handleToggleDiscount}>Update Elligibility</button> {/* Toggle button */}
        </p>

        {/* Ensure the chart takes full width */}
        <ResponsiveContainer className="chart-container" width="100%" height={300}>
          <LineChart className="chart-container" data={chartData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" label={{ value: "Date", position: "insideBottomRight", offset: -5 }} />
            <YAxis label={{ value: "Voltage", angle: -90, position: "insideLeft" }} />
            <Tooltip contentStyle={{ backgroundColor: "#444", color: "#fff" }} />
            <Legend />
            <Line type="monotone" dataKey="voltage" stroke="#8884d8" strokeWidth={1} />
          </LineChart>
        </ResponsiveContainer>
      </>
    )}
  </div>
);

}

export default ElectricLeaks;