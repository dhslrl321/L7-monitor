import React from "react";
import ReactDOM from "react-dom";

import CssBaseline from "@material-ui/core/CssBaseline";
import { ThemeProvider } from "@material-ui/core/styles";

import theme from "assets/theme/theme.js";

import "assets/plugins/nucleo/css/nucleo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "assets/scss/argon-dashboard-react.scss";

import Dashboard from "components/Dashboard"

ReactDOM.render(
  <ThemeProvider theme={theme}>
    <CssBaseline />
    <Dashboard />
  </ThemeProvider>,
  document.querySelector("#root")
);
