package org.twuni.animdroid;

/**
 * Determines the intended repetition behavior of an animation.
 */
public enum RepeatMode {

	/**
	 * Restart this animation from the beginning when it has completed a cycle.
	 */
	RESTART,

	/**
	 * Reverse this animation each time a cycle is completed.
	 */
	REVERSE,

	/**
	 * End this animation once it has completed a single cycle.
	 */
	NONE

}