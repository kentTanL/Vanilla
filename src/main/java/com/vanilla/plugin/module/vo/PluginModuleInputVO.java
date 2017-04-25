package com.vanilla.plugin.module.vo;

/**
 * InputModuleVO
 */
public class PluginModuleInputVO extends AbstractPluginModuleVO {
    private String text;
    
    public PluginModuleInputVO() {
        setType("input");
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
