package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>unsigned long int</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedLong extends Value {
	private static class UnsignedLongType extends Type {
		public UnsignedLongType() {
			super("unsigned long int");
		}
	}

	/**
	 * The type of a <code>unsigned long int</code>.
	 */
	public static final Type TYPE = new UnsignedLongType();

	private int value;

	/**
	 * Create a <code>unsigned long int</code> with default value.
	 */
	public UnsignedLong() {
		this(0);
	}

	/**
	 * Create a <code>unsigned long int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public UnsignedLong(int value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnsignedLong))
			return false;
		UnsignedLong other = (UnsignedLong) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(value);
	}
}
