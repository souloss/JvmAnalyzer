<template>
    <el-menu class="nav-menu"
      default-active="/direct"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router>
      <el-menu-item index="/direct">
        <i class="el-icon-s-data"></i>
        <span slot="title">直接分析</span>
      </el-menu-item> 
      <el-menu-item index="/file">
        <i class="el-icon-upload"></i>
        <span slot="title">文件分析</span>
      </el-menu-item>
      <el-menu-item index="/select">
        <i class="el-icon-share"></i>
        <span slot="title">选取分析</span>
      </el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  methods:{
    handleOpen(){

    },
    handleClose(){

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
