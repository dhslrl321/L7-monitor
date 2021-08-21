import React, {useState, useEffect} from 'react'
import Summary from 'components/presenter/Summary';
import { fetchSummary } from 'service/TrafficService';



const SummaryContainer = () => {

    const [summaryData, setSummaryData] = useState({
      totalTraffic : {},
      abnormalTraffic : {},
      securityLevel : {}
    });

  
    useEffect(async () => {
			
			const { totalTraffic, abnormalTraffic, securityLevel }= await fetchSummary();
      
      setSummaryData({totalTraffic, abnormalTraffic, securityLevel})

    },[]);
  
    return <Summary summaryData = {summaryData} />
  }
  
  export default SummaryContainer
  