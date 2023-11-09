import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGame extends JFrame implements ActionListener {

    private JPanel jp = new JPanel();
    private JPanel northPanel = new JPanel();
    private ArrayList<JButton> numbers = new ArrayList<>();
    private JButton emptyTile = new JButton("");
    private JButton newGame = new JButton("New Game");

    public PuzzleGame() {
        for (int i = 1; i <= 15; i++) {
            JButton jb = new JButton(String.valueOf(i));
            jb.setFont(new Font("Sans-serif", Font.BOLD, 24));
            jb.addActionListener(this);
            numbers.add(jb);
            jp.add(jb);
        }

        add(jp);
        numbers.add(emptyTile);
        northPanel.add(newGame);
        this.add(northPanel, BorderLayout.NORTH);
        newGame.addActionListener(this);

        jp.setBackground(Color.white);
        emptyTile.setBackground(Color.white);
        newGame.setBackground(Color.CYAN);
        newGame.setFont(new Font("Sans-serif", Font.BOLD, 14));
        northPanel.setBackground(Color.black);

        jp.setLayout(new GridLayout(4, 4));
        setTitle("15 Puzzle");
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private boolean validMove(int clickedIndex, int emptyIndex) {
        int diff = Math.abs(clickedIndex - emptyIndex);
        return (diff == 1 || diff == 4);
    }

    private void updatePanel() {
        jp.removeAll();
        for (JButton button : numbers) {
            jp.add(button);
        }
        jp.revalidate();
    }

    private void shufflePuzzle() {
        Collections.shuffle(numbers);
        updatePanel();
    }

    private boolean solvedPuzzle() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (!numbers.get(i).getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            jp.removeAll();
            shufflePuzzle();
        } else {
            JButton clickedButton = (JButton) e.getSource();
            if (numbers.contains(clickedButton) && numbers.contains(emptyTile)) {
                int buttonIndex = numbers.indexOf(clickedButton);
                int emptyIndex = numbers.indexOf(emptyTile);
                if (validMove(buttonIndex, emptyIndex)) {
                    Collections.swap(numbers, buttonIndex, emptyIndex);
                    updatePanel();
                    if (solvedPuzzle()) {
                        JOptionPane.showMessageDialog(this, "Congratulations, you won!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PuzzleGame pg = new PuzzleGame();
    }
}
