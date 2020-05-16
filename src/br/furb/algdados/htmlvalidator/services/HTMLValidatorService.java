package br.furb.algdados.htmlvalidator.services;

import br.furb.algdados.htmlvalidator.exceptions.HTMLBadFormattingException;
import br.furb.algdados.htmlvalidator.utils.stack.Pilha;
import br.furb.algdados.htmlvalidator.utils.stack.PilhaLista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLValidatorService {
    private File file;
    private static String openTagRegularExpression = "<[^\\/][^>]*>";
    private static String closeTagRegularExpression = "<\\/[^>]*>";

    public HTMLValidatorService() {
    }

    public HTMLValidatorService(File file) {
        this.file = file;
    }

    public HashMap<String, Integer> validate(File file) throws HTMLBadFormattingException {
        this.file = file;
        return validate();
    }

    public HashMap<String, Integer> validate() throws HTMLBadFormattingException {
        Pattern patternOpenTag = Pattern.compile(openTagRegularExpression);
        Pattern patternCloseTag = Pattern.compile(closeTagRegularExpression);
        PilhaLista<String> pilhaLista = new PilhaLista();
        HashMap<String, Integer> tagsMap = new HashMap<>();

        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                Matcher matcherOpenTag = patternOpenTag.matcher(line);
                Matcher matcherCloseTag = patternCloseTag.matcher(line);

                while (matcherOpenTag.find()) {
                    String tag = matcherOpenTag.group(0);

                    pilhaLista.push(tag);

                    if(tagsMap.containsKey(tag)) {
                        int value = tagsMap.get(tag);
                        tagsMap.replace(tag, value++);
                    } else {
                        tagsMap.put(tag, 1);
                    }
                }

                while (matcherCloseTag.find()) {
                    if(matcherCloseTag.group(0).replace("</", "<").equals(pilhaLista.peek())) {
                        pilhaLista.pop();
                    }
                }
            }

        } catch(FileNotFoundException e) {
            return null;
        }

        if(!pilhaLista.estaVazia()) {
            throw new HTMLBadFormattingException(String.format("A tag %s não foi fechada!", pilhaLista.peek()), tagsMap);
        }

        return tagsMap;
    }
}
