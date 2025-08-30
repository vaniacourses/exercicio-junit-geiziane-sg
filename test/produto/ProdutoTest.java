package produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProdutoTest {
	
	Produto livro;
	
	@BeforeEach
	public void inicializa() {
		livro = new Produto("Introdu��o ao Teste de Software", 100.00);
	}
	
	@Test
	public void testCriaProduto() {
		Assertions.assertAll("livro",
				() -> assertEquals("Introdu��o ao Teste de Software", livro.getNome()),
				() -> assertTrue(100.00 == livro.getPreco())						
				);
	}
	
	@Test
	public void testProdutosIguais() {
		Produto livro2 = new Produto("Introdu��o ao Teste de Software", 90.00);
		
		assertNotSame(livro, livro2);
		
	}
	
	
	
	@Test
	@DisplayName("Testa a alteração do nome do produto usando setNome")
	public void testSetNome() {
	    String novoNome = "Java para Iniciantes";
	    livro.setNome(novoNome);
	    }
	
	
	
	@Test
	@DisplayName ("Testa alteração do preço do produto usando getPreco")
	public void testSetPreco() {
	Double precoNovo = 34.00;
	livro.setPreco(precoNovo);
	assertEquals(precoNovo,livro.getPreco())
			;}
	
	
@DisplayName("Testa se o equals considera objetos diferentes com o mesmo nome como iguais")
	
	@Test  
	public void testProdutosDistintosNomeIguais() {  
	    Produto livro3 = new Produto("A menina que roubava livros", 40.00);  
	    Produto livro4 = new Produto("A menina que roubava livros", 45.00);  
	    assertEquals(livro3, livro4); 
	}
	
	@Test
	public void assertionComHamcrestMatcher() {
		assertThat(livro.getPreco(), equalTo(100.00));
		assertThat(livro.getNome(), notNullValue());
		assertThat(livro.getNome(), containsString("Teste"));
		assertThat(livro, instanceOf(Produto.class));
	}

}
