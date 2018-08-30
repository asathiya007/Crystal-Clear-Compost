// An Akshay Sathiya Production

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

public class MainScreen extends JPanel {
	private JTextField dayTextField;
	private JTextField totalMassTextField;
	private JScrollPane scrollPane;
	private int numericDay = 1;
	private double totalMass = 0.0;
	private String[] freqAskQuests = {
			"What materials are allowed in my compost?",
			"What are some examples of acceptable paper products?",
			"What are some examples of acceptable soiled bedding?",
			"What are some examples of acceptable garden remains?",
			"What are some examples of acceptable woody materials?",
			"What are some examples of acceptable additives?",
			"What are some examples of acceptable food remains?",
			"What is in paper products?",
			"What is in livestock manure?",
			"What is in soiled bedding?",
			"What is in straw/hay?",
			"What is in straw?",
			"What is in hay?",
			"What is in garden remains?",
			"What is in weeds?",
			"What is in woody material?",
			"What is in leaves?",
			"What is in grass clippings?",
			"What is in additives?",
			"What is in food remains?",
			"What if my compost smells like ammonia?",
			"What if nothing is happening in my compost?",
			"What if my compost's temperature is high?",
			"What if my compost's temperature is low?",
			"What if there are many pests/animals near my compost?",
			"What if my compost smells rotten?"
	};
	private String[] responseFAQs = {
			"Paper products, livestock manure, soiled bedding, straw/hay, garden remains, weeds, woody material, leaves, grass clippings, additives, and food remains.",
			"Some acceptable paper products would be newspapers, napkins, and tea bags.",
			"Some acceptable forms of soiled bedding would be wood chips, manure, and poultry stock.",
			"Some acceptable forms of acceptable garden remains would be flowers and vegetative remains.",
			"Some acceptable forms of acceptable woody materials would be brush, twigs, shavings, and other residuals.",
			"Some acceptable forms of acceptable additives would be compost starters, inoculants, soil.",
			"Some acceptable forms of food remains would be fruits, vegetables, and egg shells.",
			"Paper products contain relatively high levels of carbon.",
			"Livestock manure contains relatively high levels of carbon and nitrogen.",
			"Soiled bedding contains relatively high levels of carbon and nitrogen.",
			"Straw/hay contains relatively high levels of carbon.",
			"Straw contains relatively high levels of carbon.",
			"Hay contains relatively high levels of carbon.",
			"Garden remains contain relatively high levels of carbon and nitrogen.",
			"Weeds contain relatively high levels of carbon and nitrogen.",
			"Woody material contain relatively high levels of carbon.",
			"Leaves contain relatively high levels of carbon.",
			"Grass clippings contain relatively high levels of nitrogen.",
			"Additives contain relatively high levels of nitrogen.",
			"Food remains products contain relatively high levels of nitrogen.",
			"Mix in brown material into your compost to balance the nitrogen.",
			"Use different materials or use additives in order to stimulate composting.",
			"Decrease nitrogen levels by using organic material or move your compost to a cooler location.",
			"Increase nitrogen levels by using additives or move your compost to a warmer location.",
			"Use less food remains and use Integrated Pest Management techniques.",
			"Use less food remains and use more dry material/components in your compost."
	};
	private int numFAQResponses = responseFAQs.length;

