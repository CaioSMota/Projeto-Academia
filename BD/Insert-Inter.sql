USE acad;

-- Equipamentos

INSERT INTO aparelho (nomeAparelho) VALUE ("Leg-Press 45");
INSERT INTO aparelho (nomeAparelho) VALUE ("Mesa Flexora");
INSERT INTO aparelho (nomeAparelho) VALUE ("Cadeira Extensora");
INSERT INTO aparelho (nomeAparelho) VALUE ("Cadeira Abdutor");
INSERT INTO aparelho (nomeAparelho) VALUE ("Mult Exercitador");
INSERT INTO aparelho (nomeAparelho) VALUE ("Maquina Hack");
INSERT INTO aparelho (nomeAparelho) VALUE ("Flexora Vertical");
INSERT INTO aparelho (nomeAparelho) VALUE ("Panturrilha Sentado");
INSERT INTO aparelho (nomeAparelho) VALUE ("Banco Declinado");
INSERT INTO aparelho (nomeAparelho) VALUE ("Banco Inclinado 45");
INSERT INTO aparelho (nomeAparelho) VALUE ("Banco plano 180");
INSERT INTO aparelho (nomeAparelho) VALUE ("CrossOver");
INSERT INTO aparelho (nomeAparelho) VALUE ("Peck Deck");
INSERT INTO aparelho (nomeAparelho) VALUE ("Puxada Baixa");
INSERT INTO aparelho (nomeAparelho) VALUE ("Puxada Alta");
INSERT INTO aparelho (nomeAparelho) VALUE ("Cavalinho");
INSERT INTO aparelho (nomeAparelho) VALUE ("Remada Sentada");
INSERT INTO aparelho (nomeAparelho) VALUE ("Halter");
INSERT INTO aparelho (nomeAparelho) VALUE ("Barra");
INSERT INTO aparelho (nomeAparelho) VALUE ("Desenvolvimento Ombro");
INSERT INTO aparelho (nomeAparelho) VALUE ("Graviton");
INSERT INTO aparelho (nomeAparelho) VALUE ("Cross Angular");
INSERT INTO aparelho (nomeAparelho) VALUE ("Banco Scoot");
INSERT INTO aparelho (nomeAparelho) VALUE ("Supino Vertical");
INSERT INTO aparelho (nomeAparelho) VALUE ("Peitoral Crucifixo");
INSERT INTO aparelho (nomeAparelho) VALUE ("Sem equipamento");

-- Exercicios 
		-- Pernas
INSERT INTO exercicio (nomeExercicio, imgExercicio,idAparelho) VALUES ("Agachamento","/imgsEx/Agachamento-Barra.jpeg",5);
INSERT INTO exercicio (nomeExercicio, imgExercicio,idAparelho) VALUES ("Hack","/imgsEx/Agachamento-Hack.jpeg",6);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Adutora","/imgsEx/Cadeira-Adutora.jpeg",4);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Leg Press 45°","/imgsEx/Leg-Press-Inclinado.jpeg",1);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Agachamento Sumo","/imgsEx/Agachamento-Sumo-Halter.jpeg", 18);

-- Peitoral
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Supino Reto","/imgsEx/Supino-Reto.jpeg", 11);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Supino Inclinado","/imgsEx/Supino-Inclinado.jpeg", 10);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Supino Declinado","/imgsEx/Supino-Declinado.jpeg", 9);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Crucifixo Reto","/imgsEx/Crucifixo-Reto.jpeg", 18);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Barra Pararela","/imgsEx/Fundos-Barras-Paralelas.jpeg", 21);

-- Triceps
INSERT INTO exercicio (nomeExercicio,imgExercicio ,idAparelho) VALUES ("Triceps Polia Alta","/imgsEx/Triceps-Polia-Alta.jpeg", 12);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Francês","/imgsEx/Triceps-Frances.jpeg",18);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Testa","/imgsEx/Triceps-Testa-com-Halteres.jpeg", 18);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Paralela","/imgsEx/Triceps-Barras-Paralelas.jpeg",21);
INSERT INTO exercicio (nomeExercicio,imgExercicio, idAparelho) VALUES ("Tríceps Polia Alta Pegada Supinada","/imgsEx/Triceps-Polia-Alta-Pegada-Supino.jpeg",12);

