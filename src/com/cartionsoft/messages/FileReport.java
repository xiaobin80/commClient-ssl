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

import java.io.*;

public class FileReport extends Messages {

	private String path;
	private int bytePosition;
	private int fileLen;
	private InputStream input;
	
	public FileReport(String filePath) {
		// TODO Auto-generated constructor stub
		path = filePath;
		try {
			input = new BufferedInputStream(
					new FileInputStream(path));
			fileLen = input.available();
			
			textLen = fileLen;
		} catch (IOException ioex) {
			// TODO: handle exception
		}

	}
	
	private byte[] getReservation() {
		// TODO Auto-generated method stub
		return DEFINE_Reservation;
	}
	
	private void setReservation(int value) {
		// TODO Auto-generated method stub
		for (int i = 0; i < value; i++) {
			DEFINE_Reservation[i] = 0;
		}
	}
	
	private byte[] getMessages() throws IOException {
		// TODO Auto-generated method stub
		int b;
		byte[] data = new byte[fileLen];
		int j = 0;
		while ((b = input.read()) != -1) {
			data[j] = (byte) b;
			j++;
		}
		return data;
	}
	
	private void setMessages(byte[] text) {
		// TODO Auto-generated method stub
		Messages = text;
	}
	
	private byte[] getBOF() {
		return DEFINE_BOF;
	}
	
	private void setBOF() {
		DEFINE_BOF[0] = (byte) 0x44;
		DEFINE_BOF[1] = (byte) 0xAA;
	}
	
	
	private byte[] getEOF() {
		return DEFINE_EOF;
	}
	
	private void setEOF() {
		DEFINE_EOF[0] = (byte) 0x55;
		DEFINE_EOF[1] = (byte) 0xAA;
	}
	
	


	@Override
	public byte[] putMessages() throws Exception {
		// TODO Auto-generated method stub
		
		//
		setReservation(8);
		byte[] reservation = getReservation();
		int resLen = reservation.length;
		
		//
		setBOF();
		byte[] bof = getBOF();
		int bofLen = bof.length;
		
		byte[] textValue = getMessages();
		int textLen = textValue.length;
		
		//
		setEOF();
		byte[] eof = getEOF();
		int eofLen = eof.length;
		
		int totalLen = resLen + bofLen + textLen + eofLen;
		
		byte[] result = new byte[totalLen];
		
		int loopCount = 0;
		bytePosition = 0;
		
		for (int i = 0; i < reservation.length; i++) {
			result[bytePosition + i] = reservation[i];
			loopCount++;
		}
		
		
		bytePosition = bytePosition + loopCount;
		loopCount = 0;
		
		for (int i = 0; i < bof.length; i++) {
			result[bytePosition + i] = bof[i];
			loopCount++;
		}
		
		bytePosition = bytePosition + loopCount;
		loopCount = 0;
		for (int i = 0; i < textValue.length; i++) {
			result[bytePosition + i] = textValue[i];
			loopCount++;
		}
		
		bytePosition = bytePosition + loopCount;
		loopCount = 0;
		
		for (int i = 0; i < eof.length; i++) {
			result[bytePosition + i] = eof[i];
			loopCount++;
		}
		// System.out.println("bytePos: " + bytePosition);
		return result;
	}
}
