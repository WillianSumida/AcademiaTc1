package comando.aluno;

import DAO.AlunoDAO;
import model.Aluno;

import java.util.List;
import java.util.Scanner;

public class ListOneAluno {
    public static void execute(AlunoDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("/**** Listar Um Aluno ****/");
        System.out.println("cpf: ");
        String cpf = scan.next();

        if(dao.listOne(cpf) != null) System.out.println(dao.listOne(cpf));
        else System.out.println("O CPF indicado não existe!");
    }
}
