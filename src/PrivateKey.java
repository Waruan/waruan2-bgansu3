
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
