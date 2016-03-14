import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	private int num;
	Button(String text,int type){
		super(text);
		num = type;
	}
	
	public int getNumber (){
		return num;
	}
	
	
}
