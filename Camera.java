import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.Duration;
public class Camera extends Thread implements KeyListener, MouseListener{
   Point pos = new Point(-1000,0,0);
   Instant start = Instant.now();
   Instant end = Instant.now();
   Point lightRay = new Point(0,Math.sqrt(2)/2,-Math.sqrt(2)/2);
   double speed = 3;// CHANGE CAMERA MOVEMENT SPEED
   double a1Range = 40*Math.PI/180, a2Range = 50*Math.PI/180;
   double a1 = 0,a2 = 0;
   double time = 0;
   Point normal = new Point(Math.cos(a2)*Math.cos(a1),Math.sin(a2)*Math.cos(a1),Math.sin(a1));
   Point planeX = pos.crossProduct(new Point(Math.cos(a2)*Math.cos(a1-Math.PI/2),Math.sin(a2)*Math.cos(a1-Math.PI/2),Math.sin(a2-Math.PI/2)),normal);
   Point planeY = planeX.crossProduct(planeX, normal);
   int mouseX = 0, mouseY = 0;
   int deleteID = -1;
   boolean w = false, a = false, s = false, d = false, up = false , down = false, left = false , right = false, space = false, shift = false, mouse1 = false, delete = false;
   Panel panel;
   Camera(){
      if(planeX.x == 0 && planeX.y == 0 && planeX.z == 0){
         //System.out.println("zzzz");
         if(normal.z > 0){
            planeX = new Point(1,0,0);
         } else {
            planeX = new Point(-1,0,0);
         }
         if(normal.z < 0){
            planeX = new Point(1,0,0);
         } else {
            planeX = new Point(1,0,0);
         }
         planeY = planeX.crossProduct(planeX, normal);
      }
      //try{
      //   Thread.sleep(1000);
      //} catch (Exception e) {}
      this.start();
   }
   public void add(Panel panel){
      this.panel = panel;
   }
   public void run(){
      try{
         while(true){
            try{
               end = Instant.now();
               long dt = Duration.between(start,end).toMillis();
               if(dt < 20){
                  Thread.sleep(20-dt);
               }
               time += 0.02;
               start = Instant.now();
            } catch(Exception e){}
            if(panel != null){
               if(!panel.painting){
                  if(up){
                     if(a1 < (Math.PI/2-0.2)){
                        a1 += 0.05;
                     }
                  } else if(left){
                     a2 += 0.05;
                  } else if(down){
                     if(a1 > -Math.PI/2+0.2){
                        a1 -= 0.05;
                     }
                  } else if(right){
                     a2 -= 0.05;
                  } else if(w){//camera.pos DOES NOT UPDATE MAGNITUDE
                     pos.x += speed*Math.cos(a2);
                     pos.y += speed*Math.sin(a2);
                  } else if(a){
                     pos.x -= speed*Math.sin(a2);
                     pos.y += speed*Math.cos(a2);
                  } else if(s){
                     pos.x -= speed*Math.cos(a2);
                     pos.y -= speed*Math.sin(a2);
                  } else if(d){
                     pos.x += speed*Math.sin(a2);
                     pos.y -= speed*Math.cos(a2);
                  } else if(space){
                     pos.z += speed;
                  } else if(shift){
                     pos.z -= speed;
                  }
                  pos = new Point(pos.x,pos.y,pos.z);
               //System.out.println("camera");         //System.out.println(deleteID+","+delete);
                  if(delete && deleteID != -1 && deleteID != 0){
                     panel.deleteModel(deleteID);
                     deleteID = -1;
                  }
                  normal.z = Math.sin(a1);
                  normal.y = Math.sin(a2)*Math.cos(a1);
                  normal.x = Math.cos(a2)*Math.cos(a1);
                  //System.out.println("normal: "+normal);
                  //System.out.println("normal sub: "+(new Point(Math.cos(a2)*Math.cos(a1-Math.PI/2),Math.sin(a2)*Math.cos(a1-Math.PI/2),Math.sin(a1-Math.PI/2))));
                  planeX = planeX.crossProduct(new Point(Math.cos(a2)*Math.cos(a1-Math.PI/2),Math.sin(a2)*Math.cos(a1-Math.PI/2),Math.sin(a1-Math.PI/2)),normal);
                  planeY = planeX.crossProduct(planeX, normal);
                  planeX = planeX.scale(planeX,1/planeX.magnitude);
                  planeY = planeY.scale(planeY, 1/planeY.magnitude);
                  
                  //Put everthing AFTER THIS: meaning put anything you want to happen on the screen in this loop
                  
                  
                  
                  
                  
                  
                  //Put Everything BEFORE THIS REPAINT
                  panel.repaint();
               }
            }
         }
      }catch (Exception e){
         e.printStackTrace();
      }
   }
   public void keyTyped(KeyEvent e){
   
   }
   public void keyPressed(KeyEvent e){
      if(e.getKeyCode() == KeyEvent.VK_UP){
         up = true;
      } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
         left = true;
      } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
         down = true;
      } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
         right = true;
      } else if(e.getKeyCode() == KeyEvent.VK_W){
         w = true;
      } else if(e.getKeyCode() == KeyEvent.VK_A){
         a = true;
      } else if(e.getKeyCode() == KeyEvent.VK_S){
         s = true;
      } else if(e.getKeyCode() == KeyEvent.VK_D){
         d = true;
      } else if(e.getKeyCode() == KeyEvent.VK_SPACE){
         space = true;
      } else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
         shift = true;
      } else if(e.getKeyCode() == KeyEvent.VK_DELETE){
         delete = true;
      } 
      //System.out.println(1);
   }
   public void keyReleased(KeyEvent e){
      if(e.getKeyCode() == KeyEvent.VK_UP){
         up = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT){
         left = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_DOWN){
         down = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_RIGHT){
         right = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_W){
         w = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_A){
         a = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_S){
         s = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_D){
         d = false;
      } 
      if(e.getKeyCode() == KeyEvent.VK_SPACE){
         space = false;
      } 
      if(e.getKeyCode() == KeyEvent.VK_SHIFT){
         shift = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_DELETE){
         delete = false;
      }  
   }  
   public void mouseClicked(MouseEvent e){
      if(e.getButton() == MouseEvent.BUTTON1){
         mouseX = e.getX();
         mouseY = e.getY();
         deleteID = panel.mouse1Clicked(mouseX,mouseY);
      }
   }
   public void mouseReleased(MouseEvent e){
   
   }
   public void mousePressed(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
}