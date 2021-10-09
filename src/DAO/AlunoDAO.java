package DAO;

import model.Aluno;

import java.util.List;

public class AlunoDAO {
    private static Repositorio repositorio;

    public AlunoDAO(Repositorio repositorio){
        this.repositorio = repositorio;
    };

    public static void insert(Aluno aluno){
        repositorio.insertAluno(aluno);
    }

    public static void update(Aluno aluno){
        repositorio.updateAluno(aluno);
    }

    public static void delete(String cpf){
        repositorio.deleteAluno(cpf);
    }

    public static List<Aluno> listAll(){
        return repositorio.listAllAluno();
    }

    public static Aluno listOne(String cpf){
        return repositorio.listOneAluno(cpf);
    }

}
