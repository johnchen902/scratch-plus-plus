package org.twbbs.pccprogram.scratchpp.object;

import java.util.Objects;

/**
 * Represents anything that is a "value" in C++.
 * 
 * @author johnchen902
 */
public abstract class Value {

	private final Type type;

	/**
	 * Create a value with such type.
	 * 
	 * @param type
	 *            its type
	 */
	protected Value(Type type) {
		this.type = Objects.requireNonNull(type);
	}

	/**
	 * Get the value's type.
	 * 
	 * @return its type
	 */
	public Type getType() {
		return type;
	}
}
