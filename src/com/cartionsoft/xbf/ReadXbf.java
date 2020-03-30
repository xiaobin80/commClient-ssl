/**
 * @page License
 *
 *   Copyright (c) 2010 Guibin.Li. All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

/**
 * @(#)ReadXbf.java
 * 
 * @author xiaobin
 * @date 2009.03.06
 * @version 1.0
 */


package com.cartionsoft.xbf;

import java.io.InputStream;
import java.io.*;

/**
 * <p>JNI interface's class.
 * 
 */

public class ReadXbf {
	private int count = 0;
	private final int incI = count++;
	
	public ReadXbf(String libName) {
		// TODO Auto-generated constructor stub
		// Only one load DLL, nay
		if(incI < 2) {
			// System.load(libPath);
			if (!readNativeLib(libName)) {
				throw new RuntimeException();
			}			
		}
		else return;
	}
	
	/**
	 * <p>xbfLibR.dll Export function
	 * <p>MSSQL use
	 * 
	 * @param dimRecord -1 or 0
	 * @param filename1 xbf file is name
	 * @return xbf file is content
	 */
	public native String readRecordMsSql(int dimRecord, String filename1);
	
	/**
	 * <p>xbfLibR.dll Export function
	 * <p>MS Access library use
	 * 
	 * @param dimRecord
	 * @param filename1
	 * @return
	 */
	public native String readRecordMdb(int dimRecord, String filename1);
	
	/**
	 * <p>read native library
	 * 
	 * @param fileName library name
	 * @return success result true
	 */
	private boolean readNativeLib(String fileName) {
		try {
			InputStream input = 
				getClass().getResource("/" + fileName).openStream();
			
			File temporaryDLL = 
				File.createTempFile(getFileName(fileName), ".dll");
			FileOutputStream fileOutput = 
				new FileOutputStream(temporaryDLL);
			byte[] bAry = new byte[8192];
			for (int i = input.read(bAry); i != -1; i = input.read(bAry)) {
				fileOutput.write(bAry, 0, i);
			}
			fileOutput.close();
			temporaryDLL.deleteOnExit();
			System.load(temporaryDLL.getPath());
			
			return true;
		} catch (Throwable the) {
			// TODO: handle exception
			the.printStackTrace();
			return false;
		}
	}
	
	/**
	 * <p>move extend name
	 * 
	 * @param fullFileName 
	 * @return file name
	 */
	private String getFileName(String fullFileName) {
		int iEndIndex = fullFileName.indexOf(".");
		String result = fullFileName.substring(0, iEndIndex);
		return result;
	}
}
