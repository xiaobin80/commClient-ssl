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

package com.cartionsoft.ssl.trans;

import java.io.*;
import java.util.*;
import javax.net.ssl.*;

public abstract class Transmission {

	protected String host;
	protected int port;
	private SSLSocket socket;
	
	private SSLSocket createSocket() throws Exception {
		SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket clientSocket = (SSLSocket) clientFactory.createSocket(host, port);
		
		String[] enableList = filterSuites(clientSocket.getEnabledCipherSuites());
		clientSocket.setEnabledCipherSuites(enableList);
		
		return clientSocket;
	}
	
	/**
	 * filter high algorithms
	 * @param jvmSupports
	 * @return result
	 */
	private String[] filterSuites(String[] jvmSupports) {
		
		List<String> strList = new ArrayList<String>();

		for (int i = 0; i < jvmSupports.length; i++) {
			if ((jvmSupports[i].indexOf("_anon_") > -1) || 
					(jvmSupports[i].indexOf("_KRB5_") > -1) ||
					(jvmSupports[i].indexOf("_RC4_") > -1) ||
					(jvmSupports[i].indexOf("_EXPORT_") > -1) ||
					(jvmSupports[i].indexOf("_NULL_") > -1)) {
			} else {
				strList.add(jvmSupports[i]);
			}
		}
		
		int i = -1;
		String[] result = new String[strList.size()];
		for (String string : strList) {
			i++;
			result[i] = string;
		}
		
		return result;
	}
	
	/**
	 * <p> Receive byte array data </p>
	 * @return data
	 */
	public byte[] receiveData() throws Exception {
		// TODO Auto-generated method stub
		socket = createSocket();
		
		InputStream in = new BufferedInputStream(
				socket.getInputStream());
		int b;
		List<Byte> listBuffer = new ArrayList<Byte>();
		while((b = in.read()) != -1) {
			listBuffer.add((byte) b);
		}
		
		int i = 0;
		byte[] data = new byte[listBuffer.size()];
		for (Byte byte1 : listBuffer) {		
			data[i] = byte1;
			i++;
		}

		in.close();
		
		return data;
	}
	
	/**
	 * <p> Send byte array data </p>
	 * @param data
	 */
	public void sendData(byte[] data) throws Exception {
		socket = createSocket();
		OutputStream out = new BufferedOutputStream(
				socket.getOutputStream());
		out.write(data);
		out.flush();
		out.close();
	}
}
