package view;

import java.awt.Container;
import javax.swing.*;
import java.awt.event.*;

public class HelpSwing extends JPanel 
{
  private JButton button1, button2;
  private JLabel label;
  
  public static void main(String[] args) {
    new HelpSwing();
  }

  public HelpSwing()
  {
        
    Clicklistener click = new Clicklistener();

    button1 = new JButton ("ⓘ Tutoriel");
    button1.setBounds(40,100,100,40);
    button1.addActionListener(click);

    button2 = new JButton ("Contacts");
    button2.setBounds(150,100,100,40);
    button2.addActionListener(click);
    
    label = new JLabel();
    label.setBounds(100,150,150,20);

    this.add(button1);
    this.add(button2);
    this.add(label);
    this.setVisible(true);
  }
    
  private class Clicklistener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == button1)
      {
	final JFrame tutorial = new TutorialSwing();
      }
      
      if (e.getSource() == button2)
      {
	final JFrame contact = new ContactSwing();
      }
    }
  }
}
