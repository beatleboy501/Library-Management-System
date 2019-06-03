package com.library.ui;

import com.library.dataaccess.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by beatleboy501 on 8/10/2015.
 */
public class MainMenu extends MenuBase implements ActionListener {
    private MainMenu() {
        menuLabel.setText("Main Menu");
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(searchBoxLabel);
        searchBox.setBounds(10, 35, 150, 20);
        bottomPanel.add(searchBox);
        bottomPanel.add(inLabel);
        populateDropdown();
        bottomPanel.add(searchDropdownBox);
        bottomPanel.add(searchByLabel);
        bottomPanel.add(searchByDropdownBox);
        bottomPanel.add(searchButton);
        bottomPanel.add(searchResultPanel);
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        getContentPane().add(addItemButton, BorderLayout.SOUTH);
        searchButton.addActionListener(this);
        addItemButton.addActionListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void populateSearchBy() {
        try {
            ArrayList<String> facets = new ArrayList<>();
            facets.add("Title");
            facets.add("id");
            for (String facet : facets) searchByDropdownBox.addItem(facet);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: could not populate the list of search criteria", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("ERROR: could not populate the list of search criteria");
            e.printStackTrace();
        }
    }

    private void populateDropdown() {
        data = new DataAccess();
        try {
            ArrayList<String> items = data.getLibraryItems();
            for (String item : items) searchDropdownBox.addItem(item);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: could not populate the list of items", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("ERROR: could not populate the list of items");
            e.printStackTrace();
            System.exit(1);
        } finally {
            data = null;
        }
    }

    public static void main(String[] args) throws Exception {
        runMainMenu();
    }

    private static void runMainMenu() {
        MainMenu myApplication = new MainMenu();
        myApplication.setTitle("Library Management System");
        // Specify where will it appear on the screen:
        myApplication.setLocation(10, 10);
        myApplication.setSize(500, 500);
        // Show it!
        myApplication.setVisible(true);
        myApplication.populateSearchBy();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String getSelection = Objects.requireNonNull(searchDropdownBox.getSelectedItem()).toString();
            String facet = Objects.requireNonNull(searchByDropdownBox.getSelectedItem()).toString();
            String facetValue = searchBox.getText();
            searchButton.removeActionListener(this);
            runSelectedMenu(getSelection, facet, facetValue);
        } else if (e.getSource() == back) {
            reRunMainMenu();
        } else if (e.getSource() == addItemButton){
            addItemButton.removeActionListener(this);
            showAddNewLibraryItemDialog();
        }
    }
}
