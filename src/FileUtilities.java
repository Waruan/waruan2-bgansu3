import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
  private static final String currentPath = path + "/waruan2-bgansu3/";
  
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
      PrintWriter writer = new PrintWriter(currentPath + fileName);
      
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
      PrintWriter writer = new PrintWriter(currentPath + fileName);
      writer.println(data);
      writer.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static String promptUserForFileName()
  {
    String fileName = JOptionPane.showInputDialog(null, "Enter filename (without extensions): ", "Prompt for filename", JOptionPane.OK_CANCEL_OPTION);
    return fileName;
  }
}
