package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;

/**
 * See {@link #getType(Type)}.
 * 
 * @author johnchen902
 */
public class UsualArithmeticConversion {
	private UsualArithmeticConversion() {
	}

	/**
	 * Perform a usual arithmetic conversion as described in the C++
	 * specification chapter 5 [expr] clause 10.
	 * 
	 * @param l
	 *            the left hand side type
	 * @param r
	 *            the right hand side type
	 * @return the type result in
	 */
	public static Type getType(Type l, Type r) {
		if (l.equals(LongDouble.TYPE) || r.equals(LongDouble.TYPE))
			return LongDouble.TYPE;
		if (l.equals(CDouble.TYPE) || r.equals(CDouble.TYPE))
			return CDouble.TYPE;
		if (l.equals(CFloat.TYPE) || r.equals(CFloat.TYPE))
			return CFloat.TYPE;
		l = IntegralPromotion.promote(l);
		r = IntegralPromotion.promote(r);
		if (l.equals(r))
			return l;
		if (l.equals(UnsignedLongLong.TYPE) || r.equals(UnsignedLongLong.TYPE))
			return UnsignedLongLong.TYPE;
		if (l.equals(SignedLongLong.TYPE) || r.equals(SignedLongLong.TYPE))
			return SignedLongLong.TYPE;
		if (l.equals(UnsignedLong.TYPE) || r.equals(UnsignedLong.TYPE))
			return UnsignedLong.TYPE;
		if (l.equals(SignedInt.TYPE))
			return r;
		if (r.equals(SignedInt.TYPE))
			return l;
		return UnsignedLong.TYPE;
	}
}
