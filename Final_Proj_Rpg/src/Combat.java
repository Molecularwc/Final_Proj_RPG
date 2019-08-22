//COMBAT YAY!
public class Combat {
	boolean monsterDead = false; // this is used in the Monster Thread to delay
									// creature spawns for "x" amount of time
									// after npc death

	public void attack(String[] x) { // the attack method. Handles PC and NPC
										// attack damages/misses
		for (int i = 0; i < Game_Objects.room.size(); i++) {
			if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
				for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {
					if (Game_Objects.room.get(i).npc.get(y).id
							.equalsIgnoreCase(x[1])) {
						int npc_hit = Game_Objects.rng.returnRandom(100);
						npc_hit = npc_hit
								+ (Game_Objects.room.get(i).npc.get(y).accuracy
										/ 2);
						if (npc_hit > 50) {
							int npc_damage = (Game_Objects.rng.returnRandom(100)
									* (Game_Objects.room.get(i).npc.get(y).dmg
											/ 5))
									- Game_Objects.pc.defense;
							if (npc_damage > 0) {
								Game_Objects.pc.hp = Game_Objects.pc.hp
										- npc_damage;
								System.out.println("The "
										+ Game_Objects.room.get(i).npc
												.get(y).name
										+ " hit you for " + npc_damage
										+ " damage.");
							} else {
								System.out.println("The "
										+ Game_Objects.room.get(i).npc
												.get(y).name
										+ " hit you for no damage.");
							}
							if (Game_Objects.pc.hp <= 0) {
								pc_death();
							}

						} else {
							System.out.println("The "
									+ Game_Objects.room.get(i).npc.get(y).name
									+ " missed.");
						}

						int pc_hit = Game_Objects.rng.returnRandom(100);
						pc_hit = pc_hit + (Game_Objects.pc.accuracy / 2);
						if (pc_hit > 50) {
							int pc_damage = (Game_Objects.rng.returnRandom(100)
									* (Game_Objects.pc.dmg / 5))
									- Game_Objects.room.get(i).npc
											.get(y).defense;
							if (pc_damage > 0) {
								Game_Objects.room.get(i).npc.get(
										y).hp = Game_Objects.room.get(i).npc
												.get(y).hp - pc_damage;
								System.out.println("You hit for " + pc_damage);
							} else {
								System.out.println("You hit for no damage.");
							}
							if (Game_Objects.room.get(i).npc.get(y).hp <= 0) {
								npc_death(i, y);
							}
						} else {
							System.out.println("You missed!");
						}
					}
				}
			}
		}
	}

	public void npc_death(int i, int y) { // For when you triumph!
		System.out.println(
				Game_Objects.room.get(i).npc.get(y).name + " has died.");
		monsterDead = true;
		int dropped_Gold = Game_Objects.room.get(i).npc.get(y).droppedGold;
		dropped_Gold = dropped_Gold + Game_Objects.pc.updateGold(dropped_Gold);
		int xp_Gain = Game_Objects.room.get(i).npc.get(y).droppedXP;
		xp_Gain = xp_Gain + Game_Objects.pc.updateXP(xp_Gain);
		Game_Objects.pc.gold = Game_Objects.pc.gold + dropped_Gold;
		Game_Objects.pc.currentXP = Game_Objects.pc.currentXP + xp_Gain;
		Game_Objects.pc.xp = Game_Objects.pc.xp + Game_Objects.pc.currentXP;
		System.out
				.println("You loot " + dropped_Gold + " gold from the corpse.");
		System.out
				.println("You earn " + xp_Gain + " experience from the fight.");
		if (Game_Objects.pc.currentXP >= Game_Objects.pc.maxExp) {
			while (Game_Objects.pc.currentXP >= Game_Objects.pc.maxExp) {
				Game_Objects.pc.updateLvl();
			}
		}
		Game_Objects.pc.xpToLvl = Game_Objects.pc.maxExp
				- Game_Objects.pc.currentXP;
		System.out.println("You need " + Game_Objects.pc.xpToLvl
				+ " until your knowledge increases.");
		Game_Objects.room.get(i).npc.remove(y);
	}

	public boolean checkNPCDead() { // used by Monster_Thread
		return monsterDead;
	}

	public void pc_death() { // for when you're dumb
		Game_Objects.pc.pcResurrect();
	}
}
