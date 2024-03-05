import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class STLReader{
   STLReader(){  
   }
   public static Model getModel(){
      FileEditor fe = new FileEditor();
      JFileChooser jfc = new JFileChooser();
      jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      jfc.showDialog(new JFrame(),"Choose STL File");
      String path = ""; 
      try{
         path = jfc.getSelectedFile().getAbsolutePath();
      } catch(Exception e){
         if(jfc.getSelectedFile() == null){
            return new Model();
         }
      }
      Model model = new Model();
      //model.triangle.add()
      Point[] vertex = new Point[3];
      int index = 0;
      Point normal = null;
      Triangle triangle = new Triangle();
      String line;
      Scanner sc;
      int index1 = -1;
      try{
         File file = new File(path); 
         sc = new Scanner(file); 
         while(sc.hasNext()){
            line = sc.nextLine();
            index1++;
            //if(index1 < 20){System.out.println(line);}
            if(line.indexOf("vertex") > -1){
               //System.out.println(line);
               line = line.substring(13);
               //System.out.println(line);
               double x = Double.parseDouble(line.substring(0,line.indexOf(" ")));
               line = line.substring(line.indexOf(" ")+1);
               double y = Double.parseDouble(line.substring(0,line.indexOf(" ")));
               line = line.substring(line.indexOf(" ")+1);
               double z = Double.parseDouble(line);
               vertex[index] = new Point(x,y,z);
               index++;
               if(index == 3){
                  triangle = new Triangle();
                  index = 0;
                  triangle.vertex[0] = vertex[0];
                  triangle.vertex[1] = vertex[1];
                  triangle.vertex[2] = vertex[2];
                  //System.out.println(triangle.vertex[0]);
                  //System.out.println(triangle.vertex[1]);
                  //System.out.println(triangle.vertex[2]);
                  triangle.normal = normal;
                  //System.out.println(triangle.normal);
                  model.triangle.add(triangle);
               }//idk why but the normal vector is also null????
            } else if(line.indexOf("normal") > -1){
               //System.out.println(line);
               line = line.substring(15);
               //System.out.println(line);
               double x = Double.parseDouble(line.substring(0,line.indexOf(" ")));
               //System.out.println(x);
               line = line.substring(line.indexOf(" ")+1);
               double y = Double.parseDouble(line.substring(0,line.indexOf(" ")));
               //System.out.println(y);
               line = line.substring(line.indexOf(" ")+1);
               double z = Double.parseDouble(line);
               //System.out.println(z);
               normal = new Point(x,y,z);
               //System.out.println(normal);
            }
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
      //System.out.println(model.triangle.get(0).vertex[0]);
      //System.out.println(model.triangle.get(1).vertex[0]);
      //System.out.println(model.triangle.get(2).vertex[0]);
      //System.out.println(model.triangle.get(3).vertex[0]);
      //System.out.println(model.triangle.get(4).vertex[0]);
      //System.out.println(10);
      //System.out.println(1);
      return model;
   }
}