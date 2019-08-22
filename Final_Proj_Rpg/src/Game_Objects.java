//A listing of all the game objects called in the game
import java.util.ArrayList;
import java.util.List;

public class Game_Objects {
	static PC pc = new PC();
	static List<Room> room = new ArrayList<Room>();
	static List<NPC> npc = new ArrayList<NPC>();
	static List<Item> item = new ArrayList<Item>();
	static Combat combat = new Combat();
	static RNG rng = new RNG();
	static Equipment_Handler eh = new Equipment_Handler();
	static GameStateDataInfo gsdi = new GameStateDataInfo(Final_Proj_Rpg.gl);
	static List<Object> NPCDatabase = new ArrayList<Object>();
	static List<Object> ItemDataBase = new ArrayList<Object>();

	public static void initializeNPCArray() { // handles the NPCs
		NPCDatabase.add(new NPC());
		NPCDatabase.add(new Troll());
		NPCDatabase.add(new Dragon());
		NPCDatabase.add(new Goblin());
		NPCDatabase.add(new Skeleton());
		NPCDatabase.add(new Archer());
		NPCDatabase.add(new Khajil());

	}

	public static void initializeItemArray() { // handles all the items
		ItemDataBase.add(new Item());
		ItemDataBase.add(new Flaming_Sword());
		ItemDataBase.add(new Obsidian_Ring());
		ItemDataBase.add(new Wooden_Sword());
		ItemDataBase.add(new Wooden_Shield());
		ItemDataBase.add(new Helmet());
		ItemDataBase.add(new Cuirass());
		ItemDataBase.add(new Gauntlets());
		ItemDataBase.add(new Leg_Armor());
		ItemDataBase.add(new Greaves());

	}

}
