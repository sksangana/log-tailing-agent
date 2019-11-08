package com.sangana.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sangana.log.model.LogConfig;
import com.sangana.log.util.ConfigLoader;


/**
 * @author sksangana
 * This is the Main class which gets run when the LogTailerAgent is kicked off
 * This class first loads the LogConfig object to see what log files that needs to be tailed
 * Then it creates a Executor service thread pool based on the number of concurrent log files that needs to be tailed
 * Then is spawns different threads to tail each log file
 */
public class LogTailerExecutor {

	public LogTailerExecutor() {

	}

	public static void main(String[] args) {

		// First Load the configuration file for log tailing agent
		ConfigLoader configLoader = new ConfigLoader();
		LogConfig logConfig = configLoader.loadConfig();

		// Get all the log file names and its respective paths
		List<String> fileNames = new ArrayList<String>(Arrays.asList( logConfig.getLogFileNames().split(",")));
		List<String> filePaths = new ArrayList<String>(Arrays.asList( logConfig.getLogFilePaths().split(",")));
		
		System.out.println("Loaded Config Properties : " + logConfig.toString());
		
		if((fileNames.size() == 0) || (filePaths.size() == 0)  || (fileNames.size() != filePaths.size())) {
			System.out.println("Please enter the log file names and its paths properly in the 'logagent.config' file : Exiting now ");
			System.exit(0);
		}
		 
		ExecutorService crunchifyExecutor = Executors.newFixedThreadPool(fileNames.size());
		
		for (int i = 0; i < fileNames.size(); i++) {
			LogFileTailer logtailer = new LogFileTailer(fileNames.get(i), filePaths.get(i), logConfig);
			// Start running log file tailer
			crunchifyExecutor.execute(logtailer);

		}

	}
}
