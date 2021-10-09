import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import DAO.Repositorio;
import model.Aluno;
import model.Modalidade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModalidadeMethodsTest {
    private static Repositorio repo = new Repositorio();
    private static AlunoDAO alunoDao = new AlunoDAO(repo);
    private static ModalidadeDAO dao = new ModalidadeDAO(repo);

    public static Modalidade setarValorTeste(){

        //Mock Modalidade para fins de teste

        List<Integer> dias = new ArrayList<Integer>();
        dias.add(1);

        List<String> horarios = new ArrayList<String>();
        horarios.add("14:00");

        List<String> professores = new ArrayList<String>();
        professores.add("Gilson");

        Modalidade modalidade = new Modalidade(01, "descricaoModalidade", "02:00", dias, horarios, professores, 100);

        return modalidade;
    };


    @ParameterizedTest
    @CsvSource({"12, Musculacao , 01:30, 2, 15:00, Leo, 300f" ,
                "200, Zumba , 01:00, 4, 18:45, Claudia, 700.50f"})
    @Order(1)
    @DisplayName("TesteGetSetModalidade")
    void setModalidade(Integer codigo , String descricao, String duracao, Integer dia, String horario, String professor, Float valor ) {
        Modalidade modalidade = setarValorTeste();//setar valor
        modalidade.setCodigo(codigo);
        modalidade.setDescricao(descricao);
        modalidade.setDuracao(duracao);

        List<Integer> dias = new ArrayList<Integer>();
        dias.add(dia);
        modalidade.setDiasOferecimento(dias);

        List<String> horarios = new ArrayList<String>();
        horarios.add(horario);
        modalidade.setHorarios(horarios);

        List<String> professores = new ArrayList<String>();
        professores.add(professor);
        modalidade.setProfessores(professores);

        modalidade.setValor(valor);

        assertAll(
                ()->assertEquals(codigo, modalidade.getCodigo()),
                ()->assertEquals(descricao, modalidade.getDescricao()),
                ()->assertEquals(duracao, modalidade.getDuracao()),
                ()->assertFalse(modalidade.getDiasOferecimento().isEmpty()),
                ()->assertEquals(dia , modalidade.getDiasOferecimento().get(0)),
                ()->assertFalse(modalidade.getHorarios().isEmpty()),
                ()->assertEquals(horario, modalidade.getHorarios().get(0)),
                ()->assertFalse(modalidade.getProfessores().isEmpty()),
                ()->assertEquals(professor, modalidade.getProfessores().get(0)),
                ()->assertEquals(valor, modalidade.getValor())
        );

    }

    @Test
    @Order(2)
    @DisplayName("TesteInsertModalidade")
    void insertModalidade() {
        Modalidade modalidade  =setarValorTeste();
        modalidade.setCodigo(15);
        dao.insert(modalidade);//inserindo modalidade
        assertTrue(repo.modalidades.contains(modalidade));//verificar se inseriu
    }

    @ParameterizedTest
    @ValueSource(ints = {222,15})
    @Order(3)
    @DisplayName("TesteListOneModalidade")
    void listOneModalidade(Integer codigo) {
        //Esperado que o segundo esteja errado
        assertNotNull(repo.listOneModalidade(codigo));
    }

    @Test
    @Order(4)
    @DisplayName("TesteUpdateModalidade")
    void updateModalidade() {
        Modalidade modalidade =setarValorTeste();
        dao.insert(modalidade);

        modalidade.setDescricao("Pilates");
        dao.update(modalidade);

        Modalidade updateModalidade;
        updateModalidade = dao.listOne(modalidade.getCodigo());
        assertEquals("Pilates" , updateModalidade.getDescricao());
    }

    @Test
    @Order(5)
    @DisplayName("TesteListAllModalidade")
    void listAllModalidade() {
        assertIterableEquals(repo.modalidades, dao.listAll());
    }

    @Test
    @Order(6)
    @DisplayName("TesteDeleteAluno")
    void deleteAluno() {
        Modalidade modalidade = repo.listOneModalidade(1);

        //Mock de Aluno para demonstrar que a remoção de uma modalidade interferiu diretamente na classe Aluno que a contem
        List<Modalidade> modalidades = new ArrayList<Modalidade>();
        modalidades.add(modalidade);

        List<String> telefones = new ArrayList<String>();
        telefones.add("(12) 3456-7890");

        Aluno aluno = new Aluno("12345678900", "Aluno", "01/01/2001", "masculino", 13.12f, 10.6f, "email@email.com", telefones, modalidades);
        repo.insertAluno(aluno);

        assertTrue( repo.alunos.contains(aluno));

        dao.delete(modalidade.getCodigo());
        assertFalse( repo.modalidades.contains(modalidade));

        assertFalse( repo.alunos.get(0).getModalidades().contains(modalidade));
    }

}
