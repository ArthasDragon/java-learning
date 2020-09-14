import * as React from 'react';
import './miaosha.less';
import { Input, Button, message } from 'antd';
import { getOtp } from '@/api/home';

interface Props {
  routes: object[];
}

interface State {
  telPhone: string;
}

class Home extends React.Component<Props, State> {
  constructor(props) {
    super(props);
    this.state = {
      telPhone: '',
    };
  }

  public handleChangeValue = e => {
    e.persist();
    this.setState(state => {
      return { telPhone: e.target.value };
    });
  };

  public getOtp = async () => {
    const { status = null } = await getOtp({ telphone: this.state.telPhone });
    if (status === 'success') {
      message.success('已成功发送，请注意查收');
    }
  };

  public render() {
    const { telPhone } = this.state;
    return (
      <div className="full_wrapper miaosha">
        <div className="flex_center_column">
          <h2>获取otp信息</h2>
          <p>手机号</p>

          <Input value={telPhone} onInput={this.handleChangeValue} />
          <Button onClick={this.getOtp}>获取otp短信</Button>
        </div>
      </div>
    );
  }
}

export default Home;
