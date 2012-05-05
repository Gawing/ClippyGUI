/*
 *
 */
package ClippyV2.ui.View;

import ClippyV2.ui.Button;
import ClippyV2.ui.Dialog;
import ClippyV2.ui.Frame;
import ClippyV2.ui.RoundButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * The GUI of the Clippy Project 
 * @author GavinC 
 */
public class ClippyGui extends Frame implements Runnable{
    private final static Color backgroundColor = Color.white;
    private ImageIcon newImage = new ImageIcon(getClass().getResource("Images/clippyIntro.gif"));
    private ImageIcon micIcon = new ImageIcon(getClass().getResource("Images/mic.png")) ;
    private ImageIcon editIcon = new ImageIcon(getClass().getResource("Images/edit.png"));
    private ImageIcon speechIcon = new ImageIcon(getClass().getResource("Images/speech.png"));
    private ImageIcon exitIcon = new ImageIcon(getClass().getResource("Images/exit.png"));
    private JPanel clipPnl = new JPanel();
    private JLabel imgLbl = new JLabel();
    private JLabel speechLbl = new JLabel();
    private JTextField exeTxt = new JTextField();
    private JTextArea clippyTxt;
    private JScrollPane clippyScroll;
    private Button searchBtn = new Button("Search");
    private Button exeBtn = new Button("Execute");
    private Button helpBtn = new Button("Help");
    private RoundButton exitBtn = new RoundButton(exitIcon);
    private RoundButton voiceBtn = new RoundButton(micIcon);
    private RoundButton editBtn = new RoundButton(editIcon);
    private Dialog clipDialog = null;
    private String user = "";
    private boolean isIdle;
    private boolean exeState;
    private boolean searchState;
    private boolean errorState;
    private boolean exitState;
    private boolean voiceState;
    private Font txtFont = new Font("Castellar", Font.BOLD, 12);
    
    /**
     * Constructor for class ClippyGUI
     */
    public ClippyGui(String userName){
        user = userName;
        clippyTxt = new JTextArea("Hello " + user + "!");
        try{
            createFrame();
            createPnl();
            exitBtn.setVisible(false);
            exeBtn.setVisible(false);
            searchBtn.setVisible(false);
            editBtn.setVisible(false);
            voiceBtn.setVisible(false);
            helpBtn.setVisible(false);
            isIdle = true;
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        }
        catch(NullPointerException noUser){
            System.err.println(noUser + " = No user!");
        }
    }
    
