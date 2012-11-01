package org.twuni.animdroid.evaluator;

public class IntegerEvaluator implements TypeEvaluator<Integer> {

	@Override
	public Integer evaluate( float fraction, Integer startValue, Integer endValue ) {
		return Integer.valueOf( (int) Math.floor( startValue.intValue() + fraction * ( endValue.intValue() - startValue.intValue() ) ) );
	}

}
