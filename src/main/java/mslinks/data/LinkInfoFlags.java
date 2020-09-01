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

public class LinkInfoFlags extends BitSet32 {
	
	public LinkInfoFlags(int n) {
		super(n);
		reset();
	}
	
	public LinkInfoFlags(ByteReader data) throws IOException {
		super(data);
		reset();
	}
	
	private void reset() {
		for (int i=2; i<32; i++)
			clear(i);
	}
	
	public boolean hasVolumeIDAndLocalBasePath() 						{ return get(0); }
	public boolean hasCommonNetworkRelativeLinkAndPathSuffix()			{ return get(1); }
	
	public LinkInfoFlags setVolumeIDAndLocalBasePath() 					{ set(0); return this; }
	public LinkInfoFlags setCommonNetworkRelativeLinkAndPathSuffix()	{ set(1); return this; }
	
	public LinkInfoFlags clearVolumeIDAndLocalBasePath() 				{ clear(0); return this; }
	public LinkInfoFlags clearCommonNetworkRelativeLinkAndPathSuffix()	{ clear(1); return this; }
}
