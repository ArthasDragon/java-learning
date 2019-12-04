import * as React from 'react';
import { RouteWithSubRoutes } from '@util/index';
import { Layout } from 'antd';
import './home.less';

interface Props {
  routes: object[];
}

class Home extends React.Component<Props> {
  constructor(props) {
    super(props);
  }

  public render() {
    const { routes } = this.props;
    return (
      <Layout className="full_wrapper">
        {routes.map((route, i) => (
          <RouteWithSubRoutes key={i} {...route} />
        ))}
      </Layout>
    );
  }
}

export default Home;
