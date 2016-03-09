import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GUI extends JFrame implements ActionListener {
	private Container container;
	private GridLayout grid;
	private Button buttons[] = new Button[6];
	

	GUI(){
		super();
		grid = new GridLayout(3,3,3,3);		//set dem of layout
		container = getContentPane();		//get the container
		container.setLayout(grid);			//set grid as the layout
	
	    buttons[0] = new Button("View ASCII File",0);
	    buttons[1] = new Button("Key Creation",1);
	    buttons[2] = new Button("Block a File",2);
	    buttons[3] = new Button("Unblock a File",3);
	    buttons[4] = new Button("Encryption",4);
	    buttons[5] = new Button("Decryption",5);
	    
	    for(int i=0;i<6;i++){
	    	buttons[i].addActionListener(this);
	    	container.add(buttons[i]);
	    }
	    setSize( 300, 150 );
	    setVisible( true );
	}
	
	public static void main(String[] args) {
		GUI app = new GUI();
		app.setDefaultCloseOperation( GUI.EXIT_ON_CLOSE );
		
	}

	public void actionPerformed(ActionEvent event) {
		Button temp = (Button) event.getSource();
		int type = temp.getNumber();
		if(type == 0 || type == 2 || type == 3 || type == 4 || type == 5 ){
			subFrame subWindow = new subFrame("Enter File URL",type);
		}
	}

}
