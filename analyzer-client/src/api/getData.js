import Vue from 'vue'

var $ajax = Vue.axios
/*
直接执行则是查看 Launcher 进程的堆栈情况
*/
export const getLauncherStack = () => {
    return $ajax({
        url: '/stack',
        method: 'get',
      });
}
