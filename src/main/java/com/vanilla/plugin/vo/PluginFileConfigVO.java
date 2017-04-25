package com.vanilla.plugin.vo;

import java.util.HashMap;
import java.util.Map;

import com.vanilla.plugin.module.vo.AbstractPluginModuleVO;

/**
 * PluginFileConfigVO
 */
public class PluginFileConfigVO {
    private Map<String, String> propertys = null;
    
    private Map<String, AbstractPluginModuleVO> modules = null;
    
    public void putProperty(String key, String value) {
        if (propertys == null) {
            propertys = new HashMap<String, String>(10);
        }
        
        propertys.put(key, value);
    }
    
    public Map<String, String> getPropertys() {
        return propertys == null ? new HashMap<String, String>(0) : propertys;
    }
    
    public Map<String, String> getPropertysDirect() {
        return propertys;
    }
    
    public void setPropertys(Map<String, String> propertys) {
        this.propertys = propertys;
    }

    public Map<String, AbstractPluginModuleVO> getModules() {
        return modules == null ? new HashMap<String, AbstractPluginModuleVO>(0) : modules;
    }
    
    public Map<String, AbstractPluginModuleVO> getModulesDirect() {
        return modules;
    }

    public void setModules(Map<String, AbstractPluginModuleVO> modules) {
        this.modules = modules;
    }
    
}
