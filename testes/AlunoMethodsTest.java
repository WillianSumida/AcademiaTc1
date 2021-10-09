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
class AlunoMethodsTest {
    private static Repositorio repo = new Repositorio();
    private static AlunoDAO dao = new AlunoDAO(repo);
    private static ModalidadeDAO modalidadeDAO = new ModalidadeDAO(repo);

    public static Aluno setarValorTeste(){

        List<String> telefones = new ArrayList<String>();
        telefones.add("(12) 3456-7890");

        List<Integer> dias = new ArrayList<Integer>();
        dias.add(1);

        List<String> horarios = new ArrayList<String>();
        horarios.add("14:00");

        List<String> professores = new ArrayList<String>();
        professores.add("Gilson");

        Modalidade modalidade = new Modalidade(01, "descricaoModalidade", "02:00", dias, horarios, professores, 100);
        List<Modalidade> modalidades = new ArrayList<Modalidade>();
        modalidades.add(modalidade);

        //aluno correto
        Aluno aluno = new Aluno("12345678900", "Aluno", "01/01/2001", "masculino", 13.12f, 10.6f, "email@email.com", telefones, modalidades);
        return aluno;
    };


    @ParameterizedTest
    @CsvSource({"65478912312, NomeAluno , feminino, (12) 3369-4444, 12/12/2000, emailteste@email.com, 100.0f, 1.90f" ,
                "558962410, NomeAluno1 , masculino, (14) 3122-4885, 01/10/2015, emailteste2@email2.com, 80.0f, 1.75f"})
    @Order(1)
    @DisplayName("TesteGetSetAluno")
    void setAluno(String cpf , String nome, String sexo, String telefone, String data, String email, Float peso, Float altura ) {
        Aluno aluno = setarValorTeste();//setar valor
        aluno.setCpf(cpf);
        aluno.setNome(nome);
        aluno.setDataNascimento(data);
        aluno.setSexo(sexo);
        aluno.setPeso(peso);
        aluno.setAltura(altura);
        aluno.setEmail(email);

        List<String> telefones = new ArrayList<String>();
        telefones.add(telefone);
        aluno.setTelefones(telefones);

        assertAll(
                ()->assertEquals(11 , aluno.getCpf().length()),
                ()->assertEquals(cpf, aluno.getCpf()),
                ()->assertEquals(nome, aluno.getNome()),
                ()->assertEquals(data , aluno.getDataNascimento()),
                ()->assertEquals(sexo, aluno.getSexo()),
                ()->assertEquals(peso, aluno.getPeso()),
                ()->assertEquals(altura, aluno.getAltura()),
                ()->assertEquals(email, aluno.getEmail()),
                ()->assertFalse(aluno.getTelefones().isEmpty()),
                ()-> assertEquals(telefone, aluno.getTelefones().get(0)),
                ()->assertFalse(aluno.getModalidades().isEmpty())
        );

    }

    @Test
    @Order(2)
    @DisplayName("TesteInsertAluno")
    void insertAluno() {
        Aluno aluno  =setarValorTeste();
        aluno.setCpf("98765432100");
        dao.insert(aluno);//inserindo aluno
        assertTrue( repo.alunos.contains(aluno) );//verificar se inseriu
    }

    @Test
    @Order(4)
    @DisplayName("TesteUpdateAluno")
    void updateAluno() {
        Aluno aluno  =setarValorTeste();
        dao.insert(aluno);

        aluno.setNome("UpdateNome");
        dao.update(aluno);

        Aluno updateAluno;
        updateAluno = dao.listOne(aluno.getCpf());
        assertEquals("UpdateNome" , updateAluno.getNome());
    }

    @Test
    @Order(6)
    @DisplayName("TesteDeleteAluno")
    void deleteAluno() {
        Aluno aluno  =setarValorTeste();
        //dao.insert(aluno);
        dao.delete(aluno.getCpf());
        assertFalse( repo.alunos.contains(aluno));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567890","98765432100", "12345678900"})
    @Order(3)
    @DisplayName("TesteListOneAluno")
    void listOneAluno(String cpf) {
        //Esperado que o primeiro e o ultimo estejam errados
        assertNotNull(repo.listOneAluno(cpf));
    }

    @Test
    @Order(5)
    @DisplayName("TesteListAllAluno")
    void listAllAluno() {
        assertIterableEquals(repo.alunos, dao.listAll());
    }

}