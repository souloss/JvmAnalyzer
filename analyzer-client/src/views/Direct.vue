<template>
  <section id="direct-app" 
   v-loading.fullscreen.lock="fullscreenLoading"
   element-loading-text="拼命加载中"
   element-loading-spinner="el-icon-loading"
   element-loading-background="rgba(0, 0, 0, 0.8)">
    <el-card>
        <div slot="header" class="clearfix">
          <span>Jvm堆栈直接分析</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="getData">开始分析</el-button>
        </div>
      <section v-if="hasData">
        <div class="summary">
          <span>进程:{{reder_data.processName}}</span>
          <span>日期:{{reder_data.date}}</span>
          <span>JVM版本:{{reder_data.jvmVersion}}</span>
        </div>
      </section>
      <section v-if="!hasData" class="empty-data">
        暂无数据
      </section>
    </el-card>

    <el-card>
        <div slot="header" class="clearfix">
          <span>线程计数摘要</span>
        </div>
      <section v-if="hasData">
        <div class="icon-card-group">
          <status-card :cardArg="runThreadData" iconName="setting" />
          <status-card :cardArg="waittingThreadData" iconName="pause" />
          <status-card :cardArg="timeWaitingThreadData" iconName="time" />
        </div>
      </section>
      <section v-if="!hasData" class="empty-data">
        暂无数据
      </section>
    </el-card>

    <el-card>
        <div slot="header" class="clearfix">
          <span>守护进程与非守护进程</span>
        </div>
      <section v-if="hasData">
        <div class="icon-card-group">
          <status-card :cardArg="daemonThreadData" iconName="devil" />
          <status-card :cardArg="noDaemonThreadData" iconName="angel" />
        </div>
      </section>
      <section v-if="!hasData" class="empty-data">
        暂无数据
      </section>
    </el-card>

    <el-card>
        <div slot="header" class="clearfix">
          <span>线程状态列表</span>
        </div>
      <section v-if="hasData">

        <el-table
            :data="reder_data.threadDumps"
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


      </section>
      <section v-if="!hasData" class="empty-data">
        暂无数据
      </section>
    </el-card>

  </section>
</template>

<script>
import {getLauncherStack} from '@/api/getData'
import StatusCard from '@/components/status-card/index.vue'
import {statusCardFormat} from '@/utils/format.js'

export default {
  name: 'Direct',
  data:function(){
    return {
      hasData:false,
      reder_data:null,
      fullscreenLoading:false
    }
  },
  components:{
    StatusCard
  },
  methods:{
    getData(){
      this.fullscreenLoading = true;
      getLauncherStack().then(response=>{
        if(response.data!=null){
          this.hasData = true
          this.reder_data = response.data
        }
        this.fullscreenLoading = false;
      })
    },
  },
  computed: {
    runThreadData(){
      console.log(this.reder_data.threadDumps)
      return statusCardFormat(this.reder_data.threadDumps,"javaThreadState","RUNNABLE")
    },
    timeWaitingThreadData(){
      return statusCardFormat(this.reder_data.threadDumps,"javaThreadState","TIMED_WAITING")
    },
    waittingThreadData(){
      return statusCardFormat(this.reder_data.threadDumps,"javaThreadState","WAITING")
    },
    daemonThreadData(){
       return statusCardFormat(this.reder_data.threadDumps,"isDaemon",true,"DAEMON")
    },
    noDaemonThreadData(){
       return statusCardFormat(this.reder_data.threadDumps,"isDaemon",false,"NON-DAEMON")
    }
  }
}
</script>

<style lang="scss" scoped>
#direct-app{
  display: grid;
  grid-row-gap: 20px;
  height: 100%;
}
.summary{
  display: grid;
  text-align: left;
  font-size: small;
}
.icon-card-group{
  display: grid;
  grid-row-gap: 20px;
  grid-column-gap: 20px;
  grid-template-columns: 25% 25%;
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



