package myjava.homework.Q1.part1;

class Monsters{

	private String name;
	private int health;
	
	public Monsters(int health,String name) {
		this.health = health;
		this.name = name;
	};
	
	public String toString (){
		return "Name: " + name + ", Type: " + this.getClass().getSimpleName() + ", Heath: " + health;
	};
	
}

class MonstersPack{
	
	private Monsters[] monsters;
	
	public MonstersPack (int monsterNum)
	{
		monsters = new Monsters[monsterNum];
	}
	
	public void addMonster (Monsters newMonster, int index)
	{
		monsters[index] = newMonster;
	}
	
	public Monsters[] getMosters ()
	{
		return monsters;
	}
	
	public int countDamage ()
	{
		int damage = 0;
		
		for (int i = 0; i < monsters.length; ++i)
		{
			if (monsters[i].getClass().getSimpleName().equals("Grome"))
			{
				damage += Grome.getDamage();
			}
			else if (monsters[i].getClass().getSimpleName().equals("Elf"))
			{
				damage += Elf.getDamage();
			}
			else if (monsters[i].getClass().getSimpleName().equals("Salamander"))
			{
				damage += Salamander.getDamage();
			}
		    else if (monsters[i].getClass().getSimpleName().equals("LittleWitch"))
			{
				damage += LittleWitch.getDamage();
			}
			else if (monsters[i].getClass().getSimpleName().equals("Slime"))
			{
				damage += Slime.getDamage();
			}
			else if (monsters[i].getClass().getSimpleName().equals("Wolf"))
			{
				damage += Wolf.getDamage();
			}
		}
		return damage;
		}
}


class Grome extends Monsters{	
	Grome(int health,String name) {
		super(health,name);
	}
	public static int getDamage(){
		return 4;		
	}
}

class Elf extends Monsters{
	Elf(int health,String name) {
	super(health,name);
	}
	public static int getDamage(){
	return 3;		
	}
}

class Salamander extends Monsters{
	Salamander(int health,String name) {
	super(health,name);
	}
	public static int getDamage(){
	return 6;		
	}
}

class LittleWitch extends Monsters{
	public LittleWitch(int health,String name) {
	super(health,name);
	}
	public static int getDamage(){
	return 1;		
	}
}
 
class Slime extends Monsters{
	Slime(int health,String name) {
	super(health,name);	
	}
	public static int getDamage(){
	return 2;		
	}
}
 
class Wolf extends Monsters{
	Wolf(int health,String name) {
	super(health,name);
	}
	public static int getDamage(){
	return 5;		
	}
}

