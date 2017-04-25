package com.vanilla.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vanilla.plugin.exception.PluginConfigParseException;
import com.vanilla.plugin.vo.PluginFileConfigVO;
import com.vanilla.plugin.vo.PluginFileVO;
import com.vanilla.plugin.vo.PluginVO;

/**
 * PluginManager
 *
 */
public class PluginManager {
    private static final String PLUGIN_PATH = "/plugins";
    
    /** 可执行的类型列表 */
    private static List<String> executableTypeLs = new ArrayList<String>(5);
    
    /** 不被显示的忽略的格式列表 */
    private static List<String> pluginFileIgnores = new ArrayList<String>(3);
    
    static {
        executableTypeLs.add("sh");
        executableTypeLs.add("jar");
        
        pluginFileIgnores.add(".plugin");
        pluginFileIgnores.add("plug.in");
    }
    
    private PluginManager() {}
    
    public List<?> listPlugins() {
        File pluginDir = new File(getPluginAbsolutePath());
        
        List<PluginVO> pluginLs = new ArrayList<PluginVO>();
        
        if (pluginDir.exists() && pluginDir.isDirectory()) {
            File[] files = pluginDir.listFiles();
            
            for (File f : files) {
                PluginVO plugin = new PluginVO();
                plugin.setName(f.getName());
                plugin.setFiles(listPluginFiles(f));
                
                pluginLs.add(plugin);
            }
        }
        
        return pluginLs;
    }
    
    /**
     * 列出插件下的所有文件
     * @param pluginParent
     * @return
     */
    private List<PluginFileVO> listPluginFiles(File pluginParent) {
        List<PluginFileVO> pluginFileLs = new ArrayList<PluginFileVO>(5);
        
        File[] files = pluginParent.listFiles();
        
        String filename, filepath, fileType;
        boolean isExecutable;
        for (File f : files) {
            filename = f.getName();
            
            if (isIgnore(filename)) {
                continue;
            }
            
            filepath = getPluginAbsolutePath() + "/" + filename;
            fileType = getFileType(filename);
            isExecutable = executableTypeLs.contains(fileType);
            
            PluginFileVO pluginFile = new PluginFileVO();
            pluginFile.setPath(filepath);
            pluginFile.setName(filename);
            pluginFile.setType(fileType);
            pluginFile.setExecutable(isExecutable);
            
            try {
                PluginFileConfigVO pluginConfig = PluginFileConfigManager.getInstance().getPluginConfig(pluginParent.getName(), f.getName());
                pluginFile.setConfig(pluginConfig);
            } catch (PluginConfigParseException e) {
                e.printStackTrace();
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            
            pluginFileLs.add(pluginFile);
        }
        
        return pluginFileLs;
    }
    
    /**
     * 是否是被忽略的
     * @param fileName
     * @return
     */
    private boolean isIgnore(String fileName) {
        for (String ignore : pluginFileIgnores) {
            if (fileName.endsWith(ignore)) {
                return true;
            }
        } 
        
        return false;
    }
    
    /**
     * 获取插件的绝对路径
     * @return
     */
    public static String getPluginAbsolutePath() {
        return System.getProperty("user.dir") + PLUGIN_PATH;
    }
    
    private String getFileType(String filename) {
        if (filename == null) {
            return null;
        }
        
        String[] splitArr = filename.split("\\.");
        
        if (splitArr.length <= 1) {
            return null;
        }
        
        return splitArr[splitArr.length - 1];
    }
    
    /** 获取实例 */
    public static PluginManager getInstance() {
        PluginManager filed = instance;
        if (filed == null) {
            synchronized (PluginManager.class) {
                filed = instance;
                if (filed == null) {
                    instance = filed = new PluginManager();
                }
            }
        }

        return instance;
    }
    
    private static volatile PluginManager instance = null;
}
