package org.twbbs.pccprogram.scratchpp.syntax.expression;

import java.awt.Color;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * Denote a C++ expression.
 * 
 * @author johnchen902
 */
public interface Expression {

	/**
	 * The default color of an expression.
	 */
	public static final Color EXPRESSION_COLOR = Color.GREEN;

	/**
	 * Get the type of this expression
	 * 
	 * @return the type of this expression
	 */
	public Type getType(RuntimeEnvironment env);

	/**
	 * Evaluate the expression
	 * 
	 * @param env
	 *            the runtime environment
	 * @return the value of this expression
	 */
	public Value evaluate(RuntimeEnvironment env);
}
