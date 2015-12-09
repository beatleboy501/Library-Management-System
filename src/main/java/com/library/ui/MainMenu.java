package com.library.ui;

import com.library.server.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by allan06 on 8/10/2015.
 */
public class MainMenu extends MenuBase implements ActionListener
{
    public MainMenu()
    {
        menuLabel.setText("Main Menu");

        hyperLink.setText("<HTML>Click <FONT color=\"#000099\"><U>here</U></FONT>"
                + " to go to the LMS website.</HTML>");
        hyperLink.setHorizontalAlignment(SwingConstants.LEFT);
        hyperLink.setBorderPainted(false);
        hyperLink.setOpaque(false);
        hyperLink.setBackground(Color.WHITE);
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(searchBoxLabel);
        searchBox.setBounds(10, 35, 150, 20);
        bottomPanel.add(searchBox);
        bottomPanel.add(in);
        populateDropdown(searchDropdownBox);
        bottomPanel.add(searchDropdownBox);
        bottomPanel.add(searchByLabel);
        bottomPanel.add(searchByDropdownBox);
        bottomPanel.add(searchButton);
        bottomPanel.add(searchResultPanel);

        holdAll.setLayout(new BorderLayout());
        holdAll.add(bottomPanel, BorderLayout.CENTER);

        getContentPane().add(holdAll, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        getContentPane().add(hyperLink, BorderLayout.SOUTH);
        searchButton.addActionListener(this);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void populateSearchBy() {
        try{
            ArrayList<String> criteria = new ArrayList<String>();
            criteria.add("Title");
            criteria.add("Author");
            for(String s : criteria)
            {
                searchByDropdownBox.addItem(s);
            }
        }
        catch(Exception e)
        {
            JOptionPane popup = new JOptionPane();
            popup.showMessageDialog(null, "Error: could not populate the list of search criteria", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("ERROR: could not populate the list of search criteria");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param searchDropdownBox
     */
    public void populateDropdown(JComboBox searchDropdownBox) {
        data = new DataAccess();
        try{
            ArrayList<String> items = data.getLibraryItems();
            for(String s : items)
            {
                searchDropdownBox.addItem(s);
            }
        }
        catch(Exception e)
        {
            JOptionPane popup = new JOptionPane();
            popup.showMessageDialog(null, "Error: could not populate the list of items", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("ERROR: could not populate the list of items");
            e.printStackTrace();
            System.exit(1);
        }
        finally {
            data = null;
        }
    }

    public static void main(String[] args) throws Exception
    {
        runMainMenu();

    }

    public static void runMainMenu()
    {
        MainMenu myApplication = new MainMenu();
        myApplication.setTitle("Library Management System");
        // Specify where will it appear on the screen:
        myApplication.setLocation(10, 10);
        myApplication.setSize(800, 1000);
        myApplication.setIconImage(img.getImage());
        // Show it!
        myApplication.setVisible(true);
        myApplication.populateSearchBy();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == searchButton)
        {
            String getSelection = searchDropdownBox.getSelectedItem().toString();
            searchButton.removeActionListener(this);
            runSelectedMenu(getSelection);
        }
        else if (e.getSource() == back) {
            reRunMainMenu();
        }
    }
}
