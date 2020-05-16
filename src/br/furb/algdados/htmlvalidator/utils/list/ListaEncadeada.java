package br.furb.algdados.htmlvalidator.utils.list;

public class ListaEncadeada<T> {
    private NoLista<T> primeiro;
    private int tamanho;

    public void inserir(T info) {
        NoLista<T> noLista = new NoLista<>();
        noLista.setInfo(info);

        if(primeiro != null) {
            noLista.setProximo(primeiro);
        }

        primeiro = noLista;
        tamanho++;
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public NoLista<T> buscar(T info){
        NoLista<T> noLista = primeiro;

        while (noLista != null) {
            if (noLista.getInfo() == info) {
                return noLista;
            }

            noLista = noLista.getProximo();
        }

        return null;
    }

    public void retirar(T info) {
        if(primeiro.getInfo().equals(info)){
            primeiro = primeiro.getProximo();
            return;
        }

        NoLista<T> noLista = primeiro;
        NoLista<T> noAnterior = noLista;

        while (noLista != null){
            if(noLista.getInfo() == info) {
                break;
            }

            noAnterior = noLista;
            noLista = noLista.getProximo();
        }

        noAnterior.setProximo(noLista.getProximo());
        tamanho--;
    }

    public int obterComprimento() {
        return tamanho;
    }

    public NoLista<T> obterNo(int idx) {
        if(idx < 0 || idx > obterComprimento()){
            throw new IndexOutOfBoundsException();
        }

        NoLista<T> noLista = primeiro;
        int i = 0;

        while(noLista != null) {
            if (i == idx){
                break;
            }

            noLista = noLista.getProximo();
            i++;
        }

        return noLista;
    }

    @Override
    public String toString() {
        String string = "";
        NoLista<T> noLista = primeiro;

        while (noLista != null){
            if (string.length() > 0) {
                string += ", ";
            }

            string += noLista.getInfo();
            noLista = noLista.getProximo();
        }

        return string;
    }
}
