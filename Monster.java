class Monster {
    private String name;
    private int MaxHP;
    private int HP;
    private int defense;
    private Attack attack;
    public Monster(String name, int MaxHP, int HP, int defense, Attack attack) {
      this.name = name;
      this.MaxHP = MaxHP;
      this.HP = HP;
      this.defense = defense;
      this.attack = attack;
    }
    
    public String getName(){
      return this.name;
    }
    
    public int getHP(){
      return this.HP;
    }
    
    public int getDefense(){
      return this.defense;
    }
    
    public String getAttackName(){
      return this.attack.getName();
    }
    
    public void lowerHP(int amount) {
      this.HP -= (amount - this.defense);
    }
    
    public void bash(Monster victim) {
      // calculate damage
      // lower hp of victim
      victim.lowerHP(this.attack.getDamage());
    }
}