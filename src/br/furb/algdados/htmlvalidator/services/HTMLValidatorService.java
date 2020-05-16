package br.furb.algdados.htmlvalidator.services;

import br.furb.algdados.htmlvalidator.exceptions.HTMLBadFormattingException;
import br.furb.algdados.htmlvalidator.utils.stack.Pilha;
import br.furb.algdados.htmlvalidator.utils.stack.PilhaLista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLValidatorService {
    private File file;
    private static String openTagRegularExpression = "<[^\\/][^>]*>";
    private static String closeTagRegularExpression = "<\\/[^>]*>";

    public HTMLValidatorService(File file) {
        this.file = file;
    }

    public boolean isValid() throws HTMLBadFormattingException {
        Pattern patternOpenTag = Pattern.compile(openTagRegularExpression);
        Pattern patternCloseTag = Pattern.compile(closeTagRegularExpression);
        PilhaLista<String> pilhaLista = new PilhaLista();

        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                Matcher matcherOpenTag = patternOpenTag.matcher(line);
                Matcher matcherCloseTag = patternCloseTag.matcher(line);

                while (matcherOpenTag.find()) {
                    pilhaLista.push(matcherOpenTag.group(0));
                }

                while (matcherCloseTag.find()) {
                    if(matcherCloseTag.group(0).replace("</", "<").equals(pilhaLista.peek())) {
                        pilhaLista.pop();
                    }
                }
            }

        } catch(FileNotFoundException e) {
            return false;
        }

        if(!pilhaLista.estaVazia()) {
            throw new HTMLBadFormattingException(String.format("A tag %s não foi fechada!", pilhaLista.peek()));
        }

        return true;
    }
}
