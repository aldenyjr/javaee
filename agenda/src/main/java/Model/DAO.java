package Model;

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
	// Modelo de Conexão

	/** The driver. */
	// Parametros de Conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "s3rvic3!08";

	/**
	 * Gets the connect database.
	 *
	 * @return the connect database
	 */
	// Método de Conexão
	private Connection getConnectDatabase() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Teste de Conexão - Usando de forma ditatica.
	// public void testeConexao() {
	// try {
	// Connection con = getConnectDatabase();
	// System.out.println("MYSQL Connect Sucess?: " + con);
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	/* CRUD CREATE */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";

		try {
			// abrir conexão com o banco de dados
			Connection con = getConnectDatabase();

			// preparar a query para execucao no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// subistituir os paramentos (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();

			// Encerrar conexao com o banco de dados
			con.close();
			System.out.println("USUARIO CRIADO: " + contato.getNome());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD READ */

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando objeto para acessar a classe Javabeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by nome";

		try {
			Connection con = getConnectDatabase();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			// o laço será executado enquanto houevr contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados do banco de dados
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				// armazenar tudo no vetor dinamico ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));

			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	// Selecionando o contato.
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon=?";
		try {
			Connection con = getConnectDatabase();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variaveis Javabenas
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	// Editar o contato
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?,email=? where idcon=?";

		try {
			Connection con = getConnectDatabase();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
			System.out.println("USUARIO: " + contato.getNome() + ", Foi EDITADO!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD DELETE */
	// Ecluir um contato

	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon = ?";
		try {
			Connection con = getConnectDatabase();
			PreparedStatement pst = con.prepareStatement(delete);

			pst.setString(1, contato.getIdcon());
//			pst.execute();
			pst.executeUpdate();
			con.close();
			
			System.out.println("USUARIO: " + contato.getNome() + ", FOI EXCLUIDO!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
