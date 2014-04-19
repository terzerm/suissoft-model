package com.suissoft.model.util;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains static utility methods dealing with generics.
 */
public class GenericsUtil {
	
	/**
	 * Returns the actual type of a generic type variable associated with the
	 * given {@code type}, or null if it cannot be evaluated for instance
	 * because it is a type variable.
	 * 
	 * @param type	the type to inspect
	 * @param variable the variable of interest
	 * @return the actual type or null if it cannot be evaluated
	 * @throws IllegalArgumentException if type is none of [{@link Class}, {@link ParameterizedType}] 
	 */
	public static Class<?> getTypeArgument(final Type type, final TypeVariable<? extends GenericDeclaration> variable) {
		requireNonNull(type, "type cannot be null");
		requireNonNull(variable, "variable cannot be null");
		if (!(type instanceof Class || type instanceof ParameterizedType)) {
			throw new IllegalArgumentException("type must be a class or a parameterized type, but " + type + " is a " + type.getClass().getName());
		}
		final Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		return getTypeArgument(type, variable, resolvedTypes);
	}
	private static Class<?> getTypeArgument(final Type type, final TypeVariable<? extends GenericDeclaration> variable, Map<Type, Type> resolvedTypes) {
		// start walking up the inheritance hierarchy until we hit the end
		if (type instanceof Class) {
			final Class<?> clazz = (Class<?>) type;
			// there is no useful information for us in raw types, so just
			// keep going.
			for (final Type iface : clazz.getGenericInterfaces()) {
				final Class<?> result = getTypeArgument(iface, variable, resolvedTypes);
				if (result != null) {
					return result;
				}
			}
			final Type stype = clazz.getGenericSuperclass();
			return stype == null ? null : getTypeArgument(stype, variable, resolvedTypes);
		} else {
			final ParameterizedType parameterizedType = (ParameterizedType) type;
			final Class<?> rawType = (Class<?>) parameterizedType.getRawType();

			final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			final TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
			for (int i = 0; i < actualTypeArguments.length; i++) {
				if (typeParameters[i].equals(variable)) {
					final Class<?> cls = getClass(actualTypeArguments[i]);
					if (cls != null) {
						return cls;
					}
					return getClass(resolvedTypes.get(actualTypeArguments[i]));
				}
				resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
			}

			if (!rawType.equals(Object.class)) {
				final Type stype = rawType.getGenericSuperclass();
				return stype == null ? null : getTypeArgument(stype, variable, resolvedTypes);
			}
		}
		return null;
	}
	
	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 * 
	 * @param type
	 *            the type
	 * @return the underlying class
	 * @see <a
	 *      href="https://github.com/mongodb/morphia/blob/master/morphia/src/main/java/org/mongodb/morphia/utils/ReflectionUtils.java">morphia
	 *      ReflectionUtils</a>
	 */
	public static Class<?> getClass(final Type type) {
		if (type instanceof Class) {
			return (Class<?>) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			final Type componentType = ((GenericArrayType) type).getGenericComponentType();
			final Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// no instances
	private GenericsUtil() {
		super();
	}
}
