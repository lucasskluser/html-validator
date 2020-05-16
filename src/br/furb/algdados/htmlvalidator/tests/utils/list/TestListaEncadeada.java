package br.furb.algdados.htmlvalidator.tests.utils.list;

import br.furb.algdados.htmlvalidator.utils.list.ListaEncadeada;
import br.furb.algdados.htmlvalidator.utils.list.NoLista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestListaEncadeada {
    private ListaEncadeada<Integer> listaEncadeada;

    @Before
    public void init() {
        listaEncadeada = new ListaEncadeada<>();
    }

    @After
    public void finalize() {
        listaEncadeada = null;
    }

    @Test
    public void listaDeveEstarVazia() {
         assertEquals(listaEncadeada.estaVazia(), true);
    }

    @Test
    public void listaNaoDeveEstarVazia() {
        listaEncadeada.inserir(5);
        assertEquals(listaEncadeada.estaVazia(), false);
    }

    @Test
    public void deveIncluirNo() {
        listaEncadeada.inserir(5);
        NoLista<Integer> no = listaEncadeada.getPrimeiro();
        int info = no.getInfo();
        assertEquals(5, info);
        assertEquals(no.getProximo(), null);
    }

    @Test
    public void deveIncluirTresNos() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);

        int info = listaEncadeada.getPrimeiro().getInfo();
        assertEquals(15, info);
        listaEncadeada.retirar(15);

        info = listaEncadeada.getPrimeiro().getInfo();
        assertEquals(10, info);
        listaEncadeada.retirar(listaEncadeada.getPrimeiro().getInfo());

        info = listaEncadeada.getPrimeiro().getInfo();
        assertEquals(5, info);
        listaEncadeada.retirar(listaEncadeada.getPrimeiro().getInfo());

        assertEquals(listaEncadeada.getPrimeiro(), null);
    }

    @Test
    public void deveRetornarNoComNumeroBuscado() {
         listaEncadeada.inserir(5);
         listaEncadeada.inserir(10);
         listaEncadeada.inserir(15);
         listaEncadeada.inserir(20);

         listaEncadeada.buscar(20);

         int info = listaEncadeada.buscar(20).getInfo();
         assertEquals(20, info);

         info = listaEncadeada.buscar(15).getInfo();
         assertEquals(15, info);

         info = listaEncadeada.buscar(10).getInfo();
         assertEquals(10, info);

         info = listaEncadeada.buscar(5).getInfo();
         assertEquals(5, info);

         assertEquals(null, listaEncadeada.buscar(50));
    }

    @Test
    public void deveRetornarNosRestantes1() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        listaEncadeada.retirar(20);

        NoLista<Integer> noLista = listaEncadeada.getPrimeiro();

        int info = noLista.getInfo();
        assertEquals(15, info);

        noLista = noLista.getProximo();
        info = noLista.getInfo();
        assertEquals(10, info);

        info = noLista.getInfo();
        noLista = noLista.getProximo();
        assertEquals(5, info);
    }

    @Test
    public void deveRetornarNosRestantes2() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        listaEncadeada.retirar(15);

        NoLista<Integer> noLista = listaEncadeada.getPrimeiro();
        int info = noLista.getInfo();
        assertEquals(20, info);

        noLista = noLista.getProximo();
        info = noLista.getInfo();
        assertEquals(10, info);

        noLista = noLista.getProximo();
        info = noLista.getInfo();
        assertEquals(5, info);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deveRetornarNoBuscado() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        int info = listaEncadeada.obterNo(0).getInfo();
        assertEquals(20, info);

        info = listaEncadeada.obterNo(3).getInfo();
        assertEquals(5, info);

        listaEncadeada.obterNo(10);
    }

    @Test
    public void deveTerTamanhoZero() {
        assertEquals(0, listaEncadeada.obterComprimento());
    }

    @Test
    public void deveTerTamanhoQuatro() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        assertEquals(4, listaEncadeada.obterComprimento());
    }

}
