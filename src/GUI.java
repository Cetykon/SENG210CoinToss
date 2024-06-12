import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel tossResultLabel; // Keep a reference to the label
    private JTextField txtTossNumber;
    private JTextField txtHeadCount;
    private JTextField txtTailsCount;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {
    	setTitle("Coin Tosser");
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Label to prompt user to choose a coin
        JLabel lblNewLabel = new JLabel("Choose Coin:");
        lblNewLabel.setBounds(21, 29, 103, 13);
        contentPane.add(lblNewLabel);
        
        // Create radio buttons for each coin type
        JRadioButton rdbtnQuarter = new JRadioButton("Quarter");
        rdbtnQuarter.setBounds(21, 71, 103, 21);
        contentPane.add(rdbtnQuarter);

        JRadioButton rdbtnNickel = new JRadioButton("Nickel");
        rdbtnNickel.setBounds(21, 163, 103, 21);
        contentPane.add(rdbtnNickel);

        JRadioButton rdbtnPenny = new JRadioButton("Penny");
        rdbtnPenny.setBounds(21, 347, 103, 21);
        contentPane.add(rdbtnPenny);

        JRadioButton rdbtnDime = new JRadioButton("Dime");
        rdbtnDime.setBounds(21, 255, 103, 21);
        contentPane.add(rdbtnDime);

        // Add radio buttons to a button group so only one can be selected at a time
        ButtonGroup coinGroup = new ButtonGroup();
        coinGroup.add(rdbtnQuarter);
        coinGroup.add(rdbtnNickel);
        coinGroup.add(rdbtnPenny);
        coinGroup.add(rdbtnDime);
        
        // Initialize the Picture class to handle image display
        Picture picture = new Picture();
        
        // Label to display the result of the coin toss
        tossResultLabel = new JLabel(""); // Initialize the label
        int width = 369;
        int height = 344;
        tossResultLabel.setBounds(160, 29, width, height);
        contentPane.add(tossResultLabel);
        
        txtTossNumber = new JTextField();
        txtTossNumber.setText("1");
        txtTossNumber.setBounds(352, 381, 67, 21);
        contentPane.add(txtTossNumber);
        txtTossNumber.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Number of tosses:");
        lblNewLabel_1.setBounds(222, 386, 120, 13);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Results:");
        lblNewLabel_2.setBounds(592, 313, 45, 13);
        contentPane.add(lblNewLabel_2);
        
        txtHeadCount = new JTextField();
        txtHeadCount.setEditable(false);
        txtHeadCount.setBounds(564, 361, 96, 19);
        contentPane.add(txtHeadCount);
        txtHeadCount.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Heads");
        lblNewLabel_3.setBounds(564, 347, 45, 13);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("Tails");
        lblNewLabel_3_1.setBounds(564, 390, 45, 13);
        contentPane.add(lblNewLabel_3_1);
        
        txtTailsCount = new JTextField();
        txtTailsCount.setEditable(false);
        txtTailsCount.setColumns(10);
        txtTailsCount.setBounds(564, 404, 96, 19);
        contentPane.add(txtTailsCount);

        // Create and set up the "Toss" button
        JButton btnNewButton = new JButton("Toss");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                // Determine which coin is selected
            	String selectedCoin = null;
            	if (rdbtnQuarter.isSelected()) {
            		selectedCoin = "Quarter";
            	} else if (rdbtnNickel.isSelected()) {
            		selectedCoin = "Nickel";
            	} else if (rdbtnPenny.isSelected()) {
            		selectedCoin = "Penny";
            	} else if (rdbtnDime.isSelected()) {
            		selectedCoin = "Dime";
            	} else {
            		JOptionPane.showMessageDialog(null, "Pick a coin first");
            	}
            	
                // If a coin is selected, perform the toss
            	if (selectedCoin != null){
            		
            		CoinToss coinToss = new CoinToss();
            		int tossNumber = coinToss.getTossNumber(txtTossNumber.getText());
            		// Check if tossNumber is valid
                    if (tossNumber > 0) {
	            		for (int i = 0; i < tossNumber; i++) {
	            		
		            		String result = coinToss.Toss();
		                    JLabel newLabel = picture.showToss(selectedCoin, result, width, height);
		
		                    if (newLabel != null) {
		                        contentPane.remove(tossResultLabel); // Remove the old label
		                        tossResultLabel = newLabel; // Update the reference
		                        contentPane.add(tossResultLabel); // Add the new label
		                        contentPane.revalidate(); // Revalidate the content pane
		                        contentPane.repaint(); // Repaint the content pane
		                    }
	            		}
	            		txtHeadCount.setText(String.valueOf(coinToss.numberOfHeads));
	            		txtTailsCount.setText(String.valueOf(coinToss.numberOfTails));
                    }
            	}
            }
        });
        btnNewButton.setBounds(293, 412, 85, 21);
        contentPane.add(btnNewButton);
        
    }
}

class Picture {
    
    private final Map<String, String> coinSideToUrlMap;

    public Picture() {
        // Initialize the map with coin side and corresponding relative image paths
        coinSideToUrlMap = new HashMap<>();
        coinSideToUrlMap.put("Quarter Heads", "img/QuarterHeads.jpg");
        coinSideToUrlMap.put("Quarter Tails", "img/QuarterTails.jpg");
        coinSideToUrlMap.put("Nickel Heads", "img/NickelHeads.jpg");
        coinSideToUrlMap.put("Nickel Tails", "img/NickelTails.jpg");
        coinSideToUrlMap.put("Penny Heads", "img/PennyHeads.jpg");
        coinSideToUrlMap.put("Penny Tails", "img/PennyTails.jpg");
        coinSideToUrlMap.put("Dime Heads", "img/DimeHeads.jpg");
        coinSideToUrlMap.put("Dime Tails", "img/DimeTails.jpg");
    }
    
    JLabel showToss(String coin, String side, int width, int height) {
        // Get the relative image path based on the coin and side
        String key = coin + " " + side;
        String URL = coinSideToUrlMap.get(key);
        ImageIcon coinIcon = new ImageIcon(URL);
        
        // Resize the image
        Image originalImage = coinIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set the resized image as the icon for the JLabel
        JLabel lblCoin = new JLabel("");
        lblCoin.setIcon(scaledIcon);
        lblCoin.setBounds(160, 29, width, height);
        
        return lblCoin;
    }
}

class CoinToss {
    
    public int numberOfHeads = 0;
    public int numberOfTails = 0;
    
    public String Toss() {
        // Simulate a coin toss
        Random rand = new Random();
        int randNumber = rand.nextInt(50);
        
        if ((randNumber % 2) == 0) {
            System.out.println("Heads");
            numberOfHeads++;
            return "Heads";
        } else {
            System.out.println("Tails");
            numberOfTails++;
            return "Tails";
        }
    }
    
    public int getTossNumber(String txtTossNumber) {
    	
    	 try {
             return Integer.parseInt(txtTossNumber);
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Please enter a integer for the number of tosses.");
             return -1;  // Return -1 to indicate an error
         }
    }
}
