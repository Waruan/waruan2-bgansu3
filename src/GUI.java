import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private Container container;
	private GridLayout grid;
	private Button buttons[] = new Button[6];
	private String message[] = new String[6];
	
	GUI(){
		super();
		grid = new GridLayout(3,3,3,3);	//set dem of layout
		container = getContentPane();		//get the container
		container.setLayout(grid);			//set grid as the layout
	
	    buttons[0] = new Button("View ASCII File",0);
	    buttons[1] = new Button("Key Creation",1);
	    buttons[2] = new Button("Block a File",2);
	    buttons[3] = new Button("Unblock a File",3);
	    buttons[4] = new Button("Encryption",4);
	    buttons[5] = new Button("Decryption",5);
	    
	    message[0] = "Choose an ASCII file to view.";
	    message[1] = "Selecting Prime numbers.";
	    message[2] = "Choose a file to be blocked.";
	    message[3] = "Choose a file to be unblocked.";
	    message[4] = "Choose a Public Key file to encrypt.";
	    message[5] = "Choose a Private Key file to encrypt.";
	    
	    for(int i=0;i<6;i++)
	    {
	    	buttons[i].addActionListener(this);
	    	container.add(buttons[i]);
	    }
	    setSize( 300, 150 );
	    setLocationRelativeTo(null);
	    setVisible( true );
	}
	
	public static void main(String[] args) {
		GUI app = new GUI();
		app.setDefaultCloseOperation( GUI.EXIT_ON_CLOSE );
		
	}

	public void actionPerformed(ActionEvent event) {
		Button temp = (Button) event.getSource();
		int type = temp.getNumber();
		
		switch(type)
		{
		case 0:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  FileUtilities.ViewAsciiFile();
		  break;
		case 1:
		  boolean found = false;
		  boolean canceled = false;
		  
		  if(PrimeGenOrInputPanel.showPanel() == 2)
		  {
  		  while(!found && !canceled)
  		  {
  		    if(!PrimeNumberInputPanel.showPanel())
  		      canceled = true;
  		    else
  		    {
  		      HUI prime1 = PrimeNumberInputPanel.getPrime1();
  		      HUI prime2 = PrimeNumberInputPanel.getPrime2();
  		      if(Utilities.isPrime(prime1) && Utilities.isPrime(prime2))
  		      {
  		        KeyCreation key = new KeyCreation(prime1, prime2);
  		        found = true;
  		      }
  		      else
  		      {
  		        int result = JOptionPane.showConfirmDialog(null, "Invalid prime number input(s), would you like to try again?", "Invalid primes", JOptionPane.YES_NO_OPTION); 
  		        if(result == JOptionPane.YES_OPTION)
  		        {
  		          found = false;
  		        }
  		        else
  		        {
  		          canceled = true;
  		        }
  		      }
  		    }
  		  }
		  }
      break;
		case 2:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  Block.block();
      break;
		case 3:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  Block.unblock();
      break;
		case 4:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
      break;
		case 5:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
      break;
		default:
		  // should never reach here
		  throw new ArithmeticException();
		}
	}
}
