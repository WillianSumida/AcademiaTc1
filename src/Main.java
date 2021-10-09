import Controller.ControllerAluno;
import Controller.ControllerModalidade;
import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import DAO.Repositorio;

import java.util.Scanner;

public class Main {

    private static AlunoDAO alunoDAO;
    private static ModalidadeDAO modalidadeDAO;
    private static Scanner scan;
    private static Repositorio repositorio;
    private static Integer opcMenuSecundario;
    private static Integer opcMenuPrincipal;
    private static ControllerAluno controllerAluno;
    private static ControllerModalidade controllerModalidade;

    public static Integer menu(){
        System.out.println("/********* Menu principal *********/");
        System.out.println("1 - Menu alunos");
        System.out.println("2 - Menu modalidades");
        System.out.println("0 - Sair");

        return scan.nextInt();
    }

    public static Integer subMenu(Integer escolha){
        String tipo = escolha == 1 ? "aluno" : "modalidade";

        System.out.println("/********* Menu " + tipo + " *********/");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Listar todos");
        System.out.println("5 - Listar um");
        System.out.println("0 - Voltar");

        return scan.nextInt();
    }

    public static void setVariavel(){
        repositorio = new Repositorio();
        alunoDAO = new AlunoDAO(repositorio);
        modalidadeDAO = new ModalidadeDAO(repositorio);
        scan = new Scanner(System.in);
        controllerModalidade = new ControllerModalidade(modalidadeDAO);
        controllerAluno = new ControllerAluno(alunoDAO, modalidadeDAO);

    }


    public static void main(String[] args){
        setVariavel();

        //START MENU PRINCIPAL
        opcMenuPrincipal = menu(); //Chama menu principal
        while (opcMenuPrincipal !=0){

            opcMenuSecundario =  subMenu(opcMenuPrincipal);
            //START MENU SECUNDARIO
            while (opcMenuSecundario != 0 && (opcMenuPrincipal == 2 || opcMenuPrincipal == 1)){ //Motor menu secundario

                if(opcMenuPrincipal == 1){
                    controllerAluno.operacao(opcMenuSecundario);
                }
                else{
                    controllerModalidade.operacao(opcMenuSecundario);
                }

                opcMenuSecundario =  subMenu(opcMenuPrincipal);
            }
            //FIM MENU SECUNDARIO

            opcMenuPrincipal = menu();
        }
        //FIM MENU PRINCIPAL
    }
}
