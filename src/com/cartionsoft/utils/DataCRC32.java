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

package com.cartionsoft.utils;

import java.io.*;
import java.util.zip.CRC32;

public class DataCRC32 {
	
	private String path;
	private CRC32 myCrc32;
	
	public DataCRC32(String filePath) {
		// TODO Auto-generated constructor stub
		this.path = filePath;
		myCrc32 = new CRC32();
	}
	
	/**
	 * Calculate the value of the file crc32
	 * @return result
	 * @throws IOException
	 */
	public String getCRC32Value() throws IOException {
		InputStream in = new FileInputStream(path);
		
		String result = "";
		byte[] myFileCRC = new byte[in.available()];
		int b;
		int i = -1;
		while ((b = in.read()) != -1) {
			i++;
			myFileCRC[i] = (byte)b;
		}
		
		myCrc32.update(myFileCRC);
		
		long value = myCrc32.getValue();
		result = String.format("%08X", value);
		return result;
	}
}
