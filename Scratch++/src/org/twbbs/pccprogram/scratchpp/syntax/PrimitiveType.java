package org.twbbs.pccprogram.scratchpp.syntax;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

import org.twbbs.pccprogram.scratchpp.FontManager;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedInt;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedLong;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedLongLong;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedInt;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedLong;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedLongLong;

/**
 * The view of a primitive type in C++.
 * 
 * @author johnchen902
 */
public class PrimitiveType extends Symbol {

	private final String name;
	private final Type type;

	private PrimitiveType(int x, int y, String name, Type type) {
		super(x, y, (int) FontManager.getStringWidth(name) + 16, 16, Color.CYAN);
		this.name = Objects.requireNonNull(name);
		this.type = Objects.requireNonNull(type);
	}

	/**
	 * Create such a primitive type with the specified name and location.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param s
	 *            the type's name
	 */
	public PrimitiveType(int x, int y, String s) {
		this(x, y, s, typeOf(s));
	}

	/**
	 * Get its type
	 * 
	 * @return its type
	 */
	public Type getType() {
		return type;
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(FontManager.getFont());
		g.drawString(name, getX() + 9, getY() + 12);
	}

	@Override
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new RoundRectangle2D.Double(x, y, w, h, h, h);
	}

	private static IllegalArgumentException unsupported(String str) {
		return new IllegalArgumentException(str + " is not yet supported");
	}

	/**
	 * Get the primitive type denoted by the string
	 * 
	 * @param s
	 *            the string
	 * @return the primitive type
	 */
	public static Type typeOf(String s) {
		switch (s) {
		case "bool":
			throw unsupported("bool");
		case "char":
			throw unsupported("char");
		case "signed char":
			throw unsupported("signed char");
		case "unsigned char":
			throw unsupported("unsigned char");
		case "short":
		case "short int":
		case "signed short":
		case "signed short int":
			throw unsupported("short");
		case "unsigned short":
		case "unsigned short int":
			throw unsupported("unsigned short");
		case "int":
		case "signed":
		case "signed int":
			return SignedInt.TYPE;
		case "unsigned":
		case "unsigned int":
			return UnsignedInt.TYPE;
		case "long":
		case "long int":
		case "signed long":
		case "signed long int":
			return SignedLong.TYPE;
		case "unsigned long":
		case "unsigned long int":
			return UnsignedLong.TYPE;
		case "long long":
		case "long long int":
		case "signed long long":
		case "signed long long int":
			return SignedLongLong.TYPE;
		case "unsigned long long":
		case "unsigned long long int":
			return UnsignedLongLong.TYPE;
		case "float":
			return Float.TYPE;
		case "double":
			return Double.TYPE;
		}
		throw new IllegalArgumentException("unknown type " + s);
	}
}
