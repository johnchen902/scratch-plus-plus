package org.twbbs.pccprogram.scratchpp.object;

/**
 * Represents value of arithmetic type in C++.
 * 
 * @author johnchen902
 */
public abstract class ArithmeticValue extends Value {

	/**
	 * Create a arithmetic type with such type.
	 * 
	 * @param type
	 *            its type
	 */
	protected ArithmeticValue(ArithmeticType type) {
		super(type);
	}
}
