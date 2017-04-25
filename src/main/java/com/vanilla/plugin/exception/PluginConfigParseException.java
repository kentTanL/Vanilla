package com.vanilla.plugin.exception;

/**
 * PluginConfigParseException
 * 
 */
public class PluginConfigParseException extends Exception {
    private static final long serialVersionUID = 1326947403431821542L;

    public PluginConfigParseException(String message) {
        super(message);
    }
    
    public PluginConfigParseException(String message, Throwable e){
        super(message, e);
    }
    
    public PluginConfigParseException(Throwable ex, String k){
        super("Parsing configuration field of plugin exception, and now k is: " + k + ". Message:\n" + ex.getMessage() + "\n", ex);
    }
}
