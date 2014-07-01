package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>char</code>, 8-bit in this implementation.
 * 
 * @author johnchen902
 */
public class Char extends Value {
	private static class CharType extends Type {
		public CharType() {
			super("char");
		}
	}

	/**
	 * The type of a <code>char</code>.
	 */
	public static final Type TYPE = new CharType();

	private byte value;

	/**
	 * Create a <code>char</code> with default value.
	 */
	public Char() {
		this((byte) 0);
	}

	/**
	 * Create a <code>char</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public Char(byte value) {
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
		if (!(obj instanceof Char))
			return false;
		Char other = (Char) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Character.toString((char) (value & 0xFF));
	}
}
