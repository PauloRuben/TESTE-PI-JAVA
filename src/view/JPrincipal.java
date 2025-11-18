package view;


import java.awt.EventQueue;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import dao.GeraRelatorio;
import model.Cliente;
import model.ModeloTabela;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBusca;
	private JTable table;
	//Lista de Clientes utilizando o ArrayList
	private ArrayList<Cliente> clientes;
	private JPrincipal jPrincipal;
	private TableRowSorter<ModeloTabela> rowSorter;//FILTRAGEM
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(null);
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
	public JPrincipal() {
		//Chamando o banco de dados
		this.jPrincipal = this;
		DAO dao = new DAO();
		try {
			clientes = dao.listarCliente();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro = new JCadastro(null, jPrincipal);
				//Setando a Localização da tela de cadastro
				jCadastro.setLocationRelativeTo(jCadastro);
				//para fechar somente a tela de cadastro(JCadastro)
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				//Deixando visivel
				jCadastro.setVisible(true);
			}
		});
		
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCadastrar.setBounds(29, 11, 152, 33);
		contentPane.add(btnCadastrar);
		

		
		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//FILTRANDO na tela
				filtrar();
			}
		});
		textFieldBusca.setBounds(203, 22, 536, 20);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 78, 916, 480);
		contentPane.add(scrollPane);
		
		//CRIA O MODELO DA TABELA COM A LISTA DE CLIENTES
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		
		table = new JTable();
		table.setModel(modeloTabela);
		//EVENTO DO MOUSE
		table.addMouseListener(new MouseAdaptar() {
			//METODO QUE ALTERA A TELA DE CADASTRO
			//LOGICA PARA CLICK BOTAO ESQUERDO
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					try {
						Cliente clienteSelecionado = dao.consultarCliente
						(modeloTabela.getValueAt(table.getSelectedRow(),0).toString());
						//INSTANCIOANDO O CADASTRO
						JCadastro jCadastro = new JCadastro(clienteSelecionado, jPrincipal);
						//SETANDO A LOCALIZAÇÃO DA TELA DE CADASTRO
						jCadastro.setLocationRelativeTo(jCadastro);
						//FECHA SOMENTE A TELA DE CADASTRO
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
					} catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		});
		//ROWSORTER RECEBE MODELO DA TABELA
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
		scrollPane.setViewportView(table);
		
		JButton btnRelatorio = new JButton("Gerar Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GeraRelatorio();
			}
		});
		
		btnRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRelatorio.setBounds(760, 11, 181, 33);
		contentPane.add(btnRelatorio);
		
		scrollPane.setViewportView(table);

	}
	
	private void filtrar() {
		String busca = textFieldBusca.getText().trim();
		
		//TESTE COMPRIMENTO DO TEXTO
		if(busca.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+busca));
		}
	}
}
