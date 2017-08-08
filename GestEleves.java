package projettp;


import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


/**
 *
 * @author Laurent Fonquerne
 * @version 1.0 @since 08.08.2017
 */
public class GestEleves extends JFrame implements ActionListener , KeyListener 
{
        JLabel      znum =      new JLabel("Numero ");
        JTextField  qnum =      new JTextField();
        JLabel      znom =      new JLabel("Nom");
        JTextField  qnom =      new JTextField();
        JLabel      zpnm =      new JLabel("Prenom");
        JTextField  qpnm =      new JTextField();
        JLabel      zsex =      new JLabel("Sexe");
            
        JComboBox   box1 =      new JComboBox();
        
        JButton     bfnd =      new JButton("chercher");
        JButton     badd =      new JButton("ajouter");
        JButton     bdel =      new JButton("effacer");
                   
/**
 ** appel de la fenetre gestion des eleves
 ** --- gestion de la creation si eleve absent
 ** --- gestion du vidage des zones
 ** --- gestion de l'affichage en cas de recherche
 */
  
    public GestEleves () 
    {
             
        
        bfnd.setEnabled(false);
        badd.setEnabled(false);
        bdel.setEnabled(true);
        
        this.setVisible(true);
        this.setTitle("Gestion élève");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        znum.setBounds(20,20,150,20);  qnum.setBounds(180,20,150,20); 
        znom.setBounds(20,50,150,20);  qnom.setBounds(180,50,150,20);
        zpnm.setBounds(20,80,150,20);  qpnm.setBounds(180,80,150,20);
        zsex.setBounds(20,110,150,20); box1.setBounds(180,110,150,20); 
        bfnd.setBounds(20,140,150,20); badd.setBounds(180,140,150,20); 
        bdel.setBounds(20,170,150,20);
        box1.addItem("masculin");
        box1.addItem("feminin");
        
        this.getContentPane().add(znum);
        this.getContentPane().add(qnum);
        this.getContentPane().add(znom);
        this.getContentPane().add(qnom);
        this.getContentPane().add(zpnm);
        this.getContentPane().add(qpnm);
        this.getContentPane().add(zsex);
        ;
        this.getContentPane().add(bfnd);
        this.getContentPane().add(badd);
        this.getContentPane().add(bdel);
        this.getContentPane().add(box1);
                
        this.bfnd.addActionListener(this);
        this.badd.addActionListener(this);
        this.bdel.addActionListener(this);
        this.qnum.addKeyListener(this);
        this.qnom.addKeyListener(this);
        this.qpnm.addKeyListener(this);

    }
/**
 **gestion des boutons par keypressed
 * @param e argument
 */    
    public void keyPressed(KeyEvent e)
        {
                
        if ((qnum.getText().isEmpty()))
        { 
            badd.setEnabled(false);
            bfnd.setEnabled(false);
        }    
           
        else{
             bfnd.setEnabled(true);
             if (((qnom.getText().isEmpty())) || ((qpnm.getText().isEmpty())))
                badd.setEnabled(false); 
             else
                badd.setEnabled(true); 
           }
        }   
                    
/**
 **gestion des boutons par keyreleased
 * @param e argument
 */                
        
    public void keyReleased(KeyEvent e) 

