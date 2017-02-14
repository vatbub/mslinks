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
import mslinks.ShellLinkException;

public class EnvironmentVariable implements Serializable {

	public static final int signature = 0xA0000001;
	public static final int size = 0x314;
	
	private String variable;
	
	public EnvironmentVariable() {
		variable = "";
	}
	
	public EnvironmentVariable(ByteReader br, int sz) throws ShellLinkException, IOException {
		if (sz != size)
			throw new ShellLinkException();
		
		int pos = br.getPosition();
		variable = br.readString(260);
		br.seek(pos + 260 - br.getPosition());
		
		pos = br.getPosition();
		variable = br.readUnicodeString(260);
		br.seek(pos + 520 - br.getPosition());
	}
	
	@Override
	public void serialize(ByteWriter bw) throws IOException {
		bw.write4bytes(size);
		bw.write4bytes(signature);
		byte[] b = variable.getBytes();
		bw.writeBytes(b);
		for (int i=0; i<260-b.length; i++)
			bw.write(0);
		for (int i=0; i<variable.length(); i++)
			bw.write2bytes(variable.charAt(i));
		for (int i=0; i<260-variable.length(); i++)
			bw.write2bytes(0);
	}
	
	public String getVariable() { return variable; }
	public EnvironmentVariable setVariable(String s) { variable = s; return this; }

}
