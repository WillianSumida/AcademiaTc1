package DAO;

import model.Aluno;
import model.Modalidade;

import java.util.List;

public class ModalidadeDAO {
    private static Repositorio repositorio;

    public ModalidadeDAO(Repositorio repositorio){
        this.repositorio = repositorio;
    };

    public static void insert(Modalidade modalidade){
        repositorio.insertModalidade(modalidade);
    }

    public static void update(Modalidade modalidade){
        repositorio.updateModalidade(modalidade);
    }

    public static void delete(Integer codigo){
        repositorio.deleteModalidade(codigo);
    }

    public static List<Modalidade> listAll(){
        return repositorio.listAllModalidade();
    }

    public static Modalidade listOne(Integer codigo){
        return repositorio.listOneModalidade(codigo);
    }
}