        {
            if ((qnum.getText().isEmpty()))
        { 
            badd.setEnabled(false);
            bfnd.setEnabled(false);
        }    
           
        else{
             bfnd.setEnabled(true);
             if (((qnom.getText().isEmpty())) || ((qpnm.getText().isEmpty())))
                badd.setEnabled(false); 
             else
                badd.setEnabled(true); 
           }
            
        }
    
/**
 ** gestion des boutons par keytyped
 * @param e argument
 */                    
    public void keyTyped(KeyEvent e)                 
        {
            if ((qnum.getText().isEmpty()))
        { 
            badd.setEnabled(false);
            bfnd.setEnabled(false);
        }    
           
        else{
             bfnd.setEnabled(true);
             if (((qnom.getText().isEmpty())) || ((qpnm.getText().isEmpty())))
                badd.setEnabled(false); 
             else
                badd.setEnabled(true); 
           }
        }
    
/**
 ** gestion de l'affichage et restitution des resultat
 * @param ae argument
 */                            
    public void actionPerformed(ActionEvent ae)                
        {
            BufferedWriter bw = null;
            BufferedReader br = null;
            String mof;
            boolean trouvee = false;
            Object source = ae.getSource();
          
            int i=0;
            String nom = null;
            String pnm = null;
            String sex = null;
                                                                   
            JOptionPane jop = new JOptionPane();
/**
 ** gestion de la touche effacer
 * 
 */                                    
            if (source == bdel) {
                qnom.setText("");
                qnum.setText("");
                qpnm.setText("");
                badd.setEnabled(false);
                bfnd.setEnabled(false);
                
                
           }
/**
 ** gestion de la touche chercher
 * 
 */                                                 
            if (source == bfnd) 
               {
                    String lign2;
                                        
                    try{
                      StringTokenizer s = null;

                      br = new BufferedReader(new FileReader("eleve.txt")) ;
                       String num = null;                 
                        while ( (lign2 = br.readLine() )!= null && !trouvee){
                            s = new StringTokenizer(lign2);
                            num = s.nextToken();
                            if (num.compareTo(qnum.getText())==0){
                                trouvee =true;
                            }
                        }
                        if (trouvee){
                            nom = s.nextToken();
                            pnm = s.nextToken();
                            sex = s.nextToken();
                            qnum.setText(num);
                            qnom.setText(nom);
                            qpnm.setText(pnm);
                            
                            if (sex.compareTo("feminin")==0)
                               box1.setSelectedIndex(1);
                            else
                               box1.setSelectedIndex(0);
                                
                            badd.setEnabled(false);
                            bfnd.setEnabled(false);
                            }
                        if (!trouvee) {
                            String msg = "numero non trouvé " + qnum.getText() + " " ;
                            jop.showMessageDialog(null, msg ,"MSG INFO",JOptionPane.INFORMATION_MESSAGE);  
                            }     
                        }
                    catch(FileNotFoundException e) {e.printStackTrace();}
                    catch(IOException e)           {e.printStackTrace();} 
                 
                        
                    }
/**
 **gestion de la touche ajouter
 * 
 */                                                                
                if (source == badd) 
               {
                   int ind = box1.getSelectedIndex();
                   
                   if (ind == 1)
                       mof = "feminin";
                   else
                       mof = "masculin";
                   
                   String ligne;
                   String element = qnum.getText() + " " +
                                    qpnm.getText() + " " +
                                    qnom.getText() + " " +        
                                    mof;
                   try{
                      StringTokenizer s = null;

                      br = new BufferedReader(new FileReader("eleve.txt")) ;
                                        
                    while ( (ligne = br.readLine() )!= null && !trouvee){
                         s = new StringTokenizer(ligne);
                
                       
                             if (s.nextToken().compareTo(qnum.getText())==0){
                               // System.out.println(s.nextToken());
                                String msg = "numero existant";
                                jop.showMessageDialog(null, msg ,"MSG INFO",JOptionPane.INFORMATION_MESSAGE);
                                trouvee = true;
                                qnom.setText("");
                                qnum.setText("");
                                qpnm.setText("");
                                badd.setEnabled(false);
                                bfnd.setEnabled(false);
                             
                           }
                        
                    }
                 // instanciation du buffer
                // affect ligne suiv dans ligne
                if (!trouvee){
                     bw = new BufferedWriter(new FileWriter("eleve.txt",true)) ;
                     bw.write(element);                
                     bw.newLine();
                     String msg = qnum.getText() + " numero ajouté";
                     jop.showMessageDialog(null, msg ,"MSG INFO",JOptionPane.INFORMATION_MESSAGE);
                     qnom.setText("");
                     qnum.setText("");
                     qpnm.setText("");
                     badd.setEnabled(false);
                     bfnd.setEnabled(false);
             
                    bw.close();
                }
            } 
         catch(FileNotFoundException e) {e.printStackTrace();}
         catch(IOException e)           {e.printStackTrace();} 
                                        
               }           
               }
            
            
}
    
