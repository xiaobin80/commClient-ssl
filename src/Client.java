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

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cartionsoft.messages.*;
import com.cartionsoft.xbf.*;
import java.io.*;

import com.cartionsoft.utils.*;
import com.cartionsoft.ssl.client.*;
import com.cartionsoft.ssl.comm.CommSSL;
import com.cartionsoft.ssl.trans.Transmission;

import com.cartionsoft.utils.message.*;

public class Client implements Runnable {

	private final int DEFAULT_PORT = 7006;
	
	private ReadConfig xmlRead;
	private ReadXbf xbf;
	private DataCRC32 csvFile;
	private DataCRC32 sendFile;
	private LogOper logger;
	private String ipLocal;
	private int port;
	private String keyPath;
	private String libPath;
	private String xbfPath;
	private String csvPath;
	private String sendPath;
	private String receivePath;
	private String targetPath;
	private int stationID;
	private String xbfContent;
	private String keyPWD;
	
	/**
	 * Concrete Creator - railway
	 */
	private CommSSL railwayor;
	
	/**
	 * Concrete Creator - station
	 */
	private CommSSL stationor;
	
	/**
	 * Concrete Product - railway
	 */
	private Transmission railway;
	
	/**
	 * Concrete Product - station
	 */
	private Transmission station;
	
	/**
	 * <p> Station Type - station and railway </p>
	 * If the modulo is 0, then this is station.
	 */
	private int stationType;
	
	private MessagesParser messageParser;
	
	
	/**
	 * Provide XML path and odd or even
	 * @param xmlPath
	 * @param stationType
	 */
	public Client(String xmlPath, String stationType) {
		// TODO Auto-generated constructor stub
		xmlRead = new ReadConfig(xmlPath);
		this.stationType = Integer.parseInt(stationType);
		
		logger = new LogOper();
		messageParser = new ByteParser();
	}
	
	/**
	 * XML file for configuration information.
	 * @throws Exception
	 */
	public void getProperty() throws Exception {
		ipLocal = xmlRead.getElement(0, "ip1");
		String strPort = xmlRead.getElement(1, "port1");
		if(strPort.equals("")) {
			port = DEFAULT_PORT;
		} else {	
			port = Integer.parseInt(strPort);
			if(port < 7000) {
				port = DEFAULT_PORT;
			}
		}
		
		keyPath = xmlRead.getElement(2, "key1");
		libPath = xmlRead.getElement(3, "lib-file1");
		xbfPath = xmlRead.getElement(4, "xbf-file1");
		csvPath = xmlRead.getElement(5, "csv-file1");
		sendPath = xmlRead.getElement(5, "send-file1");
		receivePath = xmlRead.getElement(5, "receive-file1");
		targetPath = xmlRead.getElement(5, "target-file1");
	}
	
	/**
	 * <p> Set environment variables, primarily for SSL. </p>
	 */
	public void setProperty() {
		System.setProperty("javax.net.ssl.trustStore", keyPath);
	    System.setProperty("javax.net.ssl.trustStorePassword", keyPWD);
	}
	
	/**
	 * <p> Initialization process - the method call sequence. </p>
	 * @throws Exception
	 */
	public void init() throws Exception {
		getProperty();
		getStationID();
		getKeyPWD();
		setProperty();
	}
	
	/**
	 * <p> Send file is duplicate </p>
	 * @return false
	 * @throws IOException
	 */
	private boolean isDuplicate() throws IOException {
		boolean result = false;
		
		csvFile = new DataCRC32(csvPath);
		String strCrc32 = csvFile.getCRC32Value();
		sendFile = new DataCRC32(sendPath);
		String strSendCrc32 = sendFile.getCRC32Value();
		
		if(strCrc32.equals(strSendCrc32)) {
			result = true;
		}
		return result;
	}
	
	/**
	 * <p> read private strXbfContent value
	 * 
	 * @param XbfContent xbf file's content
	 * @return xbf file's DB user
	 */
	public String getDBUserMsSql(String xbfContent) {
		int iBeginIndex = xbfContent.indexOf("ID=");
		int iEndIndex = xbfContent.indexOf(";I");
		String strUser = xbfContent.substring(iBeginIndex + 3, iEndIndex);
		return strUser;
	}
	
	/**
	 * <p> read private strXbfContent value
	 * 
	 * @param XbfContent xbf file's content
	 * @return xbf file's password
	 */
	public String getPWDMsSql(String xbfContent) {
		int iBeginIndex = xbfContent.indexOf("d=");
		int iEndIndex = xbfContent.indexOf(";Per");
		String strPwd = xbfContent.substring(iBeginIndex + 2, iEndIndex);
		return strPwd;
	}
	
