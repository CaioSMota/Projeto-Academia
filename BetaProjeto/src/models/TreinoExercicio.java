package models;

public class TreinoExercicio {

    private int idTreinoAparelho;
    private int repeticoes;
    private int series;
    private String anotacoes;
    private Exercicio exercicio;
    private Treino treino;

    public int getIdTreinoAparelho() {
        return idTreinoAparelho;
    }

    public void setIdTreinoAparelho(int idTreinoAparelho) {
        this.idTreinoAparelho = idTreinoAparelho;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

}
