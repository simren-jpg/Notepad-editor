 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.geom.Area;
 import java.awt.print.PrinterException;
 import java.io.*;

 //note<<..your classname and constrater name should be same otherwise compiler create a error
 //nmae of class
class MyFirstFrame implements ActionListener {
     //jFrame use for creat a notepad
     JFrame j;
     JMenuBar Bar;
     JMenu file, edit, themes, help;
     JMenuItem darkTheme, moonLight, save, open, close, cut, copy, paste, New, selectAll, fontSize, aboutUs;
     //jtextarea define for something write anything
     JTextArea Area;
     //jscrollpanel define your ending point and creat a panel
     JScrollPane scroll;
     JPanel saveFileOptionWindow;
     JLabel fileLabel,dirLabel;
     JTextField fileName,dirName;

     MyFirstFrame() {
         // frame
         JFrame j = new JFrame("Notepad");
         //textarea...means writing
         Area = new JTextArea(32, 88);
         j.add(Area);
         //scroll for vertically and horizentelly
         scroll = new JScrollPane(Area);
         j.add(scroll);
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\LCS\\Desktop\\icon.png");
         j.setIconImage(img);
         j.setVisible(true);
         j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         j.setSize(600, 400);
         j.setLocation(500, 200);
         //create a menubar for add menu
         Bar = new JMenuBar();
         //menues
         file = new JMenu("File");
         edit = new JMenu("Edit");
         themes = new JMenu("Themes");
         help = new JMenu("Help");
         //menu files add in menuBar
         Bar.add(file);
         Bar.add(edit);
         Bar.add(themes);
         Bar.add(help);
         j.setJMenuBar(Bar);
         //menuitems

         darkTheme = new JMenuItem("DarkThemes");
         moonLight = new JMenuItem("MoonLight");
         save = new JMenuItem("Save");
         open = new JMenuItem("Open");
         close = new JMenuItem("Close");
         cut = new JMenuItem("Cut");
         copy = new JMenuItem("Copy");
         paste = new JMenuItem("Paste");
         New = new JMenuItem("New");
         selectAll = new JMenuItem("Print");
         fontSize = new JMenuItem("FontSize");
         aboutUs = new JMenuItem("AboutUs");
         //add to menuBar files
         file.add(New);
         file.add(open);
         file.add(save);
         file.add(close);
         //edit add items
         edit.add(cut);
         edit.add(copy);
         edit.add(paste);
         edit.add(selectAll);
         edit.add(fontSize);
         //themes add items
         themes.add(darkTheme);
         themes.add(moonLight);
         //help add items
         help.add(aboutUs);
         //add activeLisner for cut copy
         darkTheme.addActionListener(this);
         moonLight.addActionListener(this);
         save.addActionListener(this);
         open.addActionListener(this);
         close.addActionListener(this);
         cut.addActionListener(this);
         copy.addActionListener(this);
         paste.addActionListener(this);
         New.addActionListener(this);
         selectAll.addActionListener(this);
         fontSize.addActionListener(this);
         aboutUs.addActionListener(this);


     }


     public static void main(String[] args) {
         MyFirstFrame frame = new MyFirstFrame();


     }

     @Override
     public void actionPerformed(ActionEvent e) {

         if (e.getSource() == cut) {
             Area.cut();
         }
         if (e.getSource() == copy) {
             Area.copy();
         }
         if (e.getSource() == paste) {
             Area.paste();
         }
         if (e.getSource() == selectAll) {
             Area.selectAll();
         }

         //change the fontsize value
         if (e.getSource()==fontSize) {
             String sizeOfFont = JOptionPane.showInputDialog(j, "Enter Font Size", JOptionPane.OK_CANCEL_OPTION);
             if (sizeOfFont != null) {
                 int convertSizeOfFont = Integer.parseInt(sizeOfFont);
                 Font font = new Font(Font.SANS_SERIF, Font.PLAIN, convertSizeOfFont);
                 Area.setFont(font);


             }

         }
         if(e.getSource()==open){
             JFileChooser choosefile = new JFileChooser();
             int i= choosefile.showOpenDialog(j);
             if(i==JFileChooser.APPROVE_OPTION) {
                 File file = choosefile.getSelectedFile();//select
                 String filePath = file.getPath();//get the file path
                 String fileNameToShow = file.getName();//get the file name
                 j.setTitle(fileNameToShow);
             try{
                 BufferedReader readFile = new BufferedReader(new FileReader(filePath));
                  String tempString1 =" ";
                  String tempString2 =" ";
                 while((tempString1= readFile.readLine())!=null){
                     tempString2+=tempString1 + "/n";
                 }
                 Area.setText(tempString2);
                 readFile.close();
             }catch (Exception ae){
                 ae.printStackTrace();
             }
             }
         }
         if(e.getSource()==save){
             saveTheFile();
         }
         if(e.getSource()==New){
             Area.setText(" ");
         }if(e.getSource()==close){
             System.exit(1);
         }
         // dark themes
         if(e.getSource()==darkTheme){
             Area.setBackground(Color.darkGray);
             Area.setForeground(Color.white);
         }
         // moonlight theme
         if(e.getSource()==moonLight){
             Area.setBackground(new Color(107,169,255));
             Area.setForeground(Color.black);
         }

     }
     //save the file
     public void saveTheFile(){
         saveFileOptionWindow = new JPanel(new GridLayout(2,1));
         fileLabel=new JLabel("Filename  :-");
         dirLabel=new JLabel("Save File To  :-");
         fileName= new JTextField();
         dirName= new JTextField();
                 saveFileOptionWindow.add(fileLabel);
                 saveFileOptionWindow.add(fileName);
                 saveFileOptionWindow.add(dirLabel);
                 saveFileOptionWindow.add(dirName);
          JOptionPane.showMessageDialog(j,saveFileOptionWindow );
          String fileContent = Area.getText();
          String filePath=dirName.getText();
          try{
              BufferedWriter writeContent = new BufferedWriter(new FileWriter(filePath));
              writeContent.write(fileContent);
              writeContent.close();
              JOptionPane.showMessageDialog(j,"File successfully saved");
          }catch(Exception ex){
              ex.printStackTrace();
          }
     }
 }
