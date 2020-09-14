import defaultConfig from './defaultConfig';
import { omit } from '@util/index';
import { formatConfig } from './formatConfig';
import { message } from 'antd';

/**
 * 
 * @param u url
 * @param c config
 */
const uniHttp = async (u, c) => {
  const { dataType, autoShowError } = c;

  const { url, config } = formatConfig(u, c);

  // 将处理过后的config中的下列字段删除
  const newConfig = omit(config, [
    'ignore',
    'bodyType',
    'autoShowError',
    'isJoin',
    'errMsg',
    'dataType',
  ]);

  try {
    const info = await fetch(url, {
      ...newConfig,
    });

    const resResult = await info[dataType]();

    // 如果配置了失败自动提示则判断是否失败并提示
    if (autoShowError) {
      const { status, msg = '' } = resResult;
      if (status !== 'success') {
        message.warn(`请求失败，原因为：${msg}`);
      }
    }

    return resResult;
  } catch (err) {
    console.log(err);
    // 返回统一格式的错误数据
    return { data: {}, RetCode: 4444, msg: err && err.message, success: false };
  }
};

// 返回统一方式的请求api

export default {
  get: (url, config = {}) => async params => {
    return uniHttp(url, { method: 'GET', ...defaultConfig, ...config, body: params });
  },
  post: (url, config = {}) => async params => {
    return uniHttp(url, { method: 'POST', ...defaultConfig, ...config, body: params });
  },
  form: (url, config = {}) => async params => {
    return uniHttp(url, { method: 'POST', ...defaultConfig, ...config, bodyType: 'form', body: params });
  },
};
