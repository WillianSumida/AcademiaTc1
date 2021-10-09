package comando.aluno;

import DAO.AlunoDAO;
import model.Aluno;
import model.Modalidade;

import java.util.List;
import java.util.Scanner;

public class ListAllAluno {
    public static void execute(AlunoDAO dao) {
        if(dao.listAll().isEmpty() || dao.listAll() == null){
            System.out.println("Não existem alunos registrados!");
        }
        System.out.println(dao.listAll());
    }
}
