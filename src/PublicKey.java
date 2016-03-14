
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
