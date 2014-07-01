package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>unsigned long long int</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedLongLong extends Value {
	private static class UnsignedLongLongType extends Type {
		public UnsignedLongLongType() {
			super("unsigned long long int");
		}
	}

	/**
	 * The type of a <code>unsigned long long int</code>.
	 */
	public static final Type TYPE = new UnsignedLongLongType();

	private long value;

	/**
	 * Create a <code>unsigned long long int</code> with default value.
	 */
	public UnsignedLongLong() {
		this(0L);
	}

	/**
	 * Create a <code>unsigned long long int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public UnsignedLongLong(long value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public long getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnsignedLongLong))
			return false;
		UnsignedLongLong other = (UnsignedLongLong) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Long.toUnsignedString(value);
	}
}
