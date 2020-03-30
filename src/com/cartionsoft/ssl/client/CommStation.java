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

package com.cartionsoft.ssl.client;

import com.cartionsoft.ssl.comm.CommSSL;
import com.cartionsoft.ssl.trans.*;

/**
 * @author xiaobin
 *
 */
public class CommStation extends CommSSL {
	
	@Override
	public Transmission createTrans(String host, int port) {
		// TODO Auto-generated method stub
		return new TransStation(host, port);
	}

}
