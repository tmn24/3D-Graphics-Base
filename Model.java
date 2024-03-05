import java.util.ArrayList;
import java.awt.Color;
public class Model{
   int ID;
   Color color = Color.YELLOW;
   boolean visible = true;
   boolean clicked = false;
   Point center;
   ArrayList<Triangle> triangle = new ArrayList<Triangle>();
   Model(){}
   Model(Model model){//Copy a model into an new object
      this.visible = model.visible;
      this.center = model.center;
      triangle = new ArrayList<Triangle>(model.triangle.size());
      for(int i = 0; i < model.triangle.size(); i++){
            Triangle t = new Triangle();
            t.vertex[0] = new Point(model.triangle.get(i).vertex[0].x,model.triangle.get(i).vertex[0].y,model.triangle.get(i).vertex[0].z);
            t.vertex[1] = new Point(model.triangle.get(i).vertex[1].x,model.triangle.get(i).vertex[1].y,model.triangle.get(i).vertex[1].z);
            t.vertex[2] = new Point(model.triangle.get(i).vertex[2].x,model.triangle.get(i).vertex[2].y,model.triangle.get(i).vertex[2].z);
            triangle.add(t);  
            //System.out.println(triangle.get(i));
      }
      while(triangle.size() != model.triangle.size()){}
   }
   public void rotateByPoint(Point p, double x, double y, double z){
   //x y and z are all the amounts that you want to rotate it by around the x y and z axis
         double X = 0, Y = 0, Z = 0;
         for(int i = 0; i < triangle.size(); i++){
            for(int j = 0; j < 3; j++){
               X += triangle.get(i).vertex[j].x;
               Y += triangle.get(i).vertex[j].y;
               Z += triangle.get(i).vertex[j].z;
            }
         }
         X /= triangle.size()*3;
         Y /= triangle.size()*3;
         Z /= triangle.size()*3;
         center = new Point(X,Y,Z);
      for(int i = 0; i < triangle.size(); i++){
         for(int j = 0; j < 3; j++){
            Z = (triangle.get(i).vertex[j].z-p.z);
            Y = (triangle.get(i).vertex[j].y-p.y);
            X = (triangle.get(i).vertex[j].x-p.x);
            double ax = Math.atan(Z/Y);
            if(Y < 0){
               ax += Math.PI;
            }
            double dist = Math.sqrt(Z*Z+Y*Y);
            triangle.get(i).vertex[j].y = dist*Math.cos(ax+x);
            triangle.get(i).vertex[j].z = dist*Math.sin(ax+x);
            
            Z = (triangle.get(i).vertex[j].z-p.z);
            Y = (triangle.get(i).vertex[j].y-p.y);
            X = (triangle.get(i).vertex[j].x-p.x);
            double ay = Math.atan(Z/X);
            if(X < 0){
               ay += Math.PI;
            }
            dist = Math.sqrt(Z*Z+X*X);
            triangle.get(i).vertex[j].x = dist*Math.cos(ay+y);
            triangle.get(i).vertex[j].z = dist*Math.sin(ay+y);
            
            Z = (triangle.get(i).vertex[j].z-p.z);
            Y = (triangle.get(i).vertex[j].y-p.y);
            X = (triangle.get(i).vertex[j].x-p.x);
            double az = Math.atan(Y/X);
            if(X < 0){
               az += Math.PI;
            }
            dist = Math.sqrt(Y*Y+X*X);
            triangle.get(i).vertex[j].x = dist*Math.cos(az+z);
            triangle.get(i).vertex[j].y = dist*Math.sin(az+z);
            
            triangle.get(i).vertex[j] = new Point(triangle.get(i).vertex[j].x,triangle.get(i).vertex[j].y,triangle.get(i).vertex[j].z);
            //triangle.get(i).vertex[j] = p.add(triangle.get(i).vertex[j],p);
         }
      }
   }
   public void rotateByCenter(double x, double y, double z){
   //x y and z are all the amounts that you want to rotate it by around the x y and z axis
         double X = 0, Y = 0, Z = 0;
         for(int i = 0; i < triangle.size(); i++){
            for(int j = 0; j < 3; j++){
               X += triangle.get(i).vertex[j].x;
               Y += triangle.get(i).vertex[j].y;
               Z += triangle.get(i).vertex[j].z;
            }
         }
         X /= triangle.size()*3;
         Y /= triangle.size()*3;
         Z /= triangle.size()*3;
         center = new Point(X,Y,Z);
      for(int i = 0; i < triangle.size(); i++){
         for(int j = 0; j < 3; j++){
            double ax = Math.atan(triangle.get(i).vertex[j].z/triangle.get(i).vertex[j].y);
            if(triangle.get(i).vertex[j].y < 0){
               ax += Math.PI;
            }
            double dist = Math.sqrt(triangle.get(i).vertex[j].z*triangle.get(i).vertex[j].z+triangle.get(i).vertex[j].y*triangle.get(i).vertex[j].y);
            triangle.get(i).vertex[j].y = dist*Math.cos(ax+x);
            triangle.get(i).vertex[j].z = dist*Math.sin(ax+x);
            
            double ay = Math.atan(triangle.get(i).vertex[j].z/triangle.get(i).vertex[j].x);
            if(triangle.get(i).vertex[j].x < 0){
               ay += Math.PI;
            }
            dist = Math.sqrt(triangle.get(i).vertex[j].z*triangle.get(i).vertex[j].z+triangle.get(i).vertex[j].x*triangle.get(i).vertex[j].x);
            triangle.get(i).vertex[j].x = dist*Math.cos(ay+y);
            triangle.get(i).vertex[j].z = dist*Math.sin(ay+y);
            
            double az = Math.atan(triangle.get(i).vertex[j].y/triangle.get(i).vertex[j].x);
            if(triangle.get(i).vertex[j].x < 0){
               az += Math.PI;
            }
            dist = Math.sqrt(triangle.get(i).vertex[j].x*triangle.get(i).vertex[j].x+triangle.get(i).vertex[j].y*triangle.get(i).vertex[j].y);
            triangle.get(i).vertex[j].x = dist*Math.cos(az+z);
            triangle.get(i).vertex[j].y = dist*Math.sin(az+z);
            triangle.get(i).vertex[j] = new Point(triangle.get(i).vertex[j].x,triangle.get(i).vertex[j].y,triangle.get(i).vertex[j].z);
            //triangle.get(i).vertex[j] = p.add(triangle.get(i).vertex[j],p);
         }
      }
   }
   public void move(double x, double y, double z){
      Point p = new Point(x, y, z);
      for(int i = 0; i < triangle.size(); i++){
         for(int j = 0; j < 3; j++){
            triangle.get(i).vertex[j] = p.add(triangle.get(i).vertex[j],p);
         }
      }
      x = 0; y = 0; z = 0;
         for(int i = 0; i < triangle.size(); i++){
            for(int j = 0; j < 3; j++){
               x += triangle.get(i).vertex[j].x;
               y += triangle.get(i).vertex[j].y;
               z += triangle.get(i).vertex[j].z;
            }
         }
         x /= triangle.size()*3;
         y /= triangle.size()*3;
         z /= triangle.size()*3;
         center = new Point(x,y,z);
   }
   public void moveTo(Point p){
      if(center == null){
         double x = 0, y = 0, z = 0;
         for(int i = 0; i < triangle.size(); i++){
            for(int j = 0; j < 3; j++){
               x += triangle.get(i).vertex[j].x;
               y += triangle.get(i).vertex[j].y;
               z += triangle.get(i).vertex[j].z;
            }
         }
         x /= triangle.size()*3;
         y /= triangle.size()*3;
         z /= triangle.size()*3;
         center = new Point(x,y,z);
      }
      //System.out.println(center);
      Point s = p.subtract(p,center);
      move(s.x,s.y,s.z);
   }
}