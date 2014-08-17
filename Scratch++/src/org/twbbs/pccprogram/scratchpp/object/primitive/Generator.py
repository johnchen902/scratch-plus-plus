clazz = ["Bool",
		 "Char", "SignedChar", "UnsignedChar",
		 "SignedShort", "UnsignedShort",
		 "SignedInt", "UnsignedInt",
		 "SignedLong", "UnsignedLong",
		 "SignedLongLong", "UnsignedLongLong",
		 "CFloat", "CDouble", "LongDouble"]
types = ["bool",
		 "char", "signed char", "unsigned char",
		 "short int", "unsigned short int",
		 "int", "unsigned int",
		 "long int", "unsigned long int",
		 "long long int", "unsigned long long int",
		 "float", "double", "long double"]
under = ["boolean",
		 "byte", "byte", "byte",
		 "short", "short",
		 "int", "int",
		 "int", "int",
		 "long", "long",
		 "float", "double", "double"]
boxed = {"boolean": "Boolean",
		 "byte"  : "Byte",
		 "short" : "Short",
		 "int"   : "Integer",
		 "long"  : "Long",
		 "float" : "Float",
		 "double": "Double"}
bits = {"boolean": 1,
		 "byte"  : 8,
		 "short" : 16,
		 "int"   : 32,
		 "long"  : 64,
		 "float" : 32,
		 "double": 64}
defaultvalue = {"boolean": "false",
				"byte"  : "(byte) 0",
				"short" : "(short) 0",
				"int"   : "0",
				"long"  : "0L",
				"float" : "0.0f",
				"double": "0.0"}

def equalexpr (t) :
	if t == "float":
		return "Float.floatToIntBits(value) == Float.floatToIntBits(other.value)"
	elif t == "double":
		return "Double.doubleToLongBits(value) == Double\n\t\t\t\t.doubleToLongBits(other.value)"
	else:
		return "value == other.value"

def tostrexpr (i) :
	if types[i] == "char":
		return "Character.toString((char) (value & 0xFF))"
	if "unsigned" in types[i]:
		if types[i] == "unsigned char":
			return "Integer.toUnsignedString(value & 0xFF)"
		elif types[i] == "unsigned short int":
			return "Integer.toUnsignedString(value & 0xFFFF)"
		else:
			return boxed[under[i]] + ".toUnsignedString(value)"
	else:
		return boxed[under[i]] + ".toString(value)"

for i in range(len(clazz)):
	with open(clazz[i] + '.java', 'w') as f:
		f.write("""\
package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>""" + types[i] + """</code>, """ + str(bits[under[i]]) + """-bit in this implementation.
 * 
 * @author johnchen902
 */
public class """ + clazz[i] + """ extends Value {
	private static class """ + clazz[i] + """Type extends Type {
		public """ + clazz[i] + """Type() {
			super(\"""" + types[i] + """\");
		}
	}

	/**
	 * The type of a <code>""" + types[i] + """</code>.
	 */
	public static final Type TYPE = new """ + clazz[i] + """Type();

	private """ + under[i] + """ value;

	/**
	 * Create a <code>""" + types[i] + """</code> with default value.
	 */
	public """ + clazz[i] + """() {
		this(""" + defaultvalue[under[i]] + """);
	}

	/**
	 * Create a <code>""" + types[i] + """</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public """ + clazz[i] + """(""" + under[i] + """ value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public """ + under[i] + """ getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return """ + boxed[under[i]] + """.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof """ + clazz[i] + """))
			return false;
		""" + clazz[i] + """ other = (""" + clazz[i] + """) obj;
		return """ + equalexpr(under[i]) + """;
	}

	@Override
	public String toString() {
		return """ + tostrexpr(i) + """;
	}
}
""")
