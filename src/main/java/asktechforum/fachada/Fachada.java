package asktechforum.fachada;

import java.util.ArrayList;
import java.util.List;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.dominio.Usuario;
import asktechforum.negocio.UsuarioBC;



import asktechforum.negocio.CadastroPerguntaBC;
import asktechforum.negocio.CadastroRespostaBC;
import asktechforum.negocio.VotoBC;

/**
 * 
 * Classe responsavel por intermediar a camada controller e camada de negocio.
 * Centraliza todas as solicitacoes de servicos da interface para a camada de negocio
 *
 */
public class Fachada {
	private static Fachada instancia;
	private UsuarioBC usuarioBC;
	private CadastroPerguntaBC cadastroPerguntaBC;
	private CadastroRespostaBC cadastroRespostaBC;
	private VotoBC votoBC;
	
	private Fachada(){
		this.cadastroPerguntaBC = new CadastroPerguntaBC();
		this.usuarioBC = new UsuarioBC();
		this.cadastroRespostaBC = new CadastroRespostaBC();
		this.votoBC = new VotoBC();
	}
	
	//Utilizacao do padrao singleton para garantir que apenas uma instancia da fachada sera 
	//usada na execucao do sistema.
	public static Fachada getInstance(){
		if(instancia == null){
			instancia = new Fachada(); 			
		}
		return instancia;
	}
	
	//========================================================================================
	//Chamadas de servicos para a classe que representa as 
	//regras de negocio para o domain model usuario
	public Usuario fachadaConsultarUsuarioPorEmail_Senha(String email_form, String senha_form){
		return this.usuarioBC.consultarUsuarioPorEmail_Senha(email_form, senha_form);
	}	
	
	public Usuario fachadaConsultarUsuarioPorId(int idUsuario){
		return this.usuarioBC.consultarUsuarioPorId(idUsuario);
		
	}
	
	public int fachadaConsultarQuantidadeAdmin(Usuario usuario) {
		return this.usuarioBC.consultarQuantidadeAdmin(usuario);
		
	}
	
	public Usuario fachadaConsultarUsuarioPorEmail(String email) {
		return this.usuarioBC.consultarUsuarioPorEmail(email);
		
	} 
	
	public String fachadaFormatarDataSQL(String dataString){
		return this.usuarioBC.formatarDataSQL(dataString);
	}
	
	public boolean fachadaVerificarEmail(String email, Usuario usuario) {
		return this.usuarioBC.verificarEmail(email, usuario);
		
	}
	
	public boolean fachadaAlterarUsuario(Usuario usuario){
		return this.usuarioBC.alterarUsuario(usuario);
		
	}
	
	public boolean fachadaAlterarUsuarioAdmin(Usuario usuario){
		return this.usuarioBC.alterarUsuarioAdmin(usuario);
		
	}
	
	public boolean fachadaAdicionarUsuario(Usuario usuario){
		return this.usuarioBC.adicionarUsuario(usuario);
		
	}
	public void fachadaDeletarUsuarioPorId(int idUsuario) {
		this.usuarioBC.deletarUsuarioPorId(idUsuario);
	}
	
	public void fachadaDeletarUsuario(String email) {
		this.usuarioBC.deletarUsuario(email);
		
	}
	
	public List<Usuario> fachadaConsultarTodosUsuarios() {
		return this.usuarioBC.consultarTodosUsuarios();
	}
	
	public List<Usuario> fachadaConsultarUsuarioPorNome(String nome) {
		return this.usuarioBC.consultarUsuarioPorNome(nome);
		
	}
	
	public void sendEmailEsqueciSenha(Usuario usuario) {
		this.usuarioBC.sendEmailEsqueciSenha(usuario);
	}
	
	//========================================================================================
	//Chamadas de servicos para a classe que representa as 
	//regras de negocio para o domain model Pergunta
	public String fachadaAdcionarPergunta(Pergunta pergunta)  {
		return this.cadastroPerguntaBC.adcionarPergunta(pergunta);
		
	}
	
	public String fachadaAlterarPergunta(Pergunta pergunta){
		return this.cadastroPerguntaBC.alterarPergunta(pergunta);
		
	}
	
	public ArrayList<String> FachadaConsultaTodasAsTags() {
		return this.cadastroPerguntaBC.consultaTodasAsTags();
	}
	
	public ArrayList<ResultConsultarPergunta> fachadaConsultarPerguntaPorTag(String tag){
		return this.cadastroPerguntaBC.consultarPerguntaPorTag(tag);
	}
	
	public Pergunta fachadaConsultarPerguntaPorIdPergunta(int id) {
		return this.cadastroPerguntaBC.consultarPerguntaPorIdPergunta(id);
	}
	
	public void fachadaDeletarPergunta(int id) {
		this.cadastroPerguntaBC.deletarPergunta(id);
	}
	
	//========================================================================================
	//Chamadas de servicos para a classe que representa as 
	//regras de negocio para o domain model Resposta
	public void fachadaAdicionarVotoResposta(int id){
		this.cadastroRespostaBC.adicionarVotoResposta(id); 
		
	}
	
	public ArrayList<Resposta> fachadaConsultarRespostaPorPergunta(int id) {
		return this.cadastroRespostaBC.consultarRespostaPorPergunta(id);
	}
	
	public String fachadaAdicionarResposta(Resposta resposta) {
		return this.cadastroRespostaBC.adicionarResposta(resposta);
	}
	
	public String fachadaAlterarResposta(Resposta resposta){
		return this.cadastroRespostaBC.alterarResposta(resposta);
	}
	
	public void fachadaRemoverVotoResposta(int id){
		this.cadastroRespostaBC.removerVotoResposta(id);
		
	}
	
	public void fachadaNotificarContribuintesPerg(int idPergunta, int idUsuario){
		this.cadastroRespostaBC.notificarContribuintesPerg(idPergunta, idUsuario);
	}
	
	public Resposta fachadaConsultarRespostaPorIdResposta(int id)  {
		return this.cadastroRespostaBC.consultarRespostaPorIdResposta(id);
		
	}
	
	public void fachadaDeletarResposta(int id) {
		this.cadastroRespostaBC.deletarResposta(id);
	}
	
	//========================================================================================
	//Chamadas de servicos para a classe que representa as 
	//regras de negocio para o domain model Voto
	public void fachadaAdicionarVotoUsuario(int idUsuario, int idResposta) {
		this.votoBC.adicionarVotoUsuario(idUsuario, idResposta);
		
	}
	
	public void fachadaDeletarUsuarioVoto(int idUsuario, int idResposta) {
		this.votoBC.deletarUsuarioVoto(idUsuario, idResposta);
	}
	
}
