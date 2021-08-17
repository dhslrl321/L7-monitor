import React, { useState, useEffect } from 'react'

import UnknownLogTable from "components/presenter/UnknownLogTable";

import { fetchUnknownLog } from "service/LogService";

const UnknownLogTableContainer = () => {

  const [logDatas, setLogDatas] = useState([]);

  useEffect(async () => {
    const data = await fetchUnknownLog();

    setLogDatas(data);
  }, []);

  return <UnknownLogTable logDatas={logDatas} />
}

export default UnknownLogTableContainer
