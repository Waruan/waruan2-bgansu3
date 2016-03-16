/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * Key.java: Abstract class that holds a key
 * 
 */

public class Key {

   protected HUI nvalue;
   
   public Key(HUI nvalue)
   {
     this.nvalue = nvalue;
   }
   
   public HUI getN()
   {
     return this.nvalue;
   }
   
   
}
