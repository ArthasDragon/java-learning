import qs from 'qs';
import { isStr } from '@util/index';

export const disposeBody = (body, isJoin = true, ignore, bodyType) => {
  body = body || {};
  if (isJoin) {
    // 如果需要拼接到url后面则用qs工具处理
    return qs.stringify(body);
  } else {
    // 根据不同的body类型返回处理后的body
    switch (bodyType) {
      case 'json':
        if (isStr(body)) {
          // 如果body是字符串
          try {
            body = JSON.parse(body);
          } catch (err) {
            return body;
          }
        } else {
          // 判断body中的键值
          Object.keys(body).forEach(key => {
            let value = body[key];
            // 字符串去掉空格
            if (isStr(value)) {
              value = value.trim();
            }
            // 如果ignore中配置了对应值  则将该key删除
            if (ignore.includes(value)) {
              delete body[key];
            } else {
              body[key] = value;
            }
          });
        }
        return JSON.stringify(body);
      case 'form':
        // 避免form方式情况下isJoin不是true的情况
        return disposeBody(body, true, ignore, null);
      case 'none':
        return body;
      default:
        return body;
    }
  }
};
