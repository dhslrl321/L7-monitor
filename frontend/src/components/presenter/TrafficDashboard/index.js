import React, { useState } from "react";
import { Chart } from "chart.js";
import { Line } from "react-chartjs-2";
import { makeStyles } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";

import Grid from "@material-ui/core/Grid";

import {
  chartOptions,
  parseOptions,
  lineChart
} from "variables/charts.js";

import Header from "components/presenter/TrafficDashboard/Header";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

const TrafficDashboard = ({ activeNav, chartData, toggleNav }) => {

  const classes = useStyles();

  if (window.Chart) {
    parseOptions(Chart, chartOptions());
  }

  return (
    <Grid
      item
      xs={12}
      xl={12}
      component={Box}
      marginBottom="3rem!important"
      classes={{ root: classes.gridItemRoot }}
    >
      <Card classes={{ root: classes.cardRoot + " " + classes.cardRootBgGradient }}>
        <Header handleButtonClick={toggleNav} activeNav={activeNav} />
        <CardContent>
          <Box position="relative" height="350px">
            <Line
              data={chartData}
              options={lineChart.options}
            />
          </Box>
        </CardContent>
      </Card>
    </Grid>
  )
}

export default TrafficDashboard;

