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

public abstract class MessagesParser {

	/**
	 * Byte - 0x33
	 */
	protected final int DEFAULT_UDF = 51;
	
	/**
	 * Byte - 0x44
	 */
	protected final int DEFAULT_BOF = 68;
	
	/**
	 * Byte - 0x55
	 */
	protected final int DEFAULT_EOF = 85;
	
	/**
	 * Byte - 0xAA
	 */
	protected final int DEFAULT_END = -86;
	
	
	protected int beginIndex;
	protected int endIndex;
	
	/**
	 * <p> Location positioning flag </p>
	 * @param firstFlag
	 * @param twoFlag
	 * @param data
	 * @return result
	 */
	protected int getPosition(int firstFlag, int twoFlag, byte[] data) {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < data.length; i++) {
			if((data[i] == firstFlag) && (data[i + 1] == twoFlag)) { // 44AA
				result = i;
			}
		}
		return result;
	}
	
	/**
	 * <p> Convert byte array of characters to String </p>
	 * @param data
	 * @return result
	 */
	public String messageConverter(byte[] data) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			buffer.append((char) data[i]);
		}
		
		String result = "";
		for (int i = 0; i < buffer.length(); i++) {
			char ch = buffer.charAt(i);
			result += String.valueOf(ch);
		}	
		return result;
	}
	
	/**
	 * Get byte array in the IP section
	 * @param data
	 * @return IP
	 */
	public abstract byte[] getIP(byte[] data);
	
	/**
	 * Get byte array in the Text section
	 * @param data
	 * @return text
	 */
	public abstract byte[] getMessages(byte[] data);
	
	/**
	 * Get byte array in the MD5 section
	 * @param data
	 * @return MD5
	 */
	public abstract byte[] getMD5Message(byte[] data);
	
	/**
	 * Get byte array in the SendCount section
	 * @param data
	 * @return send count
	 */
	public abstract byte[] getSendCount(byte[] data);
	
	/**
	 * Get byte array in the DateTime section
	 * @param data
	 * @return date time
	 */
	public abstract byte[] getDateTimes(byte[] data);
	
	/**
	 * Get byte array in the StationID section
	 * @param data
	 * @return station id
	 */
	public abstract byte[] getStationID(byte[] data);
	
	/**
	 * Get byte array in the CMD section
	 * @param data
	 * @return command
	 */
	public abstract byte[] getCMD(byte[] data);
}
