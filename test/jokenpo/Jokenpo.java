package jokenpo;


import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Assertions; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste do Jokenpo")
public class JokenpoTest {

    private Jokenpo jokenpo;

    @BeforeEach
    public void inicializa() {
        jokenpo = new Jokenpo();
    }

    @DisplayName("Testa o empate no jogo")
    @Test
    public void testEmpate() {
        int resultado = jokenpo.jogar(1, 1);
       
        assertEquals(0, resultado);
    }

    @DisplayName("Testa quando o Jogador 1 vence")
    @Test
    public void testJogador1Vence() {
        int resultado = jokenpo.jogar(1, 2);
       
        assertEquals(1, resultado);
    }

    @DisplayName("Testa quando o Jogador 2 vence")
    @Test
    public void testJogador2Vence() {
        int resultado = jokenpo.jogar(2, 1);
       
        assertEquals(2, resultado);
    }

    @DisplayName("Testa retorno para opção inválida")
    @Test
    public void testOpcaoInvalida() {
        int resultado = jokenpo.jogar(1, 5);
         
        assertEquals(-1, resultado);
    }
}
