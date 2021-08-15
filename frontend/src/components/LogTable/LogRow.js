import React from 'react'

import { makeStyles } from "@material-ui/core/styles";
import { useTheme } from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import Grid from "@material-ui/core/Grid";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";

import componentStyles from "assets/theme/views/admin/dashboard.js";
const useStyles = makeStyles(componentStyles);

const LogRow = ({ data }) => {

  const { timestamp, method, uri, resDataSize, referer } = data;

  console.log(timestamp);

  const classes = useStyles();

  return (
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
        scope="row">
        {uri}
      </TableCell>
      <TableCell classes={{ root: classes.tableCellRoot }}>
        {method}
      </TableCell>
      <TableCell classes={{ root: classes.tableCellRoot }}>

        {resDataSize}
      </TableCell>
      <TableCell classes={{ root: classes.tableCellRoot }}>
        {referer}
      </TableCell>
      <TableCell classes={{ root: classes.tableCellRoot }}>
        {timestamp}
      </TableCell>
    </TableRow>
  )
}

export default LogRow
