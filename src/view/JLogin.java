package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Criptografia;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField passwordFild;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null); //centraliza a janela da tela
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(32, 41, 497, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bem Vindo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(202, 40, 134, 23);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(49, 100, 167, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordFild = new JTextField();
		passwordFild.setColumns(10);
		passwordFild.setBounds(49, 169, 167, 20);
		panel.add(passwordFild);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criptografia criptografia = new Criptografia(passwordFild.getText(), Criptografia.MD5);
				System.out.println(criptografia.criptografar());//Senha Criptografada no console
				
				if(textFieldUsuario.getText() !=null &&
					!textFieldUsuario.getText().isEmpty() &&
					passwordFild.getText() !=null &&
					!passwordFild.getText().isEmpty()){
					if(criptografia.criptografar().equals("E10ADC3949BA59ABBE56E057F20F883E")) {
						JOptionPane.showMessageDialog(btnNewButton, "Informações Válidas");
						dispose();//Fecha a janela Login
						//Ligando a Tela de Login a PRINCIPAL
						JPrincipal jPrincipal = new JPrincipal();
						jPrincipal.setLocationRelativeTo(null);
						jPrincipal.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "Aviso", JOptionPane.WARNING_MESSAGE);
				
				}
			}
		});
		
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBounds(199, 226, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(49, 75, 56, 14);
		panel.add(lblNewLabel_1);
		
		JLabel passwordField = new JLabel("Senha:");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBounds(49, 143, 50, 14);
		panel.add(passwordField);
		
		


	}
}