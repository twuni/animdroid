package org.twuni.animdroid.listener;

import org.twuni.animdroid.clock.ClockException;

/**
 * Listens for the ticking of a clock.
 */
public interface OnTickListener {

	/**
	 * This method is called whenever the clock ticks.
	 * 
	 * @param elapsedTime
	 *            The amount of time that has elapsed since the beginning of the time measurement.
	 * @throws ClockException
	 *             Stops the clock, freeing the listener and preventing this method from being
	 *             called again.
	 */
	public void onTick( long elapsedTime );

}
