import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class subFrame extends JFrame implements ActionListener  {
	private BorderLayout grid;
	private JTextField input;
	private Button button;
	private Container container;
	// lack of better naming
	private int num;
	subFrame(String text, int type){
		super(text);
		if(type == 1 ){
			num = type;
			grid = new BorderLayout(2,1);
			button = new Button("Submit",type);
			button.addActionListener(this);
			input = new JTextField();
			container = getContentPane();
			container.setLayout(grid);
			container.add(button,BorderLayout.CENTER);
			container.add(input, BorderLayout.NORTH);
			setSize( 300, 150 );
		    setVisible( true );
		}
	}

	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(this,input.getText());
		//use num to decide which function call
	}
}
