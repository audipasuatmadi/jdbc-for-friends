package com.tugas.ppbo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setConfig();

        JFrame frame = new JFrame("DB Application");
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        JTextField textField = new JTextField();

        JLabel label = new JLabel("data label");

        JButton button = new JButton();
        button.setText("add data");

        panel.add(label);
        panel.add(textField);
        panel.add(button);



        try {
            String sql = "SELECT * FROM labels";
            ResultSet resultSet = databaseConfig.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                String labelString = resultSet.getString("label");
                panel.add(new JLabel(labelString));
            }
        } catch (Exception exception) {
            System.out.println("Error at get all");
            System.out.println(exception);
        }

        frame.add(panel);
        frame.setSize(800, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = textField.getText();

                try {
                    String sql = "INSERT INTO labels (label) VALUES ('" + inputValue + "')";
                    databaseConfig.getStatement().execute(sql);
                } catch (Exception exception) {
                    System.out.println("Error at inserting");
                    System.out.println(exception);
                }

                panel.add(new JLabel(inputValue));
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        });
    }
}
