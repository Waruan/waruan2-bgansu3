

public class Block {

  private static String[] blockedData;
  private static String unblockedData;
  private static final int BLOCKSIZE = 8;
  
  public static void block()
  {
    String data;
    try {
      data = FileUtilities.OpenAsciiFile();
      if(data != null)
      {
        blockAFile(data);
        String fileName = FileUtilities.promptUserForFileName();
        if(fileName != "")
          FileUtilities.writeAndSaveTextFile(blockedData, fileName + ".txt");
      }
      
    } catch (Exception e) {
     
    }
  }
  
  private static void blockAFile(String data)
  {
    int length = data.length();
    HUI toBeBlocked = new HUI("0");
    for(int i = 0; i < length-1; i++)
    {
      int ascii = data.charAt(i) - 27;
      String asciiStr = "" + ascii;
      HUI asciiHUI = new HUI(asciiStr);
      
      for(int j = 0; j < i; j++)
      {
        asciiHUI = asciiHUI.multiplication(100);
      }
     
      toBeBlocked = toBeBlocked.addition(asciiHUI);
    }
    
    String result = toBeBlocked.getString();
    int padding = result.length() % (BLOCKSIZE*2);
    for(int i = 0; i < padding ; i++)
    {
      result = "0" + result;
    }

    int numBlocks = (int) Math.ceil((double) data.length() / BLOCKSIZE);

    blockedData = new String[numBlocks];
    int j = 0;
    for(int i = numBlocks-1; i >= 0; i--)
    {
      blockedData[j] = result.substring(i*BLOCKSIZE*2, 2*BLOCKSIZE*(i + 1)); 
      j++;
    }
  }
  
  public static void unblock()
  {
    String data;
    try {
      data = FileUtilities.OpenAsciiFile();
      if(data != null)
      {
        unblockAFile(data);
        String fileName = FileUtilities.promptUserForFileName();
        if(fileName != "")
          FileUtilities.writeAndSaveTextFile(unblockedData, fileName + ".txt");
      }
      
    } catch (Exception e) {
     
    }
  }
  
  private static void unblockAFile(String data)
  {
    unblockedData = "";
    data = data.replace("\n", "");
    System.out.println(data);
    int length = data.length();
    int numBlocks = length / (BLOCKSIZE*2);
    System.out.println(numBlocks);
    
    for(int i = 0; i < numBlocks; i++)
    {
      for(int j = BLOCKSIZE*2 - 1; j >= 0; j -= 2)
      {
        int x = j + i*BLOCKSIZE*2;
        
        int y = x - 1;
        
        String asciiStr = "";
        asciiStr += data.charAt(y);
        asciiStr += data.charAt(x);
        if(!asciiStr.equals("00"))
        {
          int ascii = Integer.parseInt(asciiStr);
          ascii += 27;
          unblockedData += (char) ascii;
        }
      }   
    }
  }
}
