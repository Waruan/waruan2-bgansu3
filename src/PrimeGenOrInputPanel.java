import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PrimeGenOrInputPanel{

  
  // returns 0 for canceled, 1 for generate, 2 for input
  public static int showPanel()
  {
    JRadioButton generate = new JRadioButton("I'd like you to randomly generate two prime numbers");
    JRadioButton input = new JRadioButton("I would like to provide two prime numbers");
    
    JPanel panel = new JPanel();
    panel.add(generate);
    panel.add(input);
    
    int result = JOptionPane.showConfirmDialog(null, panel, "Input vs Generate primes", JOptionPane.OK_CANCEL_OPTION);
    
    if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
      return 0;
    else if(generate.isSelected())
      return 1;
    else
      return 2;
	}

}
