package org.twuni.animdroid.evaluator;

public class FloatEvaluator implements TypeEvaluator<Float> {

	@Override
	public Float evaluate( float fraction, Float startValue, Float endValue ) {
		return Float.valueOf( startValue.floatValue() + fraction * ( endValue.floatValue() - startValue.floatValue() ) );
	}

}