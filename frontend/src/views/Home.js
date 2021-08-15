import React, { Fragment } from 'react';

import Header from 'components/Header';
import HomeDetail from 'components/HomeDetail';

class Home extends React.Component {
    render() {
        return (
            <Fragment>
                <Header />
                <HomeDetail />
            </Fragment>
        )
    };
}

export default Home;