package com.vanilla.plugin;

import java.io.File;
import java.io.IOException;

import com.vanilla.plugin.exception.PluginConfigParseException;
import com.vanilla.plugin.parser.PluginFileConfigParser;
import com.vanilla.plugin.vo.PluginFileConfigVO;

/**
 * PluginFileConfigManager
 */
public class PluginFileConfigManager {
    public static final String CONFIG_SUFFIX = ".plugin";
    
    private PluginFileConfigManager() {
    }
    
    /**
     * 解析
     * 
     * @param pluginName
     * @param pluginFileName
     * @return
     * @throws PluginConfigParseException
     * @throws IOException
     */
    public PluginFileConfigVO getPluginConfig(String pluginName, String pluginFileName) throws PluginConfigParseException, IOException {
        PluginFileConfigVO pluginConfig = new PluginFileConfigVO();
        
        try {
            pluginConfig = PluginFileConfigParser.getInstance().parse(getAbsoluteConfigPath(pluginName, pluginFileName));
        } catch (PluginConfigParseException e) {
            throw e;
        }
        
        return pluginConfig;
    }
    
    /**
     * 获取配置文件的绝对路径
     * @param pluginName
     * @param pluginFileName
     * @return
     */
    public String getAbsoluteConfigPath(String pluginName, String pluginFileName) {
        String pluginAbsolutePath = PluginManager.getPluginAbsolutePath(); 
        
        return pluginAbsolutePath + File.separator + pluginName 
                + File.separator + pluginFileName + CONFIG_SUFFIX;
    }
    
    /** 获取实例 */
    public static PluginFileConfigManager getInstance() {
        PluginFileConfigManager filed = instance;
        if (filed == null) {
            synchronized (PluginFileConfigManager.class) {
                filed = instance;
                if (filed == null) {
                    instance = filed = new PluginFileConfigManager();
                }
            }
        }

        return instance;
    }
    
    private static volatile PluginFileConfigManager instance = null;
}
