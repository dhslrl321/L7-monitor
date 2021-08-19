import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
import PieChart from "@material-ui/icons/PieChart";

// core components
import CardStats from "components/presenter/Summary/CardStats.js";
import componentStyles from "assets/theme/components/header.js";



const useStyles = makeStyles(componentStyles);

const ThreatSummary = ({ abnormalCount, abnormalTimestamp }) => {
  const theme = useTheme();
  return (
    <>
      <Grid item xl={4} lg={6} xs={12}>
        <CardStats
          subtitle="오늘의 보안 위협 트래픽"
          title={abnormalCount}
          icon={PieChart}
          color="bgWarning"
          footer={
            <>
              <Box
                component="span"
                fontSize=".875rem"
                color={theme.palette.error.main}
                marginRight=".5rem"
                display="flex"
                alignItems="center"
              >
                <Box />{" "}
                {abnormalTimestamp + "\n"}
              </Box>
            </>
          }
        />
      </Grid>

    </>
  );
};

export default ThreatSummary;