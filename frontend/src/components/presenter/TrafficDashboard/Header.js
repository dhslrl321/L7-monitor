import React from 'react'
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import CardHeader from "@material-ui/core/CardHeader";

import Grid from "@material-ui/core/Grid";

import Typography from "@material-ui/core/Typography";

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

const Header = ({ handleButtonClick, activeNav }) => {

  const classes = useStyles();
  const theme = useTheme();

  return (
    <CardHeader
      subheader={
        <Grid
          container
          component={Box}
          alignItems="center"
          justifyContent="space-between">
          <Grid item xs="auto">
            <Box
              component={Typography}
              variant="h6"
              letterSpacing=".0625rem"
              marginBottom=".25rem!important"
              className={classes.textUppercase}>
              <Box component="span" color={theme.palette.gray[400]}>
                Bori-BoB
                  </Box>
            </Box>
            <Box
              component={Typography}
              variant="h2"
              marginBottom="0!important">
              <Box component="span" color={theme.palette.white.main}>
                오늘의 실시간 트래픽
                        </Box>
            </Box>
          </Grid>
          <Grid item xs="auto">
            <Box
              justifyContent="flex-end"
              display="flex"
              flexWrap="wrap"
            >
              <Button
                variant="contained"
                color="primary"
                component={Box}
                marginRight="1rem!important"
                onClick={() => handleButtonClick(1)}
                classes={{
                  root:
                    activeNav === 1
                      ? ""
                      : classes.buttonRootUnselected,
                }}
              >
                Day
                        </Button>
              <Button variant="contained" color="primary" onClick={() => handleButtonClick(2)}
                classes={{
                  root:
                    activeNav === 2
                      ? ""
                      : classes.buttonRootUnselected,
                }}>
                Week</Button>
              <Button variant="contained" color="primary" onClick={() => handleButtonClick(3)}
                classes={{
                  root:
                    activeNav === 3
                      ? ""
                      : classes.buttonRootUnselected,
                }}>
                5분</Button>
            </Box>
          </Grid>
        </Grid>
      }
      classes={{ root: classes.cardHeaderRoot }}
    ></CardHeader>
  )
}

export default Header
