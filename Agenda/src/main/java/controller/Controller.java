package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" }) // caminhos
																												// definidos
// na index
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
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		// teste de conexao
		// dao.testeConexao();

		String action = request.getServletPath(); // abri uma requsição no servlet

		System.out.println(action);

		if (action.equals("/main")) {

			Contatos(request, response); // Herda as requisições do metodos contato
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			excluirContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void Contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando objeto que ira receber os dados do java Beans

		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Encaminhar a lista para agenda .jsp

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		/*
		 * Teste de recebimento lista
		 * 
		 * for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getId());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getTelefone());
		 * System.out.println(lista.get(i).getEmail());
		 * 
		 * }
		 */
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste recebimento
		/*
		 * System.out.println (request.getParameter("Nome")); System.out.println
		 * (request.getParameter("Telefone")); System.out.println
		 * (request.getParameter("Email"));
		 */
		// setar varaiveis java Beans

		contato.setNome(request.getParameter("Nome"));
		contato.setTelefone(request.getParameter("Telefone"));
		contato.setEmail(request.getParameter("Email"));
		// chamar metodo inserir contato passando o objeto contato
		dao.inserirContato(contato);
		// Redirecionar para Agenda.jsp

		response.sendRedirect("main");

	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id contato
		String id = request.getParameter("idcon");
		// Setar varaivel JavaBeans
		contato.setId(id);
		// Metodo class dao
		dao.selecionarContato(contato);
		// setar atributos do formulario com o conteudo Java Beans

		request.setAttribute("idcon", contato.getId());
		request.setAttribute("Nome", contato.getNome());
		request.setAttribute("Telefone", contato.getTelefone());
		request.setAttribute("Email", contato.getEmail());
		// Encaminhar para o editar.jsp

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
		// teste de recebimento
		// System.out.println(contato.getId());

	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar variaveis JavaBeans
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("Nome"));
		contato.setTelefone(request.getParameter("Email"));
		contato.setEmail(request.getParameter("Telefone"));

		// executar metodo para update

		dao.alterarcontato(contato);
		// redirecionar para o agenda.jsp com as alterações

		response.sendRedirect("main");
	}

	/**
	 * Excluir contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Excluir Contato
	protected void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("idcon");
		// Setar varaivel JavaBeans
		contato.setId(id);
		// executar o deletar contato

		dao.excluirContato(contato);

		// redirecionar para o agenda.jsp com as alterações

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
	//Gerar Relatorio em pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Document documento = new Document();
		
		try {
			//Tipo de conteudo
			response.setContentType("apllication/pdf");
			//nome conteudo 
			response.addHeader("Content-Disposition", "inline;filename= " +"contatos.pdf");
			//criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//Abrir o  docmuento para gerar conteudo
			documento.open();
			documento.add(new Paragraph ("Lista de contatos"));
			documento.add(new Paragraph(" "));
			//criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			//cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//colocar os contatos na tabela
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i< lista.size();i++){
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelefone());
				tabela.addCell(lista.get(i).getEmail());
				
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
