package ES1_2019_IC_METI_PL_91.ESProject;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public gui() {
	JFrame frame = new JFrame("GUI"); 
	frame.setSize(200, 200);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JLabel rule = new JLabel("RULE");
	frame.add(rule);
	frame.setVisible(true);
	
	}
	
	public static void main(String [] args) {
		System.out.println("4th");
		new gui();
	}

}

