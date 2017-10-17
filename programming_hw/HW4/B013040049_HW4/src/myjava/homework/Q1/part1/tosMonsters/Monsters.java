package myjava.homework.Q1.part1;

class Monsters {

	public String name;
	public int health;
	
	public Monsters (int type , int health, String name) {
		this.health = health;
		this.name = name;
	}
	
	public String toString (){
		return "Name: " + name + ", Type: " + typeToName (type) +  ", Heath: " + health;
	}
	
}

class MonstersPack
{
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
			if (monsters[i].type == Monsters.GNOME)
			{
				damage += Grome.getDamage;
			}
			else if (monsters[i].type == Monsters.ELF)
			{
				damage += ELF.getDamage;
			}
			else if (monsters[i].type == Monsters.SALAMANDER)
			{
				damage += SALAMANDER.getDamage;
			}
			else if (monsters[i].type == Monsters.LITTLE_WITCH)
			{
				damage += LITTLE_WITCH.getDamage;
			}
			else if (monsters[i].type == Monsters.SLIME)
			{
				damage += SLIME.getDamage;
			}
			else if (monsters[i].type == Monsters.WOLF)
			{
				damage += WOLF.getDamage;
			}
		}
		
		return damage;
	}
}

class Grome extends Monster{
	Grome(){
		super(health,name);
	}
	public static int getDamage(){
		return 4;
	}
}

class ELF extends Monster{
	ELF(){
		super(health,name);
	}
	public static int getDamage(){
		return 3;
	}
}

class SALAMANDER extends Monster{
	SALAMANDER(){
		super(health,name);
	}
	public static int getDamage(){
		return 6;
	}
}


class LITTLE_WITCH extends Monster{
	LITTLE_WITCH(){
		super(health,name);
	}
	public static int getDamage(){
		return 1;
	}
}

class SLIME extends Monster{
	SLIME(){
		super(health,name);
	}
	public static int getDamage(){
		return 2;
	}
}

class WOLF extends Monster{
	WOLF(){
		super(health,name);
	}
	public static int getDamage(){
		return 5;
	}
}