
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
    this.prime1.R_display();
    this.prime2.R_display();
    createN();
    createE();
    createD();
    
    publicKey = new PublicKey(this.nvalue, this.evalue);
    privateKey = new PrivateKey(this.nvalue, this.dvalue);
    
    this.nvalue.R_display();
    this.phivalue.R_display();
    this.evalue.R_display();
    this.dvalue.R_display();
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
  }
  
  private void createD()
  {
    HUI d = new HUI("2");
    
    boolean found = false;
    
    while(!found){
      
      HUI ed = d.multiplication(this.evalue);
      if(ed.Mod(this.phivalue).HUIequals(1))
      {
        this.dvalue = d;
        found = true;
      }
      
      d = d.addition(1);
    }
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
