/*
 * Created by JFormDesigner on Mon Mar 22 19:59:02 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import javax.swing.*;

/**
 * @author dm k
 */
public class LoadingForm extends JDialog {
    private int max;
    private int value;

    public LoadingForm(int max) {
        this.max = max;
        this.value = 0;
        initComponents();
        updateLabel();
    }

    public void setValue(int value) {
        this.value = value;
        updateLabel();
    }

    public void setMax(int value) {
        max = value;
        updateLabel();
    }

    public void updateLabel() {
        label1.setText(String.format("Generating... (%d/%d)", value, max));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        progressBar = new JProgressBar();
        label1 = new JLabel();

        //======== this ========
        setTitle("Generation...");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Generation...");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(4, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(9, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    public JProgressBar progressBar;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
