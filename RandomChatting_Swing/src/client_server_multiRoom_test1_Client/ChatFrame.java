package client_server_multiRoom_test1_Client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client_server_multiRoom_test1_Server.Server;

public class ChatFrame extends JFrame implements ActionListener{
	
	JTextArea 	chatArea		= null;
	JTextField	chatField		= null;
	JButton		submitButton	= null;
	JPanel		chatPanel		= null;
	JPanel		inputPanel		= null;
	
	Server server = new Server();
	
	ChatFrame() {
		chatArea 		= new JTextArea(20, 28);
		chatField		= new JTextField(22);
		submitButton 	= new JButton("입력");
		chatPanel		= new JPanel();
		inputPanel		= new JPanel();
		
		this.setTitle("다한채팅");
		this.setBounds(200, 200, 400, 430);
		this.setLayout(new FlowLayout());
		
		chatField.addActionListener(this);
		submitButton.addActionListener(this);
		
		chatArea.setEditable(false); 
		
		chatPanel.add(chatArea);
		inputPanel.add(chatField);
		inputPanel.add(submitButton);
		
		this.add(chatPanel);
		this.add(inputPanel);
	
		this.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitButton || e.getSource() == chatField) {
			System.out.println("go!");
			return;
		}
	}
}
