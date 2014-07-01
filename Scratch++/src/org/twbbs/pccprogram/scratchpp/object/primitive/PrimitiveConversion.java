package org.twbbs.pccprogram.scratchpp.object.primitive;

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
	 *            the type to check
	 * @return <code>true</code> if the type is primitive; <code>false</code>
	 *         otherwise
	 */
	public static boolean isPrimitiveType(Type type) {
		switch (type.getName()) {
		case "bool":
			return true;
		case "char":
			return true;
		case "signed char":
			return true;
		case "unsigned char":
			return true;
		case "short int":
			return true;
		case "unsigned short int":
			return true;
		case "int":
			return true;
		case "unsigned int":
			return true;
		case "long int":
			return true;
		case "unsigned long int":
			return true;
		case "long long int":
			return true;
		case "unsigned long long int":
			return true;
		case "float":
			return true;
		case "double":
			return true;
		case "long double":
			return true;
		}
		return false;
	}

	/**
	 * Convert a value to a specified type.
	 * 
	 * @param type
	 *            the type to convert to
	 * @param value
	 *            the value to convert from
	 * @return the value converted to
	 */
	public static Value convert(Type type, Value value) {
		Type from = value.getType();
		if (type.equals(from))
			return value;
		switch (type.getName()) {
		case "bool":
			switch (from.getName()) {
			case "char": {
				byte val = ((Char) value).getValue();
				return new Bool(val != 0);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new Bool(val != 0);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new Bool(val != 0);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new Bool(val != 0);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new Bool(val != 0);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new Bool(val != 0);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new Bool(val != 0);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new Bool(val != 0);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new Bool(val != 0);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new Bool(val != 0);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new Bool(val != 0);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new Bool(val != 0);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new Bool(val != 0);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new Bool(val != 0);
			}
			}
		case "char":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new Char((byte) (val ? 1 : 0));
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new Char(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new Char(val);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new Char((byte) val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new Char((byte) val);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new Char((byte) val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new Char((byte) val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new Char((byte) val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new Char((byte) val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new Char((byte) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new Char((byte) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new Char((byte) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new Char((byte) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new Char((byte) val);
			}
			}
		case "signed char":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new SignedChar((byte) (val ? 1 : 0));
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new SignedChar(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new SignedChar(val);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new SignedChar((byte) val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new SignedChar((byte) val);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new SignedChar((byte) val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new SignedChar((byte) val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new SignedChar((byte) val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new SignedChar((byte) val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new SignedChar((byte) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new SignedChar((byte) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new SignedChar((byte) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new SignedChar((byte) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new SignedChar((byte) val);
			}
			}
		case "unsigned char":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new UnsignedChar((byte) (val ? 1 : 0));
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new UnsignedChar(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new UnsignedChar(val);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new UnsignedChar((byte) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedChar(bd.toBigInteger().byteValue());
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedChar(bd.toBigInteger().byteValue());
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedChar(bd.toBigInteger().byteValue());
			}
			}
		case "short int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new SignedShort((short) (val ? 1 : 0));
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new SignedShort(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new SignedShort(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new SignedShort((short) (val & 0xF));
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new SignedShort(val);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new SignedShort((short) val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new SignedShort((short) val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new SignedShort((short) val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new SignedShort((short) val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new SignedShort((short) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new SignedShort((short) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new SignedShort((short) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new SignedShort((short) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new SignedShort((short) val);
			}
			}
		case "unsigned short int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new UnsignedShort((short) (val ? 1 : 0));
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new UnsignedShort((short) (val & 0xF));
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new UnsignedShort((short) (val & 0xF));
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new UnsignedShort((short) (val & 0xF));
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new UnsignedShort(val);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new UnsignedShort((short) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedShort(bd.toBigInteger().shortValue());
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedShort(bd.toBigInteger().shortValue());
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedShort(bd.toBigInteger().shortValue());
			}
			}
		case "int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new SignedInt(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new SignedInt(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new SignedInt(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new SignedInt(val & 0xF);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new SignedInt(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new SignedInt(val & 0xFF);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new SignedInt(val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new SignedInt(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new SignedInt(val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new SignedInt((int) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new SignedInt((int) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new SignedInt((int) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new SignedInt((int) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new SignedInt((int) val);
			}
			}
		case "unsigned int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new UnsignedInt(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new UnsignedInt(val & 0xF);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new UnsignedInt(val & 0xF);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new UnsignedInt(val & 0xF);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new UnsignedInt(val & 0xFF);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new UnsignedInt(val & 0xFF);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new UnsignedInt(val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new UnsignedInt(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new UnsignedInt(val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new UnsignedInt((int) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new UnsignedInt((int) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedInt(bd.toBigInteger().intValue());
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedInt(bd.toBigInteger().intValue());
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedInt(bd.toBigInteger().intValue());
			}
			}
		case "long int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new SignedLong(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new SignedLong(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new SignedLong(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new SignedLong(val & 0xF);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new SignedLong(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new SignedLong(val & 0xFF);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new SignedLong(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new SignedLong(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new SignedLong(val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new SignedLong((int) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new SignedLong((int) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new SignedLong((int) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new SignedLong((int) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new SignedLong((int) val);
			}
			}
		case "unsigned long int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new UnsignedLong(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new UnsignedLong(val & 0xF);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new UnsignedLong(val & 0xF);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new UnsignedLong(val & 0xF);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new UnsignedLong(val & 0xFF);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new UnsignedLong(val & 0xFF);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new UnsignedLong(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new UnsignedLong(val);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new UnsignedLong(val);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new UnsignedLong((int) val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new UnsignedLong((int) val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLong(bd.toBigInteger().intValue());
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLong(bd.toBigInteger().intValue());
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLong(bd.toBigInteger().intValue());
			}
			}
		case "long long int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new SignedLongLong(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new SignedLongLong(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new SignedLongLong(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new SignedLongLong(val & 0xFL);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new SignedLongLong(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new SignedLongLong(val & 0xFFL);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new SignedLongLong(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new SignedLongLong(val & 0xFFFFL);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new SignedLongLong(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new SignedLongLong(val & 0xFFFFL);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new SignedLongLong(val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new SignedLongLong((long) val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new SignedLongLong((long) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new SignedLongLong((long) val);
			}
			}
		case "unsigned long long int":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new UnsignedLongLong(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new UnsignedLongLong(val & 0xFL);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new UnsignedLongLong(val & 0xFL);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new UnsignedLongLong(val & 0xFL);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new UnsignedLongLong(val & 0xFFL);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new UnsignedLongLong(val & 0xFFL);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new UnsignedLongLong(val & 0xFFFFL);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new UnsignedLongLong(val & 0xFFFFL);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new UnsignedLongLong(val & 0xFFFFL);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new UnsignedLongLong(val & 0xFFFFL);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new UnsignedLongLong(val);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLongLong(bd.toBigInteger().longValue());
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLongLong(bd.toBigInteger().longValue());
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				BigDecimal bd = new BigDecimal(val);
				return new UnsignedLongLong(bd.toBigInteger().longValue());
			}
			}
		case "float":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new CFloat(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new CFloat(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new CFloat(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new CFloat(val >= 0 ? val : val + 0x1p8f);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new CFloat(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new CFloat(val >= 0 ? val : val + 0x1p16f);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new CFloat(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new CFloat(val >= 0 ? val : val + 0x1p32f);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new CFloat(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new CFloat(val >= 0 ? val : val + 0x1p32f);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new CFloat(val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new CFloat(val >= 0 ? val : val + 0x1p64f);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new CFloat((float) val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new CFloat((float) val);
			}
			}
		case "double":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new CDouble(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new CDouble(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new CDouble(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new CDouble(val >= 0 ? val : val + 0x1p8);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new CDouble(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new CDouble(val >= 0 ? val : val + 0x1p16);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new CDouble(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new CDouble(val >= 0 ? val : val + 0x1p32);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new CDouble(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new CDouble(val >= 0 ? val : val + 0x1p32);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new CDouble(val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new CDouble(val >= 0 ? val : val + 0x1p64);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new CDouble(val);
			}
			case "long double": {
				double val = ((LongDouble) value).getValue();
				return new CDouble(val);
			}
			}
		case "long double":
			switch (from.getName()) {
			case "bool": {
				boolean val = ((Bool) value).getValue();
				return new LongDouble(val ? 1 : 0);
			}
			case "char": {
				byte val = ((Char) value).getValue();
				return new LongDouble(val);
			}
			case "signed char": {
				byte val = ((SignedChar) value).getValue();
				return new LongDouble(val);
			}
			case "unsigned char": {
				byte val = ((UnsignedChar) value).getValue();
				return new LongDouble(val >= 0 ? val : val + 0x1p8);
			}
			case "short int": {
				short val = ((SignedShort) value).getValue();
				return new LongDouble(val);
			}
			case "unsigned short int": {
				short val = ((UnsignedShort) value).getValue();
				return new LongDouble(val >= 0 ? val : val + 0x1p16);
			}
			case "int": {
				int val = ((SignedInt) value).getValue();
				return new LongDouble(val);
			}
			case "unsigned int": {
				int val = ((UnsignedInt) value).getValue();
				return new LongDouble(val >= 0 ? val : val + 0x1p32);
			}
			case "long int": {
				int val = ((SignedLong) value).getValue();
				return new LongDouble(val);
			}
			case "unsigned long int": {
				int val = ((UnsignedLong) value).getValue();
				return new LongDouble(val >= 0 ? val : val + 0x1p32);
			}
			case "long long int": {
				long val = ((SignedLongLong) value).getValue();
				return new LongDouble(val);
			}
			case "unsigned long long int": {
				long val = ((UnsignedLongLong) value).getValue();
				return new LongDouble(val >= 0 ? val : val + 0x1p64);
			}
			case "float": {
				float val = ((CFloat) value).getValue();
				return new LongDouble(val);
			}
			case "double": {
				double val = ((CDouble) value).getValue();
				return new LongDouble(val);
			}
			}
		}
		throw new CompileException("not a primitive type");
	}
}
