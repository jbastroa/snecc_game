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
  
  void show(int size)
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
  
  boolean death()
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
  
  void update()
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
  
  void movement(float x, float y)
  {
    x_Speed = x;
    y_Speed = y;
  }
  
  void foodLocation()
  {
    {  
      int cols = width/size;
      int rows = height/size;
      foodX = (floor(random(cols))*size);
      foodY = (floor(random(rows))*size);
      rect(foodX, foodY, size, size);
      
    }
  }
    
  boolean eat()
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
  
  void currentFoodLoc()
  {
      rect(foodX,foodY, size, size);
  }
  
}
