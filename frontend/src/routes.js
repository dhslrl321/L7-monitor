// core components
import Dashboard from "components/Dashboard.js";

const routes = [
  {
    path: "/",
    name: "Dashboard",
    iconColor: "Primary",
    component: Dashboard,
  },
  {
    path: "/app",
    name: "App",
    iconColor: "Primary",
    component: Dashboard,
  }
];

export default routes;
