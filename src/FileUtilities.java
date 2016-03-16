import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileUtilities {
  
  public static String fileName;
  private static final Path currentRelativePath = Paths.get("");
  private static final String path = currentRelativePath.toAbsolutePath().toString();
  private static final String currentPath = path + "\\waruan2-bgansu3\\";
                                                   
  public static String OpenAsciiFile() throws IOException
  {
    System.out.println("OpenAsciiFile()");
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
      buffered.close(); 
      System.out.println(data.toString());
      return data.toString();  
    }
    catch(Exception e)
    {
      return null;
    }
  }
  
  public static String[] OpenAsciiFileToArray() throws IOException
  {
    Fileopener fileOpener = new Fileopener();
    File file = fileOpener.getSelectedFile();
   
    if(file == null)
    {
      return null;
    }
    try
    {
      int numLines = getNumberOfLinesOfFile(file);
      BufferedReader buffered = new BufferedReader(new FileReader(file));
      String[] data = new String[numLines];
      String line = buffered.readLine();
      
      int i = 0;
      System.out.println(numLines);
      while(line != null)
      {
        data[i] = line;
        System.out.println(data[i]);
        line = buffered.readLine();
        i++;
      }
      buffered.close(); 
      return data;   
    }
    catch(Exception e)
    {
      return null;
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
        JOptionPane.showMessageDialog(null, scrollPane, fileName, JOptionPane.PLAIN_MESSAGE);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
    }
    
  }
  
  public static String asciiEditor()
  {
      JPanel panel = new JPanel();
      JTextArea textArea= new JTextArea();
      JLabel confirm = new JLabel("Save?");
      JScrollPane scrollPane = new JScrollPane(textArea);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable(true);
      scrollPane.setPreferredSize(new Dimension(500, 500));
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.add(scrollPane);
      panel.add(confirm);
      int choice = JOptionPane.showConfirmDialog(null,  panel, "Ascii Editor", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
      
      String text = textArea.getText();
      if(choice == JOptionPane.YES_OPTION)
      {
        String fileName = promptUserForFileName("Enter filename (without extensions): ");
        writeAndSaveTextFile(text, fileName);
      }
      return text;
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
    DocumentBuilderFactory rsaFactory = 
        DocumentBuilderFactory.newInstance();
    DocumentBuilder rsaBuilder;
    try {
      rsaBuilder = rsaFactory.newDocumentBuilder();
      Document doc = rsaBuilder.newDocument();
      
      Element rootElement = doc.createElement("rsakey");
      doc.appendChild(rootElement);
      if(key instanceof PrivateKey)
      {
        Element dvalue = doc.createElement("dvalue");
        dvalue.appendChild(doc.createTextNode(((PrivateKey) key).getD().getString()));
        Element nvalue = doc.createElement("nvalue");
        nvalue.appendChild(doc.createTextNode(key.getN().getString()));
        rootElement.appendChild(dvalue);
        rootElement.appendChild(nvalue);
      }
      else if(key instanceof PublicKey)
      {
        Element evalue = doc.createElement("evalue");
        evalue.appendChild(doc.createTextNode(((PublicKey) key).getE().getString()));
        Element nvalue = doc.createElement("nvalue");  
        nvalue.appendChild(doc.createTextNode(key.getN().getString()));
        rootElement.appendChild(evalue);
        rootElement.appendChild(nvalue);
      }
      
      TransformerFactory tFactory = 
          TransformerFactory.newInstance();
      Transformer transformer = 
          tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
     // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(currentPath + fileName + ".xml"));
      transformer.transform(source, result);
      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(source, consoleResult);
    } 
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  // FIX THIS!!!!
  public static Key openAndParseRSAfile(int type)
  {
    Fileopener fileOpener = new Fileopener();
    File file = fileOpener.getSelectedFile();
    if(file == null)
    {
      return null;
    }
    String fileName = file.getName();
    if(fileName == null)
    {
      return null;
    }
    
    DocumentBuilderFactory rsaFactory = 
        DocumentBuilderFactory.newInstance();
    
    DocumentBuilder rsaBuilder;
    try {
      rsaBuilder = rsaFactory.newDocumentBuilder();
      Document doc = rsaBuilder.parse(file);
      
      NodeList nList = doc.getElementsByTagName("rsakey");
      Node nNode = nList.item(0);
      
      HUI a[] = new HUI[2];

      Element eElement = (Element) nNode;
      
      a[1] = new HUI(eElement.getElementsByTagName("nvalue").item(0).getTextContent());
     
      Key key = null;
      
      if(type == 1)
      {
        a[0] = new HUI(eElement.getElementsByTagName("evalue").item(0).getTextContent());
        key= new PublicKey(a[1], a[0]);
      }
      else if (type == 2)
      {
        a[0] = new HUI(eElement.getElementsByTagName("dvalue").item(0).getTextContent());
        key= new PrivateKey(a[1], a[0]);
      }
      
      return key;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      // TODO Auto-generated catch block
      return null;
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
  
  public static int getNumberOfLinesOfFile(File file)
  {
    LineNumberReader lnr;
    try {
      System.out.println(file.getCanonicalPath());
      lnr = new LineNumberReader(new FileReader(file));
    
    lnr.skip(Long.MAX_VALUE);
    int result = lnr.getLineNumber(); //Add 1 because line index starts at 0
    // Finally, the LineNumberReader object should be closed to prevent resource leak
    lnr.close();
    return result;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0;
    }
    
  }
  
  @SuppressWarnings("resource")
  public static HUI getRandomPrimeFromFile()
  {
    String fileName = "C:\\Users\\Ben\\CS342\\project3bgansu3\\waruan2-bgansu3\\primes.txt";
    int numLines = getNumberOfLinesOfFile(fileName);
    int randomLine = (int) (Math.random() * (numLines + 1));
    
    BufferedReader buffered;
    try {
      buffered = new BufferedReader(new FileReader(fileName));
    
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
