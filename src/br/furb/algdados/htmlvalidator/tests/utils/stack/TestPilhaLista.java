package br.furb.algdados.htmlvalidator.tests.utils.stack;

import br.furb.algdados.htmlvalidator.utils.stack.PilhaLista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPilhaLista {
    private PilhaLista pilhaLista;

    @Before
    public void init() {
        pilhaLista = new PilhaLista();
    }

    @After
    public void finalize() {
        pilhaLista = null;
    }

    @Test
    public void deveEstarVazio() {
        assertEquals(true, pilhaLista.estaVazia());
    }

    @Test
    public void deveNaoEstarVazio() {
        pilhaLista.push(10);

        assertEquals(false, pilhaLista.estaVazia());
    }

    @Test
    public void deveEmpilharDesempilharCorretamente() {
        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        assertEquals(30, pilhaLista.pop());
        assertEquals(20, pilhaLista.pop());
        assertEquals(10, pilhaLista.pop());
        assertEquals(true, pilhaLista.estaVazia());
    }

    @Test
    public void deveLiberarTamanho() {
        pilhaLista.push(10);
        pilhaLista.push(20);
        pilhaLista.push(30);

        pilhaLista.liberar();

        assertEquals(true, pilhaLista.estaVazia());
    }
}
