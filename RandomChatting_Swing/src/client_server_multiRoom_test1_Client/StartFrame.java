package client_server_multiRoom_test1_Client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrame extends JFrame implements ActionListener {
	
	JLabel 	titleLabel 		= null;
	JButton	startButton		= null;
	JPanel	titlePanel		= null;
	JPanel	buttonPanel		= null;
	
	ServerConnector serverConnector = ServerConnector.getInstance();
	
	StartFrame() {
		titleLabel 		= new JLabel("< 랜덤 채팅 프로그램 >");
		startButton 	= new JButton("시작하기");
		titlePanel		= new JPanel();
		buttonPanel		= new JPanel();
		
		this.setTitle("다한채팅");
		this.setBounds(200, 200, 250, 200);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		startButton.addActionListener(this);
		
		titlePanel.add(titleLabel);
		buttonPanel.add(startButton);
		
		this.add(titlePanel);
		this.add(buttonPanel);
	
		this.setVisible(true);
		
		System.out.println(1);
		
		serverConnector.connect();
		
		System.out.println(2);
		
		// 프로그램 종료시
		addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
            	serverConnector.disconnect();
                System.out.println("Closed");
                e.getWindow().dispose();
            }
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			new ChatFrame();
		}
	}
	
}
