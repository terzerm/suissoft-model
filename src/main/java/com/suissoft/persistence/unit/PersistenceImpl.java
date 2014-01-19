package com.suissoft.persistence.unit;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

/**
 * Implements the {@link Persistence} annotation, used to return an annotation
 * instance from {@link Unit#asAnnotation()}.
 */
class PersistenceImpl implements Persistence, Serializable {
	private final Unit value;

	PersistenceImpl(Unit value) {
		this.value = requireNonNull(value, "value cannot be null");
	}

	@Override
	public Unit value() {
		return this.value;
	}

	@Override
	public int hashCode() {
		// This is specified in java.lang.Annotation.
		return (127 * "value".hashCode()) ^ value.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Persistence)) {
			return false;
		}

		final Persistence other = (Persistence) o;
		return value.equals(other.value());
	}

	@Override
	public String toString() {
		return "@" + Persistence.class.getName() + "(value=" + value + ")";
	}

	@Override
	public Class<Persistence> annotationType() {
		return Persistence.class;
	}

	private static final long serialVersionUID = 0;
}
