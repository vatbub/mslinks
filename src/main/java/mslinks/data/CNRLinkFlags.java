package mslinks.data;

/*-
 * #%L
 * https://github.com/vatbub/mslinks
 * %%
 * Copyright (C) 2015 - 2020 Dmitrii Shamrikov, Frederik Kammel
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

import java.io.IOException;

public class CNRLinkFlags extends BitSet32 {
	
	public CNRLinkFlags(int n) {
		super(n);
		reset();
	}

	public CNRLinkFlags(ByteReader data) throws IOException {
		super(data);
		reset();
	}
	
	private void reset() {
		for (int i=2; i<32; i++)
			clear(i);
	}
	
	public boolean isValidDevice() 		{ return get(0); }
	public boolean isValidNetType()		{ return get(1); }
	
	public CNRLinkFlags setValidDevice() 		{ set(0); return this; }	
	public CNRLinkFlags setValidNetType()		{ set(1); return this; }
	
	public CNRLinkFlags clearValidDevice() 		{ clear(0); return this; }	
	public CNRLinkFlags clearValidNetType()		{ clear(1); return this; }

}
