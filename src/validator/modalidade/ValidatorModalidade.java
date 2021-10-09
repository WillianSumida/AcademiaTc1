package validator.modalidade;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class ValidatorModalidade {

    public static String duracaoValidator(String duracao){
        MaskFormatter duracaoFormatter = null;
        if (duracao.length() == 4) {
            try {
                Integer duracaoInt = Integer.parseInt(duracao);
                duracaoFormatter = new MaskFormatter("##:##");
                JFormattedTextField duracaoFormated = new JFormattedTextField(duracaoFormatter);
                duracaoFormated.setText(String.valueOf(duracaoInt));
                return duracaoFormated.getText();
            } catch (ParseException e) {
                return "false";
            } catch (NumberFormatException a){
                return "false";
            }
        }
        else return "false";
    }

    public static boolean diaValidator(Integer dia){
        if (dia < 8 && dia > 0) return true;
        return false;
    }

    public static String horarioValidator(String horario){
        MaskFormatter horarioFormatter = null;
        if (horario.length() == 4) {
            try {
                Integer horarioInt = Integer.parseInt(horario);
                horarioFormatter = new MaskFormatter("##:##");
                JFormattedTextField horarioFormated = new JFormattedTextField(horarioFormatter);
                horarioFormated.setText(String.valueOf(horarioInt));
                return horarioFormated.getText();
            } catch (ParseException e) {
                return "false";
            } catch (NumberFormatException a){
                return "false";
            }
        }
        else return "false";
    }
}
