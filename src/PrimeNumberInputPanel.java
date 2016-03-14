import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrimeNumberInputPanel {
  
  private static HUI prime1;
  private static HUI prime2;
  
  public static boolean showPanel()
  {
    JTextField prime1Field = new JTextField(12);
    JTextField prime2Field = new JTextField(12);
    
    JPanel panel = new JPanel();
    
    panel.add(new JLabel("prime1: "));
    panel.add(prime1Field);
    panel.add(Box.createHorizontalStrut(15));
    panel.add(new JLabel("prime2: "));
    panel.add(prime2Field);
    
    int result = JOptionPane.showConfirmDialog(null, panel, "Enter prime numbers", JOptionPane.OK_CANCEL_OPTION);
    if(result == JOptionPane.OK_OPTION)
    {
      prime1 = new HUI(prime1Field.getText());
      prime2 = new HUI(prime2Field.getText());
      return true;
    }
    return false;
  }
  
  public static HUI getPrime1()
  {
    return prime1;
  }
  
  public static HUI getPrime2()
  {
    return prime2;
  }

}
