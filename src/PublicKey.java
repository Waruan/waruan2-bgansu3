/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * PublicKey: Class that extends Key
 * 
 */

public class PublicKey extends Key {
  
  private HUI evalue;
  
  public PublicKey(HUI nvalue, HUI evalue)
  {
    super(nvalue);
    this.evalue = evalue;
  }
  
  public HUI getE()
  {
    return this.evalue;
  }
}
