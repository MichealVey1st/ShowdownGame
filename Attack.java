class Attack {
    private String name;
    private int damage;
    public Attack(String name, int damage){
      this.name = name;
      this.damage = damage;
    }
    
    public int getDamage(){
      return this.damage;
    }
    
    public String getName(){
      return this.name;
    }
  }