/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.exceptions;

import java.util.HashMap;

public class HTMLBadFormattingException extends Throwable {
    private HashMap<String, Integer> tagsMap;

    public HTMLBadFormattingException(String messsage, HashMap<String, Integer> tagsMap) {
        super(messsage);
        this.tagsMap = tagsMap;
    }

    public HashMap<String, Integer> getTagsMap() {
        return tagsMap;
    }
}
