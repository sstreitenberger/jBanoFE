package jBaño2021;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame {
  private static final long serialVersionUID = 2824330405822902835L;
  
  private JButton jButton1;
  
  private JButton jButton2;
  
  private JLabel jLabel1;
  
  private JTextField jTextField1;
  
  public Main() {
    initComponents();
  }
  
  private void initComponents() {
    this.jTextField1 = new JTextField();
    this.jButton1 = new JButton();
    this.jLabel1 = new JLabel();
    this.jButton2 = new JButton();
    setDefaultCloseOperation(3);
    setLocationByPlatform(true);
    this.jButton1.setText("Guardar");
    this.jButton1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Main.this.jButton1ActionPerformed(evt);
          }
        });
    this.jLabel1.setText("JBaño2021");
    this.jButton2.setText("Limpiar");
    this.jButton2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Main.this.jButton2ActionPerformed(evt);
          }
        });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
          .createSequentialGroup().addContainerGap()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.jLabel1, -1, 304, 32767)
            .addComponent(this.jTextField1).addGroup(GroupLayout.Alignment.TRAILING, 
              layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jButton2)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(this.jButton1)))
          .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addContainerGap()
          .addComponent(this.jLabel1, -1, 
            -1, 32767)
          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
          .addComponent(this.jTextField1, -2, 
            -1, -2)
          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.jButton1).addComponent(this.jButton2))
          .addContainerGap()));
    pack();
  }
  
  private void jButton1ActionPerformed(ActionEvent evt) {
    try {
      String codigo = this.jTextField1.getText();
      System.out.println(codigo);
      if (replace(codigo))
        infoBox("Cmodificado con exito.", 1); 
    } catch (Exception ex) {
      infoBox(ex.getMessage(), 0);
    } 
  }
  
  public boolean replace(String codigo) throws Exception {
    String oldFileName = "D:\\MICROS\\Res\\CAL\\Win32\\Files\\Micros\\Res\\Pos\\Etc\\FIPParam.cfg";
    String tmpFileName = "D:\\MICROS\\Res\\CAL\\Win32\\Files\\Micros\\Res\\Pos\\Etc\\tmp_FIPParam.cfg";
    File oldFile = new File(oldFileName);
    File newFile = new File(tmpFileName);
    if (!newFile.exists())
      newFile.createNewFile(); 
    BufferedReader br = null;
    BufferedWriter bw = null;
    br = new BufferedReader(new FileReader(oldFileName));
    bw = new BufferedWriter(new FileWriter(tmpFileName));
    String line;
    while ((line = br.readLine()) != null) {
      if (line.toUpperCase().startsWith("TRAILER_3".toUpperCase()))
      {
    	 System.out.println(line);
        line = "TRAILER_3=1^    CodigoBaño: " + codigo.toUpperCase(); 
      }
      bw.write(String.valueOf(line) + System.lineSeparator());
    } 
    br.close();
    bw.close();
    oldFile.delete();
    newFile.renameTo(oldFile);
    return true;
  }
  
  public boolean copyTo(File src, File dest) throws IOException {
    InputStream in = null;
    OutputStream out = null;
    in = new FileInputStream(src);
    out = new FileOutputStream(dest);
    byte[] buffer = new byte[1024];
    int length;
    while ((length = in.read(buffer)) > 0)
      out.write(buffer, 0, length); 
    in.close();
    out.close();
    return true;
  }
  
  public static void infoBox(String infoMessage, int tipo) {
    JOptionPane.showMessageDialog(null, infoMessage, "JBano", tipo);
  }
  
  private void jButton2ActionPerformed(ActionEvent evt) {
    this.jTextField1.setText("");
  }
  
  public static void main(String[] args) {
    try {
      byte b;
      int i;
      UIManager.LookAndFeelInfo[] arrayOfLookAndFeelInfo;
      for (i = (arrayOfLookAndFeelInfo = UIManager.getInstalledLookAndFeels()).length, b = 0; b < i; ) {
        UIManager.LookAndFeelInfo info = arrayOfLookAndFeelInfo[b];
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        } 
        b++;
      } 
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            Main x = new Main();
            x.setLocationRelativeTo(null);
            x.setVisible(true);
          }
        });
  }
}
