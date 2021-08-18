
import React from "react";
// @material-ui/core components

import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
//import GroupAdd from "@material-ui/icons/GroupAdd";
import InsertChartOutlined from "@material-ui/icons/InsertChartOutlined";

// core components
import CardStats from "components/presenter/Summary/CardStats.js";





const TotalSummary = ({totalCount, totalTimestamp}) => {

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
              <Box component="span" whiteSpace="nowrap">
               오늘 하루동안 들어온 총 트래픽을 보여줍니다. 하루를 기준으로 00:00:00 ~ 23:59:59 사이의 트래픽 
                      </Box>
            </>
          }
        />
      </Grid>
    </>
  );
};

export default TotalSummary;
