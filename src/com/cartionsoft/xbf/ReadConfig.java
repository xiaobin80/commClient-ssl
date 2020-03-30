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

/**
 * @(#)ReadConfig.java
 * 
 * @author xiaobin
 * @date 2009.03.06
 * @version 1.0
 */

package com.cartionsoft.xbf;

import java.io.*;
import nu.xom.*;

/**
 * <p>read XML file.
 * 
 */
public class ReadConfig {
	private String xmlFile1;
	
	public ReadConfig(String xmlFile) {
		// TODO Auto-generated constructor stub
		this.xmlFile1 = xmlFile;
	}
	
	/**
	 * <p>get XML node value.
	 * 
	 * @param index XML file index this's -1 begin
	 * @param childElements child element's name
	 * @return node value
	 * @throws Exception IOException(not found file)
	 */
	
	public String getElement(int index, String childElements) 
	  throws Exception {
		// direct read file
		// Document doc = new Builder().build(xmlFile1);
		
		// use InputStream read file
		InputStream in = new FileInputStream(xmlFile1);
		
		// use ClassLoad
//	    InputStream in = ClassLoader.getSystemResourceAsStream(xmlFile1);
//		if (in == null) {
//			ClassLoader cl = ReadConfig.class.getClassLoader();
//			in = cl.getResourceAsStream(xmlFile1);
//		}
		
		Document doc = new Builder().build(in);
		Elements elements = doc.getRootElement().getChildElements();
		Element element = elements.get(index);
		
		String result = element.getFirstChildElement(childElements).getValue();
		return result;
	}
}
