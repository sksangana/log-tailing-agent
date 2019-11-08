package com.sangana.log.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author sksangana
 * This class manages all the File operations
 */
public class FileManager {

	public FileManager() {

	}
	
	public static RandomAccessFile getFileAccess(String filepath) throws FileNotFoundException{
			return new RandomAccessFile(new File(filepath), "rw");
	}
	
	public static boolean fileExists(String path) {
		return (new File(path).exists());
	}
	
	public static long getLastKnownPosition(String filepath) throws IOException {

		if (!fileExists(filepath+".pos")) {
			return 0;
		} else {
			RandomAccessFile file = getFileAccess(filepath+".pos");
			String pos = file.readLine();
			return Integer.valueOf(pos);
		}

	}
	
	public static long getLastKnownPosition(RandomAccessFile file) throws IOException {
		try {
			return file.getChannel().position();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void writeFilePosition(String filepath, long position) throws IOException {
		RandomAccessFile file = getFileAccess(filepath+".pos");
		file.write(String.valueOf(position).getBytes());
		file.close();
	}

}
