package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>float</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class CFloat extends Value {
	private static class CFloatType extends Type {
		public CFloatType() {
			super("float");
		}
	}

	/**
	 * The type of a <code>float</code>.
	 */
	public static final Type TYPE = new CFloatType();

	private float value;

	/**
	 * Create a <code>float</code> with default value.
	 */
	public CFloat() {
		this(0.0f);
	}

	/**
	 * Create a <code>float</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public CFloat(float value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public float getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Float.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CFloat))
			return false;
		CFloat other = (CFloat) obj;
		return Float.floatToIntBits(value) == Float.floatToIntBits(other.value);
	}

	@Override
	public String toString() {
		return Float.toString(value);
	}
}
