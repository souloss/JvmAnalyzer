import Vue from 'vue'
import SvgIcon from '@/components/icons' 

// register globally
Vue.component('svg-icon', SvgIcon)

//使用 require.context 递归获取 .svg 导入到环境上下文
const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('@/components/icons/svg', false, /\.svg$/)
requireAll(req)
