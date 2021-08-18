import React from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";

import Grid from "@material-ui/core/Grid";

import Summary from "components/presenter/Summary/index";

// container
import LogTableContainer from "components/container/LogTableContainer";
import ThreatChartContainer from "components/container/ThreatChartContainer";
import TrafficDashboardContainer from "components/container/TrafficDashboardContainer";
import UnknownLogTableContainer from "components/container/UnknownLogTableContainer";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

function Dashboard() {
  const classes = useStyles();
  return (
    <>
      <Summary />
      <Container
        maxWidth={false}
        component={Box}
        marginTop="-6rem"
        classes={{ root: classes.containerRoot }}>

        <Grid container>
          <TrafficDashboardContainer />
        </Grid>

        <Grid container component={Box} marginTop="3rem">
          <LogTableContainer />
          <UnknownLogTableContainer />
          <ThreatChartContainer />
        </Grid>
      </Container>
    </>
  );
}

export default Dashboard;
