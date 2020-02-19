import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import java.security.MessageDigest;

class fileComparator extends JFrame {

  private JButton btnCheck  = new JButton("Check");

  private JButton btnB = new JButton("..");
  private JButton btnC  = new JButton("..");
  
  //private JTextField txtA = new JTextField();
  private JTextArea txtB = new JTextArea();
  private JTextArea txtC = new JTextArea();

  //private JLabel lblA = new JLabel("File :");
  private JLabel lblB = new JLabel("File :");
  private JLabel lblC = new JLabel("Hash :");

  final JFileChooser fc = new JFileChooser();

  public fileComparator(){
    setTitle("Hash comparator");
    setSize(400,200);
    setLocation(new Point(300,200));
    setLayout(null);    
    setResizable(false);

    initComponent();    
    initEvent();    
  }

  private void initComponent(){
    btnCheck.setBounds(300,130, 80,25);
    //btnTambah.setBounds(300,100, 80,25);

    btnB.setBounds(305,35, 20,20);
    btnC.setBounds(305,65, 20,20);

    //txtA.setBounds(100,10,100,20);
    txtB.setBounds(100,35,200,20);
    txtC.setBounds(100,65,200,20);

    //lblA.setBounds(20,10,100,20);
    lblB.setBounds(20,35,100,20);
    lblC.setBounds(20,65,100,20);


    add(btnCheck);
    //add(btnTambah);

    add(btnB);
    add(btnC);


    //add(lblA);
    add(lblB);
    add(lblC);

    //add(txtA);
    add(txtB);
    add(txtC);
  }

  private void initEvent(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

    btnCheck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCheckClick(e);
      }
    });

    btnB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnBClick(e);
      }
    });

    btnC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCClick(e);
      }
    });
  }
  
  private void btnCheckClick(ActionEvent evt){
    //System.exit(0);

    try {
        String fileB = txtB.getText();
        String fileC = txtC.getText();
        System.out.println(fileB +" - "+ getMD5Checksum(fileB));
        System.out.println(fileC +" - "+ getMD5Checksum(fileC));
        if (true) {
            if (getMD5Checksum(txtB.getText()).equals(getMD5Checksum(txtC.getText()))){
                System.out.println("The 2 files are identical");
            }else {
                System.out.println("The 2 files are not identical");
            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }
  }
  
  private void btnBClick(ActionEvent evt){
    //Integer x,y,z;
    try{
      int returnVal = fc.showOpenDialog(fileComparator.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          //This is where a real application would open the file.
          txtB.append(file.getAbsolutePath());
      } else {
          //txtB.append("Open command cancelled by user.");
      }

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void btnCClick(ActionEvent evt){
    //Integer x,y,z;
    try{
      int returnVal = fc.showOpenDialog(fileComparator.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          //This is where a real application would open the file.
          txtC.append(file.getAbsolutePath());
      } else {
          //txtB.append("Open command cancelled by user.");
      }

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }
  
   public static byte[] createChecksum(String filename) throws Exception {
       InputStream fis =  new FileInputStream(filename);

       byte[] buffer = new byte[1024];
       MessageDigest complete = MessageDigest.getInstance("MD5");
       int numRead;

       do {
           numRead = fis.read(buffer);
           if (numRead > 0) {
               complete.update(buffer, 0, numRead);
           }
       } while (numRead != -1);

       fis.close();
       return complete.digest();
   }

   // see this How-to for a faster way to convert
   // a byte array to a HEX string
   public static String getMD5Checksum(String filename) throws Exception {
       byte[] b = createChecksum(filename);
       String result = "";

       for (int i=0; i < b.length; i++) {
           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
       }
       return result;
   }


  public static void main(String[] args){
    fileComparator f = new fileComparator();
    f.setVisible(true);
  }
}