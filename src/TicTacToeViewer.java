import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class TicTacToeViewer extends JFrame {
    // TODO: Complete this class
    public static final int WINDOW_WIDTH = 1000,
            WINDOW_HEIGHT = 800,
            X_PADDING = (int) (0.2 * WINDOW_WIDTH),
            TITLE_BAR = 22,
            Y_PADDING = (int) (0.2 * WINDOW_HEIGHT + TITLE_BAR),
            SIDE_LENGTH = (int) (0.2 * WINDOW_HEIGHT),
            AXIS_X = (int) (X_PADDING / 2.0),
            AXIS_Y = (int) (Y_PADDING / 2.0);

    private Image[] markers;
    private TicTacToe t;

    public TicTacToeViewer(TicTacToe t) {
        this.t = t;
        markers = new Image[2];
        markers[0] = new ImageIcon("Resources/O.png").getImage();
        markers[1] = new ImageIcon("Resources/X.png").getImage();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("TicTacToe");
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.RED);
        g.setFont(new Font("Serif", Font.ITALIC, 30));
        for (int i = 0; i < 3; i++) {
            g.drawString(String.valueOf(i), AXIS_X, AXIS_Y * (3 + i * 2));
            g.drawString(String.valueOf(i), (int) (AXIS_X * (2.5 + i * 2)), AXIS_Y);
        }
        String marker;
        for (Square[] arr : t.getBoard()) {
            for (Square s : arr) {
                s.draw(g);
                marker = s.getMarker();
                if (marker.equals(TicTacToe.O_MARKER)) {
                    g.drawImage(markers[0], X_PADDING + SIDE_LENGTH * s.getCol(),
                            Y_PADDING + SIDE_LENGTH * s.getRow(), SIDE_LENGTH, SIDE_LENGTH, this);
                } else if (marker.equals(TicTacToe.X_MARKER))
                    g.drawImage(markers[1], X_PADDING + SIDE_LENGTH * s.getCol(),
                            Y_PADDING + SIDE_LENGTH * s.getRow(), SIDE_LENGTH, SIDE_LENGTH, this);
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        if (!t.getWinner().equals(TicTacToe.BLANK))
            g.drawString(t.getWinner() + " Wins!", (int) (AXIS_X * 2.5), WINDOW_HEIGHT - AXIS_Y + TITLE_BAR);
        else if (t.checkTie()) {
            g.drawString("It's a Tie!", (int) (AXIS_X * 2.5), WINDOW_HEIGHT - AXIS_Y + TITLE_BAR);
        }
    }

}
