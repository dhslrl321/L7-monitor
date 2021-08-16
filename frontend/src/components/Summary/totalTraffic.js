
import React, { useEffect, useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
// @material-ui/icons components
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import EmojiEvents from "@material-ui/icons/EmojiEvents";
//import GroupAdd from "@material-ui/icons/GroupAdd";
import InsertChartOutlined from "@material-ui/icons/InsertChartOutlined";
import PieChart from "@material-ui/icons/PieChart";

// core components
import CardStats from "components/Summary/CardStats.js";
import componentStyles from "assets/theme/components/header.js";



const useStyles = makeStyles(componentStyles);

const DrawTotalTraffic = () => {
  const classes = useStyles();
  const theme = useTheme();

  const [totalTraffic, setTotalTraffic] = useState(0);
  
  const handleOnClick = () => {
    setTotalTraffic(totalTraffic+1) 
  }

  useEffect(()=>{
    // 여기서 API 호출 일어남 --> 응답 데이터
    const response = 100 // 응답값
    setTotalTraffic(response)
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
                        <Box
                          component={ArrowUpward}
                          width="1.5rem!important"
                          height="1.5rem!important"
                        />{" "}
                        3.48%
                      </Box>
                      <Box component="span" whiteSpace="nowrap">
                        Since last month
                      </Box>
                    </>
                  }
                />
              </Grid>
    </>
  );
};

export default DrawTotalTraffic;
