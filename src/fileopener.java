import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class fileopener implements ActionListener {
	File selectedFile;

	public void actionPerformed(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          System.out.println(selectedFile.getAbsolutePath());
          Button clicked = (Button)event.getSource();
          if(clicked.getNumber() == 0){
          	subFrame2 subWindow = new subFrame2(selectedFile);
          }
        }
      }

}
