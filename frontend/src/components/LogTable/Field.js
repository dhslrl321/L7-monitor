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
        URI
      </TableCell>
      <TableCell classes={rootClasses} >
        응답 Method
      </TableCell>
      <TableCell classes={rootClasses}>
        응답 데이터 사이즈
      </TableCell>
      <TableCell classes={rootClasses}>
        Referer
      </TableCell>
      <TableCell classes={rootClasses}>
        통신 시각
      </TableCell>
    </>
  )
}

export default Field
