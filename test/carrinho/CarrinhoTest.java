package carrinho;

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
import java.util.ArrayList;
import java.util.Iterator;
import produto.Produto;
import produto.ProdutoNaoEncontradoException;

import static org.hamcrest.CoreMatchers.*;



class CarrinhoTest {
	
	Carrinho carrinho;
	Produto livro1;
	Produto marcadorPagina;
	
	@BeforeEach
	public void inicializa() {
		carrinho = new Carrinho ();
		livro1 = new Produto ("A menina quebrada", 79.00);
		marcadorPagina = new Produto ("Lilo e Stitch ", 12.00);
		
	}
	
	
	@Test
	@DisplayName("Testa se um novo carrinho é criado  e está vazio")

	void testCriaCarrinho() {
		
		
		assertEquals(0, carrinho.getQtdeItems());
		assertEquals(0.0, carrinho.getValorTotal());
		
	}
	
	
	@Test
	@DisplayName("Testa de quantidade de itens aumentar ao adicionar um produto")

	void testAdicionaItem () {
		
		carrinho.addItem(livro1);
		carrinho.addItem(livro1);
		carrinho.addItem(marcadorPagina);
		assertEquals(3, carrinho.getQtdeItems());
	}
	
	@Test
	@DisplayName("Testa o valor total dos produtos do carrinho")

	void testValorTotalCarrinho() {
		
		carrinho.addItem(livro1);
		carrinho.addItem(livro1);
		carrinho.addItem(marcadorPagina);
		assertEquals(170.00, carrinho.getValorTotal());
		
	
	}
	
	@Test
	@DisplayName("Testa remover item do carrinho ")
	void testRemoveItemExistente() throws ProdutoNaoEncontradoException {
		carrinho.addItem(livro1);
		carrinho.addItem(livro1);
		carrinho.addItem(marcadorPagina);
		assertEquals(3, carrinho.getQtdeItems());
		
		carrinho.removeItem(livro1);
		assertEquals(2, carrinho.getQtdeItems());
		
		

		}
		
		@Test
		@DisplayName("Testa o esvaziamento completo do carrinho")
		void testEsvaziaCarrinho() {
			carrinho.addItem(livro1);
			carrinho.addItem(marcadorPagina);
			assertEquals(2, carrinho.getQtdeItems());
			
			carrinho.esvazia();
			assertEquals(0, carrinho.getQtdeItems());
		}
		
		
		
	}
	
		
	
	
	

