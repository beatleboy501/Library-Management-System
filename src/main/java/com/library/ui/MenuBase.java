package com.library.ui;

import com.library.middlelayer.MiddleLayer;
import com.library.server.DataAccess;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.JTable.AUTO_RESIZE_OFF;

/**
 * Created by allan06 on 8/10/2015.
 */
public class MenuBase extends JFrame {
    JLabel searchBoxLabel = new JLabel("Search For");
    JLabel menuLabel = new JLabel();
    JLabel searchByLabel = new JLabel("Search By");
    JLabel in = new JLabel("in");
    JPanel bottomPanel = new JPanel();
    JPanel holdAll = new JPanel();
    JButton back = new JButton("Back to Main Menu");
    JButton hyperLink = new JButton();
    JButton searchButton = new JButton("Search");
    JComboBox searchDropdownBox = new JComboBox();
    JComboBox searchByDropdownBox = new JComboBox();
    JTextField searchBox = new JTextField(15);
    JTable searchResultPanel = new JTable();
    DataAccess data;
    static ImageIcon img = new ImageIcon("C:\\Users\\allan06\\Pictures\\Burn.png");

    public void runSelectedMenu(String item) {
        removeCurrentWindow();
        menuLabel.setText(item.toUpperCase());
        bottomPanel.setLayout(new FlowLayout());

//        JTable resultTable = autoFitContentTable(item);
        JTable resultTable = table(item);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
        bottomPanel.add(scrollPane);
        bottomPanel.add(back);
        back.addActionListener((ActionListener) this);
        holdAll.setLayout(new BorderLayout());
        holdAll.add(bottomPanel, BorderLayout.CENTER);
        getContentPane().add(holdAll, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);

        bottomPanel.revalidate();
        bottomPanel.repaint();
        holdAll.revalidate();
        holdAll.repaint();
        validate();
    }

    private JTable autoFitContentTable(String item) {
        final String i = item;
        JTable resultTable = table(i);

        resultTable.setAutoResizeMode(AUTO_RESIZE_OFF);

        for (int column = 0; column < resultTable.getColumnCount(); column++)
        {
            TableColumn tableColumn = resultTable.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < resultTable.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = resultTable.getCellRenderer(row, column);
                Component c = resultTable.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + resultTable.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }
        return resultTable;
    }

    public void reRunMainMenu() {
        removeCurrentWindow();
        bottomPanel.setLayout(new FlowLayout());

        bottomPanel.add(searchBoxLabel);
        bottomPanel.add(searchBox);
        bottomPanel.add(in);
        bottomPanel.add(searchDropdownBox);
        bottomPanel.add(searchByLabel);
        bottomPanel.add(searchByDropdownBox);
        bottomPanel.add(searchButton);
        searchButton.addActionListener((ActionListener) this);
        holdAll.setLayout(new BorderLayout());
        holdAll.add(bottomPanel, BorderLayout.CENTER);

        getContentPane().add(holdAll, BorderLayout.CENTER);
        getContentPane().add(menuLabel, BorderLayout.NORTH);
        getContentPane().add(hyperLink, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bottomPanel.repaint();
        holdAll.repaint();
        validate();
    }

    public void removeCurrentWindow()
    {
        menuLabel.setText(null);
        getContentPane().remove(menuLabel);
        getContentPane().remove(holdAll);
        holdAll.remove(bottomPanel);
        holdAll.invalidate();
        bottomPanel.removeAll();
        holdAll.removeAll();
    }

    public JTable table(String item) {
        final String i = item;
        JTable table = new JTable();
        try {
            final String[] header = getHeaderRow(i);
            final Object[][] data = getTableContent(i);
            table = new JTable(data, header);
        }
        catch (Exception e)
        {

        }
        return table;
    }

    public static String[] getHeaderRow(String item) {
        final String i = item;
        MiddleLayer ml = new MiddleLayer();
        String[] header = null;
        try {
             header = ml.populateTableHeader(item);
        }
        catch (Exception e) {

        }
        if(null==header) {
            throw new NullPointerException();
        }
        return header;
    }

    public static Object[][] getTableContent(String item)
    {
        final String i = item;
        MiddleLayer ml = new MiddleLayer();
        Object[][] data = null;
        try {
            data = ml.populateTableData(i);
        }
        catch (Exception e)
        {

        }
        if(null == data) {
            throw new NullPointerException();
        }
        return data;
    }
}
