package view;

import model.Cliente;
import dao.DAO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldTelefone;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblEndereo;
	private JTextField textAreaEndereco;
	private JButton btnExcluir;
	private JButton btnIncluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
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
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 64, 25);
		contentPane.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 38, 399, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ:");
		lblCpfcnpj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpfcnpj.setBounds(10, 69, 103, 25);
		contentPane.add(lblCpfcnpj);
		
		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(10, 105, 184, 20);
		contentPane.add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(271, 69, 85, 25);
		contentPane.add(lblTelefone);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(237, 105, 172, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 136, 56, 25);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(10, 174, 399, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereo.setBounds(10, 205, 89, 25);
		contentPane.add(lblEndereo);
		
		textAreaEndereco = new JTextField();
		textAreaEndereco.setBounds(10, 239, 399, 108);
		contentPane.add(textAreaEndereco);
		textAreaEndereco.setColumns(10);
		
		btnIncluir = new JButton(clienteSelecionado==null?"Incluir":"Alterar");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(null, textFieldNome.getText(), textFieldCpfCnpj.getText(), 
						textFieldEmail.getText(), textFieldTelefone.getText(), textAreaEndereco.getText());
				if(clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						dao.cadastrarCliente(cliente);
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os campos: Nome e CPF/CNPJ");
						
					}
				} else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())) {
						dao.alterarCliente(clienteSelecionado.getId());
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os campos: Nome e CPF/CNPJ");
						
					}
				} 
			}
		});
		btnIncluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIncluir.setBounds(312, 358, 97, 34);
		btnIncluir.setVisible(false);
		contentPane.add(btnIncluir);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExcluir.setBounds(10, 358, 97, 34);
		contentPane.add(btnExcluir);
		
		if(clienteSelecionado!=null) {
			preencherCampos(clienteSelecionado);
			btnIncluir.setVisible(true);
		}

	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		textFieldNome.setText(clienteSelecionado.getNome());
		textFieldCpfCnpj.setText(clienteSelecionado.getCpfCnpj());
		textFieldEmail.setText(clienteSelecionado.getEmail());
		textFieldTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereco());	
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
}
