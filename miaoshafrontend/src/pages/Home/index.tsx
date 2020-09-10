import * as React from 'react';
import { RouteWithSubRoutes } from '@util/index';
import { Layout, Menu } from 'antd';
import { Link } from 'react-router-dom'
import './home.less';

const { Content } = Layout

// 路径路由的部分类型定义
interface Route {
  path: string,
  name: string
}

interface Props {
  routes: Route[] // home下的路由列表
}

class Home extends React.Component<Props> {
  private pathRoutes // 只包含路径和名称的路由列表
  constructor(props) {
    super(props);
    this.pathRoutes = [{ path: '/', name: 'home' }, ...this.props.routes]
  }

  public render() {
    const pathRoutes = this.pathRoutes;
    const { routes } = this.props

    // 根据页面地址设置当前menu的高亮显示
    let currentIndex = '0'
    pathRoutes.some((route, i) => {
      if (route.path === window.location.pathname) {
        currentIndex = String(i)
        return true
      } else {
        return false
      }
    })
    return (
      <Layout className="home">
        {/* 根据路径列表设置导航栏 */}
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={[currentIndex]}>
          {
            pathRoutes.map((route, i) => (
              <Menu.Item key={i}><Link to={route.path}>{route.name}</Link></Menu.Item>
            ))
          }
        </Menu>

        {/* 根据home下的路由渲染特定页面 */}

        <Content style={{ padding: '50px' }}>
          <div className='content_inner'>
            {routes.map((route, i) => (
              <RouteWithSubRoutes key={i} {...route} />
            ))}
          </div>

        </Content>
      </Layout>
    );
  }
}

export default Home;
