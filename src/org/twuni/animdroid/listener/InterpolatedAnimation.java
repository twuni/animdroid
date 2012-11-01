package org.twuni.animdroid.listener;

import android.view.animation.Interpolator;

public class InterpolatedAnimation implements OnAnimationUpdateListener {

	private final Interpolator interpolator;
	private final OnAnimationUpdateListener delegate;

	public InterpolatedAnimation( Interpolator interpolator, OnAnimationUpdateListener delegate ) {
		this.interpolator = interpolator;
		this.delegate = delegate;
	}

	@Override
	public void onAnimationUpdate( float fraction ) {
		delegate.onAnimationUpdate( interpolator.getInterpolation( fraction ) );
	}

}
