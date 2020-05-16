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
