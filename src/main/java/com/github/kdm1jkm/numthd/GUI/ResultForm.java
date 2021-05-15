/*
 * Created by JFormDesigner on Fri May 07 16:46:18 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import javax.swing.*;

/**
 * @author dm k
 */
public class ResultForm extends JFrame {
    public ResultForm(String title) {
        initComponents();
        setTitle(title);
    }

    public void setContent(String content) {
        textArea_content.setText(content);
    }

    public void addContent(String content) {
        setContent(textArea_content.getText() + content);
    }

    public void addContentLn(String content) {
        addContent(content + "\n");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        scrollPane1 = new JScrollPane();
        textArea_content = new JTextArea();

        //======== this ========
        setTitle("Result");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea_content);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane1;
    private JTextArea textArea_content;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
