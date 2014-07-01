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
signs = [False,  # signed = True; unsigned = False
         True, True, False,
         True, False,
         True, False,
         True, False,
         True, False,
         True, True, True]
isint = [True,  # integral = True; floating = False
         True, True, True,
         True, True,
         True, True,
         True, True,
         True, True,
         False, False, False]
bits = [8, 8, 8, 8, 16, 16, 32, 32, 32, 32, 64, 64, 32, 64, 64]

def convert(i, j):  # i = to; j = from
    def widening(i, j):
        return "\t\t\t\treturn new " + clazz[i] + "(val);\n"
    def narrowing(i, j):
        return "\t\t\t\treturn new " + clazz[i] + "((" + under[i] + ") val);\n"
    if under[i] == under[j]:
        return widening(i, j)
    if types[i] == "bool":
        return "\t\t\t\treturn new Bool(val != 0);\n"
    if types[j] == "bool":
        if bits[i] >= 32:
            return "\t\t\t\treturn new " + clazz[i] + "(val ? 1 : 0);\n"
        else :
            return "\t\t\t\treturn new " + clazz[i] + "((" + under[i] + ") (val ? 1 : 0));\n"
    if isint[i] and isint[j]:
        if bits[i] >= bits[j]:
            if signs[i] and signs[j]:
                return widening(i, j)
            elif under[i] == "short":  # types[j] must be ? char
                return "\t\t\t\treturn new " + clazz[i] + "((short) (val & 0xF));\n"
            elif under[i] == "long":
                return "\t\t\t\treturn new " + clazz[i] + "(val & 0x" + "F" * (bits[j] // 8) + "L);\n"
            else:
                return "\t\t\t\treturn new " + clazz[i] + "(val & 0x" + "F" * (bits[j] // 8) + ");\n"
        else:
            return narrowing(i, j)
    elif not isint[i] and isint[j]:
        if signs[j]:
            return widening(i, j)
        else:
            return \
                "\t\t\t\treturn new " + clazz[i] + "(val >= 0 ? val : val + 0x1p" + str(bits[j]) + ("f" if under[i] == "float" else "") + ");\n"
    elif isint[i] and not isint[j]:
        if signs[i]:
            return narrowing(i, j)
        else:
            return "\t\t\t\tBigDecimal bd = new BigDecimal(val);\n" + \
                "\t\t\t\treturn new " + clazz[i] + "(bd.toBigInteger()." + under[i] + "Value());\n"
    else:
        if bits[i] >= bits[j]:
            return widening(i, j)
        else:
            return narrowing(i, j)

    raise "???"

with open('PrimitiveConversion.java', 'w') as f:
    fstr = """package org.twbbs.pccprogram.scratchpp.object.primitive;

import java.math.BigDecimal;

import org.twbbs.pccprogram.scratchpp.CompileException;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A handler for conversions of primitive types in C++
 * 
 * @author johnchen902
 */
public class PrimitiveConversion {
    private PrimitiveConversion() {
    }

    /**
     * Return whether a type is a primitive type.
     * 
     * @param type
     *$$$$$$$$$$$$the type to check
     * @return <code>true</code> if the type is primitive; <code>false</code>
     *$$$$$$$$$otherwise
     */
    public static boolean isPrimitiveType(Type type) {
        switch (type.getName()) {
"""
    for i in range(len(clazz)):
        fstr += "\t\tcase \"" + types[i] + "\":\n"
        fstr += "\t\t\treturn true;\n"
    fstr += """\t\t}
        return false;
    }

    /**
     * Convert a value to a specified type.
     * 
     * @param type
     *$$$$$$$$$$$$the type to convert to
     * @param value
     *$$$$$$$$$$$$the value to convert from
     * @return the value converted to
     */
    public static Value convert(Type type, Value value) {
        Type from = value.getType();
        if (type.equals(from))
            return value;
"""        
    fstr += "\t\tswitch (type.getName()) {\n""";
    for i in range(len(clazz)):
        fstr += "\t\tcase \"" + types[i] + "\":\n"
        fstr += "\t\t\tswitch (from.getName()) {\n";
        for j in range(len(clazz)):
            if i != j:
                fstr += "\t\t\tcase \"" + types[j] + "\": {\n"
                fstr += "\t\t\t\t" + under[j] + " val = ((" + clazz[j] + ") value).getValue();\n"
                fstr += convert(i, j);
                fstr += "\t\t\t}\n";
        fstr += "\t\t\t}\n";
    fstr += """\t\t}
        throw new CompileException(\"not a primitive type\");
    }
}
"""
    fstr = fstr.replace("    ", "\t")
    fstr = fstr.replace("$", " ")
    f.write(fstr);
