package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames;
    private Object[][] datas;

    public TablePanel(String[] columnNames, Object[][] datas) {
        this.columnNames = columnNames;
        this.datas = datas;
        this.setLayout(new BorderLayout());

        table = new JTable(datas, columnNames);

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        this.add(scrollPane, BorderLayout.CENTER);
    }
}
