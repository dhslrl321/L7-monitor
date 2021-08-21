// 헤더 컴포넌트 = 카드 3개 부터 그 위까지 섹션

const componentStyles = (theme) => ({
  header: {
    position: "relative",
    background:
      "linear-gradient(87deg," + theme.palette.info.main + ",#1171ef)",
    paddingBottom: "8rem",
    paddingTop: "3rem",
    [theme.breakpoints.up("md")]: {
      paddingTop: "8rem",
    },
  },
  containerRoot: {
    [theme.breakpoints.up("md")]: {
      paddingLeft: "39px",   //39
      paddingRight: "39px",   //39
    },
  },
});

export default componentStyles;
