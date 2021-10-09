package comando.modalidade;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import model.Aluno;
import model.Modalidade;

import java.util.List;

public class ListAllModalidade {
    public static void execute(ModalidadeDAO dao) {
        if(dao.listAll().isEmpty() || dao.listAll() == null){
            System.out.println("Não existem modalidades registradas!");
        }
        System.out.println(dao.listAll());
    }
}
