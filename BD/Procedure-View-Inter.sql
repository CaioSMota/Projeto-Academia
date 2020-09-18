	USE acad;
    
    -- Mostrar Treino
    
    DROP PROCEDURE IF EXISTS alunoTreino;
    DELIMITER $$
    CREATE PROCEDURE alunoTreino (in idA INT)
    BEGIN
    
    SELECT idTreinoExercicio,NomeTreino AS "Treino", nomeExercicio AS "Exercicio", nomeAparelho AS "Aparelho", series AS "Series", repeticoes AS "Repetições", anotacoes AS "Anotações" 
    FROM aluno 
    INNER JOIN treino_aluno ON  aluno.idAluno = treino_aluno.idAluno
    INNER JOIN treino ON treino.idTreino = treino_aluno.idTreino
    INNER JOIN treino_exercicio ON treino_exercicio.idTreino = treino.idTreino
    INNER JOIN exercicio ON treino_exercicio.idExercicio = exercicio.idExercicio
    INNER JOIN aparelho ON exercicio.idAparelho = aparelho.idAparelho  
    WHERE aluno.idAluno = idA;
    
    END $$
	DELIMITER ;

   -- Mostrar Treino
    
    DROP PROCEDURE IF EXISTS alunoTreinoClick;
    DELIMITER $$
    CREATE PROCEDURE alunoTreinoClick (in idE INT)
    BEGIN
    
    SELECT NomeTreino AS "Treino", nomeExercicio AS "Exercicio", nomeAparelho AS "Aparelho", series AS "Series", repeticoes AS "Repetições", anotacoes AS "Anotações",imgExercicio 
    FROM aluno 
    INNER JOIN treino_aluno ON  aluno.idAluno = treino_aluno.idAluno
    INNER JOIN treino ON treino.idTreino = treino_aluno.idTreino
    INNER JOIN treino_exercicio ON treino_exercicio.idTreino = treino.idTreino
    INNER JOIN exercicio ON treino_exercicio.idExercicio = exercicio.idExercicio
    INNER JOIN aparelho ON exercicio.idAparelho = aparelho.idAparelho 
    WHERE idTreinoExercicio = idE;
    
    END $$
	DELIMITER ;
   
    -- Mostrar Aluno do professor
    
    DROP PROCEDURE IF EXISTS alunos;
    DELIMITER $$
    CREATE PROCEDURE alunos (in idP INT)
    BEGIN
    
    SELECT idAluno,concat(pNomeAluno," ",uNomeAluno) AS "Nome Aluno", problemaSaude AS "Problema de Saúde", objetivo AS "Objetivo do aluno", telefone AS "Telefone", imc AS "IMC", sexo AS "sexo"
    FROM aluno WHERE idProf = idP;
    
    END $$
	DELIMITER ;
    
	-- Buscar Aluno do professor
    
    DROP PROCEDURE IF EXISTS BuscarAlunos;
    DELIMITER $$
    CREATE PROCEDURE BuscarAlunos (IN nomeAl VARCHAR(50),IN idP INT)
    BEGIN
    
    SELECT concat(pNomeAluno," ",uNomeAluno) AS "Nome Aluno", problemaSaude AS "Problema de Saúde", objetivo AS "Objetivo do aluno", telefone AS "Telefone", imc AS "IMC", sexo AS "sexo"
    FROM aluno WHERE pNomeAluno LIKE CONCAT('%',nomeAl,'%') AND idProf = idP;
    
    END $$
	DELIMITER ;
    
    -- Atualizar IMC do Aluno
    
    DROP PROCEDURE IF EXISTS AtualizarAluno;
    DELIMITER $$
    CREATE PROCEDURE AtualizarAluno (in idA INT, in pesoA DOUBLE, in alturaA DOUBLE,IN senhaA VARCHAR(8),IN telefoneA VARCHAR(12), IN proSaude VARCHAR(100) )
    BEGIN
    
    UPDATE aluno SET peso = pesoA, altura = alturaA, imc =  peso/(altura*altura),senha = senhaA, telefone = telefoneA, problemaSaude = proSaude WHERE idAluno = idA;
    
    END $$
	DELIMITER ;
    
    -- Inserir Aluno
    
	DROP PROCEDURE IF EXISTS InsereAluno;
    DELIMITER $$
    CREATE PROCEDURE InsereAluno(IN usuariop VARCHAR(100), IN pNome VARCHAR(10),IN uNome VARCHAR(10),IN problemaSaude VARCHAR(100),IN obj VARCHAR(100),IN dataNasc VARCHAR(10), 
    IN telefone VARCHAR(12),IN sexo VARCHAR(1), IN peso DOUBLE, IN altura DOUBLE,IN imcp VARCHAR(5), IN senhap VARCHAR(8), IN idProf INT,IN imgAluno VARCHAR(200))
    BEGIN
    
    declare v_fim int default 0;
        
		declare id INT default 0 ;
		declare cur1 cursor for
		(SELECT idAluno FROM aluno WHERE senha = senhaP AND usuario = usuarioP);
        
        declare continue handler for not found
		set v_fim = true;
        
        OPEN cur1;
        loop_curs:loop
			fetch cur1 into id;
        
			if v_fim = true then
				leave loop_curs;
			end if;
        end loop loop_curs;
		close cur1;
    
    IF id = 0 THEN
		INSERT INTO aluno (usuario,pNomeAluno,uNomeAluno,problemaSaude,objetivo,dataNasc,telefone,sexo,peso,altura,imc,senha,idProf, imgAluno) 
		VALUES (usuariop,pNome,uNome,problemaSaude,obj,dataNasc,telefone,sexo,peso,altura,imcp,senhap,idProf,imgAluno);
    END IF;
    
    END $$
	DELIMITER ;
    
	-- Insere Treino Exercicio
    
    DROP PROCEDURE IF EXISTS InsereTreinoExercicio;
    DELIMITER $$
    CREATE PROCEDURE InsereTreinoExercicio(IN repeticoesI INT,IN seriesI INT,IN anotacoesI VARCHAR(200),IN idExercicioI INT,IN idTreinoI INT)
    BEGIN
    
    INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (repeticoesI,seriesI,anotacoesI,idExercicioI,idTreinoI);
    
    END $$
	DELIMITER ;
    
	-- Inserir Professor
    
    DROP PROCEDURE IF EXISTS InsereProfessor;
    DELIMITER $$
    CREATE PROCEDURE InsereProfessor(IN pNomeProfI VARCHAR(30),IN uNomeProfI VARCHAR(30),IN dataNascI VARCHAR(10),IN telefoneI VARCHAR(11), IN sexoI VARCHAR(1),IN imgProfI VARCHAR(200), IN areaI VARCHAR(50),IN senhaI VARCHAR(8), IN usuarioI VARCHAR(10))
    BEGIN
    
    INSERT INTO professor (pNomeProf, uNomeProf, dataNasc, telefone,sexo,imgProf,area, senha, usuario) VALUES (pNomeProfI,uNomeProfI,dataNascI,telefoneI,sexoI,imgProfI,areaI,senhaI,usuarioI);
    
    END $$
	DELIMITER ;
    
    -- Insere Aparelho
    
    DROP PROCEDURE IF EXISTS InsereAparelho;
    DELIMITER $$
    CREATE PROCEDURE InsereAparelho(IN nomeAparelhoI VARCHAR(100))
    BEGIN
    
	INSERT INTO aparelho (nomeAparelho) VALUE (nomeAparelhoI);
    
    END $$
	DELIMITER ;
    
    -- Insere Exercicio
    
    DROP PROCEDURE IF EXISTS InsereExercicio;
    DELIMITER $$
    CREATE PROCEDURE InsereExercicio(IN nomeExercicioI VARCHAR(100),IN imgExercicioI VARCHAR(200),IN idAparelhoI INT)
    BEGIN
    
	INSERT INTO exercicio (nomeExercicio, imgExercicio,idAparelho) VALUES (nomeExercicioI,imgExercicioI,idAparelhoI);
    
    END $$
	DELIMITER ;
    
    -- Insere Treino
    
	DROP PROCEDURE IF EXISTS CadastraTreino;
    DELIMITER $$
    CREATE PROCEDURE CadastraTreino(IN idP INT,IN Nome VARCHAR(20))
    BEGIN
    
	INSERT INTO treino (idProfessor, NomeTreino) VALUES (idP,Nome);
    
    END $$
	DELIMITER ;
    
    -- Treino Selecionado
    
    DROP PROCEDURE IF EXISTS SelectTreino;
    DELIMITER $$
    CREATE PROCEDURE SelectTreino(IN idPr INT)
    BEGIN
    
	SELECT * FROM treino WHERE idProfessor = idPr;
    
    END $$
	DELIMITER ;
    
    call SelectTreino(1);
    -- Suplemento Selecionado
    
	DROP PROCEDURE IF EXISTS MostrarSuplementosClick;
    DELIMITER $$
    CREATE PROCEDURE MostrarSuplementosClick(IN idI int)
    BEGIN
    
	SELECT nomeSuplemento, descSuplemento,dosagemSuplemento,imgSup FROM suplemento WHERE idSuplemento = idI;
    
    
    END $$
	DELIMITER ;
    
    DROP PROCEDURE IF EXISTS InsereExercicioTreino;
    DELIMITER $$
    CREATE PROCEDURE InsereExercicioTreino(IN rept INT,IN se INT,IN ano VARCHAR(100),IN idE INT,IN idT INT)
    BEGIN
		INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (rept,se,ano,idE,idT);
        
	END $$
	DELIMITER ;
    
	DROP PROCEDURE IF EXISTS RetornaIdTreino;
    DELIMITER $$
    CREATE PROCEDURE RetornaIdTreino(IN nTreino VARCHAR(50))
    BEGIN
    
		select * FROM treino WHERE NomeTreino = nTreino;
        
	END $$
	DELIMITER ;
   
    DROP PROCEDURE IF EXISTS RetornaTreino;
    DELIMITER $$
    CREATE PROCEDURE RetornaTreino(IN idT int)
    BEGIN
    
    SELECT idTreinoExercicio,NomeTreino AS "Treino", nomeExercicio AS "Exercicio", nomeAparelho AS "Aparelho", series AS "Series", repeticoes AS "Repetições", anotacoes AS "Anotações" 
    FROM treino_exercicio INNER JOIN treino ON treino.idTreino = treino_exercicio.idTreino
    INNER JOIN exercicio ON treino_exercicio.idExercicio = exercicio.idExercicio
    INNER JOIN aparelho ON exercicio.idAparelho = aparelho.idAparelho 
    WHERE treino.idTreino = idT;
    
    END $$
	DELIMITER ;
    
    DROP PROCEDURE IF EXISTS RetornaIdAparelho;
    DELIMITER $$
    CREATE PROCEDURE RetornaIdAparelho(IN nAparelho VARCHAR(50))
    BEGIN
    
    select * FROM aparelho WHERE nomeAparelho = nAparelho;
    
    END $$
	DELIMITER ;
    
    DROP PROCEDURE IF EXISTS RemoveExercicio;
    DELIMITER $$
    CREATE PROCEDURE RemoveExercicio(IN idEx INT)
    BEGIN
    
    DELETE FROM treino_exercicio WHERE idTreinoExercicio = idEx;
    
    END $$
	DELIMITER ;
    
    
    DROP PROCEDURE IF EXISTS AtribuiTreino;
    DELIMITER $$
    CREATE PROCEDURE AtribuiTreino(IN idA INT, IN idT INT)
    BEGIN
    
	INSERT INTO treino_aluno (idAluno,idTreino) VALUE (idA,idT);
    
    
    END $$
	DELIMITER ;
    
    DROP PROCEDURE IF EXISTS AlterarTreino;
    DELIMITER $$
    CREATE PROCEDURE AlterarTreino(IN idA INT, IN idT INT)
    BEGIN
    
	UPDATE treino_aluno SET idTreino = idT WHERE idAluno = idA;
    
    
    END $$
	DELIMITER ;
    
	DROP PROCEDURE IF EXISTS getTreinoAluno;
    DELIMITER $$
    CREATE PROCEDURE getTreinoAluno(IN idA INT)
    BEGIN
    
	SELECT * FROM treino_aluno WHERE idAluno = 3;
    
    
    END $$
	DELIMITER ;
    call getTreinoAluno(3);
    
    -- VIEWS
    
    DROP VIEW IF EXISTS MontarExercicio;
	CREATE VIEW MontarExercicio AS SELECT nomeExercicio AS "Exercicio", nomeAparelho AS "Aparelho", idExercicio FROM exercicio INNER JOIN aparelho ON exercicio.idAparelho = aparelho.idAparelho;
    
    DROP VIEW IF EXISTS MostrarSuplementos;
    CREATE VIEW MostrarSuplementos AS SELECT idSuplemento,nomeSuplemento AS "Nome Suplemento", descSuplemento AS "Descrição de Suplemento",dosagemSuplemento AS "Dosagem do Suplemento" FROM suplemento;
    
    DROP VIEW IF EXISTS CriarExercicio;
	CREATE VIEW CriarExercicio AS SELECT idAparelho AS "Número Aparelho", nomeAparelho AS "Nome Aparelho" FROM aparelho;
    
    DROP VIEW IF EXISTS MontarTreino;
    CREATE VIEW MontarTreino AS SELECT idExercicio AS "Exercicio", nomeExercicio AS "Nome Exercicio" FROM exercicio;
    
    
    
    
    
    
    
    -- Usuarios 
    CREATE USER 'professor'@localhost;
    CREATE USER 'aluno'@localhost;
    CREATE USER 'adm'@localhost;
    
    -- Privilegios
    -- ADM
    GRANT ALL ON acad.* TO 'adm'@localhost;
    
    -- Aluno
    GRANT EXECUTE ON PROCEDURE alunoTreino TO 'aluno'@localhost;
    GRANT EXECUTE ON PROCEDURE AtualizarAluno TO 'aluno'@localhost;
    GRANT EXECUTE ON PROCEDURE alunoTreinoClick TO 'aluno'@localhost;
    GRANT EXECUTE ON PROCEDURE SelectTreino TO 'aluno'@localhost;
    GRANT EXECUTE ON PROCEDURE MostrarSuplementosClick TO 'aluno'@localhost;
	GRANT SELECT ON MostrarSuplementos TO 'aluno'@localhost;
    
    -- Professor
	GRANT EXECUTE ON PROCEDURE InsereAluno TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE alunos TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE BuscarAlunos TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE InsereTreinoExercicio TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE InsereProfessor TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE InsereAparelho TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE InsereExercicio TO 'professor'@localhost;
    GRANT EXECUTE ON PROCEDURE CadastraTreino TO 'professor'@localhost;
    GRANT SELECT ON MontarExercicio TO 'professor'@localhost;
    GRANT SELECT ON CriarExercicio TO 'professor'@localhost;
    GRANT SELECT ON MontarTreino TO 'professor'@localhost;