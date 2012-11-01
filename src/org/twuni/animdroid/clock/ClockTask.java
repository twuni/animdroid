package org.twuni.animdroid.clock;

import java.util.TimerTask;

import org.twuni.animdroid.listener.OnTickListener;

/**
 * A clock task reports the time elapsed since its first invocation.
 */
public class ClockTask extends TimerTask {

	private final OnTickListener onTick;
	private long time = -1;

	/**
	 * @param onTick
	 *            This listener will be notified whenever the timer runs.
	 * @throws IllegalArgumentException
	 *             if onTick is null.
	 */
	public ClockTask( OnTickListener onTick ) {
		if( onTick == null ) {
			throw new IllegalArgumentException();
		}
		this.onTick = onTick;
	}

	/**
	 * The first time this task is run, the time reported to onTick will be 0. Subsequent runs will
	 * report elapsed time relative to the first execution of this method. This task is cancelled
	 * whenever its listener throws a ClockException.
	 */
	@Override
	public void run() {

		long now = System.currentTimeMillis();

		if( time < 0 ) {
			time = now;
		}

		try {
			onTick.onTick( now - time );
		} catch( ClockException exception ) {
			cancel();
		}

	}

}
