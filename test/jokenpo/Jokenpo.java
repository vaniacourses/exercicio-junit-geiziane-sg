package jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class JokenpoTest {

    private final Jokenpo jogo = new Jokenpo();

    // =========================
    // Caminho: Empates (retorna 0)
    // =========================
    @ParameterizedTest(name = "Empate: {0} vs {1} → 0")
    @DisplayName("Deve retornar 0 quando as escolhas são iguais")
    @CsvSource({
        "1,1", // papel vs papel
        "2,2", // pedra vs pedra
        "3,3"  // tesoura vs tesoura
    })
    void deveRetornarEmpate(int j1, int j2) {
        assertEquals(0, jogo.jogar(j1, j2));
    }

    // =======================================
    // Caminho: Jogador 1 vence (retorna 1)
    // =======================================
    @ParameterizedTest(name = "J1 vence: {0} vs {1} → 1")
    @DisplayName("Deve retornar 1 quando Jogador 1 vence")
    @CsvSource({
        "1,2", // papel cobre pedra
        "2,3", // pedra quebra tesoura
        "3,1"  // tesoura corta papel
    })
    void deveRetornarVitoriaJogador1(int j1, int j2) {
        assertEquals(1, jogo.jogar(j1, j2));
    }

    // =======================================
    // Caminho: Jogador 2 vence (retorna 2)
    // =======================================
    @ParameterizedTest(name = "J2 vence: {0} vs {1} → 2")
    @DisplayName("Deve retornar 2 quando Jogador 2 vence")
    @CsvSource({
        "2,1",
        "3,2",
        "1,3"
    })
    void deveRetornarVitoriaJogador2(int j1, int j2) {
        assertEquals(2, jogo.jogar(j1, j2));
    }

    // ==================================
    // Entradas inválidas (retorna -1)
    // ==================================
    @ParameterizedTest(name = "Inválido: {0} vs {1} → -1")
    @DisplayName("Deve retornar -1 para qualquer entrada inválida")
    @CsvSource({
        "0,1", "1,0", "4,2", "2,4", "-1,3", "3,-1", "0,0", "4,4"
    })
    void deveRetornarInvalido(int j1, int j2) {
        assertEquals(-1, jogo.jogar(j1, j2));
    }

    // ===================================================
    // Propriedade de alternância de vitória
    // ===================================================
    @ParameterizedTest(name = "Alternância: {0} vs {1}")
    @DisplayName("Se J1 vence em (j1,j2), J2 vence em (j2,j1)")
    @CsvSource({
        "1,2", "2,3", "3,1"
    })
    void propriedadeAlternanciaVitoria(int j1, int j2) {
        assertEquals(1, jogo.jogar(j1, j2));
        assertEquals(2, jogo.jogar(j2, j1));
    }

    // =========================
    // Smoke test adicional
    // =========================
    @Test
    @DisplayName("Smoke: chamadas válidas não lançam exceção")
    void smokeNaoLancaExcecao() {
        jogo.jogar(1, 2);
        jogo.jogar(2, 1);
        jogo.jogar(3, 3);
    }
}
