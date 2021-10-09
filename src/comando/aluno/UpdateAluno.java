package comando.aluno;

import DAO.AlunoDAO;
import DAO.ModalidadeDAO;
import model.Aluno;
import model.Modalidade;
import validator.aluno.ValidatorAluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateAluno {
    public static void execute(AlunoDAO dao, ModalidadeDAO daoModalidade) {
        Scanner scan = new Scanner(System.in);

        System.out.println("/**** Alterar Aluno ****/");
        System.out.println("cpf deve conter 11 digitos (ex:12345678900): ");
        String cpf = scan.next();
        while (!ValidatorAluno.cpfValidator(cpf)) {
            System.out.println("Valor invalido");
            System.out.println("cpf deve conter 11 digitos (ex:12345678900)");
            cpf = scan.next();
        }

        if (dao.listOne(cpf) != null) {
            //nome
            System.out.println("nome: ");
            String nome = scan.next();

            //data de nascimento
            System.out.println("dataNascimento(dd/MM/yyyy): ");
            String dataNascimento = scan.next();

            while (ValidatorAluno.dataNascimentoValidator(dataNascimento) == "false") {
                System.out.println("Valor invalido");
                System.out.println("dataNascimento(dd/MM/yyyy): ");
                dataNascimento = scan.next();
            }
            dataNascimento = ValidatorAluno.dataNascimentoValidator(dataNascimento);

            //sexo
            System.out.println("sexo: \nmasculino \nfeminino ");
            String sexo = scan.next().toLowerCase();
            while (!ValidatorAluno.sexoValidator(sexo)) {
                System.out.println("Valor invalido");
                System.out.println("sexo:\nmasculino \nfeminino ");
                sexo = scan.next();
            }

            //peso
            System.out.println("peso: ");
            float peso = scan.nextFloat();

            //altura
            System.out.println("altura: ");
            float altura = scan.nextFloat();

            //email
            System.out.println("email: (necessita ter '@' e '.com')");
            String email = scan.next();
            while (!ValidatorAluno.emailValidator(email)) {
                System.out.println("Valor invalido (necessita ter '@' e '.com')");
                System.out.println("email: ");
                email = scan.next();
            }

            //telefone
            System.out.println("digite o telefone precisa conter 10 digitos ex:1234567890 (0 para finalizar): ");
            String telefone = scan.next();
            List<String> telefones = new ArrayList<String>();

            while (!telefone.equals("0")) {
                if(ValidatorAluno.telefoneValidator(telefone) != "false"){
                    telefone = ValidatorAluno.telefoneValidator(telefone);
                    telefones.add(telefone);
                }
                else{
                    System.out.println("Valor invalido *(precisa conter 10 digitos sem espacos)*");
                }
                System.out.println("digite o telefone: ");
                telefone = scan.next();

            }

            //modalidade
            System.out.println(daoModalidade.listAll());
            System.out.println("digite a modalidade que o aluno pratica (0 para finalizar): ");
            Integer codigoModalidade = scan.nextInt();
            Modalidade modalidade = daoModalidade.listOne(codigoModalidade);
            List<Modalidade> modalidades = new ArrayList<Modalidade>();

            while (codigoModalidade != 0) {
                if(modalidade != null) {
                    if (!modalidades.contains(modalidade)) modalidades.add(modalidade);
                }
                else System.out.println("A modalidade inserida não existe!");

                System.out.println("digite a modalidade que o aluno pratica: ");
                codigoModalidade = scan.nextInt();
                modalidade = daoModalidade.listOne(codigoModalidade);
            }

            Aluno aluno = new Aluno(cpf, nome, dataNascimento, sexo, peso, altura, email, telefones, modalidades);

            dao.update(aluno);
        } else System.out.println("O CPF indicado não existe!");
    }
}
