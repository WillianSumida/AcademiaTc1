package comando.modalidade;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import model.Aluno;
import model.Modalidade;

import java.util.List;
import java.util.Scanner;

public class DeleteModalidade {
    public  static void execute(ModalidadeDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("/**** Deletar Modalidade ****/");
        System.out.println("codigo: ");
        int codigo = scan.nextInt();

        String escolha;
        if(dao.listOne(codigo) != null) {
            do {
                System.out.println(dao.listOne(codigo));
                System.out.println("Voce tem certeza que gostaria de deletar essa modalidade? (ela tambem sera excluida de todos os alunos)");
                System.out.println("S - SIM \nN- NÃO");
                escolha  = scan.next().toUpperCase();

                if (escolha.equals("S")) dao.delete(codigo);
                else System.out.println("Modalidade não será excluida");
            }while (!escolha.equals("S") && !escolha.equals("N"));
        }
        else System.out.println("O código indicado não existe!");
    }
}
