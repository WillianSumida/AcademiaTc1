package comando.modalidade;

import DAO.ModalidadeDAO;
import model.Modalidade;
import validator.modalidade.ValidatorModalidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateModalidade {
    public static void execute(ModalidadeDAO dao) {
        Scanner scan = new Scanner(System.in);

        //codigo

        System.out.println("/**** Inserir Modalidade ****/");
        System.out.println("codigo: ");
        int codigo = scan.nextInt();

        if(dao.listOne(codigo) != null) {

            //descricao

            System.out.println("descricao: ");
            String descricao = scan.next();

            //duracao

            System.out.println("duracao (HHMM EX: 0130): ");
            String duracao = scan.next();

            while(ValidatorModalidade.duracaoValidator(duracao) == "false") {
                System.out.println("Valor invalido *(Digite nesse formato: HHMM - Ex: 0130)*");
                System.out.println("duracao: ");
                duracao = scan.next();
            }

            duracao = ValidatorModalidade.duracaoValidator(duracao);

            //dias

            System.out.println("---- Dias ----" +
                    "\n1 - Domingo "+
                    "\n2 - Segunda "+
                    "\n3 - Terça"+
                    "\n4 - Quarta"+
                    "\n5 - Quinta"+
                    "\n6 - Sexta"+
                    "\n7 - Sabado"+
                    "\n0 para finalizar" +
                    "\ndigite o dia:");
            Integer dia = scan.nextInt();
            List<Integer> dias = new ArrayList<Integer>();

            while (dia != 0) {
                if(ValidatorModalidade.diaValidator(dia)) dias.add(dia);
                else System.out.println("Valor invalido");
                System.out.println("dia: ");
                dia = scan.nextInt();
            }

            //horarios

            System.out.println("horarios (HHMM - Ex: 1240)" +
                    "\n0 para finalizar ");
            String horario = scan.next();
            List<String> horarios = new ArrayList<String>();

            while (!horario.equals("0")) {
                if(ValidatorModalidade.horarioValidator(horario) != "false"){
                    if(!horarios.contains(ValidatorModalidade.horarioValidator(horario))) horarios.add(ValidatorModalidade.horarioValidator(horario));
                }
                else System.out.println("Valor invalido *(Digite nesse formato: HHMM - Ex: 1240)*");
                System.out.println("digite o horario: ");
                horario = scan.next();
            }

            //professores

            System.out.println("professores (0 para finalizar): ");
            String professor = scan.next();
            List<String> professores = new ArrayList<String>();

            while (!professor.equals("0")) {
                professores.add(professor);
                System.out.println("digite o professor: ");
                professor = scan.next();
            }

            //valor

            System.out.println("valor: ");
            float valor = scan.nextFloat();

            Modalidade modalidade = new Modalidade(codigo, descricao, duracao, dias, horarios, professores, valor);

            dao.update(modalidade);
        }
        else System.out.println("O Código indicado não existe!");
    }
}
