// web-pack -config
const path = require('path')
const resolve = dir => path.join(__dirname, dir)
const fs = require('fs')

module.exports = {
  publicPath: './',
  runtimeCompiler: true,
  // 配置路径别名
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('_c', resolve('src/components/'))
      .set('_as', resolve('src/assets/img/'))
    
    const svgRule = config.module.rule('svg')
    svgRule.uses.clear()
    svgRule.use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
  },

  // 全局注入通用样式
  css: {
    loaderOptions: {
        sass: {
            data: fs.readFileSync('src/styles/index.scss', 'utf-8')
        }
    }
  },


}