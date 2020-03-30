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

import org.apache.log4j.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class LogOper {
	private Logger logger;
	private SimpleDateFormat mydate;
	
	public LogOper() {
		// TODO Auto-generated constructor stub
		logger = Logger.getLogger(this.getClass());
		mydate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public void writeLog(String logContent) {
		String message = mydate.format(new Date());
		message = message + ": ";
		logger.info(message + logContent);
	}
}
