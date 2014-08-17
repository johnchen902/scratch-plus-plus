clazz = ["SignedInt", "UnsignedInt",
		 "SignedLong", "UnsignedLong",
		 "SignedLongLong", "UnsignedLongLong",
		 "CFloat", "CDouble", "LongDouble"]
types = ["int", "unsigned int",
		 "long int", "unsigned long int",
		 "long long int", "unsigned long long int",
		 "float", "double", "long double"]
under = ["int", "int",
		 "int", "int",
		 "long", "long",
		 "float", "double", "double"]

opname = ["add", "minus", "multiply", "divide", "module"]
opverb = ["Adds", "Minuses", "Multiplies", "Divides", "Modules"]

def op(i, j, lhs, rhs):
	if opname[i] in ["add", "minus", "multiply"] or types[j][:8] != "unsigned":
		return lhs + " " + "+-*/%"[i] + " " + rhs
	elif opname[i] == "divide":
		if under[j] == "int":
			return "Integer.divideUnsigned(" + lhs + ", " + rhs + ")"
		else:
			return "Long.divideUnsigned(" + lhs + ", " + rhs + ")"
	elif opname[i] == "module":
		if under[j] == "int":
			return "Integer.remainderUnsigned(" + lhs + ", " + rhs + ")"
		else:
			return "Long.remainderUnsigned(" + lhs + ", " + rhs + ")"



def methodof(i):
	s = """\

	/**
	 * """ + opverb[i] + """ two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value """ + opname[i] + """(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
"""
	for j in range(len(clazz)):
		s += """\
		case \"""" + types[j] + """\": {
			""" + under[j] + """ l = ((""" + clazz[j] + """) lhs).getValue();
			""" + under[j] + """ r = ((""" + clazz[j] + """) rhs).getValue();
			return new """ + clazz[j] + """(""" + op(i, j, "l", "r") + """);
		}
"""
	s += """\
		}
		throw new CompileException(\"not a primitive type\");
	}
"""
	return s

with open('UsualOperators.java', 'w') as f:
	s = """\
package org.twbbs.pccprogram.scratchpp.object.primitive;

import static org.twbbs.pccprogram.scratchpp.object.primitive.UsualArithmeticConversion.getType;

import org.twbbs.pccprogram.scratchpp.CompileException;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A handler for basic operators in C++
 *
 * @author johnchen902
 */
public class UsualOperators {
	private UsualOperators() {
	}
"""
	for i in range(len(opname)):
		s += methodof(i);
	s += """\
}
"""
	f.write(s);
