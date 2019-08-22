//This saves the game state
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameStateDataInfo {
	Game_Logic currentGL = new Game_Logic();

	public GameStateDataInfo(Game_Logic gl) {
		currentGL = gl;
	}

	public void savePC(PC pc) { // This saves all the character data
		String fileName = String.format("GameStateData/%s.fpg", pc.name);
		try (PrintWriter pWrite = new PrintWriter(fileName)) {
			pWrite.println("Name: " + Game_Objects.pc.name);
			pWrite.println("HP: " + Game_Objects.pc.hp);
			pWrite.println("MaxHP: " + Game_Objects.pc.maxHP);
			pWrite.println("Acc: " + Game_Objects.pc.accuracy);
			pWrite.println("Dmg: " + Game_Objects.pc.dmg);
			pWrite.println("Def: " + Game_Objects.pc.defense);
			pWrite.println("Room: " + Game_Objects.pc.inRoom);
			pWrite.println("Gold: " + Game_Objects.pc.gold);
			pWrite.println("Exp: " + Game_Objects.pc.xp);
			pWrite.println("CurrentXP: " + Game_Objects.pc.currentXP);
			pWrite.println("MaxXP: " + Game_Objects.pc.maxExp);
			pWrite.println("Level: " + Game_Objects.pc.Lvl);
			for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
				pWrite.println("Item: " + Game_Objects.pc.item.get(i).id);
			}
			for (int i = 0; i < Game_Objects.pc.wornItems.size(); i++) {
				pWrite.println(
						"WornItem: " + Game_Objects.pc.wornItems.get(i).id);
			}
			System.out.println("File saved successfully!");
			System.out.println(fileName);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public void loadPC(PC pc, Scanner s) { // loads the player character
											// information
		List<String> pcItems = new ArrayList<String>();
		System.out.println(
				"What is the name of the character you wish to load?: ");
		String cName = s.nextLine();
		String fileName = String.format("GameStateData/%s.fpg", cName);
		String lines = null;
		try (BufferedReader bRead = new BufferedReader(
				new FileReader(fileName))) {
			while ((lines = bRead.readLine()) != null) {
				pcItems.add(lines);
			}
		} catch (FileNotFoundException e) {
			System.out.println(
					"File does not exist. Make sure there is a save file before trying to load.");
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		for (int i = 0; i < pcItems.size(); i++) {
			String[] items = pcItems.get(i).split(" ");
			if (items[0].equals("Name:")) {
				Game_Objects.pc.name = items[1];
			}
			if (items[0].equals("HP:")) {
				Game_Objects.pc.hp = Integer.parseInt(items[1]);
			}
			if (items[0].equals("MaxHP:")) {
				Game_Objects.pc.maxHP = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Acc:")) {
				Game_Objects.pc.accuracy = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Dmg:")) {
				Game_Objects.pc.dmg = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Def:")) {
				Game_Objects.pc.defense = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Room:")) {
				Game_Objects.pc.inRoom = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Gold:")) {
				Game_Objects.pc.gold = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Exp:")) {
				Game_Objects.pc.xp = Integer.parseInt(items[1]);
			}
			if (items[0].equals("CurrentXP:")) {
				Game_Objects.pc.currentXP = Integer.parseInt(items[1]);
			}
			if (items[0].equals("MaxXP:")) {
				Game_Objects.pc.maxExp = Integer.parseInt(items[1]);
			}
			if (items[0].equals("Level:")) {
				Game_Objects.pc.Lvl = Integer.parseInt(items[1]);
			}
			Game_Objects.pc.xpToLvl = Game_Objects.pc.maxExp
					- Game_Objects.pc.currentXP; // correctly sets the
													// experience needed to
													// level after player data
													// import
			if (items[0].equals("Item:")) {
				for (int y = 0; y < Game_Objects.ItemDataBase.size(); y++) {
					Item localItem = (Item) Game_Objects.ItemDataBase.get(y);
					if (localItem.id.equalsIgnoreCase(items[1])) {
						try {
							Game_Objects.pc.item.add((Item) Class
									.forName(localItem.id)
									.getDeclaredConstructor().newInstance());
						} catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							System.out.println(e.toString());
						}
					}
				}
			}
			if (items[0].equals("WornItem:")) {
				for (int y = 0; y < Game_Objects.ItemDataBase.size(); y++) {
					Item localItem = (Item) Game_Objects.ItemDataBase.get(y);
					if (localItem.id.equalsIgnoreCase(items[1])) {
						try {
							Game_Objects.pc.wornItems.add((Item) Class
									.forName(localItem.id)
									.getDeclaredConstructor().newInstance());
							for (int z = 0; z < Game_Objects.pc.wornItems
									.size(); z++) {
								if (Game_Objects.pc.wornItems.get(z).id
										.equalsIgnoreCase(items[1])) {
									Game_Objects.pc.wornItems
											.get(z).isEquipped = true;
								}
							}
						} catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							System.out.println(e.toString());
						}
					}
				}
			}
		}
		System.out.printf("%s.fpg loaded successfully!%n", pc.name);
	}

}
