package com.vanilla.plugin.module.vo;

/**
 * PluginModuleTextareaVO
 */
public class PluginModuleTextareaVO extends AbstractPluginModuleVO {
    private String text;

    private String rows;

    public PluginModuleTextareaVO() {
        setType("textarea");
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }
    
    
}
