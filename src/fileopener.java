import java.io.File;

import javax.swing.JFileChooser;

public class Fileopener{

  private File selectedFile;
  private String fileName;
  
  public Fileopener()
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
      fileName = selectedFile.getName();
    }
  }
	
	public File getSelectedFile()
	{
	  return selectedFile;
	}

	public String getFileName()
  {
    return fileName;
  }
}
