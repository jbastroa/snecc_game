//Simple Snake Game
//Jarod Basti R. Roa
//Apr 14, 2020

int screen = 0;

Snek snecc = new Snek(); 
int size = 20;

void setup()
{
  size(500,500); 
  frameRate(10);
  background(0); 
  snecc.foodLocation();
}

void draw()
{  
  background(0);
  //divider();
  
  if(screen == 0)
  {
    titleScreen();
  }
  else if(screen == 1)
  {
    gameScreen();
  }
  else if(screen == 2)
  {
    gameOverScreen();
  }
  
}

void titleScreen()
{
  background(0);
  textAlign(CENTER);
  text("Snecc Game", height/2, (width/2)-50);
  text("Press Enter to Start", height/2, width/2);
  
  if(key == ENTER)
  {
    screen = 1;
  }
}

void gameScreen()
{
   if(snecc.eat())
  {
   snecc.foodLocation(); 
  }
  
  if(snecc.death())
  {
    gameOverScreen();
  }
  
  snecc.update();
  snecc.show(size);
  
  fill(255, 0, 100);
  snecc.currentFoodLoc(); 
}

void gameOverScreen()
{
  screen = 2;
    
    background(0);
    textAlign(CENTER);
    text("YOU DIED", height/2, width/2);
    text("Press Z to home screen", (height/2), (width/2)+250);
    
    if(key == 'z')
    {
      screen = 0;
    }
    
  
  
}


void divider()
{
 stroke(50);
  strokeWeight(2);
  for(int x = 0; x < 500; x += 20)
  {
    line(0,x,500,x);
  }

  for(int y = 0; y < 500; y += 20)
  {
    line(y,0,y,500);
  }
  
}


void keyPressed()
{
  if(keyCode == UP)
  {
    snecc.movement(0, -1);
  }
  else if(keyCode == DOWN)
  {
    snecc.movement(0, 1);
  }
  else if(keyCode == LEFT)
  {
    snecc.movement(-1, 0);
  }
  else if(keyCode == RIGHT)
  {
    snecc.movement(1, -0);
  }
  
  
  
  
}
