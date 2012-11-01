package org.twuni.animdroid;

import org.twuni.animdroid.clock.ClockException;
import org.twuni.animdroid.listener.OnAnimationEndListener;
import org.twuni.animdroid.listener.OnAnimationStartListener;
import org.twuni.animdroid.listener.OnAnimationUpdateListener;
import org.twuni.animdroid.listener.OnTickListener;

public class Animator implements OnTickListener {

	private final long duration;
	private final RepeatMode repeatMode;

	private OnAnimationStartListener onAnimationStart;
	private OnAnimationUpdateListener onAnimationUpdate;
	private OnAnimationEndListener onAnimationEnd;

	/**
	 * @param duration
	 *            The amount of time spent for each cycle of the animation.
	 */
	public Animator( long duration ) {
		this( duration, RepeatMode.NONE );
	}

	/**
	 * @param duration
	 *            The amount of time spent for each cycle of the animation.
	 * @param repeatMode
	 *            Controls if and how the animation will repeat.
	 */
	public Animator( long duration, RepeatMode repeatMode ) {
		this.duration = duration;
		this.repeatMode = repeatMode;
	}

	@Override
	public void onTick( long time ) {

		if( time == 0 && onAnimationStart != null ) {
			onAnimationStart.onAnimationStart();
		}

		float fraction = (float) ( 1.0 * ( time % duration ) / duration );
		long cycle = 1 + time / duration;

		if( RepeatMode.REVERSE.equals( repeatMode ) && cycle % 2 == 0 ) {
			fraction = 1 - fraction;
		}

		if( cycle > 1 && RepeatMode.NONE.equals( repeatMode ) ) {
			fraction = 1.0f;
		}

		if( onAnimationUpdate != null ) {
			onAnimationUpdate.onAnimationUpdate( fraction );
		}

		if( cycle > 1 && RepeatMode.NONE.equals( repeatMode ) ) {
			if( onAnimationEnd != null ) {
				onAnimationEnd.onAnimationEnd();
			}
			throw new ClockException();
		}

	}

	/**
	 * @param onAnimationEnd
	 *            This listener will be notified when the animation has ended.
	 */
	public void setOnAnimationEnd( OnAnimationEndListener onAnimationEnd ) {
		this.onAnimationEnd = onAnimationEnd;
	}

	/**
	 * @param onAnimationStart
	 *            This listener will be notified when the animation is first started.
	 */
	public void setOnAnimationStart( OnAnimationStartListener onAnimationStart ) {
		this.onAnimationStart = onAnimationStart;
	}

	/**
	 * @param onAnimationUpdate
	 *            This listener will be notified every time the clock ticks.
	 */
	public void setOnAnimationUpdate( OnAnimationUpdateListener onAnimationUpdate ) {
		this.onAnimationUpdate = onAnimationUpdate;
	}

}
