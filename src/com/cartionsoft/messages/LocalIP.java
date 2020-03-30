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

package com.cartionsoft.messages;

import java.net.*;


public class LocalIP extends Messages {
	
	private byte[] getIP() throws UnknownHostException {
		// TODO Auto-generated method stub
		InetAddress myInet = InetAddress.getLocalHost();
		// System.out.println(myInet.getHostAddress());
		byte[] result = myInet.getAddress();
		return result;
	}
	
	private void setIP(byte[] localIP) {
		// TODO Auto-generated method stub
		IP = localIP;
	}

	@Override
	public byte[] putMessages() throws Exception {
		// TODO Auto-generated method stub
		
		byte[] ip = getIP();
		int ipLen = ip.length;		
		
		
		byte[] result = new byte[ipLen];
		
		for (int i = 0; i < ip.length; i++) {
			result[i] = ip[i];
		}
		
		return result;
	}
	
}
