import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel tossResultLabel; // Keep a reference to the label

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        Picture picture = new Picture();
        
        tossResultLabel = new JLabel(""); // Initialize the label
        int width = 369;
        int height = 344;
        tossResultLabel.setBounds(160, 29, width, height);
        contentPane.add(tossResultLabel);

        JButton btnNewButton = new JButton("Toss");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String result = coinToss.Toss();
                JLabel newLabel = picture.showToss("Quarter", result, width, height);

                if (newLabel != null) {
                    contentPane.remove(tossResultLabel); // Remove the old label
                    tossResultLabel = newLabel; // Update the reference
                    contentPane.add(tossResultLabel); // Add the new label
                    contentPane.revalidate(); // Revalidate the content pane
                    contentPane.repaint(); // Repaint the content pane
                }
            }
        });
        btnNewButton.setBounds(292, 378, 85, 21);
        contentPane.add(btnNewButton);
    }
}

class Picture {
    
    String urlQuarterHeads = "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\QuarterHeads.jpg";
    String urlQuarterTails = "C:\\Users\\jesus\\eclipse-workspace\\SENG210\\CoinTossSimulator\\img\\QuarterTails.jpg";
    
    JLabel showToss(String coin, String side, int width, int height) {
        
        ImageIcon coinIcon = null;
        
        switch (coin + " " + side) {
            case "Quarter Heads":
                coinIcon = new ImageIcon(urlQuarterHeads);
                break;
            case "Quarter Tails":
                coinIcon = new ImageIcon(urlQuarterTails);
                break;
            default:
                System.out.println("Invalid coin or side");
                return null;
        }
        
        Image originalImage = coinIcon.getImage();

        // Resize the image
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
