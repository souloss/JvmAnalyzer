package org.analyzer.console.core;

import org.analyzer.console.util.ConstraintUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Console {

    final private StringBuilder console = new StringBuilder();
    final private List<String> param = new LinkedList<>();
    final private List<String> options = new LinkedList<>();
    final private Map<String,String> optionParam = new LinkedHashMap<>();
    private String optionsPrefix = "-";
    private String optionsSuffix = "";
    private String paramPrefix = "";
    private String paramSuffix = "";


    public StringBuilder getConsole() {
        return console;
    }

    public List<String> getParam() {
        return param;
    }

    public Console addParam(String param){
        this.param.add(param);
        return this;
    }

    public List<String> getOptions() {
        return options;
    }

    public Console addOptions(String options){
        this.options.add(options);
        return this;
    }

    public Map<String, String> getOptionParam() {
        return optionParam;
    }

    public Console addOptionParam(String option, String param){
        this.optionParam.put(option, param);
        return this;
    }

    public String getOptionsPrefix() {
        return optionsPrefix;
    }

    public void setOptionsPrefix(String optionsPrefix) {
        ConstraintUtil.paramNotNullCheck(optionsPrefix);
        this.optionsPrefix = optionsPrefix;
    }

    public String getOptionsSuffix() {
        return optionsSuffix;
    }

    public void setOptionsSuffix(String optionsSuffix) {
        ConstraintUtil.paramNotNullCheck(optionsSuffix);
        this.optionsSuffix = optionsSuffix;
    }

    public String getParamPrefix() {
        return paramPrefix;
    }

    public void setParamPrefix(String paramPrefix) {
        ConstraintUtil.paramNotNullCheck(paramPrefix);
        this.paramPrefix = paramPrefix;
    }

    public String getParamSuffix() {
        return paramSuffix;
    }

    public void setParamSuffix(String paramSuffix) {
        ConstraintUtil.paramNotNullCheck(paramSuffix);
        this.paramSuffix = paramSuffix;
    }

    public String build(){
        return "";
    }
}
