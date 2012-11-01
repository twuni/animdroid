package org.twuni.animdroid.evaluator;

public interface TypeEvaluator<T> {

	public T evaluate( float fraction, T startValue, T endValue );

}