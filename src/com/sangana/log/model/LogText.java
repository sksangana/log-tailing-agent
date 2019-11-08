package com.sangana.log.model;


/**
 * @author sksangana
 *
 */

public class LogText {

//	String id;
//	/**
//	 * @return the id
//	 */
//	public String getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(String id) {
//		this.id = id;
//	}

	private String host;

	private String fileName;
	
	private String text;
	
	private String appName;

	public LogText() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
}
