import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: { name: 'direct' }
    },
    {
      path: '/direct',
      name: 'direct',
      component: () => import(/* webpackChunkName: "about" */ './views/Direct.vue')
    },
    {
      path: '/file',
      name: 'file',
      component: () => import('./views/File.vue')
    },
    {
      path: '/select',
      name: 'select',
      component: () => import('./views/Select.vue')
    }
  ]
})
