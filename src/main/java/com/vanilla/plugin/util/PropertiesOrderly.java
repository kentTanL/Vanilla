package com.vanilla.plugin.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesOrderly extends Properties {
    private static final long serialVersionUID = -2635227923565725113L;
    
    private Map<Object, Object> map = new LinkedHashMap<Object, Object>();

    /**
     * 重写put方法，按照property的存入顺序保存key到keyList，遇到重复的后者将覆盖前者。
     */
    @Override
    public synchronized Object put(Object key, Object value) {
        this.removeKeyIfExists(key);
        
        map.put(key, value);
        
        return super.put(key, value);
    }

    /**
     * 重写remove方法，删除属性时清除keyList中对应的key。
     */
    @Override
    public synchronized Object remove(Object key) {
        this.removeKeyIfExists(key);
        return super.remove(key);
    }

    @Override
    public Set<java.util.Map.Entry<Object, Object>> entrySet() {
        return map.entrySet();
    }

    public Map<Object, Object> entryMap() {
        return map;
    }
    
    /**
     * keyList中存在指定的key时则将其删除
     */
    private void removeKeyIfExists(Object key) {
        map.remove(key);
    }
}
