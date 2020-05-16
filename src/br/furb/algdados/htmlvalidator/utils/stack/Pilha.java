package br.furb.algdados.htmlvalidator.utils.stack;

public interface Pilha<T> {
    void push(T info);
    T pop();
    T peek();
    boolean estaVazia();
    void liberar();
}
