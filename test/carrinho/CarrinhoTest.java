package carrinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Carrinho - Testes de unidade (JUnit 5, Java 8)")
class CarrinhoTest {

    private Carrinho carrinho;
    private static final double EPS = 1e-9;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
    }

    // ----------------- Helpers -----------------
    private Produto p(String nome, double preco) {
        return new Produto(nome, preco);
    }

    // ----------------- Estado inicial -----------------
    @Test
    @DisplayName("Ao criar: vazio e total 0.0")
    void começaVazioComTotalZero() {
        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), EPS);
    }

    // ----------------- Adição e soma -----------------
    @Test
    @DisplayName("Adicionar um item aumenta quantidade e total")
    void adicionarUmItem() {
        carrinho.addItem(p("Banana", 3.50));
        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(3.50, carrinho.getValorTotal(), EPS);
    }

    @ParameterizedTest(name = "P1={0}, P2={1}, P3={2} -> total={3}")
    @CsvSource({
        "10.0, 5.0,  0.0, 15.0",
        "19.9, 0.1,  0.0, 20.0",
        "1.99, 2.01, 3.0, 7.0",
        "0.0,  0.0,  0.0, 0.0"
    })
    @DisplayName("Total é a soma dos preços (múltiplos itens)")
    void somaMultiplosItens(double v1, double v2, double v3, double esperado) {
        carrinho.addItem(p("P1", v1));
        carrinho.addItem(p("P2", v2));
        carrinho.addItem(p("P3", v3));
        assertEquals(3, carrinho.getQtdeItems());
        assertEquals(esperado, carrinho.getValorTotal(), EPS);
    }

    @Test
    @DisplayName("Preço 0.0 é aceito e não altera o total")
    void precoZeroAceito() {
        carrinho.addItem(p("Gratis", 0.0));
        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), EPS);
    }

    @Test
    @DisplayName("Soma com doubles usa delta (0.1+0.2≈0.3)")
    void somaDoublePrecisao() {
        carrinho.addItem(p("A", 0.1));
        carrinho.addItem(p("B", 0.2));
        assertEquals(0.3, carrinho.getValorTotal(), EPS);
    }

    // ----------------- Remoção -----------------
    @Test
    @DisplayName("Remover pela mesma instância reduz quantidade e total")
    void removerMesmaInstancia() throws Exception {
        Produto a = p("A", 10.0);
        Produto b = p("B", 5.0);
        carrinho.addItem(a);
        carrinho.addItem(b);

        carrinho.removeItem(a);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(5.0, carrinho.getValorTotal(), EPS);
    }

    @Test
    @DisplayName("Remover por igualdade (mesmo nome, instância diferente) funciona porque Produto.equals usa nome")
    void removerPorIgualdadeDeNome() throws Exception {
        Produto noCarrinho = p("Notebook", 3000.0);
        carrinho.addItem(noCarrinho);

        // Mesma identidade lógica (mesmo nome), outra instância:
        Produto outroComMesmoNome = p("Notebook", 1234.56);

        // items.remove(item) usa equals -> comparamos por nome
        carrinho.removeItem(outroComMesmoNome);

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), EPS);
    }

    @Test
    @DisplayName("Remover item inexistente lança ProdutoNaoEncontradoException")
    void removerInexistenteLancaExcecao() {
        Produto x = p("X", 7.5);
        assertThrows(ProdutoNaoEncontradoException.class, () -> carrinho.removeItem(x));
    }

    @Test
    @DisplayName("Remover até esvaziar mantém total consistente")
    void removerAteEsvaziar() throws Exception {
        Produto a = p("A", 2.0);
        Produto b = p("B", 3.0);
        carrinho.addItem(a);
        carrinho.addItem(b);
        assertEquals(5.0, carrinho.getValorTotal(), EPS);

        carrinho.removeItem(b);
        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(2.0, carrinho.getValorTotal(), EPS);

        carrinho.removeItem(a);
        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), EPS);
    }

    @Test
    @DisplayName("Adicionar a mesma instância N vezes soma N*preço")
    void adicionarMesmaInstanciaVariasVezes() {
        Produto r = p("Repetido", 4.0);
        carrinho.addItem(r);
        carrinho.addItem(r);
        carrinho.addItem(r);
        assertEquals(3, carrinho.getQtdeItems());
        assertEquals(12.0, carrinho.getValorTotal(), EPS);
    }

    // ----------------- Esvaziar -----------------
    @Test
    @DisplayName("esvazia() limpa itens e zera total")
    void esvaziarZera() {
        carrinho.addItem(p("A", 1.0));
        carrinho.addItem(p("B", 2.0));
        carrinho.addItem(p("C", 3.0));
        assertEquals(3, carrinho.getQtdeItems());
        assertEquals(6.0, carrinho.getValorTotal(), EPS);

        carrinho.esvazia();

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), EPS);
    }

    // ----------------- Mutabilidade do Produto -----------------
    @Test
    @DisplayName("Alterar o preço do Produto após adicionar reflete no total (mantém referência)")
    void alterarPrecoDepoisDeAdicionarAfetaTotal() {
        Produto p = p("Livro", 50.0);
        carrinho.addItem(p);
        assertEquals(50.0, carrinho.getValorTotal(), EPS);

        // muda o preço do mesmo objeto já dentro do carrinho
        p.setPreco(80.0);
        assertEquals(80.0, carrinho.getValorTotal(), EPS);
    }

    // ----------------- Observações sobre equals do Produto (opcional) -----------------
    @Nested
    @DisplayName("Observações sobre Produto.equals (para entender remoção por nome)")
    class ProdutoEqualsObservacoes {

        @Test
        @DisplayName("Produtos com mesmo nome são 'iguais' (mesmo nome -> equals true)")
        void produtosComMesmoNomeSaoIguais() {
            assertTrue(p("Mouse", 10).equals(p("Mouse", 999.0)));
        }

        @Test
        @DisplayName("equals não trata null/tipo diferente (pode lançar ClassCastException)")
        void equalsPodeLancarClassCast() {
            Produto prod = p("Teclado", 100.0);
            assertThrows(ClassCastException.class, () -> prod.equals("não é Produto"));
            assertThrows(NullPointerException.class, () -> prod.equals(null));
        }
    }
}

	
	


	
	
	


