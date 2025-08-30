package calculadora;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}

	@DisplayName("Testa a subtração de dois números")
	@Test
	public void testSubtracaoDoisNumeros() {
		int subtracao = calc.subtracao(10, 6);		
		Assertions.assertEquals(4, subtracao);		
	}
	
	@DisplayName("Testa a multiplicação de dois números")
	@Test
	public void testMultiplicacaoDoisNumeros() {
		int multiplicacao = calc.multiplicacao(9, 7);		
		Assertions.assertEquals(63, multiplicacao);		
	}
	

	

	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@Test
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exce��o n�o lan�ada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}

	
	
	@DisplayName("Teste se é positivo")
	
	   @Test
	    public void testEhPositivo() {
	        boolean resultado = calc.ehPositivo(2);
	        assertTrue(resultado);
	    }

	    
	    
	    @Test
	    public void testEhNegativo() {
	        boolean resultado = calc.ehPositivo(-2);
	        assertFalse(resultado);
	    }
	    
	    @Test
	    public void testEhZero() {
	        boolean resultado = calc.ehPositivo(0);
	        assertTrue(resultado);
	    }
	
	    @Test
	    @DisplayName("Testa quando os dois números são iguais")
	    public void testComparaIgual() {
	        int resultado = calc.compara(2, 2);
	        assertEquals(0, resultado);
	    }
	    
	    @Test
	    @DisplayName("Testa quando o primeiro número é maior")
	    public void testComparaMaior() {
	        int resultado = calc.compara(2, 1);
	        assertEquals(1, resultado);
	    }
	    
	
}





