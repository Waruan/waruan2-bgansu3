import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private Container container;
	private GridLayout grid;
	private Button buttons[] = new Button[7];
	private String message[] = new String[7];
	private JLabel status;
	
	GUI(){
		super();
		grid = new GridLayout(4,4,3,3);	//set dem of layout
		container = getContentPane();		//get the container
		container.setLayout(grid);			//set grid as the layout
	
	    buttons[0] = new Button("View ASCII File",0);
	    buttons[1] = new Button("Create ASCII File", 1);
	    buttons[2] = new Button("Key Creation",2);
	    buttons[3] = new Button("Block a File",3);
	    buttons[4] = new Button("Unblock a File",4);
	    buttons[5] = new Button("Encryption",5);
	    buttons[6] = new Button("Decryption",6);
	    
	    message[0] = "Choose an ASCII file to view.";
	    message[1] = "Create an ASCII file";
	    message[2] = "Selecting Prime numbers.";
	    message[3] = "Choose a file to be blocked.";
	    message[4] = "Choose a file to be unblocked.";
	    message[5] = "Choose a Public Key file to encrypt.";
	    message[6] = "Choose a Private Key file to encrypt.";
	    
	    status = new JLabel("Status: Doing nothing...");
	    for(int i=0;i<7;i++)
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
		  this.status.setText("Status: Viewing a file...");
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  FileUtilities.ViewAsciiFile();
		  this.status.setText("Status: Doing nothing...");
		  break;
		case 1:
		  this.status.setText("Status: Creating a file...");
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  FileUtilities.asciiEditor();
		  this.status.setText("Status: Doing nothing...");
		  break;
		case 2:
		  this.status.setText("Status: Creating keys...");
		  boolean canceled = false;
		  HUI prime1 = new HUI("0");
		  HUI prime2 = new HUI("0");
		  JRadioButton one = new JRadioButton("I'd like you to randomly generate two prime numbers");
		  JRadioButton two = new JRadioButton("I would like to provide two prime numbers");
		  int choice = TwoRadioButtonPanel.showPanel(one,
		      two, "Input vs Generate primes");
		  
		  if(choice == 1)
		  {
		    prime1 = FileUtilities.getRandomPrimeFromFile();
		    prime2 = FileUtilities.getRandomPrimeFromFile();
		    
		    prime1.R_display();
		    prime2.R_display();
		    
		  }
		  else if(choice == 2)
		  {
		    if(!PrimeNumberInputPanel.showPanel())
		    {
		      canceled = true;
		    }
		    else
		    {
		      prime1 = PrimeNumberInputPanel.getPrime1();
		      prime2 = PrimeNumberInputPanel.getPrime2();
		    }
		  }
  		if((choice == 1 || choice == 2) && (!canceled))
  		{
  		  if(Utilities.isPrime(prime1) && Utilities.isPrime(prime2))
        {
          KeyCreation key = new KeyCreation(prime1, prime2);
          PublicKey publicKey = key.getPublicKey();
          PrivateKey privateKey = key.getPrivateKey();
          
//           Checking if number of digits of n is greater than 8, since
//           our blocking size of use is 8
          if(publicKey.getN().getString().length() > 8)
          {
            String fileName = FileUtilities.promptUserForFileName("Enter filename for Public Key (without extensions): ");
            if(fileName != null)
              FileUtilities.writeAndSaveXMLFile(publicKey, fileName);
            
            
            String fileName2 = FileUtilities.promptUserForFileName("Enter filename for Private Key (without extensions): ");
            if(fileName2 != null)
              FileUtilities.writeAndSaveXMLFile(privateKey, fileName2);
          }
          else
          {
            JOptionPane.showMessageDialog(null,
                  "Product of the two prime number is too small", "Invalid primes", JOptionPane.OK_OPTION); 
          }
        }
  		  else
  		  {
          JOptionPane.showMessageDialog(null, "Invalid prime number input(s)", "Invalid primes", JOptionPane.OK_OPTION);
  		  }
  		}
  		this.status.setText("Status: Doing nothing...");
      break;
		case 3:
		  this.status.setText("Status: Blocking a file...");
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  Block.block();
		  this.status.setText("Status: Doing nothing...");
      break;
		case 4:
		  this.status.setText("Status: Unblocking a file...");
		  JOptionPane.showMessageDialog(this, message[type] , buttons[type].getText(), JOptionPane.PLAIN_MESSAGE);
		  Block.unblock();
		  this.status.setText("Status: Doing nothing...");
      break;
		case 5:
		  this.status.setText("Status: Encrypting...");
		  JRadioButton one1 = new JRadioButton("Choose a file from a directory");
		  JRadioButton two1 = new JRadioButton("Create an ascii file");
		  int choice1 = TwoRadioButtonPanel.showPanel(one1, two1, "ASCII file option");
		  
		  if(choice1 == 1)
		  {
		    JOptionPane.showMessageDialog(null, "Select an ASCII file for encryption", "Step 1 - Encryption", JOptionPane.PLAIN_MESSAGE);
		    String data;
        try {
          data = FileUtilities.OpenAsciiFile();
          if(data == "" || data == null)
          {
            JOptionPane.showMessageDialog(null, "File empty or null", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
            break;
          }
          EncryptionDecryption.encrypt(data);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          break;
        }
		  }
		  else if(choice1 == 2)
		  {
		    JOptionPane.showMessageDialog(null, "Create an ASCII file", "Step 1 - Encryption", JOptionPane.PLAIN_MESSAGE);
		    String data = FileUtilities.asciiEditor();
		    
		    if(data == "" || data == null)
		    {
		      JOptionPane.showMessageDialog(null, "File empty or null", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
          break;
		    }
		    EncryptionDecryption.encrypt(data);
		  }
		  this.status.setText("Status: Doing nothing...");
      break;
		case 6:
		  this.status.setText("Status: Decrypting...");
		  JOptionPane.showMessageDialog(null, "Select an Encrypted file for decryption", "Step 1 - Decryption", JOptionPane.PLAIN_MESSAGE);
      String[] data;
      try {
        data = FileUtilities.OpenAsciiFileToArray();
        
        System.out.println("length: " + data.length);
        for(int i = 0; i < data.length; i++)
        {
          System.out.println(data[i]);
        }
        if(data == null)
        {
          JOptionPane.showMessageDialog(null, "File empty or null", "Exiting Encryption", JOptionPane.PLAIN_MESSAGE);
          break;
        }
        EncryptionDecryption.decrypt(data);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        break;
      }
      this.status.setText("Status: Doing nothing...");
      break;
		default:
		  // should never reach here
		  throw new ArithmeticException();
		}
	}
}
