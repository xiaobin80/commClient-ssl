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

public class MessageCMD extends Messages {
	
	private int CMD;
	
	public MessageCMD(int cmd) {
		// TODO Auto-generated constructor stub
		this.CMD = cmd;
	}
	
	private byte getCMD() {
		// TODO Auto-generated method stub
		return (byte) CMD;
	}
	
	private void setCMD(int cmd) {
		// TODO Auto-generated method stub
		DEFINE_CMD = (byte) cmd;
	}
	
	
	private byte[] genMessagesHead() {
		byte[] result = new byte[7];
		
		result[0] = 0x58;     	// X
		result[1] = 0x42;     	// B
		result[2] = 0x4D;     	// M
		result[3] = 0x53;		// B
		
		result[4] = 0x56;		// V
		result[5] = 0x01;		// 1
		
		result[6] = getCMD();
		
		return result;
	}
	
	@Override
	public byte[] putMessages() {
		// TODO Auto-generated method stub
		return genMessagesHead();
	}

}
