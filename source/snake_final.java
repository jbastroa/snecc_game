import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class snake_final extends PApplet {

//Simple Snake Game
//Jarod Basti R. Roa
//Apr 14, 2020

int screen = 0;

Snek snecc = new Snek(); 
int size = 20;

public void setup()
{
   
  frameRate(10);
  background(0); 
  snecc.foodLocation();
}

public void draw()
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

public void titleScreen()
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

public void gameScreen()
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

public void gameOverScreen()
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


public void divider()
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


public void keyPressed()
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
class Snek
{
  int x = 0;
  int y = 0;
  float x_Speed = 1;
  float y_Speed = 0;
  int total = 0;
  int foodX = 0;
  int foodY = 0;
  int[][] tail = new int[total][2];
  
  IntList tailX = new IntList();
  IntList tailY = new IntList();
  
  public void show(int size)
  {
    for(int i = 0; i < tailX.size(); i++)
    {
      fill(255);
      int tempX = tailX.get(i);
      int tempY = tailY.get(i);
      rect(tempX, tempY, size, size);
           
    }   
      
    fill(255);
    rect(x, y, size, size);
   
  }
  
  public boolean death()
  {
    boolean desu = false;
    
    for(int i = 0; i < tailX.size(); i++)
    {
      int tempX = tailX.get(i);
      int tempY = tailY.get(i);
    
      float distance = dist(x, y, tempX, tempY);
      if(distance < 1)
      {
        x = 0;
        y = 0;
        x_Speed = 1;
        
        total = 0;
        tailX.clear();
        tailY.clear();
        
        desu = true;
      }
      else 
      {
        desu = false;
      }
    
    }
    
    return desu;
  }
  
  public void update()
  {   
    if(total == tailX.size())
      {
        for(int i = tailX.size()-1; i > 0; i--)
        {
          //tail[i][0] = tail[i-1][0];
          //tail[i][1] = tail[i-1][1];
          
          tailX.set(i, tailX.get(i-1));
          tailY.set(i, tailY.get(i-1));
        }
        
      }
    
    if(total > 0)
    {

      //tail[0][0] = x;
      //tail[0][1] = y;
      
      tailX.set(0, x);
      tailY.set(0, y);
    }  
    
    
    x += x_Speed*size;
    y += y_Speed*size;
    
    x = constrain(x, 0, width-size);
    y = constrain(y, 0, height-size);
  }
  
  public void movement(float x, float y)
  {
    x_Speed = x;
    y_Speed = y;
  }
  
  public void foodLocation()
  {
    {  
      int cols = width/size;
      int rows = height/size;
      foodX = (floor(random(cols))*size);
      foodY = (floor(random(rows))*size);
      rect(foodX, foodY, size, size);
      
    }
  }
    
  public boolean eat()
  {
    float distance = dist(x, y, foodX, foodY);
      if(distance < 1)
      {
        total++;
        
        tailX.append(total);
        tailY.append(total);
        //tail = new int[total][2];
        return true;
      }
      else 
      {
        return false;
      }
    
  }
  
  public void currentFoodLoc()
  {
      rect(foodX,foodY, size, size);
  }
  
}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "snake_final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
