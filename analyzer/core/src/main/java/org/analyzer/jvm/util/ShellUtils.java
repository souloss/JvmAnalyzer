package org.analyzer.jvm.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;

import org.analyzer.jvm.exceptions.NosupportMethod;
import org.analyzer.jvm.exceptions.ShellException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils
 */
public class ShellUtils {

    public static String env = System.getProperty("os.name").toLowerCase().substring(0, System.getProperty("os.name").indexOf(' '));
    private static final Logger log = LoggerFactory.getLogger(ShellUtils.class);
    
    private static String[] getEnvCmd(String cmd) {
        String[] ret;
        switch (env) {
        case "linux":
            // 选择sh作为shell,使用-c选项:它可以让shell将一个字串作为完整的命令来执行
            ret = new String[] { "/bin/sh", "-c", cmd };
            break;
        case "windows":
            ret = new String[] {"cmd.exe", "/c",cmd };
            break;
        default:
            throw new NosupportMethod("暂不支持当前环境:" + env);
        }
        return ret;
    }

    public static String execShellCmd(String cmd) {
        return execShellCmd(cmd, true);
    }

	public static String execShellCmd(String cmd, boolean needRetStr){
		try {
            String[] envCmd = getEnvCmd(cmd);
            
            log.debug(Arrays.asList(envCmd).toString());
            log.info("marker");
            System.out.println(Arrays.asList(envCmd).toString());

			Process process = Runtime.getRuntime().exec(envCmd);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                if(needRetStr) {
                    sb.append(line).append("\n");
                }
            }
            return sb.toString();
		} catch (Exception e) {
			throw new ShellException("命令执行异常:" + e.getMessage());
		}		
    }

    // public static Object execScriptCmd(String cmd) throws ScriptException {
    //     ScriptEngineManager manager = new ScriptEngineManager();
    //     ScriptEngine engine = manager.getEngineByName("javascript");
    //     String jsCode = "function test(){return 1;} test();";
    //     return  engine.eval(jsCode);
    // }
    
    // public static void main(String[] args) throws ScriptException {
    //     // System.out.println(System.getProperties());
    // }
}