	/**
	 * Create the panel.
	 */
	public MainScreen() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.RED);
		add(topPanel);
		
		JLabel lblNotifications = new JLabel("User Input:");
		topPanel.add(lblNotifications);
		
		JButton btnInput = new JButton("INPUT");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new FileWriter("SmartCompostDatabase.txt", true), true);
				} catch(Exception ex) {
					//ex.printStackTrace();
				}
				String itemName = JOptionPane.showInputDialog("Please enter an item name");
				String amountKG = JOptionPane.showInputDialog("Please enter the numerical mass of this item (in kg)");
				if((itemName != "" && itemName != null) && (amountKG != "" && amountKG != null))
					pw.println(itemName + "\n" + amountKG + " kg\nDay " + numericDay + "\n ");
				pw.close();
				PrintWriter pw2 =  null; 
				totalMass += Double.parseDouble(amountKG);
				File file = new File("TotalMass.txt");
				if(file.exists()) {
					file.delete();
				}
				try {
					pw2 = new PrintWriter(new FileWriter("TotalMass.txt", true), true);
				} catch(Exception ex) {
					//ex.printStackTrace();
				}
				pw2.println("" + totalMass);
				pw2.close();
				DecimalFormat df = new DecimalFormat("#.###");
				totalMassTextField.setText("" + df.format(totalMass) + " kg");
			}
		});
		topPanel.add(btnInput);
		
		JLabel lblIncrementDay = new JLabel("Increment Day:");
		topPanel.add(lblIncrementDay);
		
		JButton btnNextDay = new JButton("NEXT DAY");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numericDay++;
				dayTextField.setText("" + numericDay);
				PrintWriter pw2 =  null; 
				File file = new File("Day.txt");
				if(file.exists()) {
					file.delete();
				}
				try {
					pw2 = new PrintWriter(new FileWriter("Day.txt", true), true);
				} catch(Exception ex) {
					//ex.printStackTrace();
				}
				pw2.println("" + numericDay);
				pw2.close();
			}
		});
		topPanel.add(btnNextDay);
		
		JLabel lblNewLabel = new JLabel("Recommendations:");
		topPanel.add(lblNewLabel);
		
		JButton btnFaq = new JButton("FAQ");
		btnFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String faq = "";
				faq = JOptionPane.showInputDialog("Enter your question: ");
				int i = 0;
				for(i = 0; i < numFAQResponses; i++) {
					if(freqAskQuests[i].equalsIgnoreCase(faq.trim()))
						break;
				}
				if(i == numFAQResponses)
					JOptionPane.showMessageDialog(null, "Answer not found.");
				else
					JOptionPane.showMessageDialog(null, responseFAQs[i]);
			}
		});
		topPanel.add(btnFaq);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.GREEN);
		add(bottomPanel);
		
		JLabel lblDay = new JLabel("Day: ");
		bottomPanel.add(lblDay);
		lblDay.setHorizontalAlignment(SwingConstants.LEFT);
		
		dayTextField = new JTextField();
		dayTextField.setEditable(false);
		bottomPanel.add(dayTextField);
		dayTextField.setColumns(10);
		dayTextField.setText("" + numericDay);
		
		JLabel lblTotalMassOf = new JLabel("Total Mass of Compost:");
		bottomPanel.add(lblTotalMassOf);
		
		totalMassTextField = new JTextField();
		totalMassTextField.setEditable(false);
		bottomPanel.add(totalMassTextField);
		totalMassTextField.setColumns(10);
		
		JPanel midPanel = new JPanel();
		midPanel.setBackground(Color.YELLOW);
		add(midPanel);
		
		JLabel lblOpenDatabaseflat = new JLabel("Open Database (Flat File):");
		midPanel.add(lblOpenDatabaseflat);
		
		JLabel lblSeeViewOn = new JLabel("            See Display ->            ");
		midPanel.add(lblSeeViewOn);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setRows(8);
		textArea.setColumns(15);
		panel.add(textArea);
		
		scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane);
		
		JButton btnDatabase = new JButton("DATABASE");
		btnDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the file and print line by line
				textArea.setText(null);
				try {
					File file = new File("SmartCompostDatabase.txt");
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					StringBuffer stringBuffer = new StringBuffer();
					String line;
					while((line = bufferedReader.readLine()) != null) {
						stringBuffer.append(line);
						stringBuffer.append("\n");
					}
					fileReader.close();
					textArea.append(stringBuffer.toString());
				} catch (Exception ex) {
					//ex.printStackTrace();
				}
			}
		});
		midPanel.add(btnDatabase);
		
		JButton btnClear = new JButton("CLEAR DATABASE");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					// Reset Day
					{
						numericDay = 1;
						dayTextField.setText("" + numericDay);
						PrintWriter pw2 =  null; 
						File file = new File("Day.txt");
						if(file.exists()) {
							file.delete();
						}
						try {
							pw2 = new PrintWriter(new FileWriter("Day.txt", true), true);
						} catch(Exception ex) {
							//ex.printStackTrace();
						}
						pw2.println("" + numericDay);
						pw2.close();
					}
					// Reset Total Mass
					{
						PrintWriter pw2 =  null; 
						totalMass = 0;
						File file = new File("TotalMass.txt");
						if(file.exists()) {
							file.delete();
						}
						try {
							pw2 = new PrintWriter(new FileWriter("TotalMass.txt", true), true);
						} catch(Exception ex) {
							//ex.printStackTrace();
						}
						DecimalFormat df = new DecimalFormat("#.###");
						pw2.println("" + df.format(totalMass));
						pw2.close();
						totalMassTextField.setText("" + df.format(totalMass) + " kg");
					}
					// Clear Database
					{
						PrintWriter pw2 =  null; 
						File file = new File("SmartCompostDatabase.txt");
						if(file.exists()) {
							file.delete();
						}
						textArea.setText(null);
						try {
							FileReader fileReader = new FileReader(file);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							StringBuffer stringBuffer = new StringBuffer();
							String line;
							while((line = bufferedReader.readLine()) != null) {
								stringBuffer.append(line);
								stringBuffer.append("\n");
							}
							fileReader.close();
							textArea.append(stringBuffer.toString());
						} catch (Exception ex) {
							//ex.printStackTrace();
						}
						pw2.close();
					}
				} catch (Exception ex){
					//ex.printStackTrace();
				}
			}
		});
		midPanel.add(btnClear);
		
		try {
			File file2 = new File("Day.txt");
			FileReader fileReader2 = new FileReader(file2);
			BufferedReader bufferedReader = new BufferedReader(fileReader2);
			String line2;
			if((line2 = bufferedReader.readLine()) != null) {
				numericDay = Integer.parseInt(line2);
				dayTextField.setText("" + numericDay);
			}
			fileReader2.close();
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		
		try {
			File file3 = new File("TotalMass.txt");
			FileReader fileReader3 = new FileReader(file3);
			BufferedReader bufferedReader2 = new BufferedReader(fileReader3);
			String line3;
			if((line3 = bufferedReader2.readLine()) != null) {
				totalMass = Double.parseDouble(line3);
				DecimalFormat df = new DecimalFormat("#.###");
				totalMassTextField.setText("" + df.format(totalMass) + " kg");
			}
			fileReader3.close();
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		
	}

}