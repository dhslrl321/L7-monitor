import React from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";

import Grid from "@material-ui/core/Grid";

// core components
// todo 지금은 presenter 를 import 하지만 추후에 container 를 import 해야 함
import Summary from "components/presenter/Summary/index";
import ThreatChart from "components/presenter/ThreatChart/index";
import UnknownLogTable from "components/presenter/UnknownLogTable/index";
import TrafficDashboard from "components/presenter/TrafficDashboard/index";

// container
import LogTableContainer from "components/container/LogTableContainer";
import ThreatChartContainer from "components/container/ThreatChartContainer";
import TrafficDashboardContainer from "components/container/TrafficDashboardContainer";
import UnknownLogTableContainer from "components/container/UnknownLogTableContainer";
import SummaryContainer from "components/container/SummaryContainer";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

function Dashboard() {
  const classes = useStyles();
  return (
    <>
      <SummaryContainer />
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
