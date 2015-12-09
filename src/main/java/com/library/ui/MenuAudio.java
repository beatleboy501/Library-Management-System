package com.library.ui;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by allan06 on 8/11/2015.
 */
public class MenuAudio extends MenuBase implements ActionListener {

    public static final String ITEM = "audio";
    JTable resultTable;
    JScrollPane scrollPane;

    public MenuAudio() {
        removeCurrentWindow();
        menuLabel.setText(ITEM.toUpperCase());
        bottomPanel.setLayout(new FlowLayout());
        JTable resultTable = table(ITEM);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
        bottomPanel.add(scrollPane);
        bottomPanel.add(back);
        holdAll.setLayout(new BorderLayout());
        holdAll.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(holdAll, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);

        bottomPanel.revalidate();
        bottomPanel.repaint();
        holdAll.revalidate();
        holdAll.repaint();
        validate();
        back.addActionListener(this);
    }

    public static void runMenu() {
        MenuAudio ma = new MenuAudio();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == back)
        {
            System.exit(0);
//            back.removeActionListener(this);
//            getContentPane().removeAll();
//            holdAll.removeAll();
//            bottomPanel.removeAll();
//            scrollPane.removeAll();
//            resultTable.removeAll();
//            MainMenu mm = new MainMenu();
        }
    }
}
