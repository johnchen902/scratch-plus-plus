package org.twbbs.pccprogram.scratchpp.object;

import java.util.Objects;

/**
 * Represents anything a type in C++.
 * 
 * @author johnchen902
 */
public class Type {
	private final String name;

	/**
	 * Create a type with such name.
	 * 
	 * @param name
	 *            its name
	 */
	public Type(String name) {
		this.name = Objects.requireNonNull(name);
	}

	/**
	 * Get the type's name.
	 * 
	 * @return its name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Returns if two types are equal.
	 * 
	 * @return <code>true</code> if and only if their names are equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Type))
			return false;
		Type other = (Type) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}
}
