package br.furb.algdados.htmlvalidator.services;

import br.furb.algdados.htmlvalidator.exceptions.NotHTMLFileException;

import java.io.File;
import java.io.FileNotFoundException;

public class HTMLReaderService {

    public File readFile(String path) throws Throwable {
        File file = new File(path);
        isValid(file);

        return file;
    }

    private boolean isValid(File file) throws Throwable {
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException();
        }

        if(!file.getName().endsWith(".html")) {
            throw new NotHTMLFileException();
        }

        return true;
    }
}