package com.appServices.AppServices.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.domain.Avaliacoes;
import com.appServices.AppServices.domain.Categoria;
import com.appServices.AppServices.domain.Cidade;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.domain.Cursos;
import com.appServices.AppServices.domain.EnderecoCliente;
import com.appServices.AppServices.domain.EnderecoPrestador;
import com.appServices.AppServices.domain.Estado;
import com.appServices.AppServices.domain.Experiencias;
import com.appServices.AppServices.domain.ItensOrcamento;
import com.appServices.AppServices.domain.ItensPedido;
import com.appServices.AppServices.domain.ItensSolicitacao;
import com.appServices.AppServices.domain.Orcamento;
import com.appServices.AppServices.domain.PagamentoComCartao;
import com.appServices.AppServices.domain.FormaDePagamento;
import com.appServices.AppServices.domain.PagamentoComDinheiro;
import com.appServices.AppServices.domain.Pedido;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusPagamento;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoPagamento;
import com.appServices.AppServices.domain.enums.TipoPerfil;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.appServices.AppServices.domain.enums.TipoSituacao;
import com.appServices.AppServices.domain.enums.TipoUnidade;
import com.appServices.AppServices.repositories.AvaliacoesRepository;
import com.appServices.AppServices.repositories.CategoriaRepository;
import com.appServices.AppServices.repositories.CidadeRepository;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.repositories.CurriculoRepository;
import com.appServices.AppServices.repositories.CursosRepository;
import com.appServices.AppServices.repositories.EnderecoClienteRepository;
import com.appServices.AppServices.repositories.EnderecoPrestadorRepository;
import com.appServices.AppServices.repositories.EstadoRepository;
import com.appServices.AppServices.repositories.ExperienciasRepository;
import com.appServices.AppServices.repositories.ItensOrcamentoRepository;
import com.appServices.AppServices.repositories.ItensPedidoRepository;
import com.appServices.AppServices.repositories.ItensSolicitacaoRepository;
import com.appServices.AppServices.repositories.OrcamentoRepository;
import com.appServices.AppServices.repositories.FormaDePagamentoRepository;
import com.appServices.AppServices.repositories.PedidoRepository;
import com.appServices.AppServices.repositories.PrestadorRepository;
import com.appServices.AppServices.repositories.ProfissaoRepository;
import com.appServices.AppServices.repositories.SolicitacaoServicoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository clienteRespository;
	
	@Autowired
	private EnderecoClienteRepository enderecoClienteRespository;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private EnderecoPrestadorRepository enderecoPrestadorRepository;
	
	@Autowired
	private CategoriaRepository areaPorfissionalRepository;
	
	@Autowired
	private ProfissaoRepository servicosRepository;
	
	@Autowired
	private CurriculoRepository curriculoRepository;
	
	@Autowired
	private CursosRepository cursosRespository;
	
	@Autowired
	private ExperienciasRepository experienciasRespository;
	
	@Autowired
	private AvaliacoesRepository avaliacoesRespository;
	
	@Autowired
	private SolicitacaoServicoRepository solicitacaoRepository;
	
	@Autowired
	private ItensSolicitacaoRepository itensSolicitacaoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ItensOrcamentoRepository itensOrcamentoRespository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;
	
	@Autowired
	private ItensPedidoRepository itensPedidoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	

	
	public void instantiateTestDataBase()throws ParseException 	{
		
		
		// TODO Auto-generated method stub	
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
			
		//Cadastro de Cliente
		
		Cliente cli1 = new Cliente(null,"Dagle"," Anderson",data.parse("22/10/1994 22:00"),"1432756311","063176845960",TipoPessoa.FISICA,TipoSexo.MASCULINO,pe.encode("221094"),"dagle_life@hotmail.com");
		EnderecoCliente end1 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli1);
		cli1.setEndereco(end1);
		
		
		Cliente cli2 = new Cliente(null,"José ","",data.parse("05/12/1965 00:00"),"123453678","1234536789",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,pe.encode("221094"),"dagleandersonlima@gmail.com");	
		cli2.addPerfil(TipoPerfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end2 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli2);
		cli2.setEndereco(end2);
		Cliente cli3 = new Cliente(null,"Inove lima ","",data.parse("05/12/1965 00:00"),"123453678","789456123",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,pe.encode("221094"),"appservicesba@gmail.com");	
		cli3.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end3 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli3);
		cli3.setEndereco(end3);
		
		Cliente cli4 = new Cliente(null,"Jessica ","",data.parse("05/12/1965 00:00"),"123453678","147852369",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,pe.encode("221094"),"teste3@hotmail.com");	
		cli4.addPerfil(TipoPerfil.PRESTADOR);
		cli4.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end4 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli4);
		cli4.setEndereco(end4);
		
		Cliente cli5 = new Cliente(null,"Anderson Teste","",data.parse("05/12/1965 00:00"),"123453678","128745693",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,pe.encode("221094"),"teste4@hotmail.com");	
		cli5.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end5 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli5);
		cli5.setEndereco(end5);
		
				clienteRespository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
				enderecoClienteRespository.saveAll(Arrays.asList(end1,end2,end3,end4,end5));
				
		

		//Cadastro de Categoria e Profissao		
		Categoria areaProf1= new Categoria(null, "Construção & Reforma");		
		Profissao prof1 = new Profissao(null, "Pintor",areaProf1);
		Profissao prof2 = new Profissao(null, "Arquiteto",areaProf1);
		Profissao prof3 = new Profissao(null, "Engenheiro",areaProf1);
		Profissao prof4 = new Profissao(null, "Pintor",areaProf1);
		Profissao prof5 = new Profissao(null, "Arquiteto",areaProf1);
		Profissao prof6 = new Profissao(null, "Engenheiro",areaProf1);
		Profissao prof7 = new Profissao(null, "Pintor",areaProf1);
		Profissao prof8 = new Profissao(null, "Arquiteto",areaProf1);
		Profissao prof9 = new Profissao(null, "Engenheiro",areaProf1);
		Profissao prof10 = new Profissao(null, "Pintor",areaProf1);
		Profissao prof11 = new Profissao(null, "Arquiteto",areaProf1);
		Profissao prof12= new Profissao(null, "Engenheiro",areaProf1);
		
		Categoria areaProf2= new Categoria(null, "Tecnologia & ELetrônicos");		
		Profissao prof13 = new Profissao(null, "programador",areaProf2);
		Profissao prof14 = new Profissao(null, "Técnico em Informatica",areaProf2);
		Profissao prof15 = new Profissao(null, "Analista de sistemas",areaProf2);
		Profissao prof16 = new Profissao(null, "programador",areaProf1);
		Profissao prof17 = new Profissao(null, "Técnico em Informatica",areaProf2);
		Profissao prof18 = new Profissao(null, "Analista de sistemas",areaProf2);
		Profissao prof19 = new Profissao(null, "programador",areaProf2);
		Profissao prof20 = new Profissao(null, "Técnico em Informatica",areaProf2);
		Profissao prof21 = new Profissao(null, "Analista de sistemas",areaProf2);
		Profissao prof22 = new Profissao(null, "programador",areaProf2);
		Profissao prof23 = new Profissao(null, "Técnico em Informatica",areaProf2);
		Profissao prof24 = new Profissao(null, "Analista de sistemas",areaProf2);
		
		Categoria areaProf3= new Categoria(null, "Veículo automotores");		
		Profissao prof25 = new Profissao(null, "Mecânico",areaProf3);
		Profissao prof26= new Profissao(null, "Motorista",areaProf3);
		Profissao prof27= new Profissao(null, "Mecânico",areaProf3);
		Profissao prof28= new Profissao(null, "Motorista",areaProf3);
		Profissao prof29= new Profissao(null, "Mecânico",areaProf3);
		
		
		Categoria areaProf4 = new Categoria(null,"Saúde & Bem Estar");
		Profissao prof30= new Profissao(null, "Médico",areaProf4);
		Profissao prof31= new Profissao(null, "Instrutor",areaProf4);
		Profissao prof32= new Profissao(null, "Nutricionista",areaProf4);
		Profissao prof33= new Profissao(null, "Personal",areaProf4);
		
		Categoria areaProf5 = new Categoria(null,"Educação");
		Profissao prof34= new Profissao(null, "Professor de inglês",areaProf5);
		Profissao prof35= new Profissao(null, "Palestrante",areaProf5);

		Categoria areaProf6 = new Categoria(null,"Eletro & Domésticos");
		Profissao prof36= new Profissao(null, "montador de móveis",areaProf6);
		Profissao prof37= new Profissao(null, "eletricista",areaProf6);
		
			areaProf1.getProfissoes().addAll(Arrays.asList(
					prof1,prof2,prof3,
					prof4,prof5,prof6,
					prof7,prof8,prof9,
					prof10,prof11,prof12
					
					
					));
			areaProf2.getProfissoes().addAll(Arrays.asList(
					prof13,prof14,prof15,
					prof16,prof17,prof18,
					prof19,prof20,prof21,
					prof22,prof23,prof24
					));
			areaProf3.getProfissoes().addAll(Arrays.asList(
					prof25,prof26,prof27,
					prof28,prof29,prof30
					));
			areaProf4.getProfissoes().addAll(Arrays.asList(
					prof30,prof31,prof32,prof33
					));
			areaProf5.getProfissoes().addAll(Arrays.asList(
					prof34,prof35
					));
			areaProf6.getProfissoes().addAll(Arrays.asList(
					prof36,prof37
					));
			
			
			areaPorfissionalRepository.saveAll(Arrays.asList(areaProf1,areaProf2,areaProf3,areaProf4,areaProf5,areaProf6));
			servicosRepository.saveAll(Arrays.asList(
					prof1,prof2,prof3,
					prof4,prof5,prof6,
					prof7,prof8,prof9,
					prof10,prof11,prof12,
					prof13,prof14,prof15,
					prof16,prof17,prof18,
					prof19,prof20,prof21,
					prof22,prof23,prof24,
					prof25,prof26,prof27,
					prof28,prof29,prof30,
					prof31,prof32,prof33,
					prof34,prof35,prof36,prof37
					));
		
		//Cadsatro de Prestador	
		Prestador prest1 = new Prestador(null,"Anderson Pintor","Pinturas em geral",cli1.getEmail(),"domiciliar",prof1);	
		cli1.setPrestador(prest1);
		EnderecoPrestador end6 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest1);
		prest1.setEndereco(end6);
				
		Prestador prest2 = new Prestador(null,"Jennifer","Programadora",cli2.getEmail(),"Home Office",prof1);	
		cli2.setPrestador(prest2);
		EnderecoPrestador end7 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest2);
		prest2.setEndereco(end7);
		
		Prestador prest3 = new Prestador(null,"Engenheiros","casas em geral",cli3.getEmail(),"domiciliar",prof2);	
		cli3.setPrestador(prest3);
		EnderecoPrestador end8 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest3);
		prest3.setEndereco(end8);
		
		Prestador prest4 = new Prestador(null,"Anderson developer","sistemas em geral",cli4.getEmail(),"domiciliar",prof2);	
		cli4.setPrestador(prest4);
		EnderecoPrestador end9 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest4);
		prest4.setEndereco(end9);
		
		Prestador prest5 = new Prestador(null,"Solutech sistemas","sistemas em geral",cli5.getEmail(),"domiciliar",prof2);	
		cli5.setPrestador(prest5);
		EnderecoPrestador end10 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest5);
		prest5.setEndereco(end10);
		
		//Cadastro de Curriculo
				
		Curriculo c1 = new Curriculo(null, prest1);
		Curriculo c2 = new Curriculo(null, prest2);
		Cursos curso1 = new Cursos(null, "Oficina Coral", "Coral Titas Brasil","2 dias", c1);
		Cursos curso2 = new Cursos(null, "treinamento Suvenil ", "Suvenil tintas","7 dias ", c1);
		Cursos curso3 = new Cursos(null, "MBA full stack ", "IGTI","1 ano ", c2);
		Experiencias exp1 = new Experiencias(null,"Suvinil LTDA", "Pinto","2 anos",c1);
		c1.getCursos().addAll(Arrays.asList(curso1,curso2,curso3));
		c1.getExperiencias().addAll(Arrays.asList(exp1));
		
		prest1.setCurriculo(c1);
		prest2.setCurriculo(c2);
		
		//Solicitacao de Servico,Orcamento e Pedido
		
		SolicitacaoServico solicitacao1 = new SolicitacaoServico(null, "casa na chácara",data.parse("05/04/2019 00:00"), cli1, prof1,StatusSolicitacao.ABERTA);
		ItensSolicitacao itensSolicitacao1 = new ItensSolicitacao(null,
		"As paredes e o teto estão manchados e existem alguns pontos que precisarão de reparos(correções com massa corrida)", solicitacao1);
		ItensSolicitacao itensSolicitacao2 = new ItensSolicitacao(null, 
		"3", solicitacao1);
				
		ItensSolicitacao itensSolicitacao3 = new ItensSolicitacao(null, 
		"sala(3x4,forro de gesso,1 porta de ferro e uma janela de ferro), cozinha(4x4,forro de gesso,1 janela de ferro)", solicitacao1);	
		ItensSolicitacao itensSolicitacao4 = new ItensSolicitacao(null,
		"Barreiras", solicitacao1);
		ItensSolicitacao itensSolicitacao5 = new ItensSolicitacao(null,
		"Dentro da cidade", solicitacao1);
		ItensSolicitacao itensSolicitacao6 = new ItensSolicitacao(null,
		"15 dias", solicitacao1);
		ItensSolicitacao itensSolicitacao7 = new ItensSolicitacao(null,
		"pintura e reparado das paredes com massa corrida. Textura em uma parede da sala e pintura daa janela", solicitacao1);
		ItensSolicitacao itensSolicitacao8 = new ItensSolicitacao(null,
				"---------------", solicitacao1);
		
		
		solicitacao1.getItemServico().addAll(Arrays.asList(
				itensSolicitacao1,itensSolicitacao2,itensSolicitacao3,
				itensSolicitacao4,itensSolicitacao5,itensSolicitacao6,
				itensSolicitacao7,itensSolicitacao8));
		                  
	   /**SolicitacaoServico solicitacao2 = new SolicitacaoServico(null, "reforma do sofa",data.parse("06/04/2019 00:00"), cli3, prof1,StatusSolicitacao.ABERTA);
		ItensSolicitacao itensSolicitacao9 = new ItensSolicitacao(null, "couro rasgodo e meio quebrado", solicitacao2);
		ItensSolicitacao itensSolicitacao10 = new ItensSolicitacao(null, "quebrado", solicitacao2);
		ItensSolicitacao itensSolicitacao11 = new ItensSolicitacao(null, "reforma geral", solicitacao2);
		ItensSolicitacao itensSolicitacao12 = new ItensSolicitacao(null, "quebrado", solicitacao2);
		ItensSolicitacao itensSolicitacao13 = new ItensSolicitacao(null, "reforma geral", solicitacao2);
		ItensSolicitacao itensSolicitacao14 = new ItensSolicitacao(null, "quebrado", solicitacao2);
		ItensSolicitacao itensSolicitacao15 = new ItensSolicitacao(null, "reforma geral", solicitacao2);
		ItensSolicitacao itensSolicitacao16 = new ItensSolicitacao(null, "quebrado", solicitacao2); 

		solicitacao2.getItemServico().addAll(Arrays.asList(itensSolicitacao1,itensSolicitacao2,itensSolicitacao3));
		
		SolicitacaoServico solicitacao3 = new SolicitacaoServico(null, "reforma do sofa",data.parse("06/04/2019 00:00"), cli1, prof2,StatusSolicitacao.FECHADA);
		ItensSolicitacao itensSolicitacao7 = new ItensSolicitacao(null, "couro rasgodo e meio quebrado", solicitacao3);
		ItensSolicitacao itensSolicitacao8 = new ItensSolicitacao(null, "quebrado", solicitacao3);
		ItensSolicitacao itensSolicitacao9 = new ItensSolicitacao(null, "reforma geral", solicitacao3);
		solicitacao3.getItemServico().addAll(Arrays.asList(itensSolicitacao7,itensSolicitacao8,itensSolicitacao9));**/
		

		
		Orcamento orcamento1 = new Orcamento(null,"computador",data.parse("10/04/2019 22:00"), prest1, cli1 ,0.0,null, TipoSituacao.PENDENTE, solicitacao1);
		ItensOrcamento itensOrc1 = new ItensOrcamento(null, "memória", 1.0,TipoUnidade.UN, 0.0, 200.0, orcamento1);
		ItensOrcamento itensOrc2 = new ItensOrcamento(null, "formatação", 1.0,TipoUnidade.MT, 0.0, 80.0, orcamento1);
		ItensOrcamento itensOrc3 = new ItensOrcamento(null, "limpeza", 1.0,TipoUnidade.KG, 0.0, 20.0, orcamento1);
		orcamento1.getItensOrcamento().addAll(Arrays.asList(itensOrc1,itensOrc2,itensOrc3));
		FormaDePagamento pag1 = new PagamentoComCartao(null, orcamento1,1);
		orcamento1.setFormaDePagamento(pag1);
		
		Orcamento orcamento2 = new Orcamento(null,"computador",data.parse("11/04/2019 22:00"), prest1, cli1 ,0.0,null, TipoSituacao.PENDENTE, solicitacao1);
		ItensOrcamento itensOrc4 = new ItensOrcamento(null, "memória", 1.0,TipoUnidade.UN, 0.0, 200.0, orcamento2);
		ItensOrcamento itensOrc5 = new ItensOrcamento(null, "formatação", 1.0,TipoUnidade.MT, 0.0, 80.0, orcamento2);
		ItensOrcamento itensOrc6 = new ItensOrcamento(null, "limpeza", 1.0,TipoUnidade.KG, 0.0, 20.0, orcamento2);
		orcamento2.getItensOrcamento().addAll(Arrays.asList(itensOrc1,itensOrc2,itensOrc3));
		FormaDePagamento pag2 = new PagamentoComDinheiro(null, orcamento2,1);
		orcamento2.setFormaDePagamento(pag2);
		
		Orcamento orcamento3 = new Orcamento(null,"computador",data.parse("11/04/2019 22:00"), prest1, cli1 ,0.0,null, TipoSituacao.ANALISE, solicitacao1);
		ItensOrcamento itensOrc7 = new ItensOrcamento(null,"limpeza", 1.0,TipoUnidade.KG, 0.0, 20.0, orcamento3);
		ItensOrcamento itensOrc8 = new ItensOrcamento(null, "limpeza", 1.0,TipoUnidade.KG, 0.0, 20.0,orcamento3);
		ItensOrcamento itensOrc9 = new ItensOrcamento(null, "limpeza", 1.0,TipoUnidade.KG, 0.0, 20.0,orcamento3);
		orcamento3.getItensOrcamento().addAll(Arrays.asList(itensOrc7,itensOrc8,itensOrc9));
		FormaDePagamento pag3 = new PagamentoComDinheiro(null, orcamento3,1);
		orcamento3.setFormaDePagamento(pag3);

		/**Pedido pedido1 = new Pedido(null,"computador", prest1, cli2 ,0.0,data.parse("10/04/2019 22:00"), TipoSituacao.APROVADO,StatusPagamento.ABERTO, orcamento1);
		ItensPedido itensPed1 = new ItensPedido(null, "memória", 1.0, 0.0, 200.0, pedido1);
		ItensPedido itensPed2 = new ItensPedido(null, "formatação", 1.0, 0.0, 80.0, pedido1);
		ItensPedido itensPed3 = new ItensPedido(null, "limpeza", 1.0, 0.0, 20.0, pedido1);
		pedido1.getItensPedido().addAll(Arrays.asList(itensPed1,itensPed2,itensPed3));**/
		
		 
		 //Estados
		 Estado estado1 = new Estado(null, "Bahia");
		 Estado estado2 = new Estado(null, "Piaui");
		 
		 
		 //Cidades 
		 Cidade cidade1 = new Cidade(null, "Barrerias", estado1);
		 Cidade cidade2 = new Cidade(null, "Luis Eduardo Magalhães", estado1);
		 Cidade cidade3 = new Cidade(null, "Formosa do Rio Preto", estado1);
		 Cidade cidade4 = new Cidade(null, "São Desidério", estado1);
		 Cidade cidade5 = new Cidade(null, "Roda Velha", estado1);
		 
		 Cidade cidade6 = new Cidade(null, "Corrente", estado2);
		 Cidade cidade7 = new Cidade(null, "Terezina", estado2);
		
		
		//Avaliações de clientes
		Avaliacoes aval1 = new Avaliacoes(null, cli2, prest1, 5.0, "Um dos melhores pintores que ja contratei na vida");
		 prest1.getAvaliacoes().addAll(Arrays.asList(aval1));
		 Avaliacoes aval2 = new Avaliacoes(null, cli2, prest1, 3.0, "Minha casa ficou top");
		 prest1.getAvaliacoes().addAll(Arrays.asList(aval1));
		 Avaliacoes aval3 = new Avaliacoes(null, cli2, prest1, 1.0, "deixou tudo limpo depois do serviço");
		 prest1.getAvaliacoes().addAll(Arrays.asList(aval1));
		 Avaliacoes aval4 = new Avaliacoes(null, cli2, prest1, 1.0, "recomendo sempre!");
		 prest1.getAvaliacoes().addAll(Arrays.asList(aval1,aval2,aval3,aval4));
		 
		
		
		//savando dados em  BD
		 prestadorRepository.saveAll(Arrays.asList(prest1,prest2,prest3,prest4,prest5));
		enderecoPrestadorRepository.saveAll(Arrays.asList(end6,end7,end8,end9,end10));
		
		curriculoRepository.saveAll(Arrays.asList(c1,c2));
		cursosRespository.saveAll(Arrays.asList(curso1,curso2,curso3));
		experienciasRespository.saveAll(Arrays.asList(exp1));
		 
		 avaliacoesRespository.saveAll(Arrays.asList(aval1));
		 
		 solicitacaoRepository.saveAll(Arrays.asList(solicitacao1/**,solicitacao2,solicitacao3**/));
		 itensSolicitacaoRepository.saveAll(Arrays.asList(
				 itensSolicitacao1,itensSolicitacao2,itensSolicitacao3,
				 itensSolicitacao4,itensSolicitacao5,itensSolicitacao6,
				 itensSolicitacao7,itensSolicitacao8));
		 
		 orcamentoRepository.saveAll(Arrays.asList(orcamento1,orcamento2,orcamento3));
		 itensOrcamentoRespository.saveAll(Arrays.asList(
				 itensOrc1,itensOrc2,itensOrc3,
				  itensOrc4,itensOrc5,itensOrc6,
				 itensOrc7,itensOrc8,itensOrc9
				 ));
		 
		 formaDePagamentoRepository.saveAll(Arrays.asList(pag1,pag2,pag3));
		 
		 /** pedidoRepository.saveAll(Arrays.asList(pedido1));
		 itensPedidoRepository.saveAll(Arrays.asList(itensPed1,itensPed2,itensPed3));**/
		 
		 
		 estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		 cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3,cidade4,cidade5,cidade6,cidade7));
		 
		 
		 
		 
		 
		 
	}

}
