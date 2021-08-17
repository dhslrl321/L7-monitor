import React from 'react'

// react plugin used to create charts
import { Pie } from "react-chartjs-2";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardHeader from "@material-ui/core/CardHeader";
import Grid from "@material-ui/core/Grid";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);


const ThreatChart = () => {

  const classes = useStyles();
  const theme = useTheme();

  return (
    <Grid item xs={12} xl={4}>
      <Card classes={{ root: classes.cardRoot }}>
        <CardHeader
          title={
            <Box component="span" color={theme.palette.gray[600]}>
              최근 14일 동안 발생한 모든 공격 로그의 유형을 확인하세요
            </Box>
          }
          subheader="공격 유형"
          classes={{ root: classes.cardHeaderRoot }}
          titleTypographyProps={{
            component: Box,
            variant: "h6",
            letterSpacing: ".0625rem",
            marginBottom: ".25rem!important",
            classes: {
              root: classes.textUppercase,
            },
          }}
          subheaderTypographyProps={{
            component: Box,
            variant: "h2",
            marginBottom: "0!important",
            color: "initial",
          }} />
        <CardContent>
          <Box position="relative" height="230px">
            <Pie
              label={xValues}
              data={chartExample2.data}
              options={chartExample2.options}
            />
          </Box>
        </CardContent>
      </Card>
    </Grid>
  )
}

export default ThreatChart

// 차트 데이터

var xValues = ["SQL-Injection", "Web-Shell", "RFI", "XSS"];
var yValues = [55, 49, 44, 24];
var barColors = ["red", "green", "blue", "orange"];


// Example 2 of Chart inside src/views/Index.js (Total orders - Card)
let chartExample2 = {
  labels: xValues,
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    responsive: true,
    maintainAspectRatio: false
  }
};