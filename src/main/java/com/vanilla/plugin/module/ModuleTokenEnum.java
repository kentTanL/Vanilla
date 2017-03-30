package com.vanilla.plugin.module;

public enum ModuleTokenEnum {
    INPUT("INPUT"),
    RADIO("RADIO"),
    CHECKBOX("CHECKBOX"),
    
    NAME("NAME"),
    TYPE("TYPE"),
    TEXT("TEXT"),
    CALSS("CLASS"),
    PLACEHOLDER("PLACEHOLDER"),
    LABEL("LABEL");
    
    String text;
    
    ModuleTokenEnum(){}
    
    ModuleTokenEnum(String text) {
        this.text = text;
    }
}
