package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>unsigned char</code>, 8-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedChar extends Value {
	private static class UnsignedCharType extends Type {
		public UnsignedCharType() {
			super("unsigned char");
		}
	}

	/**
	 * The type of a <code>unsigned char</code>.
	 */
	public static final Type TYPE = new UnsignedCharType();

	private byte value;

	/**
	 * Create a <code>unsigned char</code> with default value.
	 */
	public UnsignedChar() {
		this((byte) 0);
	}

	/**
	 * Create a <code>unsigned char</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public UnsignedChar(byte value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public byte getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Byte.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnsignedChar))
			return false;
		UnsignedChar other = (UnsignedChar) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(value & 0xFF);
	}
}
