package com.sangana.log.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.sangana.log.util.ConfigLoader;
import com.sangana.log.model.LogConfig;


/**
 * @author sksangana
 * This class loads all the client and server configuration 
 * from the logagent.config file 
 * This logagent.config file holds all the information regarding the log files to be tailed and its respective paths
 * It also holds the information regarding the LogAggregator server  
 */
public class ConfigLoader {

	public ConfigLoader() {

	}

	
	public LogConfig loadConfig() {
		
	    //to load application's properties, we use this class
	    Properties prop = new Properties();

	    FileInputStream file;

	    try {

	        File jarPath=new File(ConfigLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	        String propertiesPath=jarPath.getParent(); // getParentFile().getAbsolutePath();
	        System.out.println(" propertiesPath-"+propertiesPath);
	        file = new FileInputStream(propertiesPath+"/logagent.config");
	        prop.load(file);
	        file.close();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    
	    LogConfig config = new LogConfig();

	    config.setAppName(prop.getProperty("app_name"));
	    config.setHostName(prop.getProperty("host_name"));
	    config.setLogFileName(prop.getProperty("file_name"));
	    config.setLogFileNames(prop.getProperty("log_files"));
	    config.setLogFilePaths(prop.getProperty("log_paths"));
	    config.setLogServerURL(prop.getProperty("server_url"));
	    
	    return config;
	}

}
