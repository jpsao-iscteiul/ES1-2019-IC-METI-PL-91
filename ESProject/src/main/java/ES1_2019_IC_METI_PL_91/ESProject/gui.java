package ES1_2019_IC_METI_PL_91.ESProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import iscte.iul.pt.defectDetention.File;
import iscte.iul.pt.defectDetention.Rule;

public class gui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox defects_list;
	private JComboBox metric_list;
	private JComboBox simbol_list;
	private JTextField value;
	private JTextField box;
	private JTextArea output;
	private Rule rule;
	private List<Rule> ruleList = new ArrayList<Rule>();
	private String file_path;
	private String defect;
	
	
	

	public gui() {
		
		JFrame frame = new JFrame("GUI"); 
		frame.setSize(1500, 600);
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
		JLabel defects = new JLabel("Defects");
		JLabel metrics = new JLabel("Metrics");
		JLabel simbols = new JLabel("Simbols");
		JLabel threshold = new JLabel("Threshold");

		defects_list = new JComboBox();

		defects_list.addItem("Long Method");
		defects_list.addItem("Feature Envy");

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


		value = new JTextField();
		JButton add = new JButton("Add");
		JButton clear = new JButton("Clear");

		west_panel.add(defects);
		west_panel.add(metrics);
		west_panel.add(simbols);
		west_panel.add(threshold);
		west_panel.add(defects_list);
		west_panel.add(metric_list);
		west_panel.add(simbol_list);
		west_panel.add(value);

		JPanel sub_panel = new JPanel();
		sub_panel.setLayout(new GridLayout(1,2));


		sub_panel.add(add);
		sub_panel.add(clear);
		west_panel.add(sub_panel);



		JPanel sub_west_panel = new JPanel();
		sub_west_panel.setLayout(new GridLayout(2,2));

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
				String selected_defects = getDefects();
				double selected_threshold=getThreshold();
				String str = (created_rule+" "+selected_metric + " " + selected_simbol+ " "+selected_threshold);
				box.setText(str);

				//Create a new Rule
				Rule newRule = new Rule(selected_metric, selected_simbol, selected_threshold, selected_defects);
				//Add new Rule to rule list
				ruleList.add(newRule);
				selected_metric = new String();
				selected_simbol = new String();
				selected_threshold = 0;
			}

		});

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				box.setText("");
				ruleList = new ArrayList<Rule>();
				file_path = new String();

			}

		});

		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				try {
					importFileHandler();
					
					File file = new File();
					if(file.isLongMethod(ruleList) || file.isFeatureEnvy(ruleList)) {
						String aux = file.readExcel(file_path, ruleList, getDefects());
						output.setText(aux);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
	
	
	public void importFileHandler() {
		if(file_path == null) {
			JOptionPane.showMessageDialog(null, "Please, import file.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	public JComboBox getMetric_list() {
		return metric_list;
	}
	public void setMetric_list(JComboBox metric_list) {
		this.metric_list = metric_list;
	}
	public JComboBox getSimbol_list() {
		return simbol_list;
	}
	public void setSimbol_list(JComboBox simbol_list) {
		this.simbol_list = simbol_list;
	}
	public JTextField getValue() {
		return value;
	}
	public void setValue(JTextField value) {
		this.value = value;
	}
	public JTextField getBox() {
		return box;
	}
	public void setBox(JTextField box) {
		this.box = box;
	}
	public JTextArea getOutput() {
		return output;
	}
	public void setOutput(JTextArea output) {
		this.output = output;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public JComboBox getDefects_list() {
		return defects_list;
	}
	public void setDefects_list(JComboBox defects_list) {
		this.defects_list = defects_list;
	}
	public List<Rule> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
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

	public String getDefects() {
		String aux = defects_list.getSelectedItem().toString();
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
				this.file_path = path_file;
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

