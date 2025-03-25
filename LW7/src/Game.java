import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {
    private final JFrame frame;
    private final Random random;
    public Game() {
        frame = new JFrame("Игра 'Больше-Меньше'");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        random = new Random();

        JPanel rangePanel = createRangePanel();
        frame.add(rangePanel, BorderLayout.NORTH);

        JPanel gamePanel = createGamePanel();
        frame.add(gamePanel, BorderLayout.CENTER);

        JPanel plafPanel = createPlafPanel();
        frame.add(plafPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JTextField minField;
    private JTextField maxField;
    private JPanel createRangePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel minLabel = new JLabel("Минимальное значение:");
        minField = new JTextField("1", 10);

        JLabel maxLabel = new JLabel("Максимальное значение:");
        maxField = new JTextField("100", 10);

        JButton startButton = new JButton("Начать игру");
        startButton.addActionListener(e -> startGame());

        JPanel minPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        minPanel.add(minLabel);
        minPanel.add(minField);

        JPanel maxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maxPanel.add(maxLabel);
        maxPanel.add(maxField);

        panel.add(minPanel);
        panel.add(maxPanel);
        panel.add(startButton);

        return panel;
    }

    private JButton higherButton;
    private JButton lowerButton;
    private JButton correctButton;
    private JLabel guessLabel;
    private JPanel createGamePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        guessLabel = new JLabel("Загадайте число и нажмите 'Начать игру'", SwingConstants.CENTER);
        guessLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(guessLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        higherButton = new JButton("Больше");
        higherButton.setEnabled(false);
        higherButton.addActionListener(e -> onHigher());

        lowerButton = new JButton("Меньше");
        lowerButton.setEnabled(false);
        lowerButton.addActionListener(e -> onLower());

        correctButton = new JButton("Верно!");
        correctButton.setEnabled(false);
        correctButton.addActionListener(e -> onCorrect());

        buttonPanel.add(higherButton);
        buttonPanel.add(lowerButton);
        buttonPanel.add(correctButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPlafPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        ButtonGroup plafGroup = new ButtonGroup();

        String[] plafNames = {
                "Metal",
                "Nimbus",
                "Windows"
        };

        ActionListener plafListener = e -> {
            try {
                // Сохраняем текущий размер окна
                Dimension currentSize = frame.getSize();

                // Применяем новый стиль
                String selectedPlaf = e.getActionCommand();
                UIManager.LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
                for (UIManager.LookAndFeelInfo info : installed) {
                    if (info.getName().equals(selectedPlaf)) {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                        break;
                    }
                }
                frame.setSize(currentSize);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        };

        for (String plafName : plafNames) {
            JRadioButton radioButton = new JRadioButton(plafName);
            radioButton.setActionCommand(plafName);
            radioButton.addActionListener(plafListener);
            plafGroup.add(radioButton);
            panel.add(radioButton);
        }
        return panel;
    }

    private int min;
    private int max;
    private int currentGuess;
    private void startGame() {
        try {
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());

            if (min >= max) {
                JOptionPane.showMessageDialog(frame, "Минимальное значение должно быть меньше максимального!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            currentGuess = getRandomNumber(min, max);
            guessLabel.setText("Я думаю, это число: " + currentGuess);
            higherButton.setEnabled(true);
            lowerButton.setEnabled(true);
            correctButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Введите корректные числа!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private void onHigher() {
        min = currentGuess + 1;
        makeGuess();
    }

    private void onLower() {
        max = currentGuess - 1;
        makeGuess();
    }

    private void onCorrect() {
        guessLabel.setText("Ура! Я угадал число: " + currentGuess);
        higherButton.setEnabled(false);
        lowerButton.setEnabled(false);
        correctButton.setEnabled(false);
        showPlayAgainDialog("Победа! Хотите сыграть еще раз?");
    }

    private void makeGuess() {
        if (min > max) {
            guessLabel.setText("Вы жульничаете! Такого числа не существует.");
            higherButton.setEnabled(false);
            lowerButton.setEnabled(false);
            correctButton.setEnabled(false);
            showPlayAgainDialog("Обнаружено жульничество! Хотите сыграть еще раз?");
            return;
        }

        currentGuess = getRandomNumber(min, max);
        guessLabel.setText("Я думаю, это число: " + currentGuess);
    }

    private void showPlayAgainDialog(String message) {
        int result = JOptionPane.showConfirmDialog(frame, message, "Игра окончена", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        guessLabel.setText("Загадайте число и нажмите 'Начать игру'");
        higherButton.setEnabled(false);
        lowerButton.setEnabled(false);
        correctButton.setEnabled(false);
        minField.setText("1");
        maxField.setText("100");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}