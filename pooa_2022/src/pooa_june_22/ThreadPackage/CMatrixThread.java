package pooa_june_22.ThreadPackage;

import pooa_june_22.ViewPackage.CMatrixPanel;

public class CMatrixThread extends Thread {
    private CMatrixPanel cMatrixPanel;

    public CMatrixThread(CMatrixPanel cMatrixPanel) {
        this.cMatrixPanel = cMatrixPanel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(2000);
                cMatrixPanel.textScrolling();
                cMatrixPanel.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
