import React from 'react'

import { makeStyles } from "@material-ui/core/styles";
import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";

import componentStyles from "assets/theme/views/admin/dashboard.js";
const useStyles = makeStyles(componentStyles);

const LogRow = ({ data }) => {

  const { ip, timestamp, method, uri, resDataSize, referer } = data;
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
        {ip}
      </TableCell>
      <TableCell>
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
