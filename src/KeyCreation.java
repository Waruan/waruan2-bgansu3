
public class KeyCreation {

  private PublicKey publicKey;
  private PrivateKey privateKey;
  private HUI nvalue;
  private HUI phivalue;
  private HUI evalue;
  private HUI dvalue;
  private HUI prime1;
  private HUI prime2;
  
  public KeyCreation(HUI prime1, HUI prime2)
  {
    this.prime1 = prime1;
    this.prime2 = prime2;
    createN();
    createE();
    createD();
    
    publicKey = new PublicKey(this.nvalue, this.evalue);
    privateKey = new PrivateKey(this.nvalue, this.dvalue);
  }
  
  private void createN()
  {
    this.nvalue = this.prime1.multiplication(this.prime2);
    
    this.nvalue.R_display();
  }
  
  private void createE()
  {
    HUI prime1m1 = this.prime1.subtraction(1);
    HUI prime2m1 = this.prime2.subtraction(1);
    this.phivalue = prime1m1.multiplication(prime2m1);
    this.phivalue.R_display();
    boolean found = false;
    
    HUI e = new HUI("2");
    while(!found)
    {      
      if(Utilities.coPrime(e, this.phivalue))
      {
        this.evalue = e;
        found = true;
      }
      
      e = e.addition(1);
    }
    this.evalue.R_display();
  }
  
  private void createD()
  {
    HUI d = new HUI("1");
    
    boolean found = false;
    
    // Extended Euclidian, basically trying to find the inverse mod of e mod phi
    while(!d.multiplication(this.phivalue).addition(1).Mod(this.evalue).HUIequals(0)){
      d = d.addition(1);
    }
    
    this.dvalue = d.multiplication(this.phivalue).addition(1).division(this.evalue);
    this.dvalue.R_display();
  }
  
  public PublicKey getPublicKey()
  {
    return this.publicKey;
  }
  
  public PrivateKey getPrivateKey()
  {
    return this.privateKey;
  }
  
}
