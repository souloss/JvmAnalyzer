package org.analyzer.console.core;

// import org.analyzer.console.util.ConstraintUtil;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.analyzer.console.exception.NotSupportException;
import org.analyzer.console.util.PlatformUtil;
/**
 * 一个命令的组成基本上是这样组成的
 * console [option]* [params]* [expression]*
 * 通常在windows中 options 带有 "/"前缀，而在Linux中 options 带有"-"前缀
 * 但这并不是强制性的，仅仅是一种低约束性的约定
 * 所以这里根据环境进行初始化，但仍然可以进行设置
 * 例子：构造命令“find /app/soft/apache-tomcat-7.0.63/logs/ -mmin +10 -name '*.*' -exec rm -rf {} \;”
 * String LOG_PATH = "/app/soft/apache-tomcat-7.0.63/logs/";   // 这里可以动态获取
 * String KEEP_MIN = "+10";    
 * new ConsoleBuild("find")
 * 	   .addParam(LOG_PATH)
 * 	   .addExpressions("mmin",KEEP_MIN)
 *     .addExpressions("name","'*.*'")
 *     .addExpressions("exec","rm -rf {} \\;").build();
 *即可得到需要的结果
 *
 * @author Administrator
 *
 */
public class ConsoleBuild {

	//命令
    final private String console;
    //选项集合
    final private List<String> options = new LinkedList<>();
    //参数集合
    final private List<String> params = new LinkedList<>();
    //表达式集合,表达式由 '选项 参数' 组成
    final private Map<String,String> expressions = new LinkedHashMap<>();
    //选项前后缀
    private String optionsPrefix = "-";
    private String optionsSuffix = "";
    //参数前后缀
    private String paramPrefix = "";
    private String paramSuffix = "";
    //环境变量
    private final Map<String,String> env = new LinkedHashMap<>();
    private final static String SPACE = " ";

    enum Platform{
        WINDOWS,
        LINUX;
        /**
         * 返回目前的操作系统枚举，若没有则返回缺省的LINUX
         * @return
         */
        static Platform getPlatform(){
            for(Platform OS : Platform.values()){
                if(PlatformUtil.OS.contains(OS.name())){
                    return OS;
                }
            }
            return LINUX;
        }
    };

    public ConsoleBuild(String console) {
        this.console = console;
    }

    /**
     * 构造命令时绑定操作系统，如果操作系统和当前绑定的不同则会抛出异常
     * @param platform 操作系统枚举
     * @return
     */
    public ConsoleBuild bindOS(Platform platform){
        if(platform.equals(Platform.getPlatform())){
            return this;
        }
        else{
            throw new NotSupportException(Platform.getPlatform() + "不匹配的操作系统:" + platform);
        }
    }

    public String getConsole() {
        return console;
    }

    public List<String> getParams() {
        return params;
    }

    public ConsoleBuild addParam(String param){
        this.params.add(param);
        return this;
    }

    public List<String> getOptions() {
        return options;
    }

    public ConsoleBuild addOptions(String options){
        this.options.add(options);
        return this;
    }

    public Map<String, String> getExpressions() {
        return expressions;
    }

    public ConsoleBuild addExpressions(String option, String param){
        this.expressions.put(option, param);
        return this;
    }
    
    public Map<String, String> getEnv() {
        return env;
    }

    public ConsoleBuild addEnv(String name, String value){
        this.env.put(name, value);
        return this;
    }

    public String getOptionsPrefix() {
        return optionsPrefix;
    }

    public ConsoleBuild setOptionsPrefix(String optionsPrefix) {
        this.optionsPrefix = optionsPrefix;
        return this;
    }

    public String getOptionsSuffix() {
        return optionsSuffix;
    }

    public ConsoleBuild setOptionsSuffix(String optionsSuffix) {
        this.optionsSuffix = optionsSuffix;
        return this;
    }

    public String getParamPrefix() {
        return paramPrefix;
    }

    public ConsoleBuild setParamPrefix(String paramPrefix) {
        this.paramPrefix = paramPrefix;
        return this;
    }

    public String getParamSuffix() {
        return paramSuffix;
    }

    public ConsoleBuild setParamSuffix(String paramSuffix) {
        this.paramSuffix = paramSuffix;
        return this;
    }

    public String build(){
        final StringBuilder result = new StringBuilder();
        //设置环境变量
        for(String name:env.keySet()){
            result.append(name);
            result.append("=");
            result.append(env.get(name));
            result.append(";");
            result.append(SPACE);
        }
        //开始执行命令
        result.append(console);
        //填充选项
        for(String option:options){
            result.append(SPACE);
            result.append(optionsPrefix);
            result.append(option);
            result.append(optionsSuffix);
        }
        //填充参数
        for (String param: params) {
            result.append(SPACE);
            result.append(paramPrefix);
            result.append(param);
            result.append(paramSuffix);
        }
        //填充表达式
        for(String key : expressions.keySet()){
            result.append(SPACE );
            result.append(optionsPrefix);
            result.append(key);
            result.append(optionsSuffix);
            result.append(SPACE);
            result.append(paramPrefix);
            result.append(expressions.get(key));
            result.append(paramSuffix);
        }
        //得到命令
        return result.toString();
    }
    
    
    public static void main(String[] args) {
    	 String LOG_PATH = "/app/soft/apache-tomcat-7.0.63/logs/";
    	 String KEEP_MIN = "+10";
    	 System.out.println(    	  
    	    new ConsoleBuild("find")
    	      .addEnv("Date", "'date +%Y%m$d'")
    	  	  .addParam(LOG_PATH)
    	  	  .addExpressions("mmin",KEEP_MIN)
    	      .addExpressions("name","'*.*'")
              .addExpressions("exec","rm -rf {} \\;").build());
        String test = new ConsoleBuild("tar")
                        .bindOS(Platform.LINUX)
                        .addOptions("xvf")
                        .addParam("/app/a.tar")
                        .build();
        System.out.print(test);
	}
}