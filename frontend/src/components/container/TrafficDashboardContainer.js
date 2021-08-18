import React, { useState, useEffect } from 'react'

import TrafficDashboard from "components/presenter/TrafficDashboard/index";

import { fetchRealtimeTrafficsFiveMinutes, fetchRealtimeTrafficsDays, fetchRealtimeTrafficsWeeks } from "service/TrafficService";

const TrafficDashboardContainer = () => {

  const [activeNav, setActiveNav] = useState(1);
  const [chartResponseData, setChartResponseData] = useState([]);
  const [chartData, setChartData] = useState({});

  const toggleNav = (index) => {
    setActiveNav(index);

    const arrIndex = index - 1;

    const array = chartResponseData[arrIndex];

    let labels = [];
    let datas = [];

    array.map(iter => {
      const { timestamp, count } = iter;
      if (arrIndex === 0 || arrIndex === 1) {
        const parsedTimestamp = timestamp.substring(0, 10);
        labels.push(parsedTimestamp);
      } else {
        const parsedTimestamp = timestamp.substring(11, 19);
        labels.push(parsedTimestamp);
      }

      datas.push(count);
    });

    setChartData({
      labels: labels.reverse(),
      datasets: [{ data: datas.reverse() }],
    });
  };

  useEffect(async () => {
    const fiveMinutes = await fetchRealtimeTrafficsFiveMinutes();
    const day = await fetchRealtimeTrafficsDays();
    const week = await fetchRealtimeTrafficsWeeks();

    setChartResponseData([
      week,
      day,
      fiveMinutes,
    ]);

    let labels = [];
    let datas = [];

    week.map(iter => {
      const { timestamp, count } = iter;
      const parsedTimestamp = timestamp.substring(0, 10);
      labels.push(parsedTimestamp);
      datas.push(count);
    });

    setChartData({
      labels: labels.reverse(),
      datasets: [{ data: datas.reverse() }],
    });
  }, []);

  return <TrafficDashboard activeNav={activeNav} chartData={chartData} toggleNav={toggleNav} />
}

export default TrafficDashboardContainer
