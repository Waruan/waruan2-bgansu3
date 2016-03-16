/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * PrivateKey: Class that extends Key
 * 
 */

public class PrivateKey extends Key {
  
  private HUI dvalue;
  
  public PrivateKey(HUI nvalue, HUI dvalue)
  {
    super(nvalue);
    this.dvalue = dvalue;
  }
  
  public HUI getD()
  {
    return this.dvalue;
  }
}
