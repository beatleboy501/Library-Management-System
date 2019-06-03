package com.library.ui;

import com.library.service.DataAccessService;
import com.library.dataaccess.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by beatleboy501 on 8/10/2015
 */
class MenuBase extends JFrame {
    final JLabel searchBoxLabel = new JLabel("Search For");
    final JLabel menuLabel = new JLabel();
    final JLabel searchByLabel = new JLabel("Search By");
    final JLabel inLabel = new JLabel("in");
    final JPanel bottomPanel = new JPanel();
    final JPanel containerPanel = new JPanel();
    final JButton back = new JButton("Back to Main Menu");
    final JButton searchButton = new JButton("Search");
    final JButton addItemButton = new JButton("Add New Library Item");
    final JComboBox searchDropdownBox = new JComboBox();
    final JComboBox searchByDropdownBox = new JComboBox();
    final JTextField searchBox = new JTextField(15);
    final JTable searchResultPanel = new JTable();
    DataAccess data;

    void runSelectedMenu(String item, String facet, String facetValue) {
        removeCurrentWindow();
        menuLabel.setText(item.toUpperCase());
        bottomPanel.setLayout(new FlowLayout());
        JTable resultTable = table(item, facet, facetValue);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
        bottomPanel.add(scrollPane);
        bottomPanel.add(back);
        getContentPane().add(addItemButton, BorderLayout.SOUTH);
        back.addActionListener((ActionListener) this);
        addItemButton.addActionListener((ActionListener) this);
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        getContentPane().add(addItemButton, BorderLayout.SOUTH);
        this.repaint();
        this.revalidate();
        validate();
    }

    void reRunMainMenu() {
        removeCurrentWindow();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(searchBoxLabel);
        bottomPanel.add(searchBox);
        bottomPanel.add(inLabel);
        bottomPanel.add(searchDropdownBox);
        bottomPanel.add(searchByLabel);
        bottomPanel.add(searchByDropdownBox);
        bottomPanel.add(searchButton);
        searchButton.addActionListener((ActionListener) this);
        addItemButton.addActionListener((ActionListener) this);
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        getContentPane().add(addItemButton, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.repaint();
        this.revalidate();
        validate();
    }

    void removeCurrentWindow() {
        menuLabel.setText(null);
        getContentPane().remove(menuLabel);
        getContentPane().remove(containerPanel);
        containerPanel.remove(bottomPanel);
        containerPanel.invalidate();
        bottomPanel.removeAll();
        containerPanel.removeAll();
        addItemButton.removeActionListener((ActionListener) this);
    }

    JTable table(String item, String facet, String facetValue) {
        JTable jTable = new JTable();
        try {
            final String[] headers = getHeaderRow(item);
            List<Object[]> tableContent = getTableContent(item, headers, facet, facetValue);
            Object[][] data = tableContent.toArray(new Object[tableContent.size()][]);
            jTable = new JTable(data, headers);
        } catch (Exception e) {
            System.out.println("Error showing table");
            System.out.println(e.getMessage());
        }
        return jTable;
    }

    private static String[] getHeaderRow(String item) {
        DataAccessService ml = new DataAccessService();
        String[] header = null;
        try {
            System.out.println(item);
            header = ml.populateTableHeader(item);
        } catch (Exception e) {
            System.out.println("Error getting header row");
            System.out.println(e.getMessage());
        }
        if (header == null) {
            throw new NullPointerException();
        }
        return header;
    }

    private static List<Object[]> getTableContent(String item, String[] headers, String facet, String facetValue) {
        DataAccessService service = new DataAccessService();
        List<Object[]> data = new ArrayList<>();
        try {
            data = service.populateTableData(item, headers, facet, facetValue);
        } catch (Exception e) {
            System.out.println("Error getting table content");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (data == null) {
            throw new NullPointerException();
        }
        return data;
    }

    void showAddNewLibraryItemDialog() {
        data = new DataAccess();
        Object[] possibilities = null;
        try {
            ArrayList<String> items = data.getLibraryItems();
            possibilities = items.toArray(new Object[items.size()]);
            for (Object item : possibilities) System.out.println(item.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        String s = (String)JOptionPane.showInputDialog(
                this,
                "Item to add:",
                "Add New Library Item",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                Objects.requireNonNull(possibilities)[0]);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            System.out.println(s);
            return;
        }
        //If you're here, the return value was null/empty.
        System.out.println("Add item cancelled");
    }
}
