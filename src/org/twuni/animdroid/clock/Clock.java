package org.twuni.animdroid.clock;

import java.util.Timer;

import org.twuni.animdroid.listener.OnTickListener;

/**
 * A clock is a timer that ticks at a certain interval, notifying a listener with the elapsed time
 * since the start of the timing.
 */
public class Clock {

	private Timer timer;

	/**
	 * Starts a new task for reporting time.
	 * 
	 * @param delay
	 *            The delay, in milliseconds, before the clock ticks for the first time.
	 * @param tickInterval
	 *            The time, in milliseconds, between clock ticks.
	 * @param onTick
	 *            This listener will be notified every time the clock ticks.
	 */
	public void start( long delay, long tickInterval, OnTickListener onTick ) {
		if( timer == null ) {
			timer = new Timer( false );
		}
		timer.scheduleAtFixedRate( new ClockTask( onTick ), delay, tickInterval );
	}

	/**
	 * Starts a new task for reporting time.
	 * 
	 * @param tickInterval
	 *            The time, in milliseconds, between clock ticks.
	 * @param onTick
	 *            This listener will be notified every time the clock ticks.
	 */
	public void start( long tickInterval, OnTickListener onTick ) {
		start( 0, tickInterval, onTick );
	}

	/**
	 * Cancels and purges all tasks that this clock is currently running.
	 */
	public void stop() {
		if( timer != null ) {
			timer.cancel();
			timer.purge();
			timer = null;
		}
	}

}
