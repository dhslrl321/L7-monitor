import React, { useState, useEffect } from 'react'
import Summary from 'components/presenter/Summary';
import { fetchSummary } from 'service/TrafficService';



const SummaryContainer = () => {

  const [summaryData, setSummaryData] = useState({
    totalTraffic: {},
    abnormalTraffic: {},
    securityLevel: {}
  });


  useEffect(async () => {

    const { totalTraffic, abnormalTraffic, securityLevel } = await fetchSummary();

    const { level, description, ratio } = securityLevel;

    const parsedSecurityLevel = {
      level,
      description,
      ratio: ratio.substr(0, ratio.length - 1) * 100 + "%"
    }

    setSummaryData({ totalTraffic, abnormalTraffic, securityLevel: parsedSecurityLevel })

  }, []);

  return <Summary summaryData={summaryData} />
}

export default SummaryContainer
