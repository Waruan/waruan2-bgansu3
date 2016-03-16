import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TwoRadioButtonPanel{

  
  // returns 0 for canceled, 1 for generate, 2 for input
  // public static int showPanel(String radioButton1, String radioButton2)
  public static int showPanel(JRadioButton one, JRadioButton two, String title)
  {
    JRadioButton option1 = one;
    JRadioButton option2 = two;
    ButtonGroup group = new ButtonGroup();
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    option1.setSelected(true);
    group.add(option1);
    group.add(option2);
    panel.add(option1);
    panel.add(option2);
    
    int result = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);
    
    if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
      return 0;
    else if(option1.isSelected())
      return 1;
    else
      return 2;
	}

}
