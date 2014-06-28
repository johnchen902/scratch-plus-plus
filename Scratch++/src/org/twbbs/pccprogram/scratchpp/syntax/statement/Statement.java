package org.twbbs.pccprogram.scratchpp.syntax.statement;

import java.awt.Color;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;

/**
 * Denote a C++ statement.
 * 
 * @author johnchen902
 */
public interface Statement {

	/**
	 * The default color of a statement.
	 */
	public static final Color STATEMENT_COLOR = Color.ORANGE;

	/**
	 * Execute the specified statement
	 * 
	 * @param env
	 *            the runtime environment
	 */
	public void execute(RuntimeEnvironment env);
}
