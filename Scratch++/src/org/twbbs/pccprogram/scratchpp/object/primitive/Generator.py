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
        return """Double.doubleToLongBits(value) == Double\n\t\t\t\t.doubleToLongBits(other.value)"""
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
        f.write("""package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>{tipe}</code>, {bit}-bit in this implementation.
 * 
 * @author johnchen902
 */
public class {claz} extends Value {{
    private static class {claz}Type extends Type {{
        public {claz}Type() {{
            super("{tipe}");
        }}
    }}

    /**
     * The type of a <code>{tipe}</code>.
     */
    public static final Type TYPE = new {claz}Type();

    private {unde} value;

    /**
     * Create a <code>{tipe}</code> with default value.
     */
    public {claz}() {{
        this({default_value});
    }}

    /**
     * Create a <code>{tipe}</code> with specific value.
     * 
     * @param value
     *$$$$$$$$$$$$the specific value
     */
    public {claz}({unde} value) {{
        super(TYPE);
        this.value = value;
    }}

    /**
     * Get its value.
     * 
     * @return its value
     */
    public {unde} getValue() {{
        return value;
    }}

    @Override
    public int hashCode() {{
        return {boxe}.hashCode(value);
    }}

    @Override
    public boolean equals(Object obj) {{
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof {claz}))
            return false;
        {claz} other = ({claz}) obj;
        return {equal_expr};
    }}

    @Override
    public String toString() {{
        return {tostr_expr};
    }}
}}
""".replace("    ", "\t").replace("$", " ").format(
    claz=clazz[i],
    tipe=types[i],
    unde=under[i],
    boxe=boxed[under[i]],
    bit=bits [under[i]],
    equal_expr=equalexpr(under[i]),
    tostr_expr=tostrexpr(i),
    default_value=defaultvalue[under[i]]
))
