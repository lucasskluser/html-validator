/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.utils.stack;

import br.furb.algdados.htmlvalidator.utils.list.ListaEncadeada;

public class PilhaLista<T> implements Pilha<T> {
    private ListaEncadeada<T> lista;

    public PilhaLista() {
        lista = new ListaEncadeada();
    }

    @Override
    public void push(T info) {
        lista.inserir(info);
    }

    @Override
    public T pop() {
        if(estaVazia()) {
            throw new PilhaVaziaException();
        }

        T info = peek();
        lista.retirar(info);
        return info;
    }

    @Override
    public T peek() {
        T info = lista.obterNo(0).getInfo();
        return info;
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        while (estaVazia() == false){
            pop();
        }
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