	/**
	 * <p> Read XBF file "username" part of the first number. </p>
	 * @return stationID
	 */
	private int getStationID() {
		xbf = new ReadXbf(libPath);
		xbfContent = xbf.readRecordMsSql(-1, xbfPath);
		String strUser = getDBUserMsSql(xbfContent);
		String strStationID = strUser.substring(0, 1);
		stationID = Integer.parseInt(strStationID);
		return stationID;
	}
	
	/**
	 * <p> Read XBF file "Password" section. </p>
	 * @return keyPWD
	 */
	private String getKeyPWD() {
		String strPwd = getPWDMsSql(xbfContent);
		keyPWD = strPwd;
		return keyPWD;
	}
	
	/**
	 * <p> Generated packet. </p>
	 * @return result
	 * @throws Exception
	 */
	private byte[] genMessages() throws Exception {
		List<Messages> containMessages = new ArrayList<Messages>();
		
		Report branchMessages = new Report();
		
		Messages headCmd = new MessageCMD(3);
		Messages station = new StationID(stationID);
		Messages customDate = new DateTimes();
		Messages counts = new CountSend();
		Messages md5value = new FileMD5(sendPath);
		Messages fileMessages = new FileReport(sendPath);
		Messages ipMessages = new LocalIP();
		
		branchMessages.addToMessages(headCmd);
		branchMessages.addToMessages(station);
		branchMessages.addToMessages(customDate);
		branchMessages.addToMessages(counts);
		branchMessages.addToMessages(md5value);
		branchMessages.addToMessages(fileMessages);
		branchMessages.addToMessages(ipMessages);
		
		containMessages.add(branchMessages);
		
		byte[] result = branchMessages.putMessages();
		
		return result;
	}
	
	/**
	 * <p> File save as. </p>
	 * @param sourceFile
	 * @param targetFile
	 */
	private void saveFile(String sourceFile, String targetFile) {
		try {
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(targetFile);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			
			out.flush();
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}
	
	/**
	 * <p> Byte arrays converter character arrays </p>
	 * @param bytArray
	 * @return result
	 */
	private char[] convert2charArray(byte[] bytArray) {
		char[] result = new char[bytArray.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = (char) bytArray[i];
		}
		
		return result;
	}
	
	/**
	 * <p> Character arrays converter String </p>
	 * @param chArray
	 * @return result
	 */
	private String convert2String(char[] chArray) {
		String result = "";
		for (int i = 0; i < chArray.length; i++) {
			result += chArray[i];
		}
		return result;
	}
	
	/**
	 * <p> Calculation packet "text" part of the MD5 value </p>
	 * @param bytArray
	 * @return result
	 */
	private String getMD5String(byte[] bytArray) {
		MD5 md5 = new MD5();
		md5.init();
		char[] bytInput = convert2charArray(bytArray);
		md5.update(bytInput, bytArray.length);
		md5.md5final();
		
		
		StringBuffer strBuff = new StringBuffer();
		strBuff = md5.toHexString();
		String result = "";
		for (int i = 0; i < strBuff.length(); i++) {
			char ch = strBuff.charAt(i);
			result += String.valueOf(ch);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		try {

			while(true) {
				if(stationType % 2 == 0) {
					if(!isDuplicate()) {
						saveFile(sendPath, csvPath);
						byte[] data = genMessages();
						stationor = new CommStation();
						station = stationor.createTrans(ipLocal, port);
						station.sendData(data);	
					}			
				} else {
					railwayor = new CommRailway();
					railway = railwayor.createTrans(ipLocal, port);
					byte[] rData = railway.receiveData();
					byte[] textData = messageParser.getMessages(rData);
					byte[] md5Data = messageParser.getMD5Message(rData);
					
					String strTextDataMD5 = getMD5String(textData);
					char[] chMD5Data = convert2charArray(md5Data);
					String strMD5Data = convert2String(chMD5Data);
					if(strTextDataMD5.equals(strMD5Data)) {
						OutputStream out = new DataOutputStream(
								new FileOutputStream(receivePath));
						out.write(textData);
						out.flush();
						out.close();
						saveFile(receivePath, targetPath);
					}
					
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

	
	
	
	
	
	/**
	 * @param args e:/configQ.xml
	 * @param args 2 or 3
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			System.out.println("Usage: Found not configure file!");
			return;
		}
		
		Client client = new Client(args[0], args[1]);
		try {
			client.init();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ExecutorService execServic = Executors.newCachedThreadPool();
		execServic.execute(client);
		execServic.shutdown();
	}


}
