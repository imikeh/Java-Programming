package myjava.homework.Q1.part1;

public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is ToS Monsters Ver.2");

		Grome FrostGnome = new Grome(200, "Frost Gnome");
		LittleWitch Alice = new LittleWitch(150, "Alice the Little Witch");

		MonstersPack mp = new MonstersPack(2);
		mp.addMonster(FrostGnome, 0);
		mp.addMonster(Alice, 1);

		Monsters monsters[] = mp.getMosters();
		System.out.println("Monsters in pack is");
		for (int i = 0; i < monsters.length; ++i)
			System.out.println(i + 1 + ": " + monsters[i].toString());

		System.out.println("When it is the same CD, attack point is "
				+ mp.countDamage());
	}

}
