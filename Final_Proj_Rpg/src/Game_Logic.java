//The messiest class of them all 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game_Logic {
	Scanner sc = new Scanner(System.in);

	public Game_Logic() {
		Game_Objects.room.add(new Room(0));
		List<String> roomInfo = new ArrayList<>();

		try {
			roomInfo = readLines("GameTextFiles/RoomDescriptions.fpg");
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		for (int i = 0; i < roomInfo.size(); i++) { // creates the "rooms"
			String[] firstWord = roomInfo.get(i).split(" ");
			String[] everythingElse = roomInfo.get(i).split(":");

			if (firstWord[0].equals("RoomName:")) {
				int currentRoomSize = Game_Objects.room.size();
				Game_Objects.room.add(new Room(currentRoomSize));
				Game_Objects.room.get(
						Game_Objects.room.size() - 1).name = everythingElse[1];
				Game_Objects.room.get(Game_Objects.room.size()
						- 1).number = (currentRoomSize);

				int roomcount = 0;
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) {
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if (nextFirstWord[0].equals("Desc:")) {
							String[] nextEverythingElse = roomInfo.get(z)
									.split(":");
							Game_Objects.room
									.get(Game_Objects.room.size() - 1).desc
											.add(nextEverythingElse[1]);
						}
					}
				}
				roomcount = 0;
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) {
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if (nextFirstWord[0].equals("Exit:")) {
							String[] nextEverythingElse = roomInfo.get(z)
									.split(":");
							Game_Objects.room
									.get(Game_Objects.room.size() - 1).exits
											.add(nextEverythingElse[1]);
						}
					}
				}
			}
		}
	}

	public List<String> readLines(String filename) throws IOException { // reads
																		// the
																		// things
		FileReader fReader = new FileReader(filename);
		BufferedReader bReader = new BufferedReader(fReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bReader.readLine()) != null) {
			lines.add(line);
		}
		bReader.close();
		return lines;
	}

	public void waitforCommand() { // this is the entry game loop. Or the
									// initial menu as it were.
		if (Game_Objects.pc.inRoom == 0) {
			System.out.println(
					"Welcome to the game traveler. Many have come to search for glory. I am the Game Master and I shall help you along your new journey.\nI have a selection of choices for you: ");
			System.out.println(
					"You may choose the tutorial which will guide you in navigating the game. \nYou may register your hero's name for the first time. \nOr, if you fancy yourself a veteran hero, you may recall your previous adventure.");
			String menuChoice;
			do {
				System.out.println(
						"What will you do? \nType (t)utorial for the guide, \n(n)ew to register for the first time, \nor (l)ast to recall your previous adventure.");
				menuChoice = sc.nextLine();
				switch (menuChoice.toLowerCase()) {
					case "t" :
						runTutorial();
						break;
					case "n" :
						createCharacter();
						break;
					case "l" :
						Game_Objects.gsdi.loadPC(Game_Objects.pc, sc);
						break;
					default :
						System.out.println(
								"Now, was that one of the options I presented to you? ");
				}
			} while (menuChoice.equalsIgnoreCase("t"));

		}

		System.out.println(
				"What would you like to do " + Game_Objects.pc.name + "?");
		String com = sc.nextLine();
		// parse the command by spaces
		String[] words = com.split(" ");
		processCommand(words);

	}

	public void processCommand(String[] x) { // this processes the commands
												// entered
		String a = x[0];
		switch (a.toLowerCase()) {
			case "kill" :
				Game_Objects.combat.attack(x);
				break;
			case "inspect" :
				look(x);
				break;
			case "create" :
				create_item(x);
				break;
			case "get" :
				get(x);
				break;
			case "wear" :
				Game_Objects.pc.wear(x);
				break;
			case "heal" :
				Game_Objects.pc.pcHeal();
				break;
			case "equipped" :
				Game_Objects.pc.equipped();
				break;
			case "remove" :
				Game_Objects.pc.remove(x);
				break;
			case "save" :
				Game_Objects.gsdi.savePC(Game_Objects.pc);
				break;
			case "quit" :
				exit();
				break;
			case "go" :
				move(x);
				break;
			default :
				System.out.println("I did not understand what you entered. ");
		}
	}

	public void create_item(String[] x) { // this manually creates item objects.
											// Primarily for test purposes.
											// Intentionally left out of the
											// player tutorial.
		if (x.length == 1) {
			System.out.println("Create what exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
				Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
				if (localItem.id.equalsIgnoreCase(x[1])) {
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						if (Game_Objects.room
								.get(y).number == Game_Objects.pc.inRoom) {
							try {
								Game_Objects.room.get(y).item
										.add((Item) Class.forName(localItem.id)
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

							System.out.println("You create a "
									+ Game_Objects.room.get(y).item.get(
											Game_Objects.room.get(y).item.size()
													- 1).name);
						}
					}
				}
			}
		}
	}

	public void exit() { // quits the game
		System.exit(0);
	}

	public void get(String[] x) { // this is for picking up item objects found
									// in the game world should you create/come
									// across one
		if (x.length == 1) {
			System.out.println("Get what exactly?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
				for (int y = 0; y < Game_Objects.room.size(); y++) {
					if (Game_Objects.room
							.get(y).number == Game_Objects.pc.inRoom) {
						for (int z = 0; z < Game_Objects.room.get(y).item
								.size(); z++) {
							if (x[1].equalsIgnoreCase(
									Game_Objects.room.get(y).item.get(z).id)) {
								Item localItem = Game_Objects.room.get(y).item
										.get(z);
								Game_Objects.pc.item.add(localItem);
								System.out.println(
										"You picked up the " + localItem.name);
								Game_Objects.room.get(y).item.remove(z);
								break;
							}
						}
					}
				}
			}
		}
	}

	public void look(String[] x) { // this is the inspection method so you can
									// inspect your surroundings, yourself, or
									// even your inventory
		if (x.length == 1) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					System.out.println(Game_Objects.room.get(i).name);
					for (int y = 0; y < Game_Objects.room.get(i).desc
							.size(); y++) {
						System.out
								.println(Game_Objects.room.get(i).desc.get(y));

					}
					System.out.println("Exits:");
					for (int y = 0; y < Game_Objects.room.get(i).exits
							.size(); y++) {

						String exitNameFull = Game_Objects.room.get(i).exits
								.get(y);
						String[] exitName = exitNameFull.split(" ");
						System.out.println(exitName[1]);

					}

					for (int y = 0; y < Game_Objects.room.get(i).npc
							.size(); y++) {
						System.out.println(
								Game_Objects.room.get(i).npc.get(y).desc);
					}

					for (int y = 0; y < Game_Objects.room.get(i).item
							.size(); y++) {
						System.out.println(
								Game_Objects.room.get(i).item.get(y).desc);
					}
				}
			}
		}
		if (x.length == 2) {
			if (x[1].equals("self")) {
				Game_Objects.pc.look();
			}

			else if (x[1].equalsIgnoreCase("i")) {
				Game_Objects.pc.checkInv();
			} else {
				for (int y = 0; y < Game_Objects.room.size(); y++) {
					if (Game_Objects.room
							.get(y).number == Game_Objects.pc.inRoom) {
						for (int i = 0; i < Game_Objects.room.get(y).npc
								.size(); i++) {
							if (x[1].equalsIgnoreCase(
									Game_Objects.room.get(y).npc.get(i).id)) {
								Game_Objects.room.get(y).npc.get(i).look();
							} else if (x[1].equalsIgnoreCase(
									Game_Objects.room.get(y).item.get(i).id)) {
								Game_Objects.room.get(y).item.get(i).look();
							}
						}
					}
				}
			}
		}
	}

	//For summoning NPC objects. For testing
	// purposes, intentionally left out of the player tutorial. Also obsolete
	// after Monster_Thread was added
	// public void summon(String[] x) { 
	// if (x.length == 1) {
	// System.out.println("Summon what exactly?");
	// }
	// if (x.length == 2) {
	// for (int i = 0; i < Game_Objects.NPCDatabase.size(); i++) {
	// NPC localNPC = (NPC) Game_Objects.NPCDatabase.get(i);
	// if (localNPC.id.equalsIgnoreCase(x[1])) {
	// for (int y = 0; y < Game_Objects.room.size(); y++) {
	// if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
	// try {
	// Game_Objects.room.get(y).npc
	// .add((NPC)
	// Class.forName(localNPC.id).getDeclaredConstructor().newInstance());
	// } catch (InstantiationException | IllegalAccessException |
	// ClassNotFoundException
	// | IllegalArgumentException | InvocationTargetException |
	// NoSuchMethodException
	// | SecurityException e) {
	// System.out.println(e.toString());
	// }
	// System.out.println("You summon a "
	// + Game_Objects.room.get(y).npc.get(Game_Objects.room.get(y).npc.size() -
	// 1).name);
	// }
	// }
	// }
	// }
	// }
	// }

	public void move(String[] x) { // this is used for moving your player around
									// the game world
		if (x.length == 1) {
			System.out.println("Move where? ");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					for (int y = 0; y < Game_Objects.room.get(i).exits
							.size(); y++) {
						String exitRequested = Game_Objects.room.get(i).exits
								.get(y);
						String[] exitArray = exitRequested.split(" ");
						if (x[1].equalsIgnoreCase(exitArray[1])) {
							Game_Objects.pc.inRoom = Integer
									.parseInt(exitArray[2]);
							System.out.println("You travel " + exitArray[1]);
							String[] badProgramming = new String[1];
							badProgramming[0] = "nowhere";
							look(badProgramming);
						}
					}
				}
			}
		}
	}

	public void createCharacter() { // The important bit. Character creator! It
									// sets base stats, adds and equips a couple
									// of items needed for initial combat,
									// states the room you spawn in, as well as
									// lists the exits of said room.
		System.out.println("Enter your hero's name?");
		Game_Objects.pc.name = sc.nextLine();
		System.out.println(
				"You begin your adventure with 100hp, 75 accuracy, 25 damage, and 50 defense to start. The gods also bestow upon you a Pheonix Medallion that is always around your neck.");
		Game_Objects.pc.hp = 100;
		Game_Objects.pc.maxHP = 100;
		Game_Objects.pc.accuracy = 75;
		Game_Objects.pc.dmg = 25;
		Game_Objects.pc.defense = 50;
		Game_Objects.pc.gold = 0;
		Game_Objects.pc.xp = 0;
		Game_Objects.pc.currentXP = 0;
		Game_Objects.pc.maxExp = 1000;
		Game_Objects.pc.xpToLvl = Game_Objects.pc.maxExp
				- Game_Objects.pc.currentXP;
		Game_Objects.pc.Lvl = 1;
		Game_Objects.pc.inRoom = 2;
		Game_Objects.pc.wornItems.add(new Wooden_Sword());
		Game_Objects.pc.wornItems.add(new Wooden_Shield());
		System.out.println("You need " + Game_Objects.pc.xpToLvl
				+ " experience until the next level.");
		for (int i = 0; i < Game_Objects.room.size(); i++) {
			if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
				System.out.println(
						"You awaken in " + Game_Objects.room.get(i).name);
				for (int y = 0; y < Game_Objects.room.get(i).desc.size(); y++) {
					System.out.println(Game_Objects.room.get(i).desc.get(y));

				}
				System.out.println("Exits:");
				for (int y = 0; y < Game_Objects.room.get(i).exits
						.size(); y++) {

					String exitNameFull = Game_Objects.room.get(i).exits.get(y);
					String[] exitName = exitNameFull.split(" ");
					System.out.println(exitName[1]);

				}
			}
		}

	}

	public void runTutorial() { // This runs the player tutorial for first time
								// players
		List<String> tutLines = new ArrayList<String>();
		try {
			tutLines = readLines("GameTextFiles/Tutorial.fpg");
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		for (int i = 0; i < tutLines.size(); i++) {
			String words = tutLines.get(i);
			System.out.println(words);
		}
		System.out.println();

	}

}