-- Professor
INSERT INTO professor (pNomeProf, uNomeProf, dataNasc, telefone,sexo, imgProf, area, senha, usuario) VALUES ("Gabriel", "Makiuchi", '1999-10-05', "19997225798", "M", "/imgsProf/VAYNEOBESA.jpg", "Musculação", "maki0510", "make");

-- Treino
INSERT INTO treino (idProfessor, NomeTreino) VALUES (1,"Treino Inicial");
INSERT INTO treino (idProfessor, NomeTreino) VALUES (1,"Treino Segundario");

-- Treino Aparelho
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (15,3,"Pausas de 1 minuto para cada repetição", 1,1);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (15,3,"Pausas de 1 minuto para cada repetição", 2,1);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (15,3,"Pausas de 1 minuto para cada repetição", 3,1);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (15,3,"Pausas de 1 minuto para cada repetição", 4,1);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (15,3,"Pausas de 1 minuto para cada repetição", 5,1);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (12,2,"Pausas de 1 minuto para cada repetição", 6,2);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (12,3,"Pausas de 1 minuto para cada repetição", 7,2);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (12,2,"Pausas de 1 minuto para cada repetição", 8,2);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (12,3,"Pausas de 1 minuto para cada repetição", 9,2);
INSERT INTO treino_exercicio (repeticoes,series,anotacoes,idExercicio,idTreino) VALUES (12,2,"Pausas de 1 minuto para cada repetição", 10,2);

-- Suplementos
INSERT INTO suplemento(nomeSuplemento, descSuplemento,dosagemSuplemento,imgSup) 
VALUES ("Whey Protein - GROWTH SUPPLEMENTS","AUMENTA O VOLUME DE MASSA MAGRA.POTENCIALIZA A RECUPERAÇÃO DO TECIDO MUSCULAR.","30 gr para 200 ml de água","/imgsSup/WheyProtein-GROWTHSUPPLEMENTS.jpg");

INSERT INTO suplemento(nomeSuplemento, descSuplemento,dosagemSuplemento,imgSup) 
VALUES ("Whey Protein - Shark Pro","SUPLEMENTO PROTÉICO PARA ATLETAS QUE BUSCAM MELHORES PERFORMANCES,GANHO DE MASSA MUSCULAR E FORÇA.","2 scoops para 250 ml de água.","/imgsSup/WheyProtein-SharkPro.jpg");

INSERT INTO suplemento(nomeSuplemento, descSuplemento,dosagemSuplemento,imgSup) 
VALUES ("Whey Protein - Black Skull","É um suplemento alimentar a base de 7 tipos de proteína para pessoas cujo objetivo é o aumento de massa magra ou maior ingestão de proteína em sua dieta.", "4 colheres de sopa (40g) em 150 a 180 ml de água","/imgsSup/WheyProtein-BlackSkull.jpg");

INSERT INTO suplemento(nomeSuplemento, descSuplemento,dosagemSuplemento,imgSup) 
VALUES ("Whey Protein - IntegralMédica","Cada porção fornece 30g das melhores proteínas, enriquecidas com vitaminas e minerais.","50 gr para 280 ml de água","/imgsSup/WheyProtein-IntegralMédica.jpg");

-- ALuno
INSERT INTO aluno (usuario,pNomeAluno,uNomeAluno,problemaSaude,objetivo,dataNasc,telefone,sexo,peso,altura,imc,senha,idProf, imgAluno) 
	VALUES ("caio","Caio","Mota","Escoliose na Coluna", "Ganho de Massa", '2000-10-16',"19982323017","M" ,60,1.75,peso/(altura*altura),"caio1610",1, "/imgsAluno/FotoPerfil.jpeg");
    
INSERT INTO aluno (usuario,pNomeAluno,uNomeAluno,problemaSaude,objetivo,dataNasc,telefone,sexo,peso,altura,imc,senha,idProf, imgAluno) 
	VALUES ("Nat","Natalia","Bravo","nenhum", "Definir", '2000-3-10',"19998021956","F" ,57,1.63, peso/(altura*altura) ,"anahely",1, "/imgsAluno/pp.jpg");
    
-- Treino Aluno
INSERT INTO treino_aluno (idAluno, idTreino) VALUES (1, 1);

INSERT INTO treino_aluno (idAluno, idTreino) VALUES (2, 2);