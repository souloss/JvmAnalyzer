package org.analyzer.jvm.model;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
/**
 * JavaStackDump
 */
public class JavaStackDump extends BasicModel{
    private String pid;
    private String processName;
    //JNI global references
    private String JNIGRS;
    //日期
    private String date;
    //JVM版本
    private String jvmVersion;
    //线程堆栈
    private List<ThreadDump> threadDumps = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public String getJNIGRS() {
        return JNIGRS;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setJNIGRS(String JNIGRS) {
        this.JNIGRS = JNIGRS;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<ThreadDump> getThreadDumps() {
        return threadDumps;
    }

    public void addThreadDumps(ThreadDump threadDump) {
        threadDumps.add(threadDump);
    }

    public void setThreadDumps(List<ThreadDump> threadDumps) {
        this.threadDumps = threadDumps;
    }


    @Override
    public String toString() {
        return "{" +
            " pid='" + getPid() + "'" +
            ", processName='" + getProcessName() + "'" +
            ", JNIGRS='" + getJNIGRS() + "'" +
            ", date='" + getDate() + "'" +
            ", jvmVersion='" + getJvmVersion() + "'" +
            ", threadDumps='" + getThreadDumps() + "'" +
            "}";
    }

    public static class ThreadDump{
        String threadName;
        String no;
        String prio;
        String osPrio;
        String tid;
        String nid;
        boolean isDaemon;
        State javaThreadState;
        String threadOffice;
        String threadStatus;
        String threadDump;

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }

        public String getPrio() {
            return prio;
        }

        public void setPrio(String prio) {
            this.prio = prio;
        }

        public String getOsPrio() {
            return osPrio;
        }

        public void setOsPrio(String osPrio) {
            this.osPrio = osPrio;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getNo() {
            return no;
        }

        public boolean isDaemon() {
            return isDaemon;
        }

        public void setDaemon(boolean daemon) {
            isDaemon = daemon;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getThreadOffice() {
            return threadOffice;
        }

        public void setThreadOffice(String threadOffice) {
            this.threadOffice = threadOffice;
        }

        public String getThreadDump() {
            return threadDump;
        }

        public void setThreadDump(String threadDump) {
            this.threadDump = threadDump;
        }

        public State getJavaThreadState() {
            return javaThreadState;
        }

        public void setJavaThreadState(State javaThreadState) {
            this.javaThreadState = javaThreadState;
        }

        public String getThreadStatus() {
            return threadStatus;
        }

        public void setThreadStatus(String threadStatus) {
            this.threadStatus = threadStatus;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ThreadDump{");
            sb.append("threadName='").append(threadName).append('\'');
            sb.append(", no='").append(no).append('\'');
            sb.append(", prio='").append(prio).append('\'');
            sb.append(", osPrio='").append(osPrio).append('\'');
            sb.append(", tid='").append(tid).append('\'');
            sb.append(", nid='").append(nid).append('\'');
            sb.append(", isDaemon=").append(isDaemon);
            sb.append(", javaThreadState=").append(javaThreadState);
            sb.append(", threadOffice='").append(threadOffice).append('\'');
            sb.append(", threadStatus='").append(threadStatus).append('\'');
            sb.append(", threadDump='").append(threadDump).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}