package models;

public class Aluno {

    private int idAluno;
    private String telefoneAluno;
    private String usuarioAluno;
    private String pNomeAluno;
    private String uNomeAluno;
    private String nomeCompleto;
    private String problemaSaude;
    private String objetivo;
    private String dataNascAluno;
    private String sexoAluno;
    private double pesoAluno;
    private double alturaAluno;
    private String imc;
    private String senha;
    private int Idprof;
    private String imgAluno;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String uNome, String pNome) {
        this.nomeCompleto = pNome + " " + uNome;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public String getUsuarioAluno() {
        return usuarioAluno;
    }

    public void setUsuarioAluno(String usuarioAluno) {
        this.usuarioAluno = usuarioAluno;
    }

    public String getImgAluno() {
        return imgAluno;
    }

    public void setImgAluno(String imgAluno) {
        this.imgAluno = imgAluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getpNomeAluno() {
        return pNomeAluno;
    }

    public void setpNomeAluno(String pNomeAluno) {
        this.pNomeAluno = pNomeAluno;
    }

    public String getuNomeAluno() {
        return uNomeAluno;
    }

    public void setuNomeAluno(String uNomeAluno) {
        this.uNomeAluno = uNomeAluno;
    }

    public String getProblemaSaude() {
        return problemaSaude;
    }

    public void setProblemaSaude(String problemaSaude) {
        this.problemaSaude = problemaSaude;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDataNascAluno() {
        return dataNascAluno;
    }

    public void setDataNascAluno(String dataNascAluno) {
        this.dataNascAluno = dataNascAluno;
    }

    public String getSexoAluno() {
        return sexoAluno;
    }

    public void setSexoAluno(String sexoAluno) {
        this.sexoAluno = sexoAluno;
    }

    public double getPesoAluno() {
        return pesoAluno;
    }

    public void setPesoAluno(double pesoAluno) {
        this.pesoAluno = pesoAluno;
    }

    public double getAlturaAluno() {
        return alturaAluno;
    }

    public void setAlturaAluno(double alturaAluno) {
        this.alturaAluno = alturaAluno;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdprof() {
        return Idprof;
    }

    public void setIdprof(int Idprof) {
        this.Idprof = Idprof;
    }

    public void setNomeCompleto(String Nome) {
        this.nomeCompleto = Nome;
    }

}
