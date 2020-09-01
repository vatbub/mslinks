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

public class FileAttributesFlags extends BitSet32 {
	public FileAttributesFlags(int n) {
		super(n);
		reset();
	}
	
	public FileAttributesFlags(ByteReader data) throws IOException {
		super(data);
		reset();
	}
	
	private void reset() {
		clear(3);
		clear(6);
		for (int i=15; i<32; i++)
			clear(i);
	}
	
	public boolean isReadonly() 			{ return get(0); }
	public boolean isHidden() 				{ return get(1); }
	public boolean isSystem() 				{ return get(2); }
	public boolean isDirecory() 			{ return get(4); }
	public boolean isArchive() 				{ return get(5); }
	public boolean isNormal() 				{ return get(7); }
	public boolean isTemporary() 			{ return get(8); }
	public boolean isSparseFile() 			{ return get(9); }
	public boolean isReparsePoint() 		{ return get(10); }
	public boolean isCompressed() 			{ return get(11); }
	public boolean isOffline() 				{ return get(12); }
	public boolean isNotContentIndexed() 	{ return get(13); }
	public boolean isEncypted() 			{ return get(14); }
	
	public FileAttributesFlags setReadonly() 			{ set(0); return this; }
	public FileAttributesFlags setHidden() 				{ set(1); return this; }
	public FileAttributesFlags setSystem() 				{ set(2); return this; }
	public FileAttributesFlags setDirecory() 			{ set(4); return this; }
	public FileAttributesFlags setArchive() 			{ set(5); return this; }
	public FileAttributesFlags setNormal() 				{ set(7); return this; }
	public FileAttributesFlags setTemporary() 			{ set(8); return this; }
	public FileAttributesFlags setSparseFile() 			{ set(9); return this; }
	public FileAttributesFlags setReparsePoint() 		{ set(10); return this; }
	public FileAttributesFlags setCompressed() 			{ set(11); return this; }
	public FileAttributesFlags setOffline() 			{ set(12); return this; }
	public FileAttributesFlags setNotContentIndexed() 	{ set(13); return this; }
	public FileAttributesFlags setEncypted() 			{ set(14); return this; }
	
	public FileAttributesFlags clearReadonly() 			{ clear(0); return this; }
	public FileAttributesFlags clearHidden() 			{ clear(1); return this; }
	public FileAttributesFlags clearSystem() 			{ clear(2); return this; }
	public FileAttributesFlags clearDirecory() 			{ clear(4); return this; }
	public FileAttributesFlags clearArchive() 			{ clear(5); return this; }
	public FileAttributesFlags clearNormal() 			{ clear(7); return this; }
	public FileAttributesFlags clearTemporary() 		{ clear(8); return this; }
	public FileAttributesFlags clearSparseFile() 		{ clear(9); return this; }
	public FileAttributesFlags clearReparsePoint() 		{ clear(10); return this; }
	public FileAttributesFlags clearCompressed() 		{ clear(11); return this; }
	public FileAttributesFlags clearOffline() 			{ clear(12); return this; }
	public FileAttributesFlags clearNotContentIndexed() { clear(13); return this; }
	public FileAttributesFlags clearEncypted() 			{ clear(14); return this; }
	
}
