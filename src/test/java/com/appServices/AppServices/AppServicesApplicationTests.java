package com.appServices.AppServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.appServices.AppServices.Service.exception.AuthorizationException;
import com.appServices.AppServices.Services.ClienteService;
import com.appServices.AppServices.Services.UserService;
import com.appServices.AppServices.domain.Categoria;
import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.domain.EnderecoCliente;
import com.appServices.AppServices.domain.ItensSolicitacao;
import com.appServices.AppServices.domain.Profissao;
import com.appServices.AppServices.domain.SolicitacaoServico;
import com.appServices.AppServices.domain.enums.StatusSolicitacao;
import com.appServices.AppServices.domain.enums.TipoPerfil;
import com.appServices.AppServices.domain.enums.TipoPessoa;
import com.appServices.AppServices.domain.enums.TipoSexo;
import com.appServices.AppServices.repositories.CategoriaRepository;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.repositories.EnderecoClienteRepository;
import com.appServices.AppServices.repositories.ItensSolicitacaoRepository;
import com.appServices.AppServices.repositories.OrcamentoRepository;
import com.appServices.AppServices.repositories.PedidoRepository;
import com.appServices.AppServices.repositories.ProfissaoRepository;
import com.appServices.AppServices.repositories.SolicitacaoServicoRepository;
import com.appServices.AppServices.security.UserSpringSecurity;



@RunWith(SpringRunner.class)
@DataJpaTest
public class AppServicesApplicationTests {
	
	@Autowired
	private SolicitacaoServicoRepository solicRepository;
	
	@Autowired
	private ItensSolicitacaoRepository itensSolicRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProfissaoRepository profissaoRepository;
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoClienteRepository enderecoRespository;
	
	//@Autowired
	//private BCryptPasswordEncoder pe;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	// TODO Auto-generated method stub	
	SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
			
	
	@Test
	public void TestProcessoDeSolcitacao() throws ParseException{
	
	//Teste de Novo Cliente
		
		//Novo Cliente
		Cliente cli1 = new Cliente(null,"Dagle"," Anderson",data.parse("22/10/1994 22:00"),"1432756311",
		"06317684596",TipoPessoa.FISICA,TipoSexo.MASCULINO,"221094","dagle_life@hotmail.com");
		EnderecoCliente end1 = new EnderecoCliente(null,"Barreiras","BA", "47800218", "Barreiras I", "Ceilandia",
		255, "praça 26 de maio", cli1);
		cli1.setEndereco(end1);
		
		//Salvando dados no banco
		repository.save(cli1);
		enderecoRespository.save(end1);
		
		//primeira consulta de e-mail vindo do front-end		
		 Cliente cli = repository.findByEmail("dagle_life@hotmail.com");
		
		//teste se os dados retornados são os dados esperados
		Assertions.assertThat(cli.getId()).isNotNull();
		Assertions.assertThat(cli.getNome()).isEqualTo("Dagle");
		Assertions.assertThat(cli.getCpfOuCnpj()).isEqualTo("06317684596");
		Assertions.assertThat(cli.getEmail()).isEqualTo("dagle_life@hotmail.com");
	
		
		this.TestGetSolicByCli(cli,this.CatAndProf());
	
	}
	
	private Profissao CatAndProf() {
		//Categorias e Profissoões	
		
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
				
				areaProf1.getProfissoes().addAll(Arrays.asList(
						prof1,prof2,prof3,
						prof4,prof5,prof6,
						prof7,prof8,prof9,
						prof10,prof11,prof12
						
						
						));
				
				categoriaRepository.saveAll(Arrays.asList(areaProf1));
				profissaoRepository.saveAll(Arrays.asList(
						prof1,prof2,prof3,
						prof4,prof5,prof6,
						prof7,prof8,prof9,
						prof10,prof11,prof12
						));
	
	 return prof1;
		
	}

public void TestGetSolicByCli(Cliente cliente,Profissao prof)  throws ParseException {
	//Nova Solicitação
	SolicitacaoServico solicitacao1 = new SolicitacaoServico(null, "casa na chácara",data.parse("05/04/2019 00:00"),
			cliente,prof,StatusSolicitacao.ABERTA);
	ItensSolicitacao itensSolicitacao1 = new ItensSolicitacao(null,
	"As paredes e o teto estão manchados e existem alguns pontos que"
	+ " precisarão de reparos(correções com massa corrida)",
	solicitacao1);
	
	//Salvando em banco de dados 
	solicRepository.save(solicitacao1);
	 itensSolicRepository.save(
			 itensSolicitacao1);
	 
	//Busca de cliente por Id 	
			Optional<Cliente> clienteOp = repository.findById(cliente.getId());
			//Paginação do retorno
			PageRequest  pageRequest = PageRequest.of(0,10);
			
			//Busca de solicitações por cliente	passando o cliente logado por parâmetro 
			Page<SolicitacaoServico> solic = solicRepository.findByCliente(clienteOp,pageRequest);	
			
			System.out.println(solic.getContent());
			
			System.out.println("teste aprovado!");
	}
	
	

}
