package org.cptjmg.consultaprocesso.util;

public class Validator {
    public static boolean isNumProcessoValid(String numProcesso) {
        if(numProcesso == null || numProcesso.length() < 17 || numProcesso.length() == 18) {
            return false;
        }
        return true;
    }
}
