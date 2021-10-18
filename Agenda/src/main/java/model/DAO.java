package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// Modulo de conexao
	// 1 parte Parametro de conexao

	/** The drive. */
	private String drive = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The senha. */
	private String senha = "nitro45";

	// 2 parte Metodo de conexao

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {

		Connection con = null; // Estabelece a conexao
		try {
			Class.forName(drive);
			con = DriverManager.getConnection(url, user, senha); // Se conecta no banco de dados
			return con;
		} catch (Exception e) {

			System.out.println(e);

			return null;
		}

	}
	// Criacao do crud

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO contatos (`Nome`, `Telefone`, `Email`) VALUES (?,?,?)";

		try {
			// abrir conexao banco
			Connection con = conectar();
			// Preparar a query para execução no banco
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir parametros (?) por conteudos das variaveis java Beans

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			// Executar query

			pst.executeUpdate();
			// Encerra conexao

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	// Listar
	public ArrayList<JavaBeans> listarContatos() {
		// Criando objeto para acessar a classe Java Beans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "Select * from contatos order by Nome";

		try {
			// Abrindo a conexao
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// USADO PARA ARAMZENAR O RETORNO DO BANCO E DADOS MOMONTANEAMENTE
			ResultSet rs = pst.executeQuery();
			// laço abaixo será executado enquanto houver contatos

			while (rs.next()) {
				// variaveis de apoio que recebe o nome do banco
				String id = rs.getString(1);
				String Nome = rs.getString(2);
				String Telefone = rs.getString(3);
				String Email = rs.getString(4);
				// populando o ArrayList

				contatos.add(new JavaBeans(id, Nome, Telefone, Email));

			}
			con.close();
			return contatos;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	//Selecionar contato
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		
		String read2 = "select * from contatos where idcon = ? ";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			
		while (rs.next()) {
			//setar a variveis
			contato.setId(rs.getString(1));
			contato.setNome(rs.getString(2));
			contato.setTelefone(rs.getString(3));
			contato.setEmail(rs.getString(4));

		}
		
		con.close();
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
	
	//Update
	
	/**
	 * Alterarcontato.
	 *
	 * @param contato the contato
	 */
	public void alterarcontato(JavaBeans contato) {
			
		String create = "update contatos set Nome=?,Telefone=?,Email=? where idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getId());

			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
				System.out.println(e);
		}
	}
	
	/**
	 * Excluir contato.
	 *
	 * @param contato the contato
	 */
	public void excluirContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getId());//subtitui o ? pelo id do contato
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}

