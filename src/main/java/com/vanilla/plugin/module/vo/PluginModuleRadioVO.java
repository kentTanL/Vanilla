package com.vanilla.plugin.module.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * RadioModuleVO
 */
public class PluginModuleRadioVO extends AbstractPluginModuleVO {
    private List<PluginPairVO> options;

    public PluginModuleRadioVO() {
        setType("radio");
    }
    
    public List<PluginPairVO> getOptions() {
        return options == null ? new ArrayList<PluginPairVO>(0) : options;
    }
    
    public List<PluginPairVO> getOptionsDirect() {
        return options;
    }

    public void setOptions(List<PluginPairVO> options) {
        this.options = options;
    }
    
    public void addOptions(PluginPairVO pair) {
        if (options == null) {
            options = new ArrayList<PluginPairVO>(10);
        }
        
        options.add(pair);
    }

}
