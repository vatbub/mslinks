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
import io.ByteWriter;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import mslinks.Serializable;

public class Filetime extends GregorianCalendar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2258831109960118017L;
	private long residue;
	
	public Filetime() {
		super();
	}
	
	public Filetime(ByteReader data) throws IOException {
		this(data.read8bytes());
	}
	
	public Filetime(long time) {
		long t = time / 10000;
		residue = time - t;
		setTimeInMillis(t);
		add(Calendar.YEAR, -369);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		if (!super.equals(o))
			return false;

		var obj = (Filetime)o;
		return residue == obj.residue;
	}

	@Override
	public int hashCode()
	{
		return (int)(super.hashCode() ^ ((residue & 0xffffffff00000000l) >> 32) ^ (residue & 0xffffffffl));
	}
	
	public long toLong() {
		GregorianCalendar tmp = (GregorianCalendar)clone();
		tmp.add(Calendar.YEAR, 369);
		return tmp.getTimeInMillis() + residue;
	}

	public void serialize(ByteWriter bw) throws IOException {
		bw.write8bytes(toLong());
	}
	
	public String toString() {
		return String.format("%d:%d:%d %d.%d.%d", 
				get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE), get(Calendar.SECOND),
				get(Calendar.DAY_OF_MONTH), get(Calendar.MONTH) + 1, get(Calendar.YEAR));
	}
}
