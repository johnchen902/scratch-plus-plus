package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>signed char</code>, 8-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedChar extends Value {
	private static class SignedCharType extends Type {
		public SignedCharType() {
			super("signed char");
		}
	}

	/**
	 * The type of a <code>signed char</code>.
	 */
	public static final Type TYPE = new SignedCharType();

	private byte value;

	/**
	 * Create a <code>signed char</code> with default value.
	 */
	public SignedChar() {
		this((byte) 0);
	}

	/**
	 * Create a <code>signed char</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public SignedChar(byte value) {
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
		if (!(obj instanceof SignedChar))
			return false;
		SignedChar other = (SignedChar) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Byte.toString(value);
	}
}
