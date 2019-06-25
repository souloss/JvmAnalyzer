package org.jvm.analyzer.web;
import static spark.Spark.*;
import org.jvm.analyzer.service.*;
/**
 * HelloWorld
 */
public class WebLauncher {
    private static JvmThreadInfo jvmThreadInfo = new JvmThreadInfo();

    public static void main(String[] args) {
        port(8089);
        get("/stack", (req, res) -> {
            res.header("Access-Control-Allow-Origin","http://localhost:8080");  //该字段表明可供那个源跨域
            res.header("Access-Control-Allow-Methods","GET, POST, PUT");        // 该字段表明服务端支持的请求方法", value);
            res.header("Access-Control-Allow-Credentials", "true");
            res.type("application/json");
            return jvmThreadInfo.getJavaStackDump("Launcher").toJson();
        });
    }
}