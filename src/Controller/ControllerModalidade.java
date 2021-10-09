package Controller;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import comando.modalidade.*;

public class ControllerModalidade {
    private static InsertModalidade insertModalidade = new InsertModalidade();
    private static UpdateModalidade updateModalidade = new UpdateModalidade();
    private static DeleteModalidade deleteModalidade = new DeleteModalidade();
    private static ListAllModalidade listAllModalidade = new ListAllModalidade();
    private static ListOneModalidade listOneModalidade = new ListOneModalidade();
    private static ModalidadeDAO dao;

    public ControllerModalidade(ModalidadeDAO dao){
        this.dao = dao;
    }

    public static void operacao(Integer escolha){
        switch (escolha){
            case 1: insertModalidade.execute(dao); break;
            case 2: updateModalidade.execute(dao); break;
            case 3: deleteModalidade.execute(dao); break;
            case 4: listAllModalidade.execute(dao); break;
            case 5: listOneModalidade.execute(dao); break;
            default: System.out.println("Opção inválida!");
        }
    }
}
