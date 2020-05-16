package br.furb.algdados.htmlvalidator.tests.utils.list;

import br.furb.algdados.htmlvalidator.utils.list.ListaEncadeada;
import br.furb.algdados.htmlvalidator.utils.list.NoLista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(no.getInfo(), 5);
        assertEquals(no.getProximo(), null);
    }

    @Test
    public void deveIncluirTresNos() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);

        assertEquals(15, listaEncadeada.getPrimeiro().getInfo());
        listaEncadeada.retirar(15);

        assertEquals(10, listaEncadeada.getPrimeiro().getInfo());
        listaEncadeada.retirar(listaEncadeada.getPrimeiro().getInfo());

        assertEquals(5, listaEncadeada.getPrimeiro().getInfo());
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

         assertEquals(20, listaEncadeada.buscar(20).getInfo());
         assertEquals(15, listaEncadeada.buscar(15).getInfo());
         assertEquals(10, listaEncadeada.buscar(10).getInfo());
         assertEquals(5, listaEncadeada.buscar(5).getInfo());
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
        assertEquals(15, noLista.getInfo());

        noLista = noLista.getProximo();
        assertEquals(10, noLista.getInfo());

        noLista = noLista.getProximo();
        assertEquals(5, noLista.getInfo());
    }

    @Test
    public void deveRetornarNosRestantes2() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        listaEncadeada.retirar(15);

        NoLista<Integer> noLista = listaEncadeada.getPrimeiro();
        assertEquals(20, noLista.getInfo());

        noLista = noLista.getProximo();
        assertEquals(10, noLista.getInfo());

        noLista = noLista.getProximo();
        assertEquals(5, noLista.getInfo());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deveRetornarNoBuscado() {
        listaEncadeada.inserir(5);
        listaEncadeada.inserir(10);
        listaEncadeada.inserir(15);
        listaEncadeada.inserir(20);

        assertEquals(20, listaEncadeada.obterNo(0).getInfo());
        assertEquals(5, listaEncadeada.obterNo(3).getInfo());

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
