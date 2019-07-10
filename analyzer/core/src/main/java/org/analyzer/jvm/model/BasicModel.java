package org.analyzer.jvm.model;
import com.google.gson.*;

/**
 * BasicModel
 */
public class BasicModel {
    public String toJson(){
        return new Gson().toJson(this);
    }
}