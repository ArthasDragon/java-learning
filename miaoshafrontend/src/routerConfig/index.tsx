import Home from '@/pages/Home';
import Bus from '@/pages/Bus';
import Miaosha from '@/pages/Miaosha';

export default [
  {
    path: '/',
    component: Home,
    routes: [
      {
        path: '/bus',
        component: Bus,
      },
      {
        path: '/miaosha',
        component: Miaosha,
      },
    ],
  },
];
