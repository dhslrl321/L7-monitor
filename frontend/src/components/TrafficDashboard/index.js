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
  chartExample1,
} from "variables/charts.js";


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
      <Card
        classes={{
          root: classes.cardRoot + " " + classes.cardRootBgGradient,
        }}
      >
        <CardHeader
          subheader={
            <Grid
              container
              component={Box}
              alignItems="center"
              justifyContent="space-between">
              <Grid item xs="auto">
                <Box
                  component={Typography}
                  variant="h6"
                  letterSpacing=".0625rem"
                  marginBottom=".25rem!important"
                  className={classes.textUppercase}>
                  <Box component="span" color={theme.palette.gray[400]}>
                    Bori-BoB
                  </Box>
                </Box>
                <Box
                  component={Typography}
                  variant="h2"
                  marginBottom="0!important">
                  <Box component="span" color={theme.palette.white.main}>
                    오늘의 실시간 트래픽
                        </Box>
                </Box>
              </Grid>
              <Grid item xs="auto">
                <Box
                  justifyContent="flex-end"
                  display="flex"
                  flexWrap="wrap"
                >
                  <Button
                    variant="contained"
                    color="primary"
                    component={Box}
                    marginRight="1rem!important"
                    onClick={() => toggleNavs(1)}
                    classes={{
                      root:
                        activeNav === 1
                          ? ""
                          : classes.buttonRootUnselected,
                    }}
                  >
                    Day
                        </Button>
                  <Button variant="contained" color="primary" onClick={() => toggleNavs(2)}
                    classes={{
                      root:
                        activeNav === 2
                          ? ""
                          : classes.buttonRootUnselected,
                    }}>
                    Week</Button>
                </Box>
              </Grid>
            </Grid>
          }
          classes={{ root: classes.cardHeaderRoot }}
        ></CardHeader>
        <CardContent>
          <Box position="relative" height="350px">
            <Line
              data={chartExample1[chartExample1Data]}
              options={chartExample1.options}
              getDatasetAtEvent={(e) => console.log(e)}
            />
          </Box>
        </CardContent>
      </Card>
    </Grid>
  )
}

export default TrafficDashboard
