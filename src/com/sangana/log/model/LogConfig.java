package com.sangana.log.model;

public class LogConfig {
		
		private String hostName;
		private String logFileName;
		private String appName;
		private String logServerURL;
		private String logFileNames;
		private String logFilePaths;
		private String appNames;
		
		public LogConfig() {

		}
		
		/**
		 * @return the hostName
		 */
		public String getHostName() {
			return hostName;
		}
		/**
		 * @param hostName the hostName to set
		 */
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		/**
		 * @return the logFileName
		 */
		public String getLogFileName() {
			return logFileName;
		}
		/**
		 * @param logFileName the logFileName to set
		 */
		public void setLogFileName(String logFileName) {
			this.logFileName = logFileName;
		}
		/**
		 * @return the appName
		 */
		public String getAppName() {
			return appName;
		}
		/**
		 * @param appName the appName to set
		 */
		public void setAppName(String appName) {
			this.appName = appName;
		}

		
		/**
		 * @return the logServerURL
		 */
		public String getLogServerURL() {
			return logServerURL;
		}

		/**
		 * @param logServerURL the logServerURL to set
		 */
		public void setLogServerURL(String logServerURL) {
			this.logServerURL = logServerURL;
		}


		/**
		 * @return the logFileNames
		 */
		public String getLogFileNames() {
			return logFileNames;
		}


		/**
		 * @param logFileNames the logFileNames to set
		 */
		public void setLogFileNames(String logFileNames) {
			this.logFileNames = logFileNames;
		}


		/**
		 * @return the logFilePaths
		 */
		public String getLogFilePaths() {
			return logFilePaths;
		}


		/**
		 * @param logFilePaths the logFilePaths to set
		 */
		public void setLogFilePaths(String logFilePaths) {
			this.logFilePaths = logFilePaths;
		}


		/**
		 * @return the appNames
		 */
		public String getAppNames() {
			return appNames;
		}


		/**
		 * @param appNames the appNames to set
		 */
		public void setAppNames(String appNames) {
			this.appNames = appNames;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "LogConfig [hostName=" + hostName + ", logFileName=" + logFileName + ", appName=" + appName
					+ ", logServerURL=" + logServerURL + ", logFileNames=" + logFileNames + ", logFilePaths=" + logFilePaths
					+ ", appNames=" + appNames + "]";
		}

	}