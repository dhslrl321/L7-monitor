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

const ThreatSummary = ({abnormalCount, abnormalTimestamp}) => {
  const classes = useStyles();
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
                {abnormalTimestamp+"\n"}
              </Box>
              <Box component="span" whiteSpace="nowrap">              
              오늘 하루동안 들어온 보안 위협 트래픽을 보여줍니다. 하루를 기준으로 00:00:00 ~ 23:59:59 사이의 트래픽 
              </Box>
            </>
          }
        />
      </Grid>

    </>
  );
};

export default ThreatSummary;