
package notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NotePad extends JFrame implements ActionListener{
    JTextArea area;
    String text;
    
    NotePad(){
       setBounds(150,150,800,600);
       getContentPane().setBackground(Color.WHITE);
       
       JMenuBar menubar=new JMenuBar();
       
       // ********** File ************
       
       JMenu file=new JMenu("File");
       file.setFont(new Font("TAHOMA",Font.BOLD,16));
       
       JMenuItem newdoc=new JMenuItem("New");
       newdoc.setFont(new Font("TAHOMA",Font.PLAIN,15));
       newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
       newdoc.addActionListener(this);
       file.add(newdoc);
       
        JMenuItem open=new JMenuItem("Open");
       open.setFont(new Font("TAHOMA",Font.PLAIN,15));
       open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
       open.addActionListener(this);
       file.add(open);
       
        JMenuItem save=new JMenuItem("Save");
       save.setFont(new Font("TAHOMA",Font.PLAIN,15));
       save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
       save.addActionListener(this);
       file.add(save);
       
       JMenuItem exit=new JMenuItem("Exit");
       exit.setFont(new Font("TAHOMA",Font.PLAIN,15));
       exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
       exit.addActionListener(this);
       file.add(exit);
       
       // ************* Edit **************
       
       JMenu edit=new JMenu("   Edit");
       edit.setFont(new Font("TAHOMA",Font.BOLD,16));
       
       JMenuItem copy=new JMenuItem("Copy");
       copy.setFont(new Font("TAHOMA",Font.PLAIN,15));
       copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
       copy.addActionListener(this);
       edit.add(copy);
       
        JMenuItem paste=new JMenuItem("Paste");
       paste.setFont(new Font("TAHOMA",Font.PLAIN,15));
       paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
       paste.addActionListener(this);
       edit.add(paste);
       
        JMenuItem cut=new JMenuItem("Cut");
       cut.setFont(new Font("TAHOMA",Font.PLAIN,15));
       cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
       cut.addActionListener(this);
       edit.add(cut);
       
       JMenuItem selectall=new JMenuItem("SelectAll");
       selectall.setFont(new Font("TAHOMA",Font.PLAIN,15));
       selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
       selectall.addActionListener(this);
       edit.add(selectall);
      
     
       menubar.add(file);
       menubar.add(edit);
     
       area = new JTextArea();      
       area.setBounds(0,0,1790,840);
       area.setFont(new Font("TAHOMA",Font.PLAIN,20));
       area.setLineWrap(true);
       area.setWrapStyleWord(true);
       add(area);
       
       JScrollPane scroll=new JScrollPane(area);
       scroll.setBorder(BorderFactory.createEmptyBorder());
       add(scroll);
       
       setJMenuBar(menubar);
       setVisible(true);
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")){
           area.setText("");
        }
        
        if(e.getActionCommand().equals("Open")){
           JFileChooser openfc = new JFileChooser();
           openfc.setAcceptAllFileFilterUsed(false);
           FileNameExtensionFilter  filter = new FileNameExtensionFilter("Only .txt File","txt"); 
           openfc.addChoosableFileFilter(filter);
            int action = openfc.showOpenDialog(this);
            
            if(action != JFileChooser.APPROVE_OPTION){
               return;
           }
           
           File filename = openfc.getSelectedFile();
           try{
               BufferedReader reader = new BufferedReader(new FileReader(filename));
               area.read(reader,null);
           }catch(Exception e1){}
           
        }
        
        if(e.getActionCommand().equals("Save")){
           JFileChooser fc = new JFileChooser();
           fc.setApproveButtonText("Save");
           int action = fc.showOpenDialog(this);
           if(action != JFileChooser.APPROVE_OPTION){
               return;
           }
           
           File filename = new File(fc.getSelectedFile() + ".txt");
           try{
               BufferedWriter bfr = new BufferedWriter(new FileWriter(filename));
               area.write(bfr);
           }catch(Exception e1){}
           
        }
        
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        
        //********** Edit ************
        
        if(e.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        
        if(e.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
       
        if(e.getActionCommand().equals("Cut")){
             text = area.getSelectedText();
             area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        
         if(e.getActionCommand().equals("SelectAll")){
            area.selectAll();
        }
      
         //************ Help **************
         
         if(e.getActionCommand().equals("About the NotePad")){
             
        }
    }
    
    public static void main(String[] args) {
        new NotePad();
    }

}
   
