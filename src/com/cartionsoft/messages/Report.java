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

import java.util.*;

public class Report extends Messages {
	
	private Vector<Messages> manager = new Vector<Messages>();
	//private List<Messages> manager = new ArrayList<Messages>();
	
	public void addToMessages(Messages messages) {
		manager.addElement(messages);
	}
	
	public void removeToMessages(Messages messages) {
		manager.removeElement(messages);
	}
	
	public Enumeration<Messages> messages() {
		return manager.elements();
	}

	
	@Override
	public byte[] putMessages() throws Exception {
		// TODO Auto-generated method stub
		byte[] result = new byte[textLen + 66];
		int bytePosition = 0;
		int loopCount = 0;

		for (Messages m : manager) {
			byte[] temp = m.putMessages();
			loopCount = 0;
			for (int i = 0; i < temp.length; i++) {
				result[bytePosition + i] = temp[i];
				loopCount++;
			}
			bytePosition = bytePosition + loopCount;
		}
		return result;
	}
	
}
