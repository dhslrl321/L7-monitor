import React, { useState, useEffect } from 'react'

import ThreatChart from "components/presenter/ThreatChart/index";

import { fetchThreatsCount } from "service/ThreatService";

const ThreatChartContainer = () => {

  const [threatData, setThreatData] = useState({
    sqli: { count: 0 },
    rfi: { count: 0 },
    wshell: { count: 0 },
    xss: { count: 0 }
  });

  useEffect(async () => {
    const data = await fetchThreatsCount();

    setThreatData(data);
  }, []);

  return <ThreatChart threatData={threatData} />
}

export default ThreatChartContainer