    /**
     * Creates the and sets the location of the frame for the interface  
     */
    public void createFrame(){
        this.setPreferredSize(new Dimension(350,300));
        this.setLocation((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-350, 
                (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()-300); 
    }
    /**
     * Creates a panel for the components of the interface 
     */
     public void createPnl(){
        clipPnl.setLayout(null);
        //clipPnl.setBorder(new LineBorder(Color.black, 2));
        setImage();
        addClippyTxt();
        setSpeechImg();
        addBtn();
        addField();
        this.add(clipPnl);
        clipPnl.setBounds(0, 0, 350, 300);
        clipPnl.setBackground(backgroundColor);
    }
    
     /**
     * Sets the image to the panel
     */
    public void setImage(){
        try{
            imgLbl.setIcon(newImage);
            imgLbl.setBounds(120, 40,134,150); 
            clipPnl.add(imgLbl);
            imgLbl.setVisible(true);
        }  
        catch(NullPointerException ex){
            System.err.println("Cannot find image");
        }
    }
    /**
     * Set and add the speech bubble image
     */
    public void setSpeechImg(){
        try{
            speechLbl.setIcon(speechIcon);
            speechLbl.setBounds(3, 10,150,113); 
            clipPnl.add(speechLbl);
            speechLbl.setVisible(true);
        }  
        catch(NullPointerException ex){
            System.err.println("Cannot find image");
        }
    }
    
    /**
     * Adds the buttons to the panel
     */
    public void addBtn(){
        setVoiceBtn();
        setExeBtn();
        setEditBtn();
        setSearchBtn();
        setHelpBtn();
        setExitBtn();
    }
    
    /**
     * Creates voice button and set the action event 
     */
    private void setVoiceBtn(){
        voiceBtn.setBounds(260, 55, 45, 45);
        voiceBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                     
            }
        });
        clipPnl.add(voiceBtn);
    }
    
    /**
     * Creates edit button and set the action event 
     */
    private void setEditBtn(){
        editBtn.setBounds(260, 110, 45, 45);
        editBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
               
            }
        });
        clipPnl.add(editBtn);
    }
    
    /**
     * Creates execute button and set the action event 
     */
    private void setExeBtn(){
        exeBtn.setLocation(70, 240);
        exeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(clippyTxt.equals("")){
                    genImage("clippyError");
                }
                else{
                    isIdle = false;
                    exeState = true;
                }               
            }
        });
        clipPnl.add(exeBtn);
    }
    
    /**
     * Creates search button and set the action event 
     */
    private void setSearchBtn(){
        searchBtn.setLocation(200, 240);
        searchBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
                isIdle = false;  
                searchState = true;
            }
        });
        clipPnl.add(searchBtn); 
    }
    
    /**
     * Creates help menu button and set the action event 
     */
    private void setHelpBtn(){
        helpBtn.setLocation(230, 170);
        helpBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                   
            }
        });
        clipPnl.add(helpBtn);
    }
    
    /**
     * Creates exit button and set the action event 
     */
    private void setExitBtn(){
        exitBtn.setBounds(300,1 ,21,21);
        exitBtn.setVisible(false);
        exitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                clipDialog = new Dialog("Are you sure you want to exit?");
                clipDialog.showOptionDialog();
                if(clipDialog.getConfirmation()){
                    isIdle = false;
                    exitState = true;    
                }
                else{
                    
                }
            }     
        });
        clipPnl.add(exitBtn);      
    }
     
    /**
     * Creates the design and add the input field to the panel 
     */
    public void addField(){
        exeTxt.setBounds(25, 195, 300, 30);
        exeTxt.setOpaque(false);
        exeTxt.setFont(txtFont);
        exeTxt.setBorder(new EtchedBorder());
        clipPnl.add(exeTxt);
    }
    
    /**
     * Generate a random idle Clippy animation image
     */
    public void genIdle(){
        String fileName = "";
        try{
            //Generate random image of clippy when idle  
            Random imgGen = new Random();
            int imgNum = imgGen.nextInt(4);
            fileName = "clippy" + imgNum;
            newImage = new ImageIcon(getClass().getResource("Images/" + fileName + ".gif"));
            //Generate random comments that clippy says when idle 
            if(imgNum == 0){
                setClippyTxt("Uploading Virus....");
            }
            else if(imgNum ==1){
                setClippyTxt("Are you there?");
            }
            else{
                setClippyTxt("I know you're there");
            }
        }
        catch(NullPointerException ex){   
          System.err.println("Cannot find image");
        }
       // Test Clippy Image
        //System.out.println(fileName);    
    }
    
    /**
     * Generate a specified image 
     * @param imgName 
     */
    public void genImage(String imgName){
        try{
            String img = imgName;
            newImage = new ImageIcon(getClass().getResource("Images/" + img + ".gif"));   
        }
        catch(NullPointerException ex){   
          System.err.println("Cannot find image");
        }      
        setImage();
    }
    
    /**
     * A thread that controls adding and setting default content when clippy is idle 
     * @throws InterruptedException  
     */
    public void threadIdle() throws InterruptedException{
            genIdle();
            setImage();
            Thread.sleep(4500);
            imgLbl.setVisible(false);       
    }
    
    /**
     * Create and add Clippy's output text 
     */
    public void addClippyTxt(){
        clippyTxt.setLineWrap(true);
        clippyTxt.setWrapStyleWord(true);
        clippyTxt.setEditable(false);
        clippyTxt.setFont(txtFont);
        clippyScroll = new JScrollPane(clippyTxt);
        clippyScroll.setBackground(Color.white);
        clippyScroll.setBorder(null);
        clippyScroll.setSize(119,38);
        clippyScroll.setLocation(16, 40);
        clipPnl.add(clippyScroll);
    }
    
    /**
     * Set the output text of Clippy
     * @param text Clippys output text/feedback
     */
    public void setClippyTxt(String text){
        clippyTxt.setText(text);
    }
    
    /**
     * Runs the event when the user exits Clippy  
     */
    public void runExitSte(){
        try {
            clippyTxt.setVisible(false);
            clippyScroll.setVisible(false);
            speechLbl.setVisible(false);
            genImage("clippyExit");
            Thread.sleep(3800);
            System.exit(0);
        } 
        catch (InterruptedException ex) {
            
        }
    }
    
    public void runSearchSte() throws InterruptedException{
        genImage("clippySearch");
        setClippyTxt("Searching...");
    }
    
    public void runExeSte() throws InterruptedException{
        genImage("clippyVoice");
        setClippyTxt("Executing Command...");      
    }
    
    public void runErrorSte() throws InterruptedException{
        genImage("clippyError");
        if(exeState){
            setClippyTxt("Cannot Execute Error!");
        }
        else if(voiceState){
            setClippyTxt("What? Speak Again?");
        }
        else{
            
        }
        Thread.sleep(3800);
    }
    
    /**
     * Run the overall Clippy interface events 
     */
    
    public void runClippy() throws InterruptedException{
        while(isIdle){
            try {
                threadIdle();
            } 
            catch (InterruptedException ex) {
      
            }
        }
        if(exeState){
            runExeSte();
            exeState = false;
        }
        else if(exitState){
            runExitSte();
        }
        else if(searchState){
            runSearchSte();
            searchState = false;
                
        }
        else if(errorState){
                
        }
        else{
              
        }
        
        Thread.sleep(4000); 
        isIdle = true;
        runClippy();
    }
    
    /**
     * Runs the Clippy thread 
     */
    @Override
    public void run() {
        try {
            Thread.sleep(3800);
            speechLbl.setVisible(true);
            exitBtn.setVisible(true);
            exeBtn.setVisible(true);
            searchBtn.setVisible(true);
            editBtn.setVisible(true);
            voiceBtn.setVisible(true);
            helpBtn.setVisible(true);
            runClippy();
        } 
        catch (InterruptedException | ArrayIndexOutOfBoundsException ex) {
           System.err.println("Thread Error");     
        }
    }
}

    

