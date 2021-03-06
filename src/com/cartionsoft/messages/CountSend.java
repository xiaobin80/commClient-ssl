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

public class CountSend extends Messages {
	
	public CountSend() {
		// TODO Auto-generated constructor stub
		SendCount++;
	}
	
	private byte getCount() {
		// TODO Auto-generated method stub
		return SendCount;
	}
	
	private void setCount(byte count) {
		// TODO Auto-generated method stub
		SendCount = count;
	}

	@Override
	public byte[] putMessages() {
		// TODO Auto-generated method stub
		byte[] result = new byte[1];
		result[0] = getCount(); 
		
		return result;
	}
}
