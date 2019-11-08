package com.sangana.log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.sangana.log.model.LogConfig;
import com.sangana.log.util.FileManager;
import com.sangana.log.util.LogSender;
import org.apache.log4j.Logger;

/**
 * @author sksangana
 * This class is the main log file tailer which implements the runnable interface.
 * This is invoked by the Executer service thread pool
 */

public class LogFileTailer implements Runnable {

	//private static final Logger LOGGER = Logger.getLogger(LogFileTailer.class);
	
	private int runEveryMillis = 2000;
	private long lastKnownPosition = 0;
	private boolean doRun = true;
	private File logFile = null;
	private RandomAccessFile randomAccessFile = null;
	private String logFileName;
	private String logFilePath;
	private String absoulteFilePath;
	private LogConfig logConfig;
 
	
	/**
	 * @param fileName The log file that needs to be tailed
	 * @param filePath  The absolute path of the log file being tailed
	 * @param config The LogConfig object which holds the the server and client information
	 */
	public LogFileTailer(String fileName , String filePath, LogConfig config ) {
		//logFile = new File((filePath + "/" + fileName).trim());
		this.logFilePath = filePath;
		this.logFileName = fileName;
		this.absoulteFilePath = filePath + "/" + fileName;
		this.logConfig = config;
	}


	private void printLine(String message) {
		System.out.println(message);
	}
 
	public void stopRunning() {
		doRun = false;
	}


	@Override
	public void run() {

		try {
			System.out.println("The log file being tailed : " + this.logFileName);
			//LOGGER.info( "The log file being tailed : " + this.logFileName);
			// Open the log file that needs to be tailed and get the file pointer
			this.randomAccessFile = FileManager.getFileAccess((this.absoulteFilePath).trim());
			// If it is not tailing the file from the start of the file then get the last known position where the log tailer stopped
			// this last known file position is stored in the "filename.pos" file 
			lastKnownPosition = FileManager.getLastKnownPosition((this.absoulteFilePath).trim());
			System.out.println("The Last Known postion is : " + lastKnownPosition);
			while (doRun) {
				// just pause the thread for 2 seconds after each read
				Thread.sleep(runEveryMillis);
				// Check the length of the log file to be tailed and only read it if the length is more than the last known position
				long fileLength = randomAccessFile.length();
				if (fileLength > lastKnownPosition) {

					this.randomAccessFile.seek(lastKnownPosition);
					String line = null;
					while ((line = this.randomAccessFile.readLine()) != null) {
						this.printLine(this.logFileName + " : " + line);
						// transmit the log text to the server
						sendLogsToServer(line, this.logFileName, this.logConfig);
					}
					// just keep saving the last known position after every continuous read
					lastKnownPosition = FileManager.getLastKnownPosition(randomAccessFile);
					FileManager.writeFilePosition(this.absoulteFilePath, lastKnownPosition);
				}
			}
		} catch (Exception e) {
			try {
				// save the last known position if any exception happens or server crashes
				FileManager.writeFilePosition(this.absoulteFilePath, lastKnownPosition);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			stopRunning();
		} finally {
			try {
				this.randomAccessFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}

	}
	
	
	/**
	 * @param text The actual log file text that needs to be transmitted to the server
	 * @param fileName The log file name that was being tailed
	 * @param config  The log Config object which has the server info
	 * @throws Exception 
	 */
	private void sendLogsToServer(String text, String fileName, LogConfig config) throws Exception {
		LogSender logSender = new LogSender();		
		System.out.println("Sending Log to Server with hosturl : "+ config.getLogServerURL() + " logfileName : " + fileName);
		logSender.sendLogsToServer(text, fileName, config);
	}
  
}
