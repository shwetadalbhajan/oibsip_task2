import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame {
    static JFrame f;
    static JPanel j;
    static JLabel title;
    static JLabel l1;
    static JTextField input;
    static JButton guess;
    static JTextArea result;
    private int randomNumber;
    private JLabel scorepoints;
    private JLabel instructions;
    private int maxAttempts = 10;
    private int attempts;
    private int score;
    private int rounds = 3;
    private int currentRound = 1;
    public Main() {
        f=new JFrame("Number Guessing Game");
        f.setSize(800,900);

        j=new JPanel();
        j.setLayout(null);
        j.setBackground(Color.decode("#7C7EAE"));

        title=new JLabel();
        title.setText("Number Guessing Game");
        title.setFont(new Font("Brush Script MT",Font.BOLD,36));
        title.setBounds(250,0,300,45);
        title.setPreferredSize(title.getPreferredSize());
        title.setForeground(Color.decode("#191A30"));
        title.setOpaque(false);

        l1=new JLabel();
        l1.setText("Guess the number (1-100): ");
        l1.setFont(new Font("Century",Font.BOLD|Font.ITALIC,20));
        l1.setBounds(4,50,300,40);
        l1.setPreferredSize(l1.getPreferredSize());
        l1.setForeground(Color.decode("#FFFFFF"));
        l1.setOpaque(false);

        input=new JTextField();
        input.setBounds(275,55,120,30);
        input.setPreferredSize(input.getPreferredSize());
        input.setFont(new Font("Century",Font.BOLD,20));
        input.setBackground(Color.decode("#D3CEEB"));

        guess=new JButton();
        guess.setBounds(10,100,100,40);
        guess.setText("Guess");
        guess.setFont(new Font("Century",Font.BOLD,20));
        guess.setPreferredSize(guess.getPreferredSize());
        guess.setBackground(Color.decode("#191A30"));
        guess.setForeground(Color.WHITE);
        guess.setBorder(BorderFactory.createLineBorder(Color.white));

        result=new JTextArea();
        result.setBounds(10,180,500,600);
        result.setPreferredSize(result.getPreferredSize());
        result.setFont(new Font("Century",Font.BOLD,16));
        result.setForeground(Color.decode("#191A30"));
        result.setBackground(Color.decode("#7C7EAE"));

        scorepoints=new JLabel();
        scorepoints.setBounds(600,50,100,40);
        scorepoints.setPreferredSize(scorepoints.getPreferredSize());
        scorepoints.setFont(new Font("Times New Roman",Font.BOLD,25));
        scorepoints.setForeground(Color.decode("#191A30"));
        scorepoints.setBackground(Color.decode("#7C7EAE"));
        scorepoints.setText("Score: ");

        instructions=new JLabel();
        instructions.setBounds(600,80,200,40);
        instructions.setPreferredSize(instructions.getPreferredSize());
        instructions.setFont(new Font("Century",Font.BOLD,25));
        instructions.setForeground(Color.decode("#FFFFFF"));
        instructions.setBackground(Color.decode("#7C7EAE"));
        instructions.setText("Round 1/3");

        j.add(title);
        j.add(l1);
        j.add(input);
        j.add(guess);
        j.add(result);
        j.add(scorepoints);
        j.add(instructions);

        f.add(j);
        f.setVisible(true);

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

    }
    private void checkGuess() {
        try {
            int guessnum = Integer.parseInt(input.getText());
            attempts++;

            if (guessnum < randomNumber) {
                result.append("Attempt #" + attempts + ": Your guess is too low.\n");
            } else if (guessnum > randomNumber) {
                result.append("Attempt #" + attempts + ": Your guess is too high.\n");
            } else {
                result.append("Congratulations! You guessed the number " + randomNumber + " in " + attempts + " attempts.\n\n");
                score += (maxAttempts - attempts) * 10; // Calculate score based on attempts
                scorepoints.setText("Score: " + score);

                if (currentRound < rounds) {
                    currentRound++;
                    startNewRound();
                } else {
                    guess.setEnabled(false);
                    result.append("Game Over. Your Total Score: " + score + "\n\n");
                }
            }
            if (attempts >= maxAttempts) {
                result.append("Out of attempts. The number was " + randomNumber + ".\n\n");

                if (currentRound < rounds) {
                    currentRound++;
                    startNewRound();
                } else {
                    guess.setEnabled(false);
                    result.append("Game Over. Your Total Score: " + score + "\n\n");
                }
            }
            input.setText("");
        } catch (NumberFormatException ex) {
            result.setText("Invalid input. Enter a number.");
        }
    }
    private void startNewRound() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        instructions.setText("Round " + currentRound + "/" + rounds);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
