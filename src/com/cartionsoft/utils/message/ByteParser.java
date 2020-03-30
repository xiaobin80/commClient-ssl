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

package com.cartionsoft.utils.message;

public class ByteParser extends MessagesParser {

	@Override
	public byte[] getIP(byte[] data) {
		beginIndex = getPosition(DEFAULT_EOF, DEFAULT_END, data) + 2;
		endIndex = data.length;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	public byte[] getCMD(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_UDF, DEFAULT_END, data) - 1;
		endIndex = beginIndex + 1;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	public byte[] getDateTimes(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_UDF, DEFAULT_END, data) + 4;
		endIndex = beginIndex + 6;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	public byte[] getMD5Message(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_BOF, DEFAULT_END, data) - 40;
		endIndex = beginIndex + 32;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	public byte[] getMessages(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_BOF, DEFAULT_END, data) + 2;
		endIndex = getPosition(DEFAULT_EOF, DEFAULT_END, data);
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	public byte[] getSendCount(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_UDF, DEFAULT_END, data) + 10;
		endIndex = beginIndex + 1;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
	@Override
	protected int getPosition(int firstFlag, int twoFlag, byte[] data) {
		// TODO Auto-generated method stub
		return super.getPosition(firstFlag, twoFlag, data);
	}

	@Override
	public byte[] getStationID(byte[] data) {
		// TODO Auto-generated method stub
		beginIndex = getPosition(DEFAULT_UDF, DEFAULT_END, data) + 2;
		endIndex = beginIndex + 2;
		byte[] result = new byte[endIndex - beginIndex];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[beginIndex + i];
		}
		
		return result;
	}
	
		
	@Override
	public String messageConverter(byte[] data) {
		// TODO Auto-generated method stub
		return super.messageConverter(data);
	}

}
