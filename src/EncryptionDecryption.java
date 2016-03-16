import javax.swing.JOptionPane;

public class EncryptionDecryption {
  
  public static void encrypt(String data)
  {
    System.out.println("encrypt()");
    JOptionPane.showMessageDialog(null, "Blocking the Data", "Step 2 - Encryption", JOptionPane.PLAIN_MESSAGE);
    String[] data1 = Block.blockAFile(data);
    for(int i = 0; i < data1.length; i++)
    {
      System.out.println(data1[i]);
    }
    JOptionPane.showMessageDialog(null, "Select a Public Key file", "Step 3 - Encryption", JOptionPane.PLAIN_MESSAGE);
    Key key = FileUtilities.openAndParseRSAfile(1);
    if(key == null)
    {
      JOptionPane.showMessageDialog(null, "Invalid key file", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
      return;
    }
    
    for(int i = 0; i < data1.length; i++)
    {
      System.out.println(data1[i]);
    }
    JOptionPane.showMessageDialog(null, "Encrypting the data", "Step 4 - Encryption", JOptionPane.PLAIN_MESSAGE);
    String[] encryptedData = edData(data1, key, 1);
    
    JOptionPane.showMessageDialog(null, "Saving the encrypted data", "Step 5 - Encryption", JOptionPane.PLAIN_MESSAGE);
    String fileName = FileUtilities.promptUserForFileName("Enter filename (without extensions): ");
    if(fileName == "" || fileName == null)
    {
      JOptionPane.showMessageDialog(null, "Invalid file name", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
      return;
    }
    FileUtilities.writeAndSaveTextFile(encryptedData, fileName);
    JOptionPane.showMessageDialog(null, "Done encrypting!", "Encryption", JOptionPane.PLAIN_MESSAGE);
  }
  
  public static void decrypt(String[] data)
  {
    System.out.println("decrypt()");
    JOptionPane.showMessageDialog(null, "Select a Private Key file", "Step 2 - Decryption", JOptionPane.PLAIN_MESSAGE);
    Key key = FileUtilities.openAndParseRSAfile(2);
    if(key == null)
    {
      JOptionPane.showMessageDialog(null, "Invalid key file", "Exiting Decryption", JOptionPane.PLAIN_MESSAGE);
      return;
    }
    
    JOptionPane.showMessageDialog(null, "Decrypting the data", "Step 3 - Decryption", JOptionPane.PLAIN_MESSAGE);
    String[] decryptedData = edData(data, key, 2);
   
    for(int i = 0; i < decryptedData.length; i++)
    {
      System.out.println("Decrypted data: " + decryptedData[i]);
    }
    String asciiDecData = Block.unblockAFile(decryptedData);
    
    JOptionPane.showMessageDialog(null, "Saving the decrypted data", "Step 4 - Encryption", JOptionPane.PLAIN_MESSAGE);
    String fileName = FileUtilities.promptUserForFileName("Enter filename (without extensions): ");
    if(fileName == "" || fileName == null)
    {
      JOptionPane.showMessageDialog(null, "Invalid file name", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
      return;
    }
    FileUtilities.writeAndSaveTextFile(asciiDecData, fileName);
    JOptionPane.showMessageDialog(null, "Done decrypting!", "Decryption", JOptionPane.PLAIN_MESSAGE);
  }
  
  private static String[] edData(String[] data, Key key, int type)
  {
    System.out.println("edData()");
    String[] linearData = new String[data.length];
    
    HUI de = new HUI("0");
    HUI n = new HUI("0");
    if(type == 1)
    {
      PublicKey pkey = (PublicKey) key;
      de = pkey.getE();
      n = pkey.getN();
    }
    else
    {
      PrivateKey pkey = (PrivateKey) key;
      de = pkey.getD();
      n = pkey.getN();
    }
    
   
    
    for(int i = 0; i < data.length; i++)
    {
      System.out.println("dec data str: " + data[i]);
      if(data[i] != null)
      {
        HUI M = new HUI(data[i]);
        HUI C = MpowMod(M, de, n);
        linearData[i] = C.getString();
        System.out.println("linearData: " + linearData[i]);
      }
      else
        linearData[i] = "";
    }
    return linearData;
  }
  
  
  public static HUI MpowMod(HUI msg, HUI exp, HUI mod)
  {
    HUI accum = new HUI("1");
    
    System.out.println("MSG: " + msg.getString());
    System.out.println("EXP: " + exp.getString());
    System.out.println("mod: " + mod.getString());
    msg = msg.Mod(mod);
    int i = 0;
    while(exp.greaterThan(0))
    {
        System.out.println(i);
        HUI oddCheck = exp.Mod(2);
      if(oddCheck.HUIequals(1))
      {
         
         accum = accum.multiplication(msg);
         
         accum = accum.Mod(mod);
         System.out.println("accum: " + accum.getString());
      }
      
      exp = exp.division(2);
      System.out.println("exp: " + exp.getString());
      //msg = msg.multiplication(msg);
      //msg = msg.Mod(mod);
      msg = msg.multiplication(msg);
      msg = msg.Mod(mod);
      System.out.println("msg: " + msg.getString());
    }
    return accum;
  }
  
}
