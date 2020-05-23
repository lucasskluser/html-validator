/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.exceptions;

import br.furb.algdados.htmlvalidator.utils.list.ListaEncadeada;
import br.furb.algdados.htmlvalidator.utils.tagfrequency.TagFrequency;

import java.util.HashMap;

public class HTMLBadFormattingException extends Throwable {
    private ListaEncadeada<TagFrequency> tagsMap;

    public HTMLBadFormattingException(String messsage, ListaEncadeada<TagFrequency> tagsMap) {
        super(messsage);
        this.tagsMap = tagsMap;
    }

    public ListaEncadeada<TagFrequency> getTagsMap() {
        return tagsMap;
    }
}
