package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>unsigned int</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedInt extends Value {
	private static class UnsignedIntType extends Type {
		public UnsignedIntType() {
			super("unsigned int");
		}
	}

	/**
	 * The type of a <code>unsigned int</code>.
	 */
	public static final Type TYPE = new UnsignedIntType();

	private int value;

	/**
	 * Create a <code>unsigned int</code> with default value.
	 */
	public UnsignedInt() {
		this(0);
	}

	/**
	 * Create a <code>unsigned int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public UnsignedInt(int value) {
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
		if (!(obj instanceof UnsignedInt))
			return false;
		UnsignedInt other = (UnsignedInt) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(value);
	}
}
