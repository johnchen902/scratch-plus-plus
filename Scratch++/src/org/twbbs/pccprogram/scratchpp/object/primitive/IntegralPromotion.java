package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;

/**
 * See {@link #promote(Type)}.
 * 
 * @author johnchen902
 */
public class IntegralPromotion {
	private IntegralPromotion() {
	}

	/**
	 * Perform an integral promotion as described in the C++ specification
	 * section 4.5 [conv.prom].
	 * 
	 * @param t
	 *            the source type
	 * @return the destination type
	 */
	public static Type promote(Type t) {
		if (t.equals(Char.TYPE))
			return SignedInt.TYPE;
		if (t.equals(SignedChar.TYPE))
			return SignedInt.TYPE;
		if (t.equals(SignedShort.TYPE))
			return SignedInt.TYPE;
		if (t.equals(UnsignedChar.TYPE))
			return SignedInt.TYPE;
		if (t.equals(UnsignedShort.TYPE))
			return SignedInt.TYPE;
		if (t.equals(Bool.TYPE))
			return SignedInt.TYPE;
		return t;
	}
}
