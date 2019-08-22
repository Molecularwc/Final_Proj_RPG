import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Monster_Thread {
	Game_Logic currentGL;

	public Monster_Thread(Game_Logic gl) {
		currentGL = gl;
	}
	// This runs a separate thread to populate monster spawns
	public void startMonsterThread() {
		Thread one = new Thread() {
			public void run() {
				try {
					while (true) {
						populateGame();
						if (Game_Objects.combat.monsterDead) {
							Thread.sleep(30000);
						} else {
							Thread.sleep(10000);
						}
					}
				} catch (InterruptedException v) {
					System.out.println(v);
				}
			}
		};

		one.start();
	}

	public void populateGame() { // this actually populates the game rooms
		int roomMobCount = 0;

		List<String> lines = new ArrayList<String>();
		try {
			lines = currentGL.readLines("GameTextFiles/MonsterLocs.fpg");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		for (int i = 0; i < lines.size(); i++) {
			String[] words = lines.get(i).split(" ");

			if (words[0].equals("Name:")) {
				for (int y = 0; y < Game_Objects.room.size(); y++) {
					if (Game_Objects.room.get(y).number == Integer
							.parseInt(words[2])) {
						for (int z = 0; z < Game_Objects.room.get(y).npc
								.size(); z++) {
							if (Game_Objects.room.get(y).npc.get(z).id
									.equalsIgnoreCase(words[1])) {
								roomMobCount++;
							}
						}
					}
				}
				if (roomMobCount == 0) {
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						if (Game_Objects.room.get(y).number == Integer
								.parseInt(words[2])) {
							try {
								Game_Objects.room.get(y).npc
										.add((NPC) Class.forName(words[1])
												.getDeclaredConstructor()
												.newInstance());
							} catch (InstantiationException
									| IllegalAccessException
									| ClassNotFoundException
									| IllegalArgumentException
									| InvocationTargetException
									| NoSuchMethodException
									| SecurityException e) {
								System.out.println(e.toString());
							}
						}
					}
				}

				roomMobCount = 0;
			}
		}
	}
}
