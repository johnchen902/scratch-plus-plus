package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>bool</code>, 1-bit in this implementation.
 * 
 * @author johnchen902
 */
public class Bool extends Value {
	private static class BoolType extends Type {
		public BoolType() {
			super("bool");
		}
	}

	/**
	 * The type of a <code>bool</code>.
	 */
	public static final Type TYPE = new BoolType();

	private boolean value;

	/**
	 * Create a <code>bool</code> with default value.
	 */
	public Bool() {
		this(false);
	}

	/**
	 * Create a <code>bool</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public Bool(boolean value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public boolean getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Boolean.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bool))
			return false;
		Bool other = (Bool) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}
}
