
import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
// @material-ui/icons components

// core components
import componentStyles from "assets/theme/components/header.js";

// import each functions
import SecurityLevel from "components/presenter/Summary/SecurityLevel";
import ThreatTraffic from "components/presenter/Summary/ThreatSummary"
import TotalSummary from "components/presenter/Summary/TotalSummary"


const useStyles = makeStyles(componentStyles);

const Header = () => {
  const classes = useStyles();
  const theme = useTheme();

  return (
    <>
      <div className={classes.header}>
        <Container
          maxWidth={false}
          component={Box}
          classes={{ root: classes.containerRoot }}
        >
          <div>
            <Grid container>

              <TotalSummary />
              <ThreatTraffic />
              <SecurityLevel />

            </Grid>
          </div>
        </Container>
      </div>
    </>
  );
};

export default Header;
