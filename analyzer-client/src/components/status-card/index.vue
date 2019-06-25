<template>
<section>
    <el-card class="icon-card">
        <svg-icon :icon-class="iconName" />
        <div class="icon-card-content">
            <span>{{cardArg.data.length}}</span>
            <span>{{cardArg.header}}</span>
        </div>
        <div class="bottom clearfix">
            <el-button type="text" class="button" @click="dialogTableVisible=!dialogTableVisible">查看详情</el-button>
        </div>
    </el-card>
    <el-dialog title="线程状态列表" :visible.sync="dialogTableVisible">
        <el-table
            :data="cardArg.data"
            style="width: 100%">
            <!-- 额外信息 -->
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="table-expand">
                  <el-form-item label="本地线程ID">
                    <span>{{props.row.nid}}</span>
                  </el-form-item>
                  <el-form-item label="编号">
                    <span>{{ props.row.no }}</span>
                  </el-form-item>
                  <el-form-item label="操作系统优先级">
                    <span>{{ props.row.osPrio }}</span>
                  </el-form-item>
                  <el-form-item label="优先级">
                    <span>{{ props.row.prio }}</span>
                  </el-form-item>
                  <el-form-item label="线程偏移量">
                    <span>{{ props.row.threadOffice }}</span>
                  </el-form-item>
                  <el-form-item label="线程ID">
                    <span>{{ props.row.tid }}</span>
                  </el-form-item>
                  <el-form-item label="线程堆栈">
                    <span>{{ props.row.threadDump }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <!-- 额外信息 -->
            <el-table-column
              label="线程名称"
              prop="threadName">
            </el-table-column>
            <el-table-column
              label="JVM线程状态"
              prop="javaThreadState">
            </el-table-column>
            <el-table-column
              label="线程状态"
              prop="threadStatus">
            </el-table-column>
            <el-table-column
              label="是否守护线程"
              prop="isDaemon">
            </el-table-column>
        </el-table>
    </el-dialog>
</section>
</template>

<script>
export default {
  name: 'StatusCard',
  props: {
    cardArg: {
      type: Object,
      required: true
    },
    iconName:{
        type:String,
        required:true
    }
  },
  data:function(){
      return {
          dialogTableVisible:false
      }
  },
  methods: {
  }
}
</script>

<style lang=scss scoped>
  .icon-card{
    .svg-icon{
        font-size: 4em;
    }
    padding: 20px 0px 0px 0px;
    .icon-card-content{
      float: right;
      display: grid;
      grid-row-gap: 5px;
      margin-right: 20px;
      span:first-child{
        font-size: xx-large;
        text-align: right;
      }
    }
    .bottom{
      margin-top: 13px;
      line-height: 12px;
    }
  }

.table-expand {
  font-size: 0;
}
.table-expand label {
  width: 90px;
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>