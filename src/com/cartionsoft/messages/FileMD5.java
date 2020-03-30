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

import java.io.FileInputStream;
import java.io.IOException;

import com.cartionsoft.utils.*;

public class FileMD5 extends Messages {

	private String filePath;
	
	public FileMD5(String fileName) {
		// TODO Auto-generated constructor stub
		this.filePath = fileName;
	}
	
	private char[] getMD5() throws IOException {
		FileInputStream fin = new FileInputStream(filePath);
		char[] result = new char[fin.available()];
		int b;
		int i = 0;
		while ((b = fin.read()) != -1) {
			result[i] = (char) b;
			i++;
		}
		return result;
	}
	
	private byte[] convert2ByteArray(String value) {
		char[] myChar = value.toCharArray();
		byte[] result = new byte[myChar.length];
		for (int i = 0; i < myChar.length; i++) {
		result[i] = (byte) myChar[i];
	}
	
	return result;
}
	
	@Override
	public byte[] putMessages() throws Exception {
		// TODO Auto-generated method stub
		MD5 md5 = new MD5();
		md5.init();
		
		char[] bytInput = getMD5();
		md5.update(bytInput, bytInput.length);
		md5.md5final();
		StringBuffer sBuff = new StringBuffer();
		sBuff = md5.toHexString();
		String strMD5 = "";
		for (int i = 0; i < sBuff.length(); i++) {
			char ch = sBuff.charAt(i);
			strMD5 += String.valueOf(ch);
		}
		
		byte[] result = convert2ByteArray(strMD5);
		return result;
	}

}
