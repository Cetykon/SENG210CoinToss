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

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel tossResultLabel; // Keep a reference to the label

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
            }
        });
        btnNewButton.setBounds(292, 378, 85, 21);
        contentPane.add(btnNewButton);
    }
}

class Picture {
    
	private final Map<String, String> coinSideToUrlMap;

    public Picture() {
        // Initialize the map with coin side and corresponding image URLs
        coinSideToUrlMap = new HashMap<>();
        coinSideToUrlMap.put("Quarter Heads", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\QuarterHeads.jpg");
        coinSideToUrlMap.put("Quarter Tails", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\QuarterTails.jpg");
        coinSideToUrlMap.put("Nickel Heads", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\NickelHeads.jpg");
        coinSideToUrlMap.put("Nickel Tails", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\NickelTails.jpg");
        coinSideToUrlMap.put("Penny Heads", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\PennyHeads.jpg");
        coinSideToUrlMap.put("Penny Tails", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\PennyTails.jpg");
        coinSideToUrlMap.put("Dime Heads", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\DimeHeads.jpg");
        coinSideToUrlMap.put("Dime Tails", "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\DimeTails.jpg");
    }
    
    JLabel showToss(String coin, String side, int width, int height) {
        // Get the image URL based on the coin and side
        String key = coin + " " + side;
        String url = coinSideToUrlMap.get(key);
        ImageIcon coinIcon = new ImageIcon(url);
        
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

class coinToss {
    
    public static int numberOfHeads = 0;
    public static int numberOfTails = 0;
    
    public static String Toss() {
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
}
