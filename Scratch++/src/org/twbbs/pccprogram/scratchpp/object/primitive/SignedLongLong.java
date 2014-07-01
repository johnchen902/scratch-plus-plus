package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>long long int</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedLongLong extends Value {
	private static class SignedLongLongType extends Type {
		public SignedLongLongType() {
			super("long long int");
		}
	}

	/**
	 * The type of a <code>long long int</code>.
	 */
	public static final Type TYPE = new SignedLongLongType();

	private long value;

	/**
	 * Create a <code>long long int</code> with default value.
	 */
	public SignedLongLong() {
		this(0L);
	}

	/**
	 * Create a <code>long long int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public SignedLongLong(long value) {
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
		if (!(obj instanceof SignedLongLong))
			return false;
		SignedLongLong other = (SignedLongLong) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}
}
