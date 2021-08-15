import React from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";

// core components
import Summary from "components/Summary/index";
import ThreatChart from "components/ThreatChart/index";
import UnknownLogTable from "components/UnknownLogTable/index";
import LogTable from "components/LogTable/index";
import TrafficDashboard from "components/TrafficDashboard/index";

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
          <TrafficDashboard />
        </Grid>

        <Grid container component={Box} marginTop="3rem">
          <LogTable />
          <UnknownLogTable />
          <ThreatChart />
        </Grid>
      </Container>
    </>
  );
}

export default Dashboard;
