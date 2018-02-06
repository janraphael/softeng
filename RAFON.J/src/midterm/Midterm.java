package midterm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Midterm extends JFrame implements ActionListener
{
	String[] city= {"Select City","Quezon City","Olongapo City","Makati City","Davao City"};
	private JButton btSubmit;
	private JLabel lbName, lbPassword,lbConfirm,lbCity,lbGender,lbGmail;
	private JTextField tfName,tfGmail;
	private JPasswordField pfPassword, pfConfirm;
	private JRadioButton rbMale,rbFemale;
	private JComboBox cbCity;
	public Midterm() 
	{
		setLayout(null);
		lbName = new JLabel("Name:");
		add(lbName).setBounds(40,20,100,30);
		tfName = new JTextField();
		add(tfName).setBounds(250, 20, 200, 30);
		
		lbPassword = new JLabel("Password:");
		add(lbPassword).setBounds(40, 60, 100, 30);
		pfPassword = new JPasswordField();
		add(pfPassword).setBounds(250, 60, 200, 30);
		
		lbConfirm = new JLabel("Confirm Password:");
		add(lbConfirm).setBounds(40, 100, 200, 30);
		pfConfirm = new JPasswordField();
		add(pfConfirm).setBounds(250, 100, 200, 30);
		
		lbCity = new JLabel("City:");
		add(lbCity).setBounds(40, 140, 100, 30);
		cbCity = new JComboBox(city);
		add(cbCity).setBounds(250, 140, 200, 30);
		
		lbGender = new JLabel("Gender");
		add(lbGender).setBounds(40, 180, 100, 30);
		rbMale = new JRadioButton("Male");
		add(rbMale).setBounds(250, 180, 100, 30);
		rbFemale = new JRadioButton("Female");
		add(rbFemale).setBounds(250, 210, 100, 30);
		
		lbGmail = new JLabel("Gmail:");
		add(lbGmail).setBounds(40, 250, 100,30);
		tfGmail = new JTextField();
		add(tfGmail).setBounds(250, 250, 200, 30);
		
		btSubmit = new JButton("Submit!");
		add(btSubmit).setBounds(150, 300, 200, 30);
		btSubmit.addActionListener(this);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbMale);
		group.add(rbFemale);
		
		setSize(500,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		Theme();
		Midterm mid= new Midterm();
	}
	public static void Theme()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		SettersAndGetters sg = new SettersAndGetters();
		if(e.getSource() == btSubmit)
		{
			if(tfName.getText().equals("") || pfPassword.getText().equals("") || pfConfirm.getText().equals("")
					|| tfGmail.getText().equals("")|| !(rbMale.isSelected()|| rbFemale.isSelected()) )
			{
				JOptionPane.showMessageDialog(null, "Please Fill-up the needed information!");
			}
			else if (cbCity.getSelectedItem().equals("Select City"))
			{
				JOptionPane.showMessageDialog(null, "Invalid City!");
			}
			else if(!(pfConfirm.getText().equals(pfPassword.getText())))
			{
				JOptionPane.showMessageDialog(null, "the password is not the same");
			}
			else if(!(tfGmail.getText().contains("@")&&tfGmail.getText().contains(".")))
			{
				JOptionPane.showMessageDialog(null, "invalid Gmail!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You are now registered!");
				sg.setName(tfName.getText());
				sg.setPassword(pfPassword.getText());
				sg.setConfirm(pfConfirm.getText());
				sg.setCity(cbCity.getSelectedItem().toString());
				if(rbMale.isSelected())
				{
					sg.setGender(rbMale.getText());
				}
				if(rbFemale.isSelected())
				{
					sg.setGender(rbFemale.getText());
				}
				sg.setGmail(tfGmail.getText());
				FileWriter fw;
				try {
					fw = new FileWriter("C:\\Users\\Rap Rap\\workspace\\RAFON.J\\src\\midterm\\Output.txt");
					fw.append("Name:" +sg.getName()+"; ");
					fw.append("Password:" +sg.getPassword()+"; ");
					fw.append("Confirm Password:" +sg.getConfirm()+"; ");
					fw.append("City:" + sg.getCity() +"; ");
					fw.append("Gender:" + sg.getGender() +"; ");
					fw.append("Gmail:" + sg.getGmail() +"; ");
					fw.close();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
	}
}
