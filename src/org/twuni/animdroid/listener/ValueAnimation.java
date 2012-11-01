package org.twuni.animdroid.listener;

import org.twuni.animdroid.evaluator.ColorEvaluator;
import org.twuni.animdroid.evaluator.DoubleEvaluator;
import org.twuni.animdroid.evaluator.FloatEvaluator;
import org.twuni.animdroid.evaluator.IntegerEvaluator;
import org.twuni.animdroid.evaluator.TypeEvaluator;
import org.twuni.animdroid.receiver.ObjectPropertyReceiver;
import org.twuni.animdroid.receiver.ValueReceiver;

public class ValueAnimation<T> implements OnAnimationUpdateListener {

	public static ValueAnimation<Integer> ofColor( int fromValue, int toValue, ValueReceiver<Integer> receiver ) {
		return new ValueAnimation<Integer>( Integer.valueOf( fromValue ), Integer.valueOf( toValue ), receiver, new ColorEvaluator() );
	}

	public static ValueAnimation<Integer> ofColor( Object target, String propertyName, int fromValue, int toValue ) {
		return ValueAnimation.ofColor( fromValue, toValue, new ObjectPropertyReceiver<Integer>( target, propertyName ) );
	}

	public static ValueAnimation<Double> ofDouble( double fromValue, double toValue, ValueReceiver<Double> receiver ) {
		return new ValueAnimation<Double>( Double.valueOf( fromValue ), Double.valueOf( toValue ), receiver, new DoubleEvaluator() );
	}

	public static ValueAnimation<Double> ofDouble( Object target, String propertyName, double fromValue, double toValue ) {
		return ValueAnimation.ofDouble( fromValue, toValue, new ObjectPropertyReceiver<Double>( target, propertyName ) );
	}

	public static ValueAnimation<Float> ofFloat( float fromValue, float toValue, ValueReceiver<Float> receiver ) {
		return new ValueAnimation<Float>( Float.valueOf( fromValue ), Float.valueOf( toValue ), receiver, new FloatEvaluator() );
	}

	public static ValueAnimation<Float> ofFloat( Object target, String propertyName, float fromValue, float toValue ) {
		return ValueAnimation.ofFloat( fromValue, toValue, new ObjectPropertyReceiver<Float>( target, propertyName ) );
	}

	public static ValueAnimation<Integer> ofInt( int fromValue, int toValue, ValueReceiver<Integer> receiver ) {
		return new ValueAnimation<Integer>( Integer.valueOf( fromValue ), Integer.valueOf( toValue ), receiver, new IntegerEvaluator() );
	}

	public static ValueAnimation<Integer> ofInt( Object target, String propertyName, int fromValue, int toValue ) {
		return ValueAnimation.ofInt( fromValue, toValue, new ObjectPropertyReceiver<Integer>( target, propertyName ) );
	}

	protected final T fromValue;
	protected final T toValue;
	protected final TypeEvaluator<T> evaluator;
	protected final ValueReceiver<T> receiver;

	public ValueAnimation( T fromValue, T toValue, ValueReceiver<T> receiver, TypeEvaluator<T> evaluator ) {
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.evaluator = evaluator;
		this.receiver = receiver;
	}

	@Override
	public void onAnimationUpdate( float fraction ) {
		receiver.receive( evaluator.evaluate( fraction, fromValue, toValue ) );
	}

}
