package tosMonsters;

class Monsters {
	public static final int GNOME = 0;
	public static final int ELF = 1;
	public static final int SALAMANDER = 2;
	public static final int LITTLE_WITCH = 3;
	public static final int SLIME = 4;
	public static final int WOLF = 5;
	
	public int type;
	public String name;
	public int health;
	
	public Monsters (int type , int health, String name) 
	{
		this.type = type;
		this.health = health;
		this.name = name;
	}
	
	public String toString ()
	{
		return "Name: " + name + ", Type: " + typeToName (type) +  ", Heath: " + health;
	}
	
	private String typeToName (int type)
	{
		switch (type)
		{
		  case GNOME:
			  return "Grome";
		  case ELF:
			  return "Elf";
		  case SALAMANDER:
			  return "Salamader";
		  case LITTLE_WITCH:
			  return "Little Witch";
		  case SLIME:
			  return "Slime";
		  case WOLF:
			  return "Wolf";
		  default:
			  return null;
		}
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
				damage += 4;
			}
			else if (monsters[i].type == Monsters.ELF)
			{
				damage += 3;
			}
			else if (monsters[i].type == Monsters.SALAMANDER)
			{
				damage += 6;
			}
			else if (monsters[i].type == Monsters.LITTLE_WITCH)
			{
				damage += 1;
			}
			else if (monsters[i].type == Monsters.SLIME)
			{
				damage += 2;
			}
			else if (monsters[i].type == Monsters.WOLF)
			{
				damage += 5;
			}
		}
		
		return damage;
	}
}