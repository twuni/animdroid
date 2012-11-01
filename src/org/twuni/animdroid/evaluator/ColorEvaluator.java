package org.twuni.animdroid.evaluator;

public class ColorEvaluator implements TypeEvaluator<Integer> {

	@Override
	public Integer evaluate( float fraction, Integer startValue, Integer endValue ) {

		int start = startValue.intValue();

		int startAlpha = start >> 24;
		int startRed = 0xFF & start >> 16;
		int startGreen = 0xFF & start >> 8;
		int startBlue = 0xFF & start;

		int end = endValue.intValue();

		int endAlpha = end >> 24;
		int endRed = 0xFF & end >> 16;
		int endGreen = 0xFF & end >> 8;
		int endBlue = 0xFF & end;

		int stepAlpha = (int) ( startAlpha + Math.floor( fraction * ( endAlpha - startAlpha ) ) );
		int stepRed = (int) ( startRed + Math.floor( fraction * ( endRed - startRed ) ) );
		int stepGreen = (int) ( startGreen + Math.floor( fraction * ( endGreen - startGreen ) ) );
		int stepBlue = (int) ( startBlue + Math.floor( fraction * ( endBlue - startBlue ) ) );

		int step = stepAlpha << 24 | stepRed << 16 | stepGreen << 8 | stepBlue;

		return Integer.valueOf( step );

	}

}
