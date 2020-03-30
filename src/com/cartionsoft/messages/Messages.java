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

public abstract class Messages {
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>1</td><td>ROT</td><td>4 byte</td><td>Message Of Type</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_ROT = new byte[4];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>2</td><td>VERSION</td><td>2 byte</td><td>Version</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_VERSION = new byte[2];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>3</td><td>CMD</td><td>1 byte</td><td>Command</td></tr>
	 * </table border>
	 */
	public byte DEFINE_CMD;
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>4</td><td>UDF</td><td>1 byte</td><td>User Datagram Flag</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_UDF = new byte[2];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>5</td><td>ID</td><td>2 byte</td><td>Station Identity</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_ID = new byte[2];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>6</td><td>DateNow</td><td>6 byte</td><td>For Example: 0x20,0x10,0x11,0x29,0x23,0x15</td></tr>
	 * </table border>
	 */
	public byte[] DateNow = new byte[6];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>7</td><td>SendCount</td><td>1 byte</td><td>For Example: 0x01</td></tr>
	 * </table border>
	 */
	public byte SendCount;
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>8</td><td>HashValue</td><td>32 byte</td><td>Hash Is Values</td></tr>
	 * </table border>
	 */
	public byte[] HashValue = new byte[32];
	
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>9</td><td>Reservation</td><td>8 byte</td><td>Place holder</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_Reservation = new byte[8];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>10</td><td>BOF</td><td>2 byte</td><td>Begin Of Flag</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_BOF = new byte[2];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>11</td><td>Messages</td><td>Variant byte</td><td>Begin Of Flag</td></tr>
	 * </table border>
	 */
	public byte[] Messages;
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>12</td><td>EOF</td><td>2 byte</td><td>End Of Flag</td></tr>
	 * </table border>
	 */
	public byte[] DEFINE_EOF = new byte[2];
	
	/**
	 * <p>Message Of Format</p>
	 * <table border>
	 * <tr><th>order</th><th>name</th><th>length</th><th>notes</th></tr>
	 * <tr><td>13</td><td>IP</td><td>4 byte</td><td>IP Address</td></tr>
	 * </table border>
	 */
	public byte[] IP = new byte[4];
	
	/**
	 * <p>File length</p>
	 */
	protected static int textLen;
	
	/**
	 * <p>Return byte array</p>
	 * @return result
	 * @throws Exception
	 */
	public abstract byte[] putMessages() throws Exception;
	
//	protected byte[] convert2ByteArray(String value) {
//		char[] myChar = value.toCharArray();
//		byte[] result = new byte[myChar.length];
//		for (int i = 0; i < myChar.length; i++) {
//			result[i] = (byte) myChar[i];
//		}
//		
//		return result;
//	}
}
