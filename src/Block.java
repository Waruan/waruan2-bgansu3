

public class Block {

  private static final int BLOCKSIZE = 8;
  
  public static void block()
  {
    String data;
    try {
      data = FileUtilities.OpenAsciiFile();
      if(data != null)
      {
        String[] blockedData = blockAFile(data);
        String fileName = FileUtilities.promptUserForFileName("Enter filename (without extensions): ");
        if(fileName != "")
          FileUtilities.writeAndSaveTextFile(blockedData, fileName);
      }
      
    } catch (Exception e) {
     
    }
  }
  
  public static String[] blockAFile(String data)
  {
    System.out.println("blockAFile()");
    int length = data.length();
    System.out.println(data);
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
    System.out.println(result);
    int resultLength = result.length();
    int padding = 0;
    if(result.length() >= BLOCKSIZE)
      padding = resultLength % (BLOCKSIZE*2);
    else
      padding = BLOCKSIZE * 2 - resultLength;
      
    System.out.println(padding);
    for(int i = 0; i < padding ; i++)
    {
      result = "0" + result;
    }

    data.replace("\n", "");
    System.out.println(data);
    int ceilCheck = (toBeBlocked.getNumLength() % (BLOCKSIZE*2) == 0) ? 0 : 1;
    int numBlocks = toBeBlocked.getNumLength()/ (BLOCKSIZE*2) + ceilCheck; 
    System.out.println(numBlocks);
    String[] dataBlocked = new String[numBlocks];
    int j = 0;
    
    System.out.println("Numblocks: " + numBlocks);
    for(int i = numBlocks-1; i >= 0; i--)
    {
    //if(2*BLOCKSIZE*(i + 1) < length)
      dataBlocked[j] = result.substring(i*BLOCKSIZE*2, 2*BLOCKSIZE*(i + 1)); 
//      else
//        dataBlocked[j] = result.substring(i*BLOCKSIZE*2, length); 
      j++;
    }
    
    return dataBlocked;
  }
  
  public static void unblock()
  {
    String[] data;
    try {
      data = FileUtilities.OpenAsciiFileToArray();
      if(data != null)
      {
        String unblockedData = unblockAFile(data);
        String fileName = FileUtilities.promptUserForFileName("Enter filename (without extensions): ");
        if(fileName != "")
          FileUtilities.writeAndSaveTextFile(unblockedData, fileName);
      }
      
    } catch (Exception e) {
     
    }
  }
  
  public static String unblockAFile(String[] data)
  {
    String unblockedData = "";
    int length = data.length;
    
    for(int i = 0; i < length; i++)
    {
      int lineLength = data[i].length();
      if(lineLength % 2 == 1)
      {
        data[i] = "0" + data[i];
        lineLength++;
      }
      for(int j = lineLength - 1; j >= 0; j -= 2)
      {
        
        String asciiStr = "";
        
        int y = j;
        int x = y - 1;
        asciiStr += data[i].charAt(x);
        asciiStr += data[i].charAt(y); 
        
        if(!asciiStr.equals("00"))
        {
          int ascii = Integer.parseInt(asciiStr);
          ascii += 27;
          unblockedData += (char) ascii;
        }
      }  
    }
    return unblockedData;
  }
}
