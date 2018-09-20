package client_server_multiRoom_test1_Server;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel 	titleLabel 		= null;
	JButton	startButton		= null;
	JButton	stopButton		= null;
	JPanel	titlePanel		= null;
	JPanel	buttonPanel		= null;
	
	Server server = new Server();
	
	MainFrame() {
		titleLabel 		= new JLabel("< 랜덤 채팅 프로그램 >");
		startButton 	= new JButton("실행");
		stopButton 		= new JButton("종료");
		titlePanel		= new JPanel();
		buttonPanel		= new JPanel();
		
		this.setTitle("다한채팅");
		this.setBounds(200, 200, 250, 200);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		titlePanel.add(titleLabel);
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		
		this.add(titlePanel);
		this.add(buttonPanel);
	
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			server.start();
			return;
		} else if (e.getSource() == stopButton) {
			server.stop();
			return;
		} 
	}

}
