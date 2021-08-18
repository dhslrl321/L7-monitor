
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
import ThreatTraffic from "components/presenter/Summary/ThreatSummary";
import TotalSummary from "components/presenter/Summary/TotalSummary";


const useStyles = makeStyles(componentStyles);

const Summary = ({summaryData}) => {
  const classes = useStyles();
  const theme = useTheme();

  const {count: totalCount, timestamp: totalTimestamp} = summaryData.totalTraffic;
  const {count: abnormalCount, timestamp: abnormalTimestamp} = summaryData.abnormalTraffic;
  const {level, description, ratio} = summaryData.securityLevel;


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

              <TotalSummary totalCount={totalCount} totalTimestamp={totalTimestamp}/>
              <ThreatTraffic abnormalCount={abnormalCount} abnormalTimestamp={abnormalTimestamp}/>
              <SecurityLevel level={level} description={description} ratio={ratio}/>

            </Grid>
          </div>
        </Container>
      </div>
    </>
  );
};

export default Summary;
