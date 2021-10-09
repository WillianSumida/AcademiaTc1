package comando.aluno;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import model.Aluno;
import model.Modalidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteAluno {
    public static void execute(AlunoDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("/**** Deletar Aluno ****/");
        System.out.println("cpf: ");
        String cpf = scan.next();

        String escolha;
        if(dao.listOne(cpf) != null) {
            do {
                System.out.println(dao.listOne(cpf));
                System.out.println("Voce tem certeza que gostaria de deletar esse aluno?");
                System.out.println("S - SIM \nN- NÃO");
                escolha  = scan.next().toUpperCase();

                if (escolha.equals("S")) dao.delete(cpf);
                else System.out.println("Aluno não será excluido");
            }while (!escolha.equals("S") && !escolha.equals("N"));
        }
        else System.out.println("O CPF indicado não existe!");
    }
}
