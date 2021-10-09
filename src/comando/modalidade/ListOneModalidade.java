package comando.modalidade;

import DAO.ModalidadeDAO;
import model.Aluno;
import model.Modalidade;

import java.util.List;
import java.util.Scanner;

public class ListOneModalidade {
    public static void execute(ModalidadeDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("/**** Listar uma Modalidade ****/");
        System.out.println("codigo: ");
        int codigo = scan.nextInt();

        if(dao.listOne(codigo) != null) System.out.println(dao.listOne(codigo));
        else System.out.println("O Código indicado não existe!");
    }
}
