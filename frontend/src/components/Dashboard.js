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
import LogTable from "components/presenter/LogTable/index";
import TrafficDashboard from "components/presenter/TrafficDashboard/index";

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
