ArrayList<Attack> attack = new ArrayList<Attack>();
ArrayList<Monster> monster = new ArrayList<Monster>();
class Shape {
  private color c = #000000;
  private int x, y, d, w, h = 0;
  public Shape(color c, int x, int y, int d) {
    this.c=c;
    this.x=x;
    this.y=y;
    // circle
    this.d=d;
  }

  public Shape(color c, int x, int y, int w, int h) {
    this.c=c;
    this.x=x;
    this.y=y;
    // rect
    this.w=w;
    this.h=h;
  }

  public void draw() {
    noStroke();
    fill(this.c);
    // if this.d exists, it's a circle
    if (this.d != 0) {
      circle(this.x, this.y, this.d);
    } else {
      rect(this.x, this.y, this.w, this.h);
    }
  }

  public boolean isColliding(int mouseX, int mouseY) {
    if (this.d != 0) {
      // collision for a circle
      // TODO
      return this.d/2 >= Math.sqrt(Math.pow(mouseX - this.x, 2) + Math.pow(mouseY - this.y, 2));
    } else {
      // collision for a rect
      // TODO
      return mouseX > this.x && mouseX < x+w && mouseY > this.y && mouseY < y+h;
    }
  }
}

Shape button1 = new Shape(#0095FF, 100, 300, 50);
Shape button2 = new Shape(#0095FF, 300, 300, 50);

void setup(){
  size(400, 400);
  background(255);
  attackCreate();
  monsterCreate();
}

void draw(){
  if(GLOBALS.win1 || GLOBALS.win2){
    endScreen();
  }else{
    fill(0);
    //Name
    textSize(40);
    text(monster.get(0).getName(), 40, 70);
    text(monster.get(1).getName(), 200, 70);
    textSize(30);
    //HP
    text("HP: " + monster.get(0).getHP(), 40, 100);
    text("HP: " + monster.get(1).getHP(), 200, 100);
    //Defense
    text("Defense: " + monster.get(0).getDefense(), 40, 125);
    text("Defense: " + monster.get(1).getDefense(), 200, 125);
    //Attack name
    textSize(20);
    text("Attack: " + monster.get(0).getAttackName(), 40, 150);
    text("Attack: " + monster.get(1).getAttackName(), 200, 150);
    
    if(monster.get(0).getHP() <= 0){
      GLOBALS.win2 = true;
    }
    
    if(monster.get(1).getHP() <= 0){
      GLOBALS.win1 = true;
    }
    
    // Shape drawer
    if(GLOBALS.turn){
      button2.draw();
      
      if (button2.isColliding(mouseX, mouseY)) {
      cursor(HAND);
      if (mousePressed) {
        attack(1, 0);
      }
    }
    }else{
      button1.draw();
      
      if (button1.isColliding(mouseX, mouseY)) {
      cursor(HAND);
      if (mousePressed) {
        attack(0, 1);
      }
    }
    }
  }
}

void attackCreate(){
  attack.add(new Attack("Screech", 7));
  attack.add(new Attack("Punch", 4));
  attack.add(new Attack("Spit", 10));
  attack.add(new Attack("Name Calling", 20));
}

void monsterCreate(){
  monster.add(new Monster("Player1", 100, 100, 5, attack.get((int)random(0,3))));
  monster.add(new Monster("Player2", 110, 110, 3, attack.get((int) random(0,3))));
}

void attack(int monstered, int monsterer){
   clear();
   background(255);
   monster.get(monstered).bash(monster.get(monsterer)); 
   if(GLOBALS.turn){
     GLOBALS.turn = false;
   }else{
     GLOBALS.turn = true;
   }
}

void endScreen(){
  clear();
  background(255);
  if(GLOBALS.win1){
    text("Player1 wins", 200, 200);
  }else{
    text("Player2 wins", 200, 200);
  }
}