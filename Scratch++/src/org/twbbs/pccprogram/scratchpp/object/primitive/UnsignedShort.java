package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>unsigned short int</code>, 16-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedShort extends Value {
	private static class UnsignedShortType extends Type {
		public UnsignedShortType() {
			super("unsigned short int");
		}
	}

	/**
	 * The type of a <code>unsigned short int</code>.
	 */
	public static final Type TYPE = new UnsignedShortType();

	private short value;

	/**
	 * Create a <code>unsigned short int</code> with default value.
	 */
	public UnsignedShort() {
		this((short) 0);
	}

	/**
	 * Create a <code>unsigned short int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public UnsignedShort(short value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public short getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Short.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnsignedShort))
			return false;
		UnsignedShort other = (UnsignedShort) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(value & 0xFFFF);
	}
}
