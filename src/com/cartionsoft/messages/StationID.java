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

public class StationID extends Messages {
	
	private int station;
	
	public StationID(int stationID) {
		// TODO Auto-generated constructor stub
		this.station = stationID;
	}
	
	private byte[] getStationID() {
		// TODO Auto-generated method stub
		byte[] result = new byte[4];
		result[0] = 0x33;
		result[1] = (byte) 0xAA;
		result[2] = 0x53;
		result[3] = (byte) station;
		return result;
	}
	
	private void setStationID(byte[] stationID) {
		// TODO Auto-generated method stub
		DEFINE_ID = stationID;
	}

	@Override
	public byte[] putMessages() {
		// TODO Auto-generated method stub
		return getStationID();
	}
	
}
