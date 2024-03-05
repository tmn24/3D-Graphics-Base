import javax.swing.*;
import java.awt.*;
public class Runner{
   public static void main(String[] args){
      int cw = 1000, ch = 800;
      Camera camera = new Camera();
      Panel panel = new Panel(cw, ch, camera);
      panel.setFocusable(true);
      //JButton addModel = new JButton("Add Model");
      //addModel.setLocation(0,0);
      //addModel.setSize(100,100);
      camera.add(panel);
      JFrame frame = new JFrame("Woj's Slicer");
      frame.setFocusable(true);
      //panel.addKeyListener(camera);
      panel.addKeyListener(camera);
      panel.addMouseListener(camera);
      frame.addKeyListener(camera);
      frame.addMouseListener(camera);
      //panel.add(addModel);
      //frame.add(addModel);
      frame.setPreferredSize(new Dimension(cw,ch));
      frame.getContentPane().add(panel);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      /*while(true){
         try{
            Thread.sleep(6);
            panel.repaint();
         } catch(Exception e){}
      }*/
   }
}