package DAO;

import model.Aluno;
import model.Modalidade;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    public static List<Aluno> alunos = new ArrayList<Aluno>();
    public static List<Modalidade> modalidades = new ArrayList<Modalidade>();

    public static void insertAluno(Aluno aluno) {
        List<String> telefonesFormated = new ArrayList<String>();

        //tratativa para formato de telefone
        for (Object telefone : aluno.getTelefones()){
            MaskFormatter phoneFormatter = null;
            try {
                phoneFormatter = new MaskFormatter("(##) ####-####");
                JFormattedTextField formatedTelefone = new JFormattedTextField(phoneFormatter);
                formatedTelefone.setText((String)telefone);
                telefonesFormated.add(formatedTelefone.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        aluno.setTelefones(telefonesFormated);
        alunos.add(aluno);
    }

    public static void insertModalidade(Modalidade modalidade){
        modalidades.add(modalidade);
    }

    public static void updateAluno(Aluno aluno){
        for (Aluno dado : alunos){
            //tratativa para formato de telefone
            if (aluno.getCpf().equals(dado.getCpf())) {
                List<String> telefonesFormated = new ArrayList<String>();
                for (Object telefone : aluno.getTelefones()){
                    MaskFormatter phoneFormatter = null;
                    try {
                        phoneFormatter = new MaskFormatter("(##) ####-####");
                        JFormattedTextField formatedTelefone = new JFormattedTextField(phoneFormatter);
                        formatedTelefone.setText((String)telefone);
                        telefonesFormated.add(formatedTelefone.getText());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                aluno.setTelefones(telefonesFormated);

                if(alunos.size() == 0){
                    alunos.set(alunos.indexOf(dado)+1,aluno);
                }
                else alunos.set(alunos.indexOf(dado),aluno);
            }
        }
    }

    public static void updateModalidade(Modalidade modalidade){
        for (Modalidade dado : modalidades) {
            if (modalidade.getCodigo() == dado.getCodigo()) {
                if(modalidades.size() == 0){
                    modalidades.set(modalidades.indexOf(dado) + 1, modalidade);
                }
                else modalidades.set(modalidades.indexOf(dado), modalidade);
            }
        }
    }

    public static void deleteAluno(String cpf){
        for (Aluno dado : alunos){
            if (cpf.equals(dado.getCpf())) {
                alunos.remove(dado);
                return;
            }
        }
    }

    public static void deleteModalidade(int codigo){
        for (Modalidade dado : modalidades){
            if (codigo == dado.getCodigo()){
                for(Aluno aluno : alunos){
                    if( aluno.getModalidades().contains(dado) ){
                        aluno.getModalidades().remove(dado);
                    }
                }

                modalidades.remove(dado);
                return;
            }
        }
    }

    public static List<Aluno> listAllAluno(){
        return alunos;
    }

    public static List<Modalidade> listAllModalidade(){
        return modalidades;
    }

    public static Aluno listOneAluno(String cpf){
        for (Aluno dado : alunos){
            if (cpf.equals(dado.getCpf())) return dado;
        }
        return null;
    }

    public static Modalidade listOneModalidade(Integer codigo){
        for (Modalidade dado : modalidades){
            if (codigo.equals(dado.getCodigo())) return dado;
        }
        return null;
    }


}
