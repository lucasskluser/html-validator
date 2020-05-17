/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.services;

import br.furb.algdados.htmlvalidator.exceptions.HTMLBadFormattingException;
import br.furb.algdados.htmlvalidator.utils.stack.PilhaLista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLValidatorService {
    private File file;
    private static String openTagRegularExpression = "<([^\\s\\/>][^!\\-\\-\\s>]*)";
    private static String closeTagRegularExpression = "<\\/[^>]+|<[^>]+\\/>";
    private static String[] singletonTags = {
            "meta", "base", "br", "col", "command", "embed", "hr", "img", "input", "link", "param", "source", "!DOCTYPE"
    };

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
            int lineNumber = 0;
            while(scanner.hasNext()) {
                lineNumber++;
                String line = scanner.nextLine();
                Matcher matcherOpenTag = patternOpenTag.matcher(line);
                Matcher matcherCloseTag = patternCloseTag.matcher(line);

                while (matcherOpenTag.find()) {
                    String tag = String.format("%s>", matcherOpenTag.group(0));

                    if (!isSingletonTag(tag) && tag.length() < 20) {
                        pilhaLista.push(tag);
                    }

                    if (tag.length() < 20) {
                        if(tagsMap.containsKey(tag)) {
                            int value = tagsMap.get(tag);
                            tagsMap.replace(tag, ++value);
                        } else {
                            tagsMap.put(tag, 1);
                        }
                    }
                }

                while (matcherCloseTag.find()) {
                    String tag = matcherCloseTag.group(0);
                    tag = String.format(
                        "%s>",
                        tag
                        .split("\\s")[0]
                        .trim()
                        .replace("</", "<")
                    );

                    if(tag.equals(pilhaLista.peek())) {
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

    private boolean isSingletonTag(String tag) {
        for(String singletonTag : singletonTags) {
            if (tag.equals(String.format("<%s>", singletonTag))) {
                return true;
            }
        }

        return false;
    }
}
