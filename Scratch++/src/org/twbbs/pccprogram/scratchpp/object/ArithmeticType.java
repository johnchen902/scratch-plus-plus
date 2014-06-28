package org.twbbs.pccprogram.scratchpp.object;

/**
 * Represents the arithmetic type in C++.
 * 
 * @author johnchen902
 */
public abstract class ArithmeticType extends Type {

	/**
	 * Create a arithmetic type with such name.
	 * 
	 * @param name
	 *            its name
	 */
	public ArithmeticType(String name) {
		super(name);
	}

	public abstract ArithmeticValue of(ArithmeticValue v);
}
