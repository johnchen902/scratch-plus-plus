package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>short int</code>, 16-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedShort extends Value {
	private static class SignedShortType extends Type {
		public SignedShortType() {
			super("short int");
		}
	}

	/**
	 * The type of a <code>short int</code>.
	 */
	public static final Type TYPE = new SignedShortType();

	private short value;

	/**
	 * Create a <code>short int</code> with default value.
	 */
	public SignedShort() {
		this((short) 0);
	}

	/**
	 * Create a <code>short int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public SignedShort(short value) {
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
		if (!(obj instanceof SignedShort))
			return false;
		SignedShort other = (SignedShort) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Short.toString(value);
	}
}
