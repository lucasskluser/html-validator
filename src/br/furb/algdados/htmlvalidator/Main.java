package br.furb.algdados.htmlvalidator;

import br.furb.algdados.htmlvalidator.controllers.HTMLController;

public class Main {
    public static void main(String[] args) {
        HTMLController htmlController = new HTMLController();

        try {
            htmlController.view();
        } catch (Throwable e) {
            htmlController.showError("Houve um erro durante a execuação do programa!");
        }
    }
}
