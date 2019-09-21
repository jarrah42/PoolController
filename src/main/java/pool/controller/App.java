package pool.controller;

import pool.controller.comms.Comms;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
        Comms comms = new Comms();
        comms.run();
    }
}
