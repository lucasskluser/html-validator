/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.controllers;

import br.furb.algdados.htmlvalidator.exceptions.HTMLBadFormattingException;
import br.furb.algdados.htmlvalidator.exceptions.NotHTMLFileException;
import br.furb.algdados.htmlvalidator.services.HTMLReaderService;
import br.furb.algdados.htmlvalidator.services.HTMLValidatorService;
import br.furb.algdados.htmlvalidator.views.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class HTMLController {
    private MainView mainView;
    private String openedFile;
    private HTMLValidatorService htmlValidatorService;
    private HTMLReaderService htmlReaderService;

    public HTMLController() {
        mainView = new MainView(this);
        htmlValidatorService = new HTMLValidatorService();
        htmlReaderService = new HTMLReaderService();
    }

    public void view() {
        mainView.show();
    }

    public void selectFile(String filePath) {
        try {
            File file = new File(filePath);
            JFileChooser fileChooser = new JFileChooser();

            if(file.isDirectory() || openedFile.equals(filePath)) {
                fileChooser.setCurrentDirectory(file);
                fileChooser.setFileFilter(new FileNameExtensionFilter("HTML", "html"));

                int returnVal = fileChooser.showOpenDialog(mainView.getJPanel());

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    file = htmlReaderService.readFile(fileChooser.getSelectedFile().getAbsolutePath());
                } else if (returnVal == JFileChooser.CANCEL_OPTION){
                    return;
                }
            }

            openedFile = file.getAbsolutePath();
            validateFile(file);
        } catch (NotHTMLFileException e) {
            showError("O arquivo não está no formato HTML!");
        } catch (FileNotFoundException e) {
            showError("Não foi possível encontrar o arquivo!");
        } catch (Throwable throwable) {
            showError("Não foi possível carregar o arquivo!");
        }
    }

    public void showInfo(String message) {
        mainView.showInfo(message);
    }

    public void showError(String message) {
        mainView.showError(message);
    }

    public void validateFile(File file) {
        try {
            HashMap<String, Integer> tagsMap = htmlValidatorService.validate(file);
            mainView.loadResult(openedFile, tagsMap);
        } catch (HTMLBadFormattingException e) {
            mainView.loadResult(openedFile, e.getTagsMap(), e.getMessage());
        }
    }
}
