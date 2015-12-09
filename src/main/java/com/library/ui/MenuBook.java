package com.library.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by allan06 on 8/13/2015.
 */
public class MenuBook extends MenuBase implements ActionListener {

    public static final String ITEM = "books";

    public MenuBook() {
        menuLabel.setText("Books");

        bottomPanel.setLayout(new FlowLayout());
        JTable resultTable = table("books");
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
        bottomPanel.add(scrollPane);
        bottomPanel.add(back);
        holdAll.setLayout(new BorderLayout());
        holdAll.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(holdAll, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        back.addActionListener(this);

        bottomPanel.revalidate();
        bottomPanel.repaint();
        holdAll.revalidate();
        holdAll.repaint();
        validate();
    }

    public static void runMenu() {
        MenuBook mb = new MenuBook();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
