import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class subFrame2 extends JFrame implements ActionListener  {
	private BorderLayout grid;
	private JTextArea input;
	private Container container;
	private JScrollPane scroll;
	// lack of better naming
	private int num;
	BufferedReader in;
	
	subFrame2(File file){
		super();
		input = new JTextArea();
		scroll = new JScrollPane(input);
		input.setEditable(false);
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Error");
		}
		String line = "";
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		while(line != null){
		  System.out.println(line);
		  input.append(line + "\n");
		  try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		}
		grid = new BorderLayout(2,1);
		container = getContentPane();
		container.setLayout(grid);
		container.add(scroll, BorderLayout.CENTER);
		setSize( 300, 150 );
		setVisible( true );
		
	}

	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(this,input.getText());
		//use num to decide which function call
	}
}
