
import React, { useEffect, useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
//import GroupAdd from "@material-ui/icons/GroupAdd";
import InsertChartOutlined from "@material-ui/icons/InsertChartOutlined";

// core components
import CardStats from "components/Summary/CardStats.js";
import componentStyles from "assets/theme/components/header.js";



const useStyles = makeStyles(componentStyles);

const TotalTraffic = () => {
  const classes = useStyles();
  const theme = useTheme();

  const [totalTraffic, setTotalTraffic] = useState(0);
  const [timestamp, setTimestamp] = useState(0);
  

  useEffect(()=>{
    // 여기서 API 호출 일어남 --> 응답 데이터
    const count = 78291 // 응답값
    const time = "2021-08-12T15:59:36.946549"
    setTotalTraffic(count)
    setTimestamp(time)
  })

  return (
    <>
              <Grid item xl={4} lg={6} xs={12}>
                <CardStats
                  subtitle="오늘의 총 트래픽"
                  title= {totalTraffic}
                  icon={InsertChartOutlined}
                  color="bgError"
                  footer={
                    <>
                      <Box
                        component="span"
                        fontSize=".875rem"
                        color={theme.palette.success.main}
                        marginRight=".5rem"
                        display="flex"
                        alignItems="center"
                      >
                        <Box/>
                        {timestamp}
                      </Box>
                      <Box component="span" whiteSpace="nowrap">
                        Total traffic from 00:00 - 23:59
                      </Box>
                    </>
                  }
                />
              </Grid>
    </>
  );
};

export default TotalTraffic;
