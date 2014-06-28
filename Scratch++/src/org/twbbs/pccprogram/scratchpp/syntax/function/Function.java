package org.twbbs.pccprogram.scratchpp.syntax.function;

import java.awt.Color;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * Denote a C++ function.
 * 
 * @author johnchen902
 */
public interface Function {

	/**
	 * The default color of an function.
	 */
	public static final Color FUNCTION_COLOR = new Color(191, 150, 0);

	/**
	 * Get the type of the arguments
	 */
	public Type[] getArgumentsType();

	/**
	 * Get the return type
	 */
	public Type getReturnType();

	/**
	 * Invoke the function
	 * 
	 * @param env
	 *            the runtime environment
	 * @param args
	 *            the actual arguments
	 * @return the return value
	 */
	public Value invoke(RuntimeEnvironment env, Value... args);
}
