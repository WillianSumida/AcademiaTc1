package validator.aluno;

import DAO.ModalidadeDAO;
import DAO.Repositorio;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidatorAluno {

    public static boolean cpfValidator(String cpf){
        if (cpf.length() != 11) return false;
        return true;
    }

    public static String dataNascimentoValidator(String dataNascimento){
        DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date date = (Date)formatter.parse(dataNascimento);
            return formatter.format(date);
        } catch (ParseException e) {
            return "false";
        }

    }

    public static  boolean sexoValidator (String sexo){
        if (!sexo.equals("masculino") && !sexo.equals("feminino"))return false;
        else return true;
    }

    public static boolean emailValidator(String email){
        if (email.contains("@") && email.contains(".com")) return true;
        else return false;
    }

    public static String telefoneValidator(String telefone){
        MaskFormatter phoneFormatter = null;
        if (telefone.length() == 10) {
            try {
                phoneFormatter = new MaskFormatter("(##) ####-####");
                JFormattedTextField formatedTelefone = new JFormattedTextField(phoneFormatter);
                formatedTelefone.setText((String) telefone);
                return formatedTelefone.getText();
            } catch (ParseException e) {
                return "false";
            }
        }
        else return "false";
    }
}
