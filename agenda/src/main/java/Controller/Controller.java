package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.DAO;
import Model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

		if (action.equals("/main")) {
			contato(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else if (action.equals("/update")) {
			editarContato(request, response);

		} else if (action.equals("/delete")) {
			removerContato(request, response);

		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);

		} else {
			response.sendRedirect("index.html");
		}

		// Teste de COnexao com o Banco de Dados
		// dao.testeConexao();
	}

	/**
	 * Contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar Contatos
	protected void contato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

//		//teste de recebimento da lista
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getNome());
//		}

		// Encaminar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

//		response.sendRedirect("agenda.jsp");
	};

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento dos dados do formulario
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));

		// setar as variaveis do JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o metodo inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	};

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do ID do contato que será editado
		String idcon = request.getParameter("idcon");

		// Setar variavel Javabeans
		contato.setIdcon(idcon);

		// Execultar o metodo selecionar contato
		dao.selecionarContato(contato);

		// teste de recebimento
//		System.out.println(contato.getIdcon());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());

		// Setar os atributos do formulario com o conteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		//teste de recebimento
//		System.out.println(request.getParameter("idcon"));
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));

		// Setar as variaveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Execultar o metodo alterarContato
		dao.alterarContato(contato);

		// Encaminhar ao documento agenda via man
		response.sendRedirect("main");
	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do contato a ser excluido (popup.js)
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		dao.deletarContato(contato);

		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatorio em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instancia do documento
		Document documento = new Document(PageSize.A4);
		// Definindo margens
		documento.setMargins(40f, 40f, 40f, 40f);

		try {
			// tipo de conteudo
			response.setContentType("application/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// Criar o documento
			response.reset();
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento -> conteudo
			documento.open();

			Paragraph linhaEmBranco = new Paragraph(new Phrase(20F, "                                                 ",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 16F)));
			linhaEmBranco.setAlignment(Element.ALIGN_CENTER);

			// Adicionando Imagem
			Image imagem = Image.getInstance(
					"C:\\Users\\aldeny.junior\\curso-youtube\\agenda\\src\\main\\webapp\\imagens\\agenda.png");
			imagem.scaleAbsolute(100F, 100F);
			imagem.setAlignment(Element.ALIGN_CENTER);
			documento.add(imagem);
			documento.add(linhaEmBranco);

			// Criando um paragrafo para o titulo do documento
			Paragraph tituloDoRelatorio = new Paragraph(
					new Phrase(20F, "LISTA DE CONTATOS", FontFactory.getFont(FontFactory.TIMES_BOLD, 16F)));
			tituloDoRelatorio.setAlignment(Element.ALIGN_CENTER);
			documento.add(tituloDoRelatorio);

			documento.add(linhaEmBranco);

			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			tabela.setWidthPercentage(100f);
			tabela.setWidths(new float[] { 30f, 30f, 40f });

			PdfPCell col1 = new PdfPCell(
					new Paragraph(new Phrase(16F, "Nome", FontFactory.getFont(FontFactory.TIMES_BOLD, 12F))));
			PdfPCell col2 = new PdfPCell(
					new Paragraph(new Phrase(16F, "Fone", FontFactory.getFont(FontFactory.TIMES_BOLD, 12F))));
			PdfPCell col3 = new PdfPCell(
					new Paragraph(new Phrase(16F, "Email", FontFactory.getFont(FontFactory.TIMES_BOLD, 12F))));

			col1.setBackgroundColor(new BaseColor(102, 187, 255));
			col2.setBackgroundColor(new BaseColor(102, 187, 255));
			col3.setBackgroundColor(new BaseColor(102, 187, 255));

			// Com da borda
			col1.setBorderColor(new BaseColor(128, 128, 128));
			col2.setBorderColor(new BaseColor(128, 128, 128));
			col3.setBorderColor(new BaseColor(128, 128, 128));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			// popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {

				String nome = lista.get(i).getNome();
				String fone = lista.get(i).getFone();
				String email = lista.get(i).getEmail();

				PdfPCell coluna1 = new PdfPCell(
						new Paragraph(new Phrase(20F, nome, FontFactory.getFont(FontFactory.TIMES_BOLD, 10F))));
				PdfPCell coluna2 = new PdfPCell(
						new Paragraph(new Phrase(20F, fone, FontFactory.getFont(FontFactory.TIMES_BOLD, 10F))));
				PdfPCell coluna3 = new PdfPCell(
						new Paragraph(new Phrase(20F, email, FontFactory.getFont(FontFactory.TIMES_BOLD, 10F))));

				coluna1.setBorderColor(new BaseColor(128, 128, 128));
				coluna2.setBorderColor(new BaseColor(128, 128, 128));
				coluna3.setBorderColor(new BaseColor(128, 128, 128));

				tabela.addCell(coluna1);
				tabela.addCell(coluna2);
				tabela.addCell(coluna3);
			}
			documento.add(tabela);

			documento.close();

		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
