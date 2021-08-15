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
        {timestamp}
      </TableCell>
      <TableCell classes={{ root: classes.tableCellRoot }}>
        {uri}
      </TableCell>
    </TableRow>
  )
}

export default LogRow
