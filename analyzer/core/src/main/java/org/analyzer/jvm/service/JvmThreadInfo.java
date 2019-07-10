package org.analyzer.jvm.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.analyzer.jvm.exceptions.ShellException;
import org.analyzer.jvm.model.JavaMemoryHeap;
import org.analyzer.jvm.model.JavaMemoryHisto;
import org.analyzer.jvm.model.JavaStackDump;
import org.analyzer.jvm.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.analyzer.jvm.util.ShellUtils.*;

/**
 * JvmThreadInfo
 */
public class JvmThreadInfo {
    
    static final Path javaToolDir = Paths.get(System.getProperties().getProperty("java.home")).getParent().resolve("bin");

    //jps | grep Bootstrap | awk '{print $1}'
    private static String getPid(String processName){
        String ret = null;
        switch(env){
            case "windows":
                ret = execShellCmd("for /f \"tokens=1\" %i in ('jps ^| findstr \"" + processName + "\"') do @echo %i").trim();
                break;
            case "linux":
                ret = execShellCmd(javaToolDir.resolve("jps") + " | grep " + processName + " | awk '{print $1}'");
                break;
        }
        if(StringUtils.isEmpty(ret)) {
            throw new ShellException("进程" + processName + "不存在!");
        }
        return ret;
    }

    private static String getThreadStackDump(String processName) {
        return execShellCmd("\"" + javaToolDir.resolve("jstack") + "\" " + getPid(processName));
    }

    private static String getThreadMemoryMapHeap(String processName) {
        return execShellCmd("\"" + javaToolDir.resolve("jmap") + "\" -heap " + getPid(processName));
    }

    private static String getThreadMemoryMapHisto(String processName) {
        return execShellCmd("\"" + javaToolDir.resolve("jmap") + "\" -histo " + getPid(processName));
    }

    /*--------------- public Api ------------------------*/
    public static JavaStackDump getJavaStackDump(String processName){
        JavaStackDump ret = new JavaStackDump();
        ret.setProcessName(processName);
        String[] items = getThreadStackDump(processName).split("\\n\\n");
        ret.setPid(getPid(processName));
        String[] mateInfo = items[0].split("\\n");
        ret.setDate(mateInfo[0]);
        ret.setJvmVersion(mateInfo[1]);

        Pattern javaThreadPattern = Pattern.compile("(\".*\") #(\\d+)( |daemon ){1,2}prio=(-?\\d+) os_prio=(-?\\d+) tid=(\\w+) nid=(\\w+) ([ \\w\\.\\(\\)]*)\\[(\\w*)\\]\\s*java.lang.Thread.State: (.*)\\s*([\\S\\s]*)");
        Pattern vmThreadPattern = Pattern.compile("(\".*\") os_prio=(-?\\d+) tid=(\\w+) nid=(\\w+) ([ \\w\\.\\(\\)]*)");

        int index = 1;
        while(index < items.length){
            JavaStackDump.ThreadDump threadDump = new JavaStackDump.ThreadDump();
            Matcher matcher = javaThreadPattern.matcher(items[index]);
            if(matcher.find()){
                threadDump.setThreadName(matcher.group(1));
                threadDump.setNo(matcher.group(2));
                threadDump.setDaemon(!(StringUtils.isEmpty(matcher.group(3))||matcher.group(3).matches("\\s*")));
                threadDump.setPrio(matcher.group(4));
                threadDump.setOsPrio(matcher.group(5));
                threadDump.setTid(matcher.group(6));
                threadDump.setNid(matcher.group(7));
                threadDump.setThreadStatus(matcher.group(8));
                threadDump.setThreadOffice(matcher.group(9));
                if(matcher.group(10).contains("("))
                    threadDump.setJavaThreadState(Thread.State.valueOf(matcher.group(10).substring(0, matcher.group(10).indexOf("(")-1)));
                else
                    threadDump.setJavaThreadState(Thread.State.valueOf(matcher.group(10)));
                threadDump.setThreadDump(matcher.group(11));
                ret.addThreadDumps(threadDump);
                index++;
            }else{
                // System.err.println("普通线程遍历完毕,开始遍历系统线程");
                // System.err.println(index + ";" + items[index]);
                break;
            }
        }

        while(index < items.length){
            JavaStackDump.ThreadDump threadDump = new JavaStackDump.ThreadDump();
            Matcher matcher = vmThreadPattern.matcher(items[index]);
            if(matcher.find()){
                threadDump.setThreadName(matcher.group(1));
                threadDump.setOsPrio(matcher.group(2));
                threadDump.setTid(matcher.group(3));
                threadDump.setNid(matcher.group(4));
                threadDump.setThreadStatus(matcher.group(5));
                ret.addThreadDumps(threadDump);
                index++;
            }else{
                // System.out.println("全部遍历完毕");
                // System.err.println(index + ";" + items[index]);
                break;
            }
        }
        ret.setJNIGRS(items[items.length-1].substring(22));
        return ret;
    }

    public static JavaMemoryHeap getJavaMemoryHeap(String processName){
        JavaMemoryHeap ret = new JavaMemoryHeap();

        return ret;
    }

    public static JavaMemoryHisto getJavaMemoryHisto(String processName){
        JavaMemoryHisto ret = new JavaMemoryHisto();

        return ret;
    }

    private static Logger log = LoggerFactory.getLogger(JvmThreadInfo.class);

    public static void main(String[] args) {
         log.info("marker, format, arg");
         System.out.println("-------------------------------------");
         System.out.println(getJavaStackDump("Launcher").getJvmVersion());
    }
}

