package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private JLabel setTitle;

    public TitlePanel(String title) {
        this.setLayout(new FlowLayout());
        setTitle = new JLabel(
                "<html>" +
                        "<p></p>" +
                        "<p>" + title + "</p>" +
                        "<p></p>" +
                        "</html>"
        );
        setTitle.setFont(new Font("Eras Bold ITC", Font.TRUETYPE_FONT, 25));
        this.add(setTitle);
    }
}
