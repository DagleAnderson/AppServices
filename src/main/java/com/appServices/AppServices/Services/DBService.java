package com.appServices.AppServices.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.domain.Avaliacoes;
import com.appServices.AppServices.domain.Categoria;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.Curriculo;
import com.appServices.AppServices.domain.Cursos;
import com.appServices.AppServices.domain.EnderecoCliente;
import com.appServices.AppServices.domain.EnderecoPrestador;
import com.appServices.AppServices.domain.Experiencias;
import com.appServices.AppServices.domain.ItensSolicitacao;
import com.appServices.AppServices.domain.Prestador;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.appServices.AppServices.repositories.AvaliacoesRepository;
import com.appServices.AppServices.repositories.CategoriaRepository;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.repositories.CurriculoRepository;
import com.appServices.AppServices.repositories.CursosRepository;
import com.appServices.AppServices.repositories.EnderecoClienteRepository;
import com.appServices.AppServices.repositories.EnderecoPrestadorRepository;
import com.appServices.AppServices.repositories.ExperienciasRepository;
import com.appServices.AppServices.repositories.ItensSolicitacaoRepository;
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
	
	public void instantiateTestDataBase()throws ParseException 	{
		
		
		// TODO Auto-generated method stub	
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
			
		//Cadastro de Cliente
		
		Cliente cli1 = new Cliente(null,"Dagle"," Anderson",data.parse("22/10/1994 22:00"),"1432756311","063176845960",TipoPessoa.FISICA,TipoSexo.MASCULINO,"Dagle22",pe.encode("221094"),"dagle_life@hotmail.com");
		EnderecoCliente end1 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli1);
		cli1.setEndereco(end1);
		
		
		Cliente cli2 = new Cliente(null,"José ","",data.parse("05/12/1965 00:00"),"123453678","1234536789",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,"Jose22",pe.encode("221094"),"teste@hotmail.com");	
		cli2.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end2 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli2);
		cli2.setEndereco(end2);
		
		Cliente cli3 = new Cliente(null,"Inove lima ","",data.parse("05/12/1965 00:00"),"123453678","789456123",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,"Jose224",pe.encode("221094"),"test2e@hotmail.com");	
		cli3.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end3 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli3);
		cli3.setEndereco(end3);
		
		Cliente cli4 = new Cliente(null,"Jessica ","",data.parse("05/12/1965 00:00"),"123453678","147852369",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,"Jose225",pe.encode("221094"),"teste3@hotmail.com");	
		cli4.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end4 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli4);
		cli4.setEndereco(end4);
		
		Cliente cli5 = new Cliente(null,"Anderson Teste ","",data.parse("05/12/1965 00:00"),"123453678","128745693",TipoPessoa.JURIDICA,TipoSexo.MASCULINO,"Jose226",pe.encode("221094"),"teste4@hotmail.com");	
		cli5.getTelefones().addAll(Arrays.asList("77-991489740"));
		EnderecoCliente end5 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio", cli5);
		cli5.setEndereco(end5);
		
				clienteRespository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
				enderecoClienteRespository.saveAll(Arrays.asList(end1,end2,end3,end4,end5));
				
		

		//Cadastro de Categoria e Profissao		
		Categoria areaProf1= new Categoria(null, "Construção e Reforma");		
		Profissao prof1 = new Profissao(null, "Pintor",areaProf1);
		Profissao prof2 = new Profissao(null, "Arquiteto",areaProf1);
		Profissao prof3 = new Profissao(null, "Engenheiro",areaProf1);
		
		Categoria areaProf2= new Categoria(null, "Tecnologia e ELetrônicos");		
		Profissao prof4 = new Profissao(null, "programador",areaProf2);
		Profissao prof5 = new Profissao(null, "Técnico em Informatica",areaProf2);
		Profissao prof6 = new Profissao(null, "Analista de sistemas",areaProf2);
		
		Categoria areaProf3= new Categoria(null, "Veículo automotores");		
		Profissao prof7 = new Profissao(null, "Mecânico",areaProf3);
		Profissao prof8 = new Profissao(null, "Motorista",areaProf3);
			areaProf1.getProfissoes().addAll(Arrays.asList(prof1,prof2,prof3));
			areaProf2.getProfissoes().addAll(Arrays.asList(prof4,prof5,prof6));
			areaProf3.getProfissoes().addAll(Arrays.asList(prof7,prof8));
			
			areaPorfissionalRepository.saveAll(Arrays.asList(areaProf1,areaProf2,areaProf3));
			servicosRepository.saveAll(Arrays.asList(prof1,prof2,prof3,prof4,prof5,prof6,prof7,prof8));
		
		//Cadsatro de Prestador	
		Prestador prest1 = new Prestador(null,"Ceará Pintor","pinturas em geral","domiciliar", cli1,prof1);	
		EnderecoPrestador end6 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest1);
				prest1.setEndereco(end6);
				
		Prestador prest2 = new Prestador(null,"Jessica Manicure","unhas em geral","domiciliar", cli2,prof1);	
		EnderecoPrestador end7 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest2);
		prest2.setEndereco(end7);
		
		Prestador prest3 = new Prestador(null,"Domesticos e Cia","casas em geral","domiciliar", cli3,prof3);	
		EnderecoPrestador end8 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest3);
		prest3.setEndereco(end8);
		
		Prestador prest4 = new Prestador(null,"Anderson developer","sistemas em geral","domiciliar", cli4,prof2);	
		EnderecoPrestador end9 = new EnderecoPrestador(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia", 255, "praça 26 de maio",prest4);
		prest4.setEndereco(end9);
		
		Prestador prest5 = new Prestador(null,"Solutech sistemas","sistemas em geral","domiciliar", cli5,prof2);	
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
		
		//Solicitacao de Servico
		
		SolicitacaoServico solicitacao = new SolicitacaoServico(null, "computador", cli1, prof1);
		ItensSolicitacao itensSolicitacao1 = new ItensSolicitacao(null, "5 anos", solicitacao);
		ItensSolicitacao itensSolicitacao2 = new ItensSolicitacao(null, "lentidao e virus", solicitacao);
		ItensSolicitacao itensSolicitacao3 = new ItensSolicitacao(null, "formatação", solicitacao);
		solicitacao.getItemServico().addAll(Arrays.asList(itensSolicitacao1,itensSolicitacao2,itensSolicitacao3));

		
		solicitacaoRepository.save(solicitacao);
		
		//Avaliações de clientes
		Avaliacoes aval1 = new Avaliacoes(null, cli1, prest1, 5, "Um dos melhores pintores que ja contratei na vida");
		 prest1.getAvaliacoes().addAll(Arrays.asList(aval1));
		
		itensSolicitacaoRepository.saveAll(Arrays.asList(itensSolicitacao1,itensSolicitacao2,itensSolicitacao3));
		
		prestadorRepository.saveAll(Arrays.asList(prest1,prest2,prest3,prest4,prest5));
		enderecoPrestadorRepository.saveAll(Arrays.asList(end6,end7,end8,end9,end10));

		
		
		curriculoRepository.saveAll(Arrays.asList(c1,c2));
		cursosRespository.saveAll(Arrays.asList(curso1,curso2,curso3));
		experienciasRespository.saveAll(Arrays.asList(exp1));
		 
		 
		 
		 avaliacoesRespository.saveAll(Arrays.asList(aval1));
	}

}
