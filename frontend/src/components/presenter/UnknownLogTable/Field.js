import React from 'react'

import { makeStyles } from "@material-ui/core/styles";
import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";

import componentStyles from "assets/theme/views/admin/dashboard.js";
const useStyles = makeStyles(componentStyles);

const Field = () => {

  const classes = useStyles();

  const rootClasses = {
    root:
      classes.tableCellRoot +
      " " +
      classes.tableCellRootHead,
  }

  return (
    <>
      <TableCell classes={rootClasses} >
        IP
      </TableCell>
      <TableCell classes={rootClasses} >
        Data
      </TableCell>
    </>
  )
}

export default Field
