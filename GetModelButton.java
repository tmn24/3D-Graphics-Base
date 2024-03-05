public class GetModelButton extends Button{
   Panel panel = null;
   GetModelButton(int x, int y, int width, int height, String text, Panel panel){
      super(x,y,width,height,text);
      this.panel = panel;
   }
   public void clicked(){
      panel.mm.model.add(STLReader.getModel());  
   }
}