package org.twuni.animdroid;

import org.twuni.animdroid.clock.Clock;
import org.twuni.animdroid.evaluator.TypeEvaluator;
import org.twuni.animdroid.listener.InterpolatedAnimation;
import org.twuni.animdroid.listener.OnAnimationUpdateListener;
import org.twuni.animdroid.listener.ValueAnimation;
import org.twuni.animdroid.receiver.ValueReceiver;

import android.view.animation.Interpolator;

/**
 * This convenience wrapper makes it easy to trigger simple animations.
 */
public class Animdroid {

	private static final long DEFAULT_FPS = 30;

	private static Animator createAnimator( long duration, RepeatMode repeatMode, Interpolator interpolator, OnAnimationUpdateListener onAnimationUpdate ) {
		Animator animator = new Animator( duration, repeatMode );
		animator.setOnAnimationUpdate( interpolator == null ? onAnimationUpdate : new InterpolatedAnimation( interpolator, onAnimationUpdate ) );
		return animator;
	}

	private final Clock clock = new Clock();

	private final long fps;

	public Animdroid() {
		this( Animdroid.DEFAULT_FPS );
	}

	public Animdroid( long fps ) {
		this.fps = fps;
	}

	public void animate( long duration, double fromValue, double toValue, ValueReceiver<Double> receiver ) {
		animate( duration, null, fromValue, toValue, receiver );
	}

	public void animate( long duration, float fromValue, float toValue, ValueReceiver<Float> receiver ) {
		animate( duration, fromValue, toValue, receiver, RepeatMode.NONE );
	}

	public void animate( long duration, float fromValue, float toValue, ValueReceiver<Float> receiver, RepeatMode repeatMode ) {
		animate( duration, null, fromValue, toValue, receiver, repeatMode );
	}

	public void animate( long duration, int fromValue, int toValue, ValueReceiver<Integer> receiver ) {
		animate( duration, null, fromValue, toValue, receiver );
	}

	public void animate( long duration, Interpolator interpolator, double fromValue, double toValue, ValueReceiver<Double> receiver ) {
		animate( duration, interpolator, ValueAnimation.ofDouble( fromValue, toValue, receiver ) );
	}

	public void animate( long duration, Interpolator interpolator, float fromValue, float toValue, ValueReceiver<Float> receiver ) {
		animate( duration, interpolator, fromValue, toValue, receiver, RepeatMode.NONE );
	}

	public void animate( long duration, Interpolator interpolator, float fromValue, float toValue, ValueReceiver<Float> receiver, RepeatMode repeatMode ) {
		animate( duration, interpolator, ValueAnimation.ofFloat( fromValue, toValue, receiver ), repeatMode );
	}

	public void animate( long duration, Interpolator interpolator, int fromValue, int toValue, ValueReceiver<Integer> receiver ) {
		animate( duration, interpolator, ValueAnimation.ofInt( fromValue, toValue, receiver ) );
	}

	public void animate( long duration, Interpolator interpolator, Object target, String propertyName, double fromValue, double toValue ) {
		animate( duration, interpolator, ValueAnimation.ofDouble( target, propertyName, fromValue, toValue ) );
	}

	public void animate( long duration, Interpolator interpolator, Object target, String propertyName, float fromValue, float toValue ) {
		animate( duration, interpolator, ValueAnimation.ofFloat( target, propertyName, fromValue, toValue ) );
	}

	public void animate( long duration, Interpolator interpolator, Object target, String propertyName, int fromValue, int toValue ) {
		animate( duration, interpolator, target, propertyName, fromValue, toValue, RepeatMode.NONE );
	}

	public void animate( long duration, Interpolator interpolator, Object target, String propertyName, int fromValue, int toValue, RepeatMode repeatMode ) {
		animate( duration, interpolator, ValueAnimation.ofInt( target, propertyName, fromValue, toValue ), repeatMode );
	}

	public void animate( long duration, Interpolator interpolator, OnAnimationUpdateListener onAnimationUpdate ) {
		animate( duration, interpolator, onAnimationUpdate, RepeatMode.NONE );
	}

	public void animate( long duration, Interpolator interpolator, OnAnimationUpdateListener onAnimationUpdate, RepeatMode repeatMode ) {
		clock.start( 1000 / fps, Animdroid.createAnimator( duration, repeatMode, interpolator, onAnimationUpdate ) );
	}

	public <T> void animate( long duration, Interpolator interpolator, T fromValue, T toValue, ValueReceiver<T> receiver, TypeEvaluator<T> evaluator ) {
		animate( duration, interpolator, new ValueAnimation<T>( fromValue, toValue, receiver, evaluator ) );
	}

	public void animate( long duration, Object target, String propertyName, double fromValue, double toValue ) {
		animate( duration, null, target, propertyName, fromValue, toValue );
	}

	public void animate( long duration, Object target, String propertyName, float fromValue, float toValue ) {
		animate( duration, null, target, propertyName, fromValue, toValue );
	}

	public void animate( long duration, Object target, String propertyName, int fromValue, int toValue ) {
		animate( duration, target, propertyName, fromValue, toValue, RepeatMode.NONE );
	}

	public void animate( long duration, Object target, String propertyName, int fromValue, int toValue, RepeatMode repeatMode ) {
		animate( duration, null, target, propertyName, fromValue, toValue, repeatMode );
	}

	public void animate( long duration, OnAnimationUpdateListener onAnimationUpdate ) {
		animate( duration, null, onAnimationUpdate );
	}

	public void animate( long duration, OnAnimationUpdateListener onAnimationUpdate, RepeatMode repeatMode ) {
		animate( duration, null, onAnimationUpdate, repeatMode );
	}

	public <T> void animate( long duration, T fromValue, T toValue, ValueReceiver<T> receiver, TypeEvaluator<T> evaluator ) {
		animate( duration, null, fromValue, toValue, receiver, evaluator );
	}

}
