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

package com.cartionsoft.ssl.client;

import com.cartionsoft.ssl.trans.*;

/**
 * @author xiaobin
 * @version 1.0, 2010/04/15
 */
public class TransStation extends Transmission {
	
	public TransStation(String host, int port) {
		// TODO Auto-generated constructor stub
		this.host = host;
		this.port = port;		
	}
	
	public byte[] receiveData() throws Exception {
		// TODO Auto-generated method stub
		return super.receiveData();
	}
	
	public void sendData(byte[] data) throws Exception {
		// TODO Auto-generated method stub
		super.sendData(data);
	}
	
}
