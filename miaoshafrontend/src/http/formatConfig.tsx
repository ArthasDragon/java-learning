import { disposeBody } from './body';

export const formatConfig = (url, config) => {
  const { body, ignore, bodyType, method } = config;
  const m = method.toLowerCase().trim(); // 请求方式
  // 参数是否拼接到url后面
  let isJoin = m === 'get' || m === 'delete' || bodyType === 'form';
  // 若config中自定义了则按照config中的进行处理
  if ('isJoin' in config) {
    isJoin = config.isJoin;
  }
  // 根据配置处理body数据
  const params = disposeBody(body, isJoin, ignore, bodyType);

  if (isJoin) {
    // 需要将数据通过url传递
    if (params) {
      // 根绝情况对url进行处理之后添加 处理后的params
      if (url.indexOf('?') === -1) {
        url += '?';
      } else if (!url.endsWith('&')) {
        url += '&';
      }
      url += params;
    }
    // 添加之后删除body
    delete config.body;
  } else {
    // 将处理后的body覆盖掉原有的
    config.body = params;
  }

  if (bodyType === 'form' && config.headers) {
    // 设置form格式的content-type
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
  }
  return { url, config };
};
