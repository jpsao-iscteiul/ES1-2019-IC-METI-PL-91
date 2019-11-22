package ES1_2019_IC_METI_PL_91.ESProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox metric_list;
	private JComboBox simbol_list;
	private JTextField value;
	private JTextField box;
	private JTextArea output;
	
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
	 box = new JTextField(30); 
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
	
	metric_list = new JComboBox();
	//add lmetrics to metrics_list
	metric_list.addItem("LOC");
	metric_list.addItem("CYCLO");
	metric_list.addItem("ATFD");
	metric_list.addItem("LAA");
	
	 simbol_list = new JComboBox();
	//add symbols
	simbol_list.addItem("<");
	simbol_list.addItem(">");
	simbol_list.addItem("<=");
	simbol_list.addItem(">=");
	simbol_list.addItem("=");
	
	
	
	
	 value = new JTextField();
	JButton add = new JButton("Add");
	JButton clear = new JButton("Clear");
	
	west_panel.add(metrics);
	west_panel.add(simbols);
	west_panel.add(threshold);
	west_panel.add(metric_list);
	west_panel.add(simbol_list);
	west_panel.add(value);
	
	JPanel sub_panel = new JPanel();
	sub_panel.setLayout(new GridLayout(2,1));
	
	
	sub_panel.add(add);
	sub_panel.add(clear);
	west_panel.add(sub_panel);

	
	
	JPanel sub_west_panel = new JPanel();
	sub_west_panel.setLayout(new GridLayout(2,2));
	
	JButton p1 = new JButton("and");
	JButton p2 = new JButton("or");
	JButton p3 = new JButton("(");
	JButton p4 = new JButton(")");
	
	sub_west_panel.add(p1);
	sub_west_panel.add(p3);
	sub_west_panel.add(p2);
	sub_west_panel.add(p4);
	
	west_panel.add(sub_west_panel);
	
	output = new JTextArea(20,20);
	
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String path = fileChooser();
			try {
				writeRuleToFile(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 JOptionPane.showMessageDialog(null, "Rule saved sucessfully");
		}
	});
	
	add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String created_rule = box.getText();
			String selected_metric=getMetric();
			String selected_simbol=getSymbol();
			double selected_threshold=getThreshold();
			String str = (created_rule+" "+selected_metric + " " + selected_simbol+ " "+selected_threshold);
			box.setText(str);
			
		}
		
	});
	
	clear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			box.setText("");
			
		}
		
	});
	
	p1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			box.setText(box.getText()+" " + "and" + " ");
			}		
	});
	
	p2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			box.setText(box.getText()+" " + "or" + " ");
			}		
	});
	
	p3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			box.setText(box.getText()+" " + "(" + " ");
			}		
	});
	
	p4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			box.setText(box.getText()+" " + ")" + " ");
			}		
	});
	
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Rule rule = new Rule(box.getText());
			box.setText("");
			System.out.println(rule.getRule());
			}		
	});
	
	load.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				box.setText("");
				load(output);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}		
	});
	
	
	import_file.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fileChooser();
		}
	});
	//panel center
	
	
	
	
	//add panels to main frame
	frame.add("Center", output);
	frame.add("North", north_panel);
	frame.add("West", west_panel);
	frame.add("East", east_panel);
	frame.setVisible(true);
	
	
	
	
	}
	public String getMetric() {
		String aux = metric_list.getSelectedItem().toString();
		return aux;
	}
	public String getSymbol() {
		String aux = simbol_list.getSelectedItem().toString();
		return aux;
	}
	public double getThreshold() {
		double aux = Double.parseDouble(value.getText());
		return aux;
	}
	
	public String fileChooser() {
		String path_file=""; 
		try {
			     JFileChooser chooser = new JFileChooser();
			     int retorno = chooser.showOpenDialog(null);
			     
			     if (retorno == JFileChooser.APPROVE_OPTION) {
			       FileReader reader = new FileReader(chooser.getSelectedFile());
			        path_file=  chooser.getSelectedFile().getAbsolutePath().toString();
			         JOptionPane.showMessageDialog(null, "File imported sucessfully");
			         
			     }
			   } catch (FileNotFoundException e) {
			     e.printStackTrace();
			   }
		  return path_file;
	}
	
	public void writeRuleToFile(String path_file) 
			  throws IOException {
					String fileName = path_file;
					Path path = Paths.get(fileName);
					byte[] bytes = Files.readAllBytes(path);
					List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
		
				String aux="";
				
			    BufferedWriter writer = new BufferedWriter(new FileWriter(path_file, false));
			    for(int i=0 ; i<allLines.size();i++) {
			    	 aux=aux+allLines.get(i).toString()+"\n";
			    	 System.out.println(aux);
			    }
			   writer.write(aux+box.getText() + "\n");
			   writer.close();
		
			}
	
	public void load(JTextArea area) throws IOException {
		String rules="";
		String path_file="";
			try {
			     JFileChooser chooser = new JFileChooser();
			     int retorno = chooser.showOpenDialog(null);
			     
			     if (retorno == JFileChooser.APPROVE_OPTION) {
			       FileReader reader = new FileReader(chooser.getSelectedFile());
			       path_file=  chooser.getSelectedFile().getAbsolutePath().toString();
			       rules=new String(Files.readAllBytes(Paths.get(path_file)));
			       JOptionPane.showMessageDialog(null, "Rules sucessfully imported");
			         
			     }
			   } catch (FileNotFoundException e) {
			     e.printStackTrace();
			   }
			area.setText(rules);
		 
	}
	
	
	public static void main(String [] args) {
		
		new gui();
	}

}

