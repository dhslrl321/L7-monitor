import React from 'react'

import { Chart } from "chart.js";
// react plugin used to create charts
import { Line, Pie } from "react-chartjs-2";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardHeader from "@material-ui/core/CardHeader";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import LinearProgress from "@material-ui/core/LinearProgress";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";
// @material-ui/icons components

import componentStyles from "assets/theme/views/admin/dashboard.js";

const useStyles = makeStyles(componentStyles);

const UnknownLogTable = () => {

  const classes = useStyles();
  const theme = useTheme();

  return (
    <Grid item xs={12} xl={4}>
      <Card classes={{ root: classes.cardRoot }}>
        <CardHeader
          subheader={
            <Grid
              container
              component={Box}
              alignItems="center"
              justifyContent="space-between"
            >
              <Grid item xs="auto">
                <Box
                  component={Typography}
                  variant="h3"
                  marginBottom="0!important"
                >
                  Social traffic
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
                    size="small"
                  >
                    See all
                </Button>
                </Box>
              </Grid>
            </Grid>
          }
          classes={{ root: classes.cardHeaderRoot }}
        ></CardHeader>
        <TableContainer>
          <Box
            component={Table}
            alignItems="center"
            marginBottom="0!important"
          >
            <TableHead>
              <TableRow>
                <TableCell
                  classes={{
                    root:
                      classes.tableCellRoot +
                      " " +
                      classes.tableCellRootHead,
                  }}
                >
                  Refferal
              </TableCell>
                <TableCell
                  classes={{
                    root:
                      classes.tableCellRoot +
                      " " +
                      classes.tableCellRootHead,
                  }}
                >
                  Visitors
              </TableCell>
                <TableCell
                  classes={{
                    root:
                      classes.tableCellRoot +
                      " " +
                      classes.tableCellRootHead,
                  }}
                ></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow>
                <TableCell
                  classes={{
                    root:
                      classes.tableCellRoot +
                      " " +
                      classes.tableCellRootBodyHead,
                  }}
                  component="th"
                  variant="head"
                  scope="row"
                >
                  Facebook
              </TableCell>
                <TableCell classes={{ root: classes.tableCellRoot }}>
                  1,480
              </TableCell>
                <TableCell classes={{ root: classes.tableCellRoot }}>
                  <Box display="flex" alignItems="center">
                    <Box component="span" marginRight=".5rem">
                      60%
                  </Box>
                    <Box width="100%">
                      <LinearProgress
                        variant="determinate"
                        value={60}
                        classes={{
                          root: classes.linearProgressRoot,
                          bar: classes.bgGradientError,
                        }}
                      />
                    </Box>
                  </Box>
                </TableCell>
              </TableRow>
            </TableBody>
          </Box>
        </TableContainer>
      </Card>
    </Grid>
  )
}

export default UnknownLogTable
