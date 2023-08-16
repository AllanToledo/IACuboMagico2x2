import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
public class Estado implements Comparable<Estado> {
    @ToString.Exclude
    private List<Estado> estadosFilhos;
    @ToString.Exclude
    private Estado estadoPai;
    private Problema problema;
    private boolean caminhoSolucao = false;
    private int profudindidade = 0;
    private int euristicaEProfunidade;
    public static Map<Estado, Integer> estados = new HashMap<>();
    public static int coeficienteProfundidade = 7;
    public static int coeficienteHeuristica = 3;

    public Estado(Estado estadoPai, Problema problema) {
        this.estadoPai = estadoPai;
        this.problema = problema;
        this.profudindidade = estadoPai.profudindidade + 1;
        euristicaEProfunidade = this.profudindidade * coeficienteProfundidade +
                this.problema.getHeuristica() * coeficienteHeuristica;
        estadosFilhos = new ArrayList<>();
    }

    public Estado(Problema problema) {
        this.problema = problema;
        euristicaEProfunidade = this.problema.getHeuristica() * coeficienteHeuristica;
        estadosFilhos = new ArrayList<>();
    }

    public void gerarEstadosFilhos() {
        if(profudindidade >= 14) return;
        for (Problema problemaFilho : problema.gerarProblemasFilhos()) {
            Estado novo = new Estado(this, problemaFilho);
            Integer nivelRegistrado = estados.get(novo);
            if (nivelRegistrado == null || novo.profudindidade < nivelRegistrado) {
                estados.put(novo, novo.profudindidade);
                estadosFilhos.add(novo);
            }
        }
    }

    public List<Estado> getEstadosFilhos() {
        return estadosFilhos;
    }

    public void setCaminhoSolucao(boolean caminhoSolucao) {
        this.caminhoSolucao = caminhoSolucao;
        if (estadoPai != null) estadoPai.setCaminhoSolucao(caminhoSolucao);
    }

    @Override
    public int compareTo(Estado o) {
        return euristicaEProfunidade - o.euristicaEProfunidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return problema.equals(estado.problema);
    }

    @Override
    public int hashCode() {
        return problema.hashCode();
    }
}
