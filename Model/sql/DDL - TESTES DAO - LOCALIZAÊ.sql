INSERT INTO usuario(nome, email, senha, tipoUsuario_fk) VALUES ('Marcos Antônio', 'marcosantonio@localizae.net.br', '123456789', 1);
INSERT INTO usuario(nome, email, senha, tipoUsuario_fk) VALUES ('Roberto Porto Júnior', 'robertojunior@localizae.net.br', '123456789', 2);
INSERT INTO usuario(nome, email, senha, tipoUsuario_fk) VALUES ('Everton', 'everton@localizae.net.br', '123456789', 3);
INSERT INTO usuario(nome, email, senha, tipoUsuario_fk) VALUES ('Lyan Masterson', 'lyanmasterson@localizae.net.br', '123456789', 4);

INSERT INTO estande(curso, descricao, periodo, nome, areaTematica, numero) VALUES ('Sistemas de Informação', 'Uma descrição', 2, 'Ap Game', 'Jogos', 45);
INSERT INTO estande(curso, descricao, periodo, nome, areaTematica, numero) VALUES ('Sistemas de Informação', 'Uma descrição do estande', 2, 'Old Game', 'Jogos', 47);
INSERT INTO estande(curso, descricao, periodo, nome, areaTematica, numero) VALUES ('Pedagogia', 'Uma descrição parcial do estande', 2, 'Educação Infantil', 'Educação', 43);
INSERT INTO estande(curso, descricao, periodo, nome, areaTematica, numero) VALUES ('Pedagogia', 'Uma descrição parcial do trabalho apresentado', 2, 'Educação Inclusiva', 'Educação', 41);

INSERT INTO promocao(nome, descricao, dataHora, estande_fk) VALUES ('Minha promo', 'Descrição da promo', clock_timestamp(), 1);
INSERT INTO promocao(nome, descricao, dataHora, estande_fk) VALUES ('Minha promo', 'Descrição da promo', clock_timestamp(), 2);
INSERT INTO promocao(nome, descricao, dataHora, estande_fk) VALUES ('Minha promo', 'Descrição da promo', clock_timestamp(), 3);
INSERT INTO promocao(nome, descricao, dataHora, estande_fk) VALUES ('Minha promo', 'Descrição da promo', clock_timestamp(), 4);

INSERT INTO criterioAvaliacao(nome) VALUES ('Organização do Estande');
INSERT INTO criterioAvaliacao(nome) VALUES ('Limpeza do Estande');
INSERT INTO criterioAvaliacao(nome) VALUES ('Harmonia da Equipe');
INSERT INTO criterioAvaliacao(nome) VALUES ('Documentação');

INSERT INTO informacoesParaAvaliacao(usuario_fk, estande_fk, criterioAvaliacao_fk) VALUES (4, 1, 1);
INSERT INTO informacoesParaAvaliacao(usuario_fk, estande_fk, criterioAvaliacao_fk) VALUES (4, 2, 2);
INSERT INTO informacoesParaAvaliacao(usuario_fk, estande_fk, criterioAvaliacao_fk) VALUES (4, 3, 3);
INSERT INTO informacoesParaAvaliacao(usuario_fk, estande_fk, criterioAvaliacao_fk) VALUES (4, 4, 2);

INSERT INTO avaliacaoVisitante(nota, comentario, usuario_fk, estande_fk) VALUES (5, 'Comentário de Teste Para o Estande 1', 2, 1);
INSERT INTO avaliacaoVisitante(nota, comentario, usuario_fk, estande_fk) VALUES (2, 'Comentário de Teste Para o Estande 2', 2, 2);
INSERT INTO avaliacaoVisitante(nota, comentario, usuario_fk, estande_fk) VALUES (3, 'Comentário de Teste Para o Estande 3', 2, 3);
INSERT INTO avaliacaoVisitante(nota, comentario, usuario_fk, estande_fk) VALUES (1, 'Comentário de Teste Para o Estande 4', 2, 4);

INSERT INTO avaliacaoJurado(informacoesParaAvaliacao_fk, nota, opiniao) VALUES (2, 2, 'Opinião do Jurado sobre o Estande');
INSERT INTO avaliacaoJurado(informacoesParaAvaliacao_fk, nota, opiniao) VALUES (3, 5, 'Opinião do Jurado sobre o Estande');
INSERT INTO avaliacaoJurado(informacoesParaAvaliacao_fk, nota, opiniao) VALUES (4, 3, 'Opinião do Jurado sobre o Estande');