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

	/**
	 * Adds two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value add(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
		case "int": {
			int l = ((SignedInt) lhs).getValue();
			int r = ((SignedInt) rhs).getValue();
			return new SignedInt(l + r);
		}
		case "unsigned int": {
			int l = ((UnsignedInt) lhs).getValue();
			int r = ((UnsignedInt) rhs).getValue();
			return new UnsignedInt(l + r);
		}
		case "long int": {
			int l = ((SignedLong) lhs).getValue();
			int r = ((SignedLong) rhs).getValue();
			return new SignedLong(l + r);
		}
		case "unsigned long int": {
			int l = ((UnsignedLong) lhs).getValue();
			int r = ((UnsignedLong) rhs).getValue();
			return new UnsignedLong(l + r);
		}
		case "long long int": {
			long l = ((SignedLongLong) lhs).getValue();
			long r = ((SignedLongLong) rhs).getValue();
			return new SignedLongLong(l + r);
		}
		case "unsigned long long int": {
			long l = ((UnsignedLongLong) lhs).getValue();
			long r = ((UnsignedLongLong) rhs).getValue();
			return new UnsignedLongLong(l + r);
		}
		case "float": {
			float l = ((CFloat) lhs).getValue();
			float r = ((CFloat) rhs).getValue();
			return new CFloat(l + r);
		}
		case "double": {
			double l = ((CDouble) lhs).getValue();
			double r = ((CDouble) rhs).getValue();
			return new CDouble(l + r);
		}
		case "long double": {
			double l = ((LongDouble) lhs).getValue();
			double r = ((LongDouble) rhs).getValue();
			return new LongDouble(l + r);
		}
		}
		throw new CompileException("not a primitive type");
	}

	/**
	 * Minuses two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value minus(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
		case "int": {
			int l = ((SignedInt) lhs).getValue();
			int r = ((SignedInt) rhs).getValue();
			return new SignedInt(l - r);
		}
		case "unsigned int": {
			int l = ((UnsignedInt) lhs).getValue();
			int r = ((UnsignedInt) rhs).getValue();
			return new UnsignedInt(l - r);
		}
		case "long int": {
			int l = ((SignedLong) lhs).getValue();
			int r = ((SignedLong) rhs).getValue();
			return new SignedLong(l - r);
		}
		case "unsigned long int": {
			int l = ((UnsignedLong) lhs).getValue();
			int r = ((UnsignedLong) rhs).getValue();
			return new UnsignedLong(l - r);
		}
		case "long long int": {
			long l = ((SignedLongLong) lhs).getValue();
			long r = ((SignedLongLong) rhs).getValue();
			return new SignedLongLong(l - r);
		}
		case "unsigned long long int": {
			long l = ((UnsignedLongLong) lhs).getValue();
			long r = ((UnsignedLongLong) rhs).getValue();
			return new UnsignedLongLong(l - r);
		}
		case "float": {
			float l = ((CFloat) lhs).getValue();
			float r = ((CFloat) rhs).getValue();
			return new CFloat(l - r);
		}
		case "double": {
			double l = ((CDouble) lhs).getValue();
			double r = ((CDouble) rhs).getValue();
			return new CDouble(l - r);
		}
		case "long double": {
			double l = ((LongDouble) lhs).getValue();
			double r = ((LongDouble) rhs).getValue();
			return new LongDouble(l - r);
		}
		}
		throw new CompileException("not a primitive type");
	}

	/**
	 * Multiplies two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value multiply(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
		case "int": {
			int l = ((SignedInt) lhs).getValue();
			int r = ((SignedInt) rhs).getValue();
			return new SignedInt(l * r);
		}
		case "unsigned int": {
			int l = ((UnsignedInt) lhs).getValue();
			int r = ((UnsignedInt) rhs).getValue();
			return new UnsignedInt(l * r);
		}
		case "long int": {
			int l = ((SignedLong) lhs).getValue();
			int r = ((SignedLong) rhs).getValue();
			return new SignedLong(l * r);
		}
		case "unsigned long int": {
			int l = ((UnsignedLong) lhs).getValue();
			int r = ((UnsignedLong) rhs).getValue();
			return new UnsignedLong(l * r);
		}
		case "long long int": {
			long l = ((SignedLongLong) lhs).getValue();
			long r = ((SignedLongLong) rhs).getValue();
			return new SignedLongLong(l * r);
		}
		case "unsigned long long int": {
			long l = ((UnsignedLongLong) lhs).getValue();
			long r = ((UnsignedLongLong) rhs).getValue();
			return new UnsignedLongLong(l * r);
		}
		case "float": {
			float l = ((CFloat) lhs).getValue();
			float r = ((CFloat) rhs).getValue();
			return new CFloat(l * r);
		}
		case "double": {
			double l = ((CDouble) lhs).getValue();
			double r = ((CDouble) rhs).getValue();
			return new CDouble(l * r);
		}
		case "long double": {
			double l = ((LongDouble) lhs).getValue();
			double r = ((LongDouble) rhs).getValue();
			return new LongDouble(l * r);
		}
		}
		throw new CompileException("not a primitive type");
	}

	/**
	 * Divides two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value divide(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
		case "int": {
			int l = ((SignedInt) lhs).getValue();
			int r = ((SignedInt) rhs).getValue();
			return new SignedInt(l / r);
		}
		case "unsigned int": {
			int l = ((UnsignedInt) lhs).getValue();
			int r = ((UnsignedInt) rhs).getValue();
			return new UnsignedInt(Integer.divideUnsigned(l, r));
		}
		case "long int": {
			int l = ((SignedLong) lhs).getValue();
			int r = ((SignedLong) rhs).getValue();
			return new SignedLong(l / r);
		}
		case "unsigned long int": {
			int l = ((UnsignedLong) lhs).getValue();
			int r = ((UnsignedLong) rhs).getValue();
			return new UnsignedLong(Integer.divideUnsigned(l, r));
		}
		case "long long int": {
			long l = ((SignedLongLong) lhs).getValue();
			long r = ((SignedLongLong) rhs).getValue();
			return new SignedLongLong(l / r);
		}
		case "unsigned long long int": {
			long l = ((UnsignedLongLong) lhs).getValue();
			long r = ((UnsignedLongLong) rhs).getValue();
			return new UnsignedLongLong(Long.divideUnsigned(l, r));
		}
		case "float": {
			float l = ((CFloat) lhs).getValue();
			float r = ((CFloat) rhs).getValue();
			return new CFloat(l / r);
		}
		case "double": {
			double l = ((CDouble) lhs).getValue();
			double r = ((CDouble) rhs).getValue();
			return new CDouble(l / r);
		}
		case "long double": {
			double l = ((LongDouble) lhs).getValue();
			double r = ((LongDouble) rhs).getValue();
			return new LongDouble(l / r);
		}
		}
		throw new CompileException("not a primitive type");
	}

	/**
	 * Modules two value.
	 * 
	 * @param lhs
	 *            the left hand side operand
	 * @param rhs
	 *            the right hand side operand
	 * @return the resulting value
	 */
	public static Value module(Value lhs, Value rhs) {
		Type t = getType(lhs.getType(), rhs.getType());
		lhs = PrimitiveConversion.convert(t, lhs);
		rhs = PrimitiveConversion.convert(t, rhs);
		switch (t.getName()) {
		case "int": {
			int l = ((SignedInt) lhs).getValue();
			int r = ((SignedInt) rhs).getValue();
			return new SignedInt(l % r);
		}
		case "unsigned int": {
			int l = ((UnsignedInt) lhs).getValue();
			int r = ((UnsignedInt) rhs).getValue();
			return new UnsignedInt(Integer.remainderUnsigned(l, r));
		}
		case "long int": {
			int l = ((SignedLong) lhs).getValue();
			int r = ((SignedLong) rhs).getValue();
			return new SignedLong(l % r);
		}
		case "unsigned long int": {
			int l = ((UnsignedLong) lhs).getValue();
			int r = ((UnsignedLong) rhs).getValue();
			return new UnsignedLong(Integer.remainderUnsigned(l, r));
		}
		case "long long int": {
			long l = ((SignedLongLong) lhs).getValue();
			long r = ((SignedLongLong) rhs).getValue();
			return new SignedLongLong(l % r);
		}
		case "unsigned long long int": {
			long l = ((UnsignedLongLong) lhs).getValue();
			long r = ((UnsignedLongLong) rhs).getValue();
			return new UnsignedLongLong(Long.remainderUnsigned(l, r));
		}
		case "float": {
			float l = ((CFloat) lhs).getValue();
			float r = ((CFloat) rhs).getValue();
			return new CFloat(l % r);
		}
		case "double": {
			double l = ((CDouble) lhs).getValue();
			double r = ((CDouble) rhs).getValue();
			return new CDouble(l % r);
		}
		case "long double": {
			double l = ((LongDouble) lhs).getValue();
			double r = ((LongDouble) rhs).getValue();
			return new LongDouble(l % r);
		}
		}
		throw new CompileException("not a primitive type");
	}
}
