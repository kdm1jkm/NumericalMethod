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

    public void next(){
        setValue(++value);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        progressBar = new JProgressBar();
        label1 = new JLabel();

        //======== this ========
        setTitle("Generation...");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(0, 219, Short.MAX_VALUE))
                        .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
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
