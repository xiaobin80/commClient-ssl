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

import java.text.*;
import java.util.*;

public class DateTimes extends Messages {
	
	private SimpleDateFormat simpleDate;
	private String dateStr;
	private Date now;
	
	public DateTimes() {
		// TODO Auto-generated constructor stub
		simpleDate = new SimpleDateFormat("yyyyMMddHHmm");
		now = new Date();
	}
	
	@SuppressWarnings("deprecation")
	private byte[] getDateTime() {
		// TODO Auto-generated method stub
		dateStr = simpleDate.format(now);
		
		String strFyear = dateStr.substring(0, 2);
		String strByear = dateStr.substring(2, 4);
		String strMonth = dateStr.substring(4, 6);
		String strDay = dateStr.substring(6, 8);
		String strHour = dateStr.substring(8, 10);
		String strMinute = dateStr.substring(10, 12);
		
		int fontYear = Integer.parseInt(strFyear);
		int backYear = Integer.parseInt(strByear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(strDay);
		int hour = Integer.parseInt(strHour);
		int minute = Integer.parseInt(strMinute);
		
		byte[] result = new byte[6];
		result[0] = (byte) fontYear;
		result[1] = (byte) backYear;
		result[2] = (byte) month;
		result[3] = (byte) day;
		result[4] = (byte) hour;
		result[5] = (byte) minute;
		
		return result;
	}
	
	private void setDateTime(byte[] dateTime) {
		// TODO Auto-generated method stub
		DateNow = dateTime;
	}

	
	@Override
	public byte[] putMessages() {
		// TODO Auto-generated method stub
		byte[] temp = getDateTime();
		byte[] result = new byte[6];
		
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i];
		}
		
		return result;
	}

}
