import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileUtilities {
  
  public static String fileName;
  private static final Path currentRelativePath = Paths.get("");
  private static final String path = currentRelativePath.toAbsolutePath().toString();
  private static final String currentPath = path + "\\waruan2-bgansu3\\";
  private static final String XMLwrapperPUBLIC =  "<rsakey>\n\t" + 
                                                  "<evalue>%s</evalue>\n\t" +
                                                  "<nvalue>%s</nvalue>\n" +
                                                  "</rsakey>";
  private static final String XMLwrapperPRIVATE = "<rsakey>\n\t" + 
                                                  "<dvalue>%s</dvalue>\n\t" +
                                                  "<nvalue>%s</nvalue>\n" +
                                                  "</rsakey>";
                                                   
  public static String OpenAsciiFile() throws IOException
  {
    Fileopener fileOpener = new Fileopener();
    File file = fileOpener.getSelectedFile();
    
    if(file == null)
    {
      return null;
    }
    try
    {
      BufferedReader buffered = new BufferedReader(new FileReader(file));
      StringBuilder data = new StringBuilder();
      String line = buffered.readLine();
      
      while(line != null)
      {
        data.append(line);
        data.append("\n");
        line = buffered.readLine();
      }
      fileName = file.getName();
      buffered.close(); 
      return data.toString();   
    }
    catch(Exception e)
    {
      return "";
    }
  }
  
  public static void ViewAsciiFile()
  {
    
    String data1 = "";
    try {
      data1 = OpenAsciiFile();
      if(data1 != null)
      {
        JTextArea textArea= new JTextArea(data1);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, fileName, JOptionPane.INFORMATION_MESSAGE);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
    }
    
  }
  
  public static void writeAndSaveTextFile(String[] data, String fileName)
  {
    
    try {
      PrintWriter writer = new PrintWriter(currentPath + fileName + ".txt");
      
      for(int i = 0; i < data.length; i++)
      {
        writer.println(data[i]);
      }
      writer.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void writeAndSaveTextFile(String data, String fileName)
  {
    
    try {
      PrintWriter writer = new PrintWriter(currentPath + fileName + ".txt");
      writer.print(data);
      writer.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void writeAndSaveXMLFile(Key key, String fileName)
  {
    String data = "";
    if(key instanceof PrivateKey)
    {
      data = String.format(XMLwrapperPRIVATE, ((PrivateKey) key).getD().getString(), key.getN().getString());
    }
    else if(key instanceof PublicKey)
    {
      data = String.format(XMLwrapperPUBLIC, ((PublicKey) key).getE().getString(), key.getN().getString());
    }
    
    PrintWriter writer;
    try {
      writer = new PrintWriter(currentPath + fileName + ".xml");
      writer.print(data);
      writer.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }
  public static String promptUserForFileName(String message)
  {
    String fileName = JOptionPane.showInputDialog(null, message, "Prompt for filename", JOptionPane.OK_CANCEL_OPTION);
    return fileName;
  }
  
  public static int getNumberOfLinesOfFile(String fileName)
  {
    LineNumberReader lnr;
    try {
      File file = new File(fileName);
      System.out.println(file.getCanonicalPath());
      lnr = new LineNumberReader(new FileReader(file));
    
    lnr.skip(Long.MAX_VALUE);
    int result = lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
    // Finally, the LineNumberReader object should be closed to prevent resource leak
    lnr.close();
    return result;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0;
    }
    
  }
  
  public static HUI getRandomPrimeFromFile()
  {
    String fileName = "C:\\Users\\Ben\\CS342\\project3bgansu3\\waruan2-bgansu3\\primes.txt";
    int numLines = getNumberOfLinesOfFile(fileName);
    int randomLine = (int) (Math.random() * (numLines + 1));
    
    BufferedReader buffered;
    try {
      buffered = new BufferedReader(new FileReader(fileName));

    StringBuilder data = new StringBuilder();
    String line = buffered.readLine();
    
    for(int i = 0; i < randomLine - 1; i++)
    {
      buffered.readLine();
    }
    
    String prime = buffered.readLine();
    
    return new HUI(prime);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new HUI("0");
    }
    
  }
}
