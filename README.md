Animdroid: An Animation Library for Android
=======================================================

A property animation system was introduced to Android in API level 11.
Though this system is pretty self-contained, the Android support library
still does not provide a backwards-compatible API. Animdroid provides
property animation support for all platforms.

What is Property Animation?
---------------------------

When you have a Java object with a property that needs to change over
time, you probably want Property Animation. Property Animation is a way
to transition between values on arbitrary properties of arbitrary objects.

Sample Code
-----------

In this simple example, a progress bar is animated from 0 to 100 for 10 seconds:

    new Animdroid().animate( 10000, findViewById( R.id.progress ), "progress", 0, 100 );

In this slightly more complex example, a string is written character-by-character to a TextView
for 2.5 seconds:

    final String message = "This is a good message.";
    
    new Animdroid().animate( 2500, 0, message.length(), new ValueReceiver<Integer>() {
    
        @Override
        public void receive( final Integer length ) {
            runOnUiThread( new Runnable() {
            
                @Override
                public void run() {
                    TextView view = (TextView) findViewById( R.id.message );
                    view.setText( message.substring( 0, length.intValue() ) );
                }
                
            } );
        }
        
    } );

For more control and fine-tuning, here's the long way to do the exact same thing as above:

    final String message = "This is a good message.";
    final long FPS = 30;
    
    Animator animator = new Animator( 2500, RepeatMode.NONE );
    Interpolator interpolator = new LinearInterpolator();
    
    ValueAnimation<Integer> animation = ValueAnimation.ofInt( 0, message.length(), new ValueReceiver<Integer>() {
    
        @Override
        public void receive( final Integer length ) {
            runOnUiThread( new Runnable() {
            
                @Override
                public void run() {
                    TextView view = (TextView) findViewById( R.id.message );
                    view.setText( message.substring( 0, length.intValue() ) );
                }
                
            } );
        }
        
    } );
    
    animator.setOnAnimationUpdate( new InterpolatedAnimation( interpolator, animation ) );
    
    Clock clock = new Clock();
    clock.start( 1000 / FPS, animator );

