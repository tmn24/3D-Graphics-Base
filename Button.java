import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
public class Button{
   int x, y, width, height;
   String text;
   Color color = new Color(0,200,200);
   Button(int x, int y, int width, int height, String text){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.text = text;
   }
   void display(Graphics g){
      display((Graphics2D) g);
   }
   void clicked(){
      
   }
   void display(Graphics2D g2){
      g2.setColor(color);
      g2.fillRect(x,y,width,height);
      Font font = new Font(Font.SANS_SERIF,0,(int)(15.0*height/width));
      g2.setFont(font);
      g2.setColor(new Color(0,0,0));
      g2.drawString(text,x+width/4,y+height/2+8);
   }
}