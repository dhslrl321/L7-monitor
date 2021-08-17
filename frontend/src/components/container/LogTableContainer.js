import React, { useEffect, useState } from 'react'

import LogTable from "components/presenter/LogTable/index";

import { fetchAllLog } from "service/LogService";
const LogTableContainer = () => {

  const [logDatas, setLogDatas] = useState([]);

  useEffect(async () => {
    const data = await fetchAllLog();
    setLogDatas(data);
  }, []);

  return <LogTable logDatas={logDatas} />;
}

export default LogTableContainer
