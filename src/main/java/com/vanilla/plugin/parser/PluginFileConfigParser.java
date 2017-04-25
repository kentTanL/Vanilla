package com.vanilla.plugin.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vanilla.plugin.exception.PluginConfigParseException;
import com.vanilla.plugin.module.vo.AbstractPluginModuleVO;
import com.vanilla.plugin.module.vo.PluginModuleInputVO;
import com.vanilla.plugin.module.vo.PluginModuleKeyInfoVO;
import com.vanilla.plugin.module.vo.PluginModuleRadioVO;
import com.vanilla.plugin.module.vo.PluginModuleTextareaVO;
import com.vanilla.plugin.module.vo.PluginPairVO;
import com.vanilla.plugin.util.PropertiesOrderly;
import com.vanilla.plugin.vo.PluginFileConfigVO;

public class PluginFileConfigParser {
    private static final String MODULE_PREFIX = "module";
    private static final String MODULE_PATTERN = "module.[a-z]+.[a-z]+-[0-9]+";
    
    private PluginFileConfigParser() {
    }
    
    public boolean isModulePropertKey(String k) {
        return Pattern.matches(MODULE_PATTERN, k);
    }
    
    /**
     * 解析
     * @param entrySet
     * @return
     * @throws PluginConfigParseException
     */
    // FIXME 完善这里的解析
    public PluginFileConfigVO parse(String configPath) throws PluginConfigParseException, IOException {
        Set<Entry<Object, Object>> entrySet = null;
        try {
            entrySet = getPropertyEntrys(configPath);
        } catch (FileNotFoundException e) {
            // 不存在就不存在，很正常
        }
        
        if (entrySet == null || entrySet.isEmpty()) {
            return null;
        } 
        
        PluginFileConfigVO pluginConfig = new PluginFileConfigVO();
        
        Map<String, AbstractPluginModuleVO> moduleMap = new LinkedHashMap<String, AbstractPluginModuleVO>();
        
        Iterator<Entry<Object, Object>> it = entrySet.iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            String k = String.valueOf(entry.getKey());
            String v = String.valueOf(entry.getValue());
            // 如果是Module类型的属性Key
            if (isModulePropertKey(k)) {
                PluginModuleKeyInfoVO keyInfo = getModuleKeyInfo(k);
                
                AbstractPluginModuleVO module = moduleMap.get(getModuleMapKey(keyInfo));
                if (module == null) {
                    if ("radio".equalsIgnoreCase(keyInfo.getType())) {
                        module = new PluginModuleRadioVO();
                    } else if ("input".equalsIgnoreCase(keyInfo.getType())) {
                        module = new PluginModuleInputVO();
                    } else if ("textarea".equalsIgnoreCase(keyInfo.getType())) {
                        module = new PluginModuleTextareaVO();
                    }
                }
                
                // FIXME 新的解析方法
               //  module.putAttribute(keyInfo.getName(), v);
                moduleMap.put(getModuleMapKey(keyInfo), module);
            } else {
                pluginConfig.putProperty(k, v);
            }
        }
        
        pluginConfig.setModules(moduleMap);
        
        return pluginConfig;
    }
    
    /**
     * 获取配置文件的属性Entrys
     * @param configPath
     * @return
     * @throws FileNotFoundException 如果配置文件不存在，则抛出此异常
     * @throws IOException - 如果发生IO异常，则抛出此异常
     */
    private Set<Entry<Object, Object>> getPropertyEntrys(String configPath) throws FileNotFoundException, IOException {
        Properties prop = new PropertiesOrderly();
        
        prop.load(new FileInputStream(new File(configPath)));
        
        Set<Entry<Object, Object>> entryMap = prop.entrySet();
        
        return entryMap;
    }
    
    /**
     * 获取Module的Index
     * @param k
     * @return
     * @throws PluginConfigParseException
     */
    private PluginModuleKeyInfoVO getModuleKeyInfo(String k) throws PluginConfigParseException {
        String pattern = "(" + MODULE_PREFIX + ").([a-z]+).([a-z]+)-([0-9]+$)";
        
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(k);
        
        if (m.find()) {
            PluginModuleKeyInfoVO moduleKeyInfo = new PluginModuleKeyInfoVO();
            moduleKeyInfo.setPrefix(m.group(1));
            moduleKeyInfo.setType(m.group(2));
            moduleKeyInfo.setName(m.group(3));
            moduleKeyInfo.setIndex(m.group(4));
            
            return moduleKeyInfo;
        }
        
        throw new PluginConfigParseException("Module property key end must contain umbers , now key is: " + k);
    } 
    
    /**
     * 解析属性值成List格式
     * @param v
     * @return
     */
    public static List<PluginPairVO> parseValueToList(String v) {
        List<PluginPairVO> pairs = new ArrayList<PluginPairVO>();
        
        String[] arr = v.split(",");
        
        for (String e : arr) {
            PluginPairVO pair = null;
            
            String[] n_v = e.split(":");
            if (n_v.length == 2) {
                pair = new PluginPairVO(n_v[0], n_v[1]);
            } else if (n_v.length == 1) {
                pair = new PluginPairVO(n_v[0], n_v[0]);
            } else {
                continue;
                // throw new PluginConfigParseException("Module property vlaue can't parse to list , now value is: " + e);
            }
            
            pairs.add(pair);
        }
        
        return pairs;
    }
    
    private String getModuleMapKey(PluginModuleKeyInfoVO keyInfo) {
        return String.format("%s_%s-%s", keyInfo.getPrefix(), keyInfo.getType(), keyInfo.getIndex());
    }
    
    /** 获取实例 */
    public static PluginFileConfigParser getInstance() {
        PluginFileConfigParser filed = instance;
        if (filed == null) {
            synchronized (PluginFileConfigParser.class) {
                filed = instance;
                if (filed == null) {
                    instance = filed = new PluginFileConfigParser();
                }
            }
        }

        return instance;
    }
    
    private static volatile PluginFileConfigParser instance = null;
}
