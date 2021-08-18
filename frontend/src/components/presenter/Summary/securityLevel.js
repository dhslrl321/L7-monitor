import React from "react";
// @material-ui/core components
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
// @material-ui/icons components

import EmojiEvents from "@material-ui/icons/EmojiEvents";
//import GroupAdd from "@material-ui/icons/GroupAdd";

// core components
import CardStats from "components/presenter/Summary/CardStats.js";


const SecurityLevel = ({level, description, ratio}) => {

  const theme = useTheme();

  return (
    <>
      <Grid item xl={4} lg={6} xs={12}>
        <CardStats
          subtitle="오늘의 보안 레벨"
          title={"LV." + level}
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
                {ratio}
              </Box>
              <Box component="span" whiteSpace="nowrap">
                {description}
              </Box>
            </>
          }
        />
      </Grid>
    </>
  );
};

export default SecurityLevel;