
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

// import each functions
import DrawSecurityLevel from "./securityLevel";
import DrawThreatTraffic from "./threatTraffic";
import DrawTotalTraffic from "./totalTraffic";


const useStyles = makeStyles(componentStyles);

const Header = () => {
  const classes = useStyles();
  const theme = useTheme();

  const [totalTraffic, setTotalTraffic] = useState(0);
  
  const handleOnClick = () => {
    setTotalTraffic(totalTraffic+1) 
  }

  useEffect(()=>{
    // 여기서 API 호출 일어남 --> 응답 데이터
    const response = 100 // 응답값
    setTotalTraffic(response)
  })

  return (
    <>
      <button onClick={handleOnClick}>hello</button>
      <div className={classes.header}>
        <Container
          maxWidth={false}
          component={Box}
          classes={{ root: classes.containerRoot }}
        >
          <div>
            <Grid container>
                

            <DrawTotalTraffic/>
            <DrawThreatTraffic/>
            <DrawSecurityLevel/>
            
            
            </Grid>
          </div>
        </Container>
      </div>
    </>
  );
};

export default Header;
