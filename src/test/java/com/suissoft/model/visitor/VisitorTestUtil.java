package com.suissoft.model.visitor;

import java.lang.reflect.Method;

import com.suissoft.model.entity.Entity;

class VisitorTestUtil {

	static int getVisitMethodCount(Class<?> visitorClass) {
		int count = 0;
		for (final Method method : visitorClass.getMethods()) {
			if (method.getName().startsWith("visit")) {
				count++;
			}
		}
		return count;
	}
	
	static Entity[] merge(Entity[]... arrays) {
		int count = 0;
		for (int i = 0; i < arrays.length; i++) {
			count += arrays[i].length;
		}
		final Entity[] result = new Entity[count];
		int offset = 0;
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
			offset += arrays[i].length;
		}
		return result;
	}

}
