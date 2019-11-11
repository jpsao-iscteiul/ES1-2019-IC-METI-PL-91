package ES1_2019_IC_METI_PL_91.ESProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class gui extends JFrame{
	
	public gui() {
	JFrame frame = new JFrame("GUI"); 
	frame.setSize(600, 600);
	frame.setLayout(new BorderLayout());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel east_panel= new JPanel();
	JPanel north_panel= new JPanel();
	JPanel west_panel= new JPanel();
	
	//panel north
	JLabel rule = new JLabel("RULE");
	JTextField box = new JTextField(30); 
	north_panel.add(rule);
	north_panel.add(box);
	
	
	// panel east
	east_panel.setLayout(new GridLayout(4,1));
	JButton import_file= new JButton ("Import");
	JButton save = new JButton("Save");
	JButton load = new JButton("Load");
	JButton submit = new JButton("Submit");
	
	east_panel.add(import_file);
	east_panel.add(save);
	east_panel.add(load);
	east_panel.add(submit);
	
	//panel west
	west_panel.setLayout(new GridLayout(3,3));
	JLabel metrics = new JLabel("Metrics");
	JLabel simbols = new JLabel("Simbols");
	JLabel threshold = new JLabel("Threshold");
	
	JComboBox metric_list = new JComboBox();
	//add lmetrics to metrics_list
	metric_list.addItem("LOC");
	metric_list.addItem("CYCLO");
	metric_list.addItem("ATFD");
	metric_list.addItem("LAA");
	
	JComboBox simbol_list = new JComboBox();
	//add symbols
	simbol_list.addItem("<");
	simbol_list.addItem(">");
	simbol_list.addItem("<=");
	simbol_list.addItem(">=");
	simbol_list.addItem("=");
	
	JTextField value = new JTextField();
	JButton add = new JButton("Add");
	
	
	west_panel.add(metrics);
	west_panel.add(simbols);
	west_panel.add(threshold);
	west_panel.add(metric_list);
	west_panel.add(simbol_list);
	west_panel.add(value);
	west_panel.add(add);
	
	
	//panel center
	
	JTextArea output = new JTextArea(20,20);
	
	
	//add panels to main frame
	frame.add("Center", output);
	frame.add("North", north_panel);
	frame.add("West", west_panel);
	frame.add("East", east_panel);
	frame.setVisible(true);
	
	}
	
	public static void main(String [] args) {
		System.out.println("4th");
		new gui();
	}

}

