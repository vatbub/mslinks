/*
	https://github.com/BlackOverlord666/mslinks
	
	Copyright (c) 2015 Dmitrii Shamrikov

	Licensed under the WTFPL
	You may obtain a copy of the License at
 
	http://www.wtfpl.net/about/
 
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/
package mslinks.extra;

/*-
 * #%L
 * FOKProjects MSLinks
 * %%
 * Copyright (C) 2016 - 2017 Frederik Kammel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import io.ByteReader;
import io.ByteWriter;

import java.io.IOException;

import mslinks.Serializable;

public class Stub implements Serializable {
	
	private int sign;
	private byte[] data;

	public Stub(ByteReader br, int sz, int sgn) throws IOException {
		int len = sz - 8;
		sign = sgn;
		data = new byte[len];
		for (int i=0; i<len; i++)
			data[i] = (byte)br.read();
	}
	
	@Override
	public void serialize(ByteWriter bw) throws IOException {
		bw.write4bytes(data.length + 8);
		bw.write4bytes(sign);
		bw.writeBytes(data);
	}

}
