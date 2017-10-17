package tosMonsters;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println ("This is ToS Monster demo project");
		
		Monsters FrostGnome = new Monsters (Monsters.GNOME, 200, "Frost Gnome");
		Monsters AliceTheLittleWitch = new Monsters (Monsters.LITTLE_WITCH, 150, "Alice the Little Witch");
		
		MonstersPack mp = new MonstersPack (2);
		mp.addMonster(FrostGnome, 0);
		mp.addMonster(AliceTheLittleWitch, 1);
		
		Monsters monsters[] = mp.getMosters();
		System.out.println ("Monsters in pack is");
		for (int i = 0; i < monsters.length; ++i)
			System.out.println ( i+1 + ": " + monsters[i].toString() );
		
		System.out.println ("When it is the same CD, attack point is " + mp.countDamage());
		
	}

}
