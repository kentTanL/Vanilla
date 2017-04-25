package com.vanilla.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * PluginEngine
 *
 */
public class PluginEngine {
    private static Map<String, String> commandMap = new HashMap<String, String>();
    
    static {
        // 初始化命令模板
        commandMap.put("sh", "sh {name} {args}");
        commandMap.put("jar", "java -jar {name} {args}");
    }
    
    private PluginManager pluginManager = null;
    
    private PluginEngine() {
        init();
    }
    
    public void init() {
        pluginManager = PluginManager.getInstance();
        
    }
    
    
    
    /** 获取实例 */
    public static PluginEngine getInstance() {
        PluginEngine filed = instance;
        if (filed == null) {
            synchronized (PluginEngine.class) {
                filed = instance;
                if (filed == null) {
                    instance = filed = new PluginEngine();
                }
            }
        }

        return instance;
    }
    
    private static volatile PluginEngine instance = null; 
}
