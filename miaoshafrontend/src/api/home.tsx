import http from '@http';

export const getOtp = http.form('/user/getotp', { bodyType: 'form' });
