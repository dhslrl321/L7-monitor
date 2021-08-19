
import React from "react";
// @material-ui/core components

import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
//import GroupAdd from "@material-ui/icons/GroupAdd";
import InsertChartOutlined from "@material-ui/icons/InsertChartOutlined";

// core components
import CardStats from "components/presenter/Summary/CardStats.js";





const TotalSummary = ({ totalCount, totalTimestamp }) => {

  const theme = useTheme();


  return (
    <>
      <Grid item xl={4} lg={6} xs={12}>
        <CardStats
          subtitle="오늘의 총 트래픽"
          title={totalCount}
          icon={InsertChartOutlined}
          color="bgError"
          footer={
            <>
              <Box
                component="span"
                fontSize=".875rem"
                color={theme.palette.success.main}
                marginRight=".5rem"
                display="flex"
                alignItems="center"
              >
                <Box />
                {totalTimestamp}
              </Box>
            </>
          }
        />
      </Grid>
    </>
  );
};

export default TotalSummary;
