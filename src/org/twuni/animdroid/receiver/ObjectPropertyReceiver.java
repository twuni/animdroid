package org.twuni.animdroid.receiver;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectPropertyReceiver<T> implements ValueReceiver<T> {

	private static Method getSetterMethod( Object target, String propertyName ) {
		String setterMethodName = ObjectPropertyReceiver.getSetterMethodName( propertyName );
		for( Method method : target.getClass().getMethods() ) {
			if( setterMethodName.equals( method.getName() ) && method.getParameterTypes().length == 1 ) {
				return method;
			}
		}
		throw new RuntimeException( new NoSuchMethodException( setterMethodName ) );
	}

	private static String getSetterMethodName( String propertyName ) {
		return String.format( "set%s%s", propertyName.substring( 0, 1 ).toUpperCase(), propertyName.substring( 1 ) );
	}

	private final SoftReference<Object> reference;
	private final Method setterMethod;

	public ObjectPropertyReceiver( Object target, String propertyName ) {
		this.reference = new SoftReference<Object>( target );
		this.setterMethod = ObjectPropertyReceiver.getSetterMethod( target, propertyName );
	}

	@Override
	public void receive( T newValue ) {
		Object target = reference.get();
		if( target == null ) {
			return;
		}
		try {
			setterMethod.invoke( target, newValue );
		} catch( IllegalAccessException exception ) {
			// Critical failure.
			throw new RuntimeException( exception );
		} catch( IllegalArgumentException exception ) {
			throw new RuntimeException( exception );
			// Ignore the frame? Cancel the animation?
		} catch( InvocationTargetException exception ) {
			throw new RuntimeException( exception );
			// Ignore the frame? Cancel the animation?
		}
	}

}
