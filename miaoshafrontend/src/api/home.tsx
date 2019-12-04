import http from '@http';

export const getOtp = http.post('/user/getotp', { bodyType: 'form' });
