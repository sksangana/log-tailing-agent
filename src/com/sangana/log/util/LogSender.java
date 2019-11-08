package com.sangana.log.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangana.log.model.LogConfig;
import com.sangana.log.model.LogText;


/**
 * @author sksangana
 * This class has the methods to do a HTTP POST request to send the logs to the Log Aggregation Service
 */
public class LogSender {

	public LogSender() {

	}
	
	
	/**
	 * @param logData The Actual Log data to be sent
	 * @param fileName  The log file name
	 * @param config  The LogConfig object which has server info
	 * @throws Exception
	 */
	public void sendLogsToServer(String logData, String fileName, LogConfig config) throws Exception {

		try {
			LogText data = new LogText();
			data.setFileName(fileName);
			data.setHost(config.getHostName());
			data.setText(logData);
			
			//Convert the LogText Object to the JSON String
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(data);

			doPostRequest(config.getLogServerURL(), json);

		} catch (Exception e) {
			String msg = "Exception thrown while trying to send log data to the server : " + config.getLogServerURL() + " Check if server is down. ";
			System.out.println(msg);
			e.printStackTrace();
			throw new Exception(msg, e);
			//System.exit(0);
		}
	}
	
	/**
	 * @param url The Log Aggregation Server URL
	 * @param json The Json String
	 * @throws Exception
	 */
	private void doPostRequest(String url, String json) throws Exception {

		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// Setting basic post request
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/json");
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(json);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			System.out.println("Sending 'POST' request to URL : " + url);
			System.out.println("Post Data : " + json);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();
			// printing result from response
			System.out.println(response.toString());
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.fillInStackTrace());
		}
	}

}
