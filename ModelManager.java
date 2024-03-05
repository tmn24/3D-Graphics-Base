import java.util.ArrayList;
import java.awt.Color;
public class ModelManager{
   Camera camera;
   int cw, ch;
   ArrayList<Model> model = new ArrayList<Model>();
   ModelManager(Camera camera, int cw, int ch){
      this.camera = camera;
      this.cw = cw;
      this.ch = ch;
      Model model1 = new Model();
      Triangle tri1 = new Triangle();
      tri1.vertex[0] = new Point(-100,-100,-100);
      tri1.vertex[1] = new Point(-100,100,-100);
      tri1.vertex[2] = new Point(100,100,-100);
      tri1.normal = new Point(0,0,-1);
      Triangle tri2 = new Triangle();
      tri2.vertex[0] = new Point(100,-100,-100);
      tri2.vertex[1] = new Point(100,100,-100);
      tri2.vertex[2] = new Point(-100,-100,-100);
      tri2.normal = new Point(0,0,-1);
      Triangle tri3 = new Triangle();
      tri3.vertex[0] = new Point(1,1,0);
      tri3.vertex[1] = new Point(1,0,0);
      tri3.vertex[2] = new Point(1,0,1);
      tri3.normal = new Point(1,0,0);
      Triangle tri4 = new Triangle();
      tri4.vertex[0] = new Point(1,1,1);
      tri4.vertex[1] = new Point(1,1,0);
      tri4.vertex[2] = new Point(1,0,1);
      tri3.normal = new Point(1,0,0);
      model1.triangle.add(tri1);
      model1.triangle.add(tri2);
      model.add(model1);
      model.add(STLReader.getModel());
      //model.add(STLReader.getModel());
      //model.add(STLReader.getModel());
      //model.add(STLReader.getModel());
      //model1.triangle.add(tri3);
      //model1.triangle.add(tri4);
      for(int i = 0; i < model.size(); i++){
         for(int j = 0; j < model.get(i).triangle.size(); j++){
         }
      }
      //model.add(model1);
   }
   public void projectAllModels(){
       Point pos = camera.pos;
       Point normal = camera.normal;
       Point planeX = camera.planeX;
       Point planeY = camera.planeY;
       Point lightRay = camera.lightRay;
       double a1Range = camera.a1Range;
       double a2Range = camera.a2Range;
       for(int j = 0; j < model.get(0).triangle.size(); j++){
            //System.out.println(model.get(i).triangle.get(j).vertex[0]);
            //System.out.println(model.get(i).triangle.get(j).vertex[1]);
            ///System.out.println(model.get(i).triangle.get(j).vertex[2]);
            //System.out.println(j);
            model.get(0).triangle.get(j).modelID = 0;
            projectToScreen(model.get(0).color, model.get(0).triangle.get(j),false, pos, normal, planeX, planeY, a1Range, a2Range, lightRay);
            //System.out.println(model.get(i).triangle.get(j).screenVertex[0]);
            //System.out.println(model.get(i).triangle.get(j).screenVertex[1]);
            //System.out.println(model.get(i).triangle.get(j).screenVertex[2]);
            //System.out.println(j);
         }
      for(int i = 1; i < model.size(); i++){             //!!!!!!!!!!!!!!!!!!*******THIS STARTS FROM ONE NOW*********!!!!!!!!!!!!!!!!!!!!!!!!!!
         //System.out.println(model.get(i).ID);
         model.get(i).ID = i;
         for(int j = 0; j < model.get(i).triangle.size(); j++){
               //System.out.println(model.get(i).triangle.get(j).vertex[0]);
               //System.out.println(model.get(i).triangle.get(j).vertex[1]);
               ///System.out.println(model.get(i).triangle.get(j).vertex[2]);
               //System.out.println(j);
               model.get(i).triangle.get(j).modelID = i;
               projectToScreen(model.get(i).color, model.get(i).triangle.get(j),model.get(i).clicked, pos, normal, planeX, planeY, a1Range, a2Range, lightRay);
               //System.out.println(model.get(i).triangle.get(j).screenVertex[0]);
               //System.out.println(model.get(i).triangle.get(j).screenVertex[1]);
               //System.out.println(model.get(i).triangle.get(j).screenVertex[2]);
               //System.out.println(j);
         }
      }
   }
   public void projectToScreen(Color color, Triangle triangle, boolean clicked, Point pos, Point normal, Point planeX, Point planeY, double a1Range, double a2Range, Point lightRay){
      Point p3 = new Point(0,0,0);
      double minA = 0;
      double totDist = 0;
      triangle.visible = true;
      for(int i = 0; i < 3; i++){
         double a = p3.scalarProject(p3.subtract(triangle.vertex[i],pos),normal);
         //System.out.println(a);
         if(a <= 0){
            triangle.visible = false;
         }
         //System.out.println(triangle.vertex[i]);
         //System.out.println(triangle.vertex[i].dim);
         //System.out.println(camera.pos.dim);
         //System.out.println(p3.subtract(triangle.vertex[i],camera.pos));
         //System.out.println(camera.pos);
         //System.out.println(camera.planeX+"|");
         //System.out.println(camera.normal);
         //System.out.println(camera.planeY);
         double b = p3.scalarProject(p3.subtract(triangle.vertex[i],pos),planeX);
         double c = p3.scalarProject(p3.subtract(triangle.vertex[i],pos),planeY);
         //System.out.println(p3.add(p3.add(p3.scale(camera.normal,a),p3.scale(camera.planeX,b)),p3.scale(camera.planeY,c)));
         //System.out.println(p3.subtract(triangle.vertex[i],camera.pos));
         double dist = p3.subtract(triangle.vertex[i],pos).magnitude;
         //if(dist < 0.1){
            //System.out.println(dist);
         //}
         //triangle.distance += p3.subtract(triangle.vertex[i],camera.pos).magnitude; 
         //if(i == 0){
         //   minA = dist;
         //} else if(a < minA){
         //   minA = dist;
         //}
         //triangle.distance = minA;
         triangle.distances[i] = dist;
         int scale = 50;
         //System.out.println(b+","+c);
         double a1 = Math.atan(b/a);
         double a2 = Math.atan(c/a);
         b = ch*a1/a1Range;
         c = cw*a2/a2Range;
         //System.out.println(b+","+c);
         //System.out.println(a);
         if(b > 100 || c > 100){
            //System.out.println(b+","+c);
            //System.out.println(a);
            //System.out.println(triangle.vertex[i]);
            //System.out.println(camera.pos);
            //System.out.println(p3.subtract(triangle.vertex[i],camera.pos));
         }
         Point n = p3.normalize(p3.crossProduct(p3.subtract(triangle.vertex[2],triangle.vertex[0]),p3.subtract(triangle.vertex[1],triangle.vertex[0])));
         double shadow = Math.abs(p3.dotProduct(n, lightRay));
         if(!clicked){
            //System.out.println(shadow);
            //System.out.println(new Point(color.getRed(),color.getGreen(),color.getBlue()));
            triangle.color = new Color((int)(color.getRed()*shadow),(int)(color.getGreen()*shadow),(int)(color.getBlue()*shadow));
         } else {
            triangle.color = new Color(0,(int)shadow,(int)shadow);
         }
         triangle.screenVertex[i] = new Point(b,c);
         //System.out.println(i+"|index");
         //System.out.println(triangle.screenVertex[i]);
      }
         boolean[] bool = new boolean[3];
         for(int i = 0; i < 3; i++){
            if(triangle.screenVertex[i].x > camera.panel.cw/2+1){
               bool[i] = true;
            }
         }
         if(bool[0] && bool[1] && bool[2]){
            //System.out.println(1);
            triangle.visible = false;
            return;
         }
         bool = new boolean[3];
         for(int i = 0; i < 3; i++){
            if(triangle.screenVertex[i].x < -camera.panel.cw/2-1){
               bool[i] = true;
            }
         }
         if(bool[0] && bool[1] && bool[2]){
            //System.out.println(1);
            triangle.visible = false;
            return;
         }
         bool = new boolean[3];
         for(int i = 0; i < 3; i++){
            if(triangle.screenVertex[i].y > camera.panel.ch/2+1){
               bool[i] = true;
            }
         }
         if(bool[0] && bool[1] && bool[2]){
            //System.out.println(1);
            triangle.visible = false;
            return;
         }

      bool = new boolean[3];
      for(int i = 0; i < 3; i++){
         if(triangle.screenVertex[i].y < -camera.panel.ch/2-1){
            bool[i] = true;
         }
      }
      if(bool[0] && bool[1] && bool[2]){
         //System.out.println(1);
         triangle.visible = false;
         return;
      }
      //triangle.distance *= 0.3333333;
      //System.out.println(triangle.distance);
   }
}