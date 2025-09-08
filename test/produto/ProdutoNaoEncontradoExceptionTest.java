package produto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import carrinho.Carrinho;

public class ProdutoNaoEncontradoExceptionTest {
    
    @Test
    @DisplayName("Testa se exceção é lançada ao remover produto não existente")
    public void testLancaExcecaoProdutoNaoEncontrado() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Livro Java", 79.90);
        
        assertThrows(ProdutoNaoEncontradoException.class, 
            () -> carrinho.removeItem(produto));
    }
}
