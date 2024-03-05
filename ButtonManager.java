import java.util.ArrayList;
public class ButtonManager{
   ArrayList<Click> button = new ArrayList<Click>();
   ButtonManager(){
      button.add(new Button1());
   }
   void click(Click button){
      button.click();
   }
}
class Click{
   public void click() {}
}
class Button1 extends Click{
   public void click(){
      System.out.println(1);
   }
}
class Button2 extends Click{
   public void click(){
      System.out.println(1);
   }
}