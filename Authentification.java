package projettp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Laurent Fonquerne
 */

public class Authentification extends JFrame implements ActionListener , KeyListener 
{
        JButton     but1 =      new JButton("Se Connecter :");
        
        JLabel      lab1 =      new JLabel("Nom d'utilisateur :");
        JLabel      lab2 =      new JLabel("Mot de Passe");
        JTextField  tex1 =      new JTextField();
        JTextField  tex2 =      new JTextField();
        static int nbconnexions = 0;

/** appel de la fenetre d'auhentification
 * 
 */
        
        public Authentification()
        {
        but1.setEnabled(false);
        this.setVisible(true);
        this.setTitle("Connexion");
        this.setSize(700,200);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        lab1.setBounds(020,20,150,20); tex1.setBounds(180,20,150,20); 
        lab2.setBounds(020,50,150,20); tex2.setBounds(180,50,150,20);
                                       but1.setBounds(180,80,150,20);
                
        this.getContentPane().add(lab1);
        this.getContentPane().add(tex1);
        this.getContentPane().add(lab2);
        this.getContentPane().add(tex2);
        this.getContentPane().add(but1);
                        
        this.but1.addActionListener(this);
        this.tex1.addKeyListener(this);
        this.tex2.addKeyListener(this);
        }

/** gestion du keypressed gestion des touches
 * 
 * @param e argument
 */
        
        public void keyPressed(KeyEvent e)
        {
        if ((tex1.getText().isEmpty()) || (tex2.getText().isEmpty()))
           but1.setEnabled(false);
        else
           but1.setEnabled(true);
        }
/** gestion du keyreleased gestion des touches
 * 
 * 
 * @param e argument
 */        
        
        public void keyReleased(KeyEvent e) 
        {
        if ((tex1.getText().isEmpty()) || (tex2.getText().isEmpty()))
           but1.setEnabled(false);
        else
           but1.setEnabled(true);
        }
/** gestion du keytyped gestion des touches
 * 
 * @param e argument
 */        
        public void keyTyped(KeyEvent e)                 
        {
        if ((tex1.getText().isEmpty()) || (tex2.getText().isEmpty()))
           but1.setEnabled(false);
        else
           but1.setEnabled(true);
        }
/** gestion de l'affichage fenêtre et restitution des résultats
 *  
 * @param ae argument
 */        
    
        public void actionPerformed(ActionEvent ae)                
        {
            Object source = ae.getSource();
            
            int choice    = 0;
            int somme     = 0;
            int moins     = 0;
            int produit   = 0;
            int divise    = 0;
            int resultat  = 0;
            String restxt = "00";
            String nom = "titi";
            String pwd = "titi" ; 
            Boolean okconnect = false ;       
            String msg = "xxx";
       
            JOptionPane jop = new JOptionPane();
            nom = tex1.getText();
            pwd = tex2.getText();
            
/** si authentification réussie en moins de 3 appels
 *     @see GestEleves
 ** sinon
 **    Fermeture de la fenetre et lancement de missile en corée du nord
 * 
 */            
          
            if (source == but1) 
               {
                if (nbconnexions >= 2)
                   dispose();
                else 
                   {
                    if ((tex1.getText().compareTo("laurent") == 0 ) && 
                        (tex2.getText().compareTo("laurent") == 0 ))
                       { 
                      //   System.out.println("nbconnexions : " + nbconnexions + "*" + nom + "*" + pwd + "*");  
                         msg = "authentification OK";
                         jop.showMessageDialog(null, msg ,"MSG INFO",JOptionPane.INFORMATION_MESSAGE);
                         GestEleves g = new GestEleves();
                       }
                    else
                        {
                        nbconnexions = nbconnexions + 1;
                        msg = "Nom d'utilisateur ou mot de passe invalide";
                        jop.showMessageDialog(null, msg ,"MSG INFO",JOptionPane.INFORMATION_MESSAGE);
                        }
                   } 
            
                }
        
        }           
}
