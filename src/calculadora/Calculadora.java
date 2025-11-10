package calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de unidade para a classe Calculadora usando JUnit 5 (Jupiter).
 * Compatível com Java 8.
 *
 * Observações de escopo (inferidas pelos javadocs e assinaturas do arquivo):
 * - soma(a,b): a + b
 * - subtracao(a,b): a - b
 * - multiplicacao(a,b): a * b
 * - divisao(a,b): a / b (divisão inteira); b==0 deve lançar ArithmeticException (Java)
 * - somatoria(n): soma de 0 até n (inclusive). Para n < 0, espera-se 0 (loop não executa).
 * - ehPositivo(n): verdadeiro se n >= 0
 * - compara(a,b): 0 se a==b; 1 se a>b; -1 se a<b
 */
public class CalculadoraTest {

    private final Calculadora calc = new Calculadora();

    // ----------------------------- soma ---------------------------------

    @Test
    @DisplayName("soma: deve somar dois números positivos")
    void somaDoisPositivos() {
        assertEquals(7, calc.soma(3, 4));
    }

    @Test
    @DisplayName("soma: deve somar positivo e negativo")
    void somaPositivoENegativo() {
        assertEquals(-2, calc.soma(3, -5));
        assertEquals(2, calc.soma(-3, 5));
    }

    @Test
    @DisplayName("soma: deve somar dois números negativos")
    void somaDoisNegativos() {
        assertEquals(-9, calc.soma(-4, -5));
    }

    // -------------------------- subtracao -------------------------------

    @Test
    @DisplayName("subtracao: básico")
    void subtracaoBasico() {
        assertEquals(1, calc.subtracao(5, 4));
        assertEquals(-1, calc.subtracao(4, 5));
    }

    @Test
    @DisplayName("subtracao: com negativos")
    void subtracaoComNegativos() {
        assertEquals(-9, calc.subtracao(-4, 5));
        assertEquals(1, calc.subtracao(-4, -5));
    }

    // ------------------------ multiplicacao -----------------------------

    @Test
    @DisplayName("multiplicacao: casos básicos")
    void multiplicacaoBasico() {
        assertEquals(20, calc.multiplicacao(4, 5));
        assertEquals(0, calc.multiplicacao(0, 7));
        assertEquals(-15, calc.multiplicacao(3, -5));
        assertEquals(15, calc.multiplicacao(-3, -5));
    }

    // --------------------------- divisao --------------------------------

    @Test
    @DisplayName("divisao: divisão inteira/truncada")
    void divisaoInteira() {
        assertEquals(2, calc.divisao(9, 4)); // 9/4 = 2 (inteiro)
        assertEquals(-3, calc.divisao(10, -3)); // 10/-3 = -3 (truncado)
        assertEquals(0, calc.divisao(0, 5));
    }

    @Test
    @DisplayName("divisao: deve lançar ArithmeticException quando divisor for zero")
    void divisaoPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> calc.divisao(5, 0));
    }

    // -------------------------- somatoria -------------------------------

    @Test
    @DisplayName("somatoria: n=0 deve retornar 0")
    void somatoriaZero() {
        assertEquals(0, calc.somatoria(0));
    }

    @Test
    @DisplayName("somatoria: n positivo (ex.: 1, 5)")
    void somatoriaPositivos() {
        assertEquals(1, calc.somatoria(1));   // 0+1 = 1
        assertEquals(15, calc.somatoria(5));  // 0+1+2+3+4+5 = 15
        assertEquals(55, calc.somatoria(10)); // 55
    }

    @Test
    @DisplayName("somatoria: n negativo deve retornar 0 (loop não executa)")
    void somatoriaNegativo() {
        assertEquals(0, calc.somatoria(-3));
    }

    // ------------------------- ehPositivo --------------------------------

    @Test
    @DisplayName("ehPositivo: verdadeiro para n>=0; falso para n<0")
    void ehPositivoComZeroPositivoENegativo() {
        assertTrue(calc.ehPositivo(0));
        assertTrue(calc.ehPositivo(7));
        assertFalse(calc.ehPositivo(-1));
        assertFalse(calc.ehPositivo(-100));
    }

    // --------------------------- compara ---------------------------------

    @Test
    @DisplayName("compara: igualdade")
    void comparaIgualdade() {
        assertEquals(0, calc.compara(3, 3));
        assertEquals(0, calc.compara(-2, -2));
    }

    @Test
    @DisplayName("compara: maior e menor")
    void comparaMaiorMenor() {
        assertEquals(1, calc.compara(5, 2));   // a > b
        assertEquals(-1, calc.compara(2, 5));  // a < b
        assertEquals(1, calc.compara(-1, -3)); // -1 > -3
        assertEquals(-1, calc.compara(-4, -2));// -4 < -2
    }
}
