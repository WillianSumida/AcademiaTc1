package Controller;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import comando.aluno.*;

public class ControllerAluno {

    private static InsertAluno insertAluno = new InsertAluno();
    private static UpdateAluno updateAluno = new UpdateAluno();
    private static DeleteAluno deleteAluno = new DeleteAluno();
    private static ListAllAluno listAllAluno = new ListAllAluno();
    private static ListOneAluno listOneAluno = new ListOneAluno();
    private static AlunoDAO dao;
    private static ModalidadeDAO daoModalidade;

    public ControllerAluno(AlunoDAO dao, ModalidadeDAO daoModalidade){
        this.dao = dao;
        this.daoModalidade = daoModalidade;
    }

    public static void operacao(Integer escolha){
        switch (escolha){
            case 1: insertAluno.execute(dao, daoModalidade); break;
            case 2: updateAluno.execute(dao, daoModalidade); break;
            case 3: deleteAluno.execute(dao); break;
            case 4: listAllAluno.execute(dao); break;
            case 5: listOneAluno.execute(dao); break;
            default: System.out.println("Opção inválida!");
        }
    }
}
