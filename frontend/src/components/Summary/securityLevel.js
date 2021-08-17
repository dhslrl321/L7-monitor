import React, { useEffect, useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
// @material-ui/icons components
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import EmojiEvents from "@material-ui/icons/EmojiEvents";
//import GroupAdd from "@material-ui/icons/GroupAdd";
import InsertChartOutlined from "@material-ui/icons/InsertChartOutlined";
import PieChart from "@material-ui/icons/PieChart";

// core components
import CardStats from "components/Summary/CardStats.js";
import componentStyles from "assets/theme/components/header.js";



const useStyles = makeStyles(componentStyles);

const DrawSecurityLevel = () => {
  const classes = useStyles();
  const theme = useTheme();


  const [securityLevel, setSecurityLevel] = useState(0);
  const [securityDescription, setSecurityDescription] = useState(0);
  const [securityRatio, setSecurityRatio] = useState(0);


  useEffect(() => {
    const level = 3
    const description = "주의 단계 (이상 트래픽 0.01%)- 이상 트래픽이 다수 탐지되고 있습니다."
    const ratio = "0.0412%"

    setSecurityLevel(level)
    setSecurityDescription(description)
    setSecurityRatio(ratio)
  })



  return (
    <>
      <Grid item xl={4} lg={6} xs={12}>
        <CardStats
          subtitle="오늘의 보안 레벨"
          title={"LV." + securityLevel}
          icon={EmojiEvents}
          color="bgInfo"
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
                <Box />{" "}
                {securityRatio}
              </Box>
              <Box component="span" whiteSpace="nowrap">
                {securityDescription}
              </Box>
            </>
          }
        />
      </Grid>
    </>
  );
};

export default DrawSecurityLevel;