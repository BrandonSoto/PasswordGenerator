package view;

import java.awt.EventQueue;

/**
 * Starts the Password_GUI. 
 * @author Brandon Soto
 * @version Apr 10, 2014
 */
public final class StartGUI {
	
	/** Prevents instantiation. */
	private StartGUI() {
		// do nothing
	}

	/**
	 * Calls the Password_GUI constructor to start the program. 
	 * @param args command line arguments. (unused)
	 */
	public static void main(String ...args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Password_GUI();
			}
		});
	}
}
