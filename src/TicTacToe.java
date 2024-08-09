import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 600;

    JFrame frame = new JFrame("Tic Tac Toe Project");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            checkWinner();

                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].getText().equals("") &&
                    board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][1].getText().equals(board[i][2].getText())) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (!board[0][j].getText().equals("") &&
                    board[0][j].getText().equals(board[1][j].getText()) &&
                    board[1][j].getText().equals(board[2][j].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }
        }

        if (!board[0][0].getText().equals("") &&
                board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            setWinner(board[0][0]);
            setWinner(board[1][1]);
            setWinner(board[2][2]);
            gameOver = true;
            return;
        }

        if (!board[0][2].getText().equals("") &&
                board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }


}
