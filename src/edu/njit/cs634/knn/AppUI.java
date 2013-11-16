package edu.njit.cs634.knn;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class AppUI {

	private JFrame frame;
	private JTextField txtSupport;
	private JTextField txtConfidence;
	private JComboBox cBoxSetSelection;
	private float support;
	private Clustering clustering;
	private JTextPane tpDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppUI window = new AppUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		
		JLabel support = new JLabel("Support");
		panel_1.add(support);
		
		txtSupport = new JTextField();
		txtSupport.setHorizontalAlignment(SwingConstants.LEFT);
		txtSupport.setColumns(10);
		panel_1.add(txtSupport);
		
		JLabel confidence = new JLabel("Confidence");
		panel_1.add(confidence);
		
		txtConfidence = new JTextField();
		txtConfidence.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfidence.setColumns(10);
		panel_1.add(txtConfidence);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel coachClassServiceTIme = new JLabel("Data Set Selection");
		panel_2.add(coachClassServiceTIme);
		
		cBoxSetSelection = new JComboBox();
		cBoxSetSelection.setModel(new DefaultComboBoxModel(new String[] {"Sample1", "Sample2", "Sample3", "Sample4", "Sample5"}));
		panel_2.add(cBoxSetSelection);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton bttnRun = new JButton("Run");
		panel_4.add(bttnRun);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tpDisplay = new JTextPane();
		scrollPane.setViewportView(tpDisplay);
		bttnRun.addActionListener(new BttnHandler());
		
	}
	
	/**
	 * Handles the actions performed once the "Run" button is pressed
	 */
	protected class BttnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			tpDisplay.setText("");
			//Validate that support is good
			if(validateForm())
			{
				clustering = new Clustering();
				if(fillApriori())
				{
					tpDisplay.setText("Running...");
					clustering.run();
					String[] items = apriori.getCommonItems();
					tpDisplay.setText("");
					Document doc = tpDisplay.getDocument();
					//Print common items
					for(int i=0; i<items.length; i++)
					{
						try {
							doc.insertString(doc.getLength(), items[i] + "\n", null);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
		}
		
		/**
		 * Validates the values entered into the window.  Messages are printed to the 
		 * window if invalid values are provided.  If there are no invalid values then
		 * the value "true" is returned.
		 * @return boolean - if the form is valid or not
		 */
		public boolean validateForm()
		{
			boolean valid = true;
			
			if(txtSupport.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for Support\n");
			}
			else if(txtConfidence.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for Confidence\n");
			}
			
			return valid;
		}
		
		/**
		 * This method fills the Config object with the values supplied by the user.
		 * If any values are not valid a message is printed in the application window.
		 * @return boolean - Whether the values are valid or not.
		 */
		public boolean fillApriori()
		{
			boolean valid = true;
			String file = (String) cBoxSetSelection.getSelectedItem();
			String filepath = "src/" + file + ".txt";
			try
			{
				apriori.setFile(filepath);
				apriori.setSupport(Float.valueOf(txtSupport.getText()));
				apriori.setConfidence(Float.valueOf(txtConfidence.getText()));
			}
			catch(Exception e)
			{
				valid = false;
				tpDisplay.setText("Invalid parameters given\n");
			}
			
			return valid;
		}
		
	}

}
