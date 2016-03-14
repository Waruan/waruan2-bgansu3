import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private Container container;
	private GridLayout grid;
	private Button buttons[] = new Button[6];
	private String message[] = new String[6];
	private JTextArea status;
	
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
	    
	    status = new JTextArea("Doing nothing...");
	    status.setEditable(false);
	    status.setSize(100, 20);
	    
	    for(int i=0;i<6;i++)
	    {
	    	buttons[i].addActionListener(this);
	    	container.add(buttons[i]);
	    }
	    container.add(status);
	    setSize( 500, 150);
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
		  this.status.setText("Viewing a file...");
		  FileUtilities.ViewAsciiFile();
		  break;
		case 1:
		  boolean found = false;
		  boolean canceled = false;
		  HUI prime1 = new HUI("0");
		  HUI prime2 = new HUI("0");
		  int choice = PrimeGenOrInputPanel.showPanel();
		  
		  if(choice == 1)
		  {
		    prime1 = FileUtilities.getRandomPrimeFromFile();
		    prime2 = FileUtilities.getRandomPrimeFromFile();
		    
		    prime1.R_display();
		    prime2.R_display();
		    
		  }
		  else if(choice == 2)
		  {
  		  while(!found && !canceled)
  		  {
  		    if(!PrimeNumberInputPanel.showPanel())
  		      canceled = true;
  		    else
  		    {
  		      prime1 = PrimeNumberInputPanel.getPrime1();
  		      prime2 = PrimeNumberInputPanel.getPrime2();
  		      
  		    }
  		  }
		  }
  		if(choice == 1 || choice == 2)
  		  this.status.setText("Checking primes for legality");
  		  if(Utilities.isPrime(prime1) && Utilities.isPrime(prime2))
        {
  		    this.status.setText("Creating keys...");
          KeyCreation key = new KeyCreation(prime1, prime2);
          this.status.setText("Saving files...");
          PublicKey publicKey = key.getPublicKey();
          PrivateKey privateKey = key.getPrivateKey();
          
          // Checking if number of digits of n is greater than 8, since
          // our blocking size of use is 8
          if(publicKey.getN().getString().length() > 8)
          {
            String fileName = FileUtilities.promptUserForFileName("Enter filename for Public Key (without extensions): ");
            if(fileName != "")
              FileUtilities.writeAndSaveXMLFile(publicKey, fileName);
            
            
            String fileName2 = FileUtilities.promptUserForFileName("Enter filename for Private Key (without extensions): ");
            if(fileName2 != "")
              FileUtilities.writeAndSaveXMLFile(privateKey, fileName2);
            
            found = true;
          }
          else
          {
            if(choice == 2)
            {
              int result = JOptionPane.showConfirmDialog(null,
                  "Product of the two prime number is too small, would you like to try again?", "Invalid primes", JOptionPane.YES_NO_OPTION); 
              if(result == JOptionPane.YES_OPTION)
              {
                found = false;
              }
              else
              {
                canceled = true;
              }
            }
            else if(choice == 1)
            {
              JOptionPane.showMessageDialog(null, "Invalid prime number input(s)", "Invalid primes", JOptionPane.YES_OPTION);
              canceled = true;
            }
          }
        }
        else
        {
          if(choice == 2)
          {
            int result = JOptionPane.showConfirmDialog(null, "Invalid prime number input(s)", "Invalid primes", JOptionPane.YES_NO_OPTION); 
            if(result == JOptionPane.YES_OPTION)
            {
              found = false;
            }
            else
            {
              canceled = true;
            }
          }
          else if(choice == 1)
          {
            JOptionPane.showMessageDialog(null, "Invalid prime number input(s)", "Invalid primes", JOptionPane.YES_OPTION);
            canceled = true;
          }
        }
  		  this.status.setText("Doing nothing...");
      break;
		case 2:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  this.status.setText("Blocking a file...");
		  Block.block();
		  this.status.setText("Doing nothing...");
      break;
		case 3:
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  this.status.setText("Unblocking a file...");
		  Block.unblock();
		  this.status.setText("Doing nothing...");
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
