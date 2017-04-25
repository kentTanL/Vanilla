package com.vanilla.plugin.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * PluginVO
 *
 */
public class PluginVO {
    private String name;

    private List<PluginFileVO> files;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PluginFileVO> getFiles() {
        return files;
    }

    public void setFiles(List<PluginFileVO> files) {
        this.files = files;
    }
    
    public void addFile(PluginFileVO file) {
        if (files == null) {
            files = new ArrayList<PluginFileVO>(6);
        }

        files.add(file);
    }
}
