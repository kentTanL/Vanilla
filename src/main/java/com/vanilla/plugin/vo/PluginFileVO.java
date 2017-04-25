package com.vanilla.plugin.vo;

/**
 * PluginFileVO
 */
public class PluginFileVO {
    private String path;
    
    private String name;

    private String type;
    
    private Boolean executable;
    
    private PluginFileConfigVO config;
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isExecutable() {
        return executable;
    }
    
    public Boolean getExecutable() {
        return executable;
    }
    
    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }

    public PluginFileConfigVO getConfig() {
        return config;
    }

    public void setConfig(PluginFileConfigVO config) {
        this.config = config;
    }
}
