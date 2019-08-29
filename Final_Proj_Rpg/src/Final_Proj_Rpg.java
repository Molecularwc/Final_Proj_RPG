/** 
 *	@author: Richard Salvaty
 *	Subject: CIT 130
 *	Project: HW11.1 *
 */

/**	
 * Main game class
 */
public class Final_Proj_Rpg {
	static Game_Logic gl = new Game_Logic();

	public static void main(String[] args) {
		Game_Objects.initializeNPCArray();
		Game_Objects.initializeItemArray();
		Monster_Thread mt = new Monster_Thread(gl);
		mt.startMonsterThread();
		while (true) {
			game_loop();
		}

	}

	/**
	 * runs the game loop
	 */
	public static void game_loop() {
		gl.waitforCommand();
	}

}
