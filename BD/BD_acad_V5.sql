DROP DATABASE IF EXISTS acad;
CREATE DATABASE IF NOT EXISTS acad;
USE acad;

CREATE TABLE professor(
	idProf INT PRIMARY KEY AUTO_INCREMENT,
	pNomeProf VARCHAR(30) NOT NULL,
	uNomeProf VARCHAR(30) NOT NULL,
	dataNasc DATE NOT NULL,
    telefone VARCHAR(12)NOT NULL UNIQUE,
	sexo VARCHAR(1) NOT NULL,
    imgProf VARCHAR(200),
	area VARCHAR(50) NOT NULL,
    senha VARCHAR(8) NOT NULL,
    usuario VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE aluno(
	idAluno INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(10) NOT NULL UNIQUE,
	pNomeAluno VARCHAR(10) NOT NULL,
	uNomeAluno VARCHAR(10) NOT NULL,
    problemaSaude VARCHAR(100),
    objetivo VARCHAR(100),
	dataNasc VARCHAR(10) NOT NULL,
	telefone VARCHAR(11)NOT NULL UNIQUE,
    sexo VARCHAR(1) NOT NULL,
	peso DOUBLE NOT NULL,
	altura DOUBLE NOT NULL,
	imc VARCHAR(5) NOT NULL,
    senha VARCHAR(8) NOT NULL,
    imgAluno VARCHAR(200),
    idProf INT NOT NULL,
    FOREIGN KEY (idProf) REFERENCES professor(idProf) ON UPDATE CASCADE
);



CREATE TABLE suplemento(
	idSuplemento INT PRIMARY KEY AUTO_INCREMENT,
	nomeSuplemento VARCHAR(80) NOT NULL,
	descSuplemento VARCHAR(200) NOT NULL,
	dosagemSuplemento VARCHAR(100) NOT NULL,
    imgSup VARCHAR(200)
);

CREATE TABLE aparelho(
	idAparelho INT PRIMARY KEY AUTO_INCREMENT,
    nomeAparelho VARCHAR(50) NOT NULL
);

CREATE TABLE exercicio(
	idExercicio INT PRIMARY KEY AUTO_INCREMENT,
    nomeExercicio VARCHAR(100) NOT NULL,
	imgExercicio VARCHAR(200) ,
    idAparelho INT,
	FOREIGN KEY (idAparelho) REFERENCES aparelho(idAparelho) ON UPDATE CASCADE
);


CREATE TABLE treino(
	idTreino INT PRIMARY KEY AUTO_INCREMENT,
    idProfessor INT NOT NULL,
	NomeTreino VARCHAR(20) NOT NULL,
    FOREIGN KEY (idProfessor) REFERENCES professor(idProf) ON UPDATE CASCADE
);

CREATE TABLE treino_exercicio(
	idTreinoExercicio INT PRIMARY KEY AUTO_INCREMENT,
	repeticoes INT NOT NULL,
    series INT NOT NULL,
    anotacoes VARCHAR(200) NOT NULL,
    idExercicio INT NOT NULL,
    idTreino INT NOT NULL,
    FOREIGN KEY (idExercicio) REFERENCES exercicio(idExercicio) ON UPDATE CASCADE,
	FOREIGN KEY (idTreino) REFERENCES treino(idTreino) ON UPDATE CASCADE
);

CREATE TABLE treino_aluno(
	idTreino_aluno INT PRIMARY KEY AUTO_INCREMENT,
    idAluno INT NOT NULL,
    idTreino INT NOT NULL,
    FOREIGN KEY (idAluno) REFERENCES aluno(idAluno) ON UPDATE CASCADE,
	FOREIGN KEY (idTreino) REFERENCES treino(idTreino) ON UPDATE CASCADE
);
    