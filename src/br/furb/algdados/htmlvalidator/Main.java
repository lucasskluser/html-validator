/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator;

import br.furb.algdados.htmlvalidator.controllers.HTMLController;

public class Main {
    public static void main(String[] args) {
        HTMLController htmlController = new HTMLController();

        try {
            htmlController.view();
        } catch (Throwable e) {
            htmlController.showError("Houve um erro durante a execução do programa!");
        }
    }
}
