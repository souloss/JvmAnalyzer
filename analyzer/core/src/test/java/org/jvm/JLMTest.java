package org.jvm;

import org.junit.Test;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class JLMTest {
    static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    static final List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
    static final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    static float M = 1024 * 1024;

    public static void getMemoryUsage() {
        MemoryUsage memNonHeap = memoryMXBean.getNonHeapMemoryUsage();
        MemoryUsage memHeap = memoryMXBean.getHeapMemoryUsage();
        Runtime runtime = Runtime.getRuntime();
        System.out.println(JvmInfoEnum.MemNonHeapUsedM + ": " + memNonHeap.getUsed() / M);
        System.out.println(JvmInfoEnum.MemNonHeapCommittedM + ": " + memNonHeap.getCommitted() / M);
        System.out.println(JvmInfoEnum.MemNonHeapMaxM + ": " + memNonHeap.getMax() / M);
        System.out.println(JvmInfoEnum.MemHeapUsedM + ": " + memHeap.getUsed() / M);
        System.out.println(JvmInfoEnum.MemHeapCommittedM + ": " + memHeap.getCommitted() / M);
        System.out.println(JvmInfoEnum.MemHeapMaxM + ": " + memHeap.getMax() / M);
        System.out.println(JvmInfoEnum.MemMaxM + ": " + runtime.maxMemory() / M);
    }

    public static void getGcUsage() {
        long count = 0;
        long timeMillis = 0;
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            long c = gcBean.getCollectionCount();
            long t = gcBean.getCollectionTime();
            count += c;
            timeMillis += t;
        }
        System.out.println(JvmInfoEnum.GcCount + ": " + count);
        System.out.println(JvmInfoEnum.GcTimeMillis + ": " + timeMillis);
    }

    public static void getThreadUsage() {
        int threadsNew = 0;
        int threadsRunnable = 0;
        int threadsBlocked = 0;
        int threadsWaiting = 0;
        int threadsTimedWaiting = 0;
        int threadsTerminated = 0;
        long threadIds[] = threadMXBean.getAllThreadIds();
        for (ThreadInfo threadInfo : threadMXBean.getThreadInfo(threadIds, 0)) {
            if (threadInfo == null) continue; // race protection
            switch (threadInfo.getThreadState()) {
                case NEW:           threadsNew++;           break;
                case RUNNABLE:      threadsRunnable++;      break;
                case BLOCKED:       threadsBlocked++;       break;
                case WAITING:       threadsWaiting++;       break;
                case TIMED_WAITING: threadsTimedWaiting++;  break;
                case TERMINATED:    threadsTerminated++;    break;
            }
        }
        System.out.println(JvmInfoEnum.ThreadsNew + ": " + threadsNew);
        System.out.println(JvmInfoEnum.ThreadsRunnable + ": " + threadsRunnable);
        System.out.println(JvmInfoEnum.ThreadsBlocked + ": " + threadsBlocked);
        System.out.println(JvmInfoEnum.ThreadsWaiting + ": " + threadsWaiting);
        System.out.println(JvmInfoEnum.ThreadsTimedWaiting + ": " + threadsTimedWaiting);
        System.out.println(JvmInfoEnum.ThreadsTerminated + ": " + threadsTerminated);
    }


    enum JvmInfoEnum {
        JvmInfo("JVM related info etc."), // record info
        MemNonHeapUsedM("Non-heap memory used in MB"),
        MemNonHeapCommittedM("Non-heap memory committed in MB"),
        MemNonHeapMaxM("Non-heap memory max in MB"),
        MemHeapUsedM("Heap memory used in MB"),
        MemHeapCommittedM("Heap memory committed in MB"),
        MemHeapMaxM("Heap memory max in MB"),
        MemMaxM("Max memory size in MB"),
        GcCount("Total GC count"),
        GcTimeMillis("Total GC time in milliseconds"),
        ThreadsNew("Number of new threads"),
        ThreadsRunnable("Number of runnable threads"),
        ThreadsBlocked("Number of blocked threads"),
        ThreadsWaiting("Number of waiting threads"),
        ThreadsTimedWaiting("Number of timed waiting threads"),
        ThreadsTerminated("Number of terminated threads"),
        LogFatal("Total number of fatal log events"),
        LogError("Total number of error log events"),
        LogWarn("Total number of warning log events"),
        LogInfo("Total number of info log events");

        private final String desc;

        JvmInfoEnum(String desc) { this.desc = desc; }

        public String description() { return desc; }
    }

    @Test
    public void test(){
//        getThreadUsage();
//        getGcUsage();
        getMemoryUsage();

    }



}
