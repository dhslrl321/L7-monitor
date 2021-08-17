import React, { useState, useEffect } from 'react'

import TrafficDashboard from "components/presenter/TrafficDashboard/index";

import { fetchRealtimeTrafficsFiveMinutes, fetchRealtimeTrafficsDays, fetchRealtimeTrafficsWeeks } from "service/TrafficService";

const TrafficDashboardContainer = () => {

  const [activeNav, setActiveNav] = useState(1);
  const [chartResponseData, setChartResponseData] = useState({});
  const [chartData, setChartData] = useState();

  const toggleNav = (index) => {
    setActiveNav(index);

    if (index === 1) {

    }
  }

  useEffect(async () => {
    const fiveMinutesData = await fetchRealtimeTrafficsFiveMinutes();
    const dayData = await fetchRealtimeTrafficsDays();
    const weekData = await fetchRealtimeTrafficsWeeks();

    setChartResponseData({
      fiveMinutesData,
      dayData,
      weekData
    });
  }, [])

  return <TrafficDashboard activeNav={activeNav} chartData={chartData} toggleNav={toggleNav} />
}

export default TrafficDashboardContainer

const chartData = {
  data1: () => {
    return {
      labels: ["00", "01", "02", "03", "04", "05", "07"],
      datasets: [
        {
          data: [5210, 4120, 6614, 3381, 8115, 6721, 5344],
        },
      ],
    };
  },


  data2: () => {
    return {
      labels: ["Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"],
      datasets: [
        {
          data: [611, 210, 512, 150, 281, 270, 56],
        },
      ],
    };
  },

  data3: () => {
    return {
      labels: ["Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"],
      datasets: [
        {
          data: [12, 16, 21, 13, 23, 35, 16],
        },
      ],
    }
  }
}