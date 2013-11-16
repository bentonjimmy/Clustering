package edu.njit.cs634.knn;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

/**
 * This class is used to display the user interface for the application.
 * @author Jim Benton
 *
 */
public class AppUI {

	private JFrame frame;
	private JTextField txtp;
	private JTextField txtD;
	private double p, d;
	private int numOfPoints;
	private Clustering clustering;
	private JTextPane tpDisplay;
	private JTextField txtPoints;
	private JTextField txtClusters;

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
		
		JLabel plbl = new JLabel("p");
		panel_1.add(plbl);
		
		txtp = new JTextField();
		txtp.setHorizontalAlignment(SwingConstants.LEFT);
		txtp.setColumns(3);
		panel_1.add(txtp);
		
		JLabel dlbl = new JLabel("D");
		panel_1.add(dlbl);
		
		txtD = new JTextField();
		txtD.setHorizontalAlignment(SwingConstants.LEFT);
		txtD.setColumns(4);
		panel_1.add(txtD);
		
		JLabel pointslbl = new JLabel("Number Of Points");
		panel_1.add(pointslbl);
		
		txtPoints = new JTextField();
		panel_1.add(txtPoints);
		txtPoints.setColumns(5);
		
		JLabel NumOfClusterlbl = new JLabel("Number of Clusters");
		panel_1.add(NumOfClusterlbl);
		
		txtClusters = new JTextField();
		panel_1.add(txtClusters);
		txtClusters.setColumns(5);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
			//Validate that values are good
			if(validateForm())
			{
				clustering = new Clustering();
				if(fillApplication())
				{
					tpDisplay.setText("Running...");
					String status = clustering.run();
					tpDisplay.setText(status);
					
					Document doc = tpDisplay.getDocument();
					//Print results
					Vector[] points = clustering.getOutliers();
					if(points.length > 0)
					{
						try {
							doc.insertString(doc.getLength(), "\nThe " + points.length + " outliers found were: \n", null);
							for(int i=0; i<points.length; i++)
							{
								doc.insertString(doc.getLength(), points[i] +"\n", null);
							}
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						try {
							doc.insertString(doc.getLength(), "\nNo outliers were found.\n", null);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
					
					HashMap<String, Double> coefficients = clustering.getCoefficients();
					Set<String> keySet = coefficients.keySet();
					Iterator<String> iter = keySet.iterator();
					String bestClustering = null;
					double highestCoef = -1;
					
					try {
						doc.insertString(doc.getLength(), "\nThe Silhouette coefficients were:\n", null);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					while(iter.hasNext())
					{
						String name = iter.next();
						double c = coefficients.get(name);
						if(c > highestCoef)
						{
							highestCoef = c;
							bestClustering = name;
						}
						try {
							doc.insertString(doc.getLength(), name +": "+c + "\n", null);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
					
					try {
						doc.insertString(doc.getLength(), "The most accurate clustering was: " + bestClustering, null);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
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
			
			if(txtp.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for p\n");
			}
			else if(txtD.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for D\n");
			}
			else if(txtPoints.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("Number of Points has no value\n");
			}
			else if(txtClusters.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("Number of Clusters has no value\n");
			}
			
			return valid;
		}
		
		/**
		 * This method fills the Config object with the values supplied by the user.
		 * If any values are not valid a message is printed in the application window.
		 * @return boolean - Whether the values are valid or not.
		 */
		public boolean fillApplication()
		{
			boolean valid = true;
			try
			{
				clustering.setP(Float.valueOf(txtp.getText()));
				clustering.setD(Float.valueOf(txtD.getText()));
				clustering.setNumOfPoints(Integer.valueOf(txtPoints.getText()));
				clustering.setNumOfClusters(Integer.valueOf(txtClusters.getText()));
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
