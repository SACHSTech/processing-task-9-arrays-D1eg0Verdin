import processing.core.PApplet;

public class Sketch extends PApplet {
 
  float[] circleX = new float[35];
  float[] circleY = new float[35];
  boolean[] ballHideStatus = new boolean[35];
  int intSnowballSpeed = 2;

  float fltPlayerX = 400;
  float fltPlayerY = 800;

  boolean boolUpPressed = false;
  boolean boolDownPressed = false;
  boolean boolLeftPressed = false;
  boolean boolRightPressed = false;

  boolean boolPlayerStatus = true;
  int intLives = 3;

  boolean boolMouseClicked = false;
 

  public void settings() {
    size(800,800);
  }

 
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  
  }
  public void draw() {
  // check to see if player has not died
  if (boolPlayerStatus == true) {
    background(50);
  
    fill(255);

    // draw snowballs to the screen if ballHideStatus is false
    for (int i = 0; i < circleY.length; i++) {
      if (ballHideStatus[i] == false) {
        ellipse(circleX[i], circleY[i], 50, 50);

        circleY[i] += intSnowballSpeed;
      }
  
     
      if (circleY[i] > height - 25) {
        circleY[i] = 0;
      }

      // if player circle collides with snowball, stop drawing snowball to screen and remove 1 life
      if (dist(fltPlayerX, fltPlayerY, circleX[i], circleY[i]) <= 37.5 && ballHideStatus[i] == false) {
        ballHideStatus[i] = true;
        intLives--;
      }
      
      // if snowball is clicked, delete it
      if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 25 && boolMouseClicked) {
          ballHideStatus[i] = true;
      }
      
    }

    fill(0, 0, 255);

    // draw player circle
    ellipse(fltPlayerX, fltPlayerY, 25, 25);
    
    // keyboard controls for player using w, a, s, d
    if (boolLeftPressed) {
      fltPlayerX += -5;
    }
    if (boolRightPressed) {
      fltPlayerX += 5;
    }
    if (boolUpPressed) {
      fltPlayerY += -5;
    }
    if (boolDownPressed) {
      fltPlayerY += 5;
    }

    fill(246, 7, 17);

    
    for (int i = 1; i <= intLives; i++) {
      rect(50 * i, 50, 50, 50);
    }

    if (intLives == 0) {
      boolPlayerStatus = false;
    }
  }

  // screen turns white when player dies
  else {
    background(255);
  }
  
  }
  public void keyPressed() {
    if (key == 'a')  {
      boolLeftPressed = true;
    }
    else if (key == 'd') {
      boolRightPressed = true;
    }
    else if (key == 'w') {
      boolUpPressed = true;
    }
    else if (key == 's') {
      boolDownPressed = true;
    }

    if (keyCode == UP) {
      intSnowballSpeed = 1;
    }
    if (keyCode == DOWN) {
      intSnowballSpeed = 5;
    }
  }

  
  public void keyReleased() {
    if (key == 'a')  {
      boolLeftPressed = false;
    }
    else if (key == 'd') {
      boolRightPressed = false;
    }
    else if (key == 'w') {
      boolUpPressed = false;
    }
    else if (key == 's') {
      boolDownPressed = false;
    }

    if (keyCode == UP) {
      intSnowballSpeed = 2;
    }
    
    if (keyCode == DOWN) {
      intSnowballSpeed = 2;
    }
  }


  public void mousePressed() {
    boolMouseClicked = true;
  }

  public void mouseReleased() {
    boolMouseClicked = false;
  }
  
  
}