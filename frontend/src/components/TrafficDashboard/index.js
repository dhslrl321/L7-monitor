import React from "react";
import { useState } from 'react' // to manage statement adding option
// javascipt plugin for creating charts
import { Chart } from "chart.js";
// react plugin used to create charts
import { Line } from "react-chartjs-2";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardHeader from "@material-ui/core/CardHeader";

import Grid from "@material-ui/core/Grid";

import Typography from "@material-ui/core/Typography";
import {
  chartOptions,
  parseOptions,
  lineChart
} from "variables/charts.js";

import Header from "components/TrafficDashboard/Header";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

const TrafficDashboard = () => {

  const classes = useStyles();
  const theme = useTheme();

  const [activeNav, setActiveNav] = useState(1);
  const [chartExample1Data, setChartExample1Data] = useState("data1");

  if (window.Chart) {
    parseOptions(Chart, chartOptions());
  }

  const toggleNavs = (index) => {
    setActiveNav(index);
    setChartExample1Data("data" + index);
  };

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
        <Header handleButtonClick={toggleNavs} activeNav={activeNav} />
        <CardContent>
          <Box position="relative" height="350px">
            <Line
              data={chartData[chartExample1Data]}
              options={lineChart.options}
              getDatasetAtEvent={(e) => console.log(e)}
            />
          </Box>
        </CardContent>
      </Card>
    </Grid>
  )
}

export default TrafficDashboard;

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