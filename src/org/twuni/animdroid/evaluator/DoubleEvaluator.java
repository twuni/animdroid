package org.twuni.animdroid.evaluator;

public class DoubleEvaluator implements TypeEvaluator<Double> {

	@Override
	public Double evaluate( float fraction, Double startValue, Double endValue ) {
		return Double.valueOf( startValue.doubleValue() + fraction * ( endValue.doubleValue() - startValue.doubleValue() ) );
	}

}