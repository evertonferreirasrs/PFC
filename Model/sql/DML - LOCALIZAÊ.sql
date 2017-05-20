CREATE TABLE usuario(
	id BIGSERIAL NOT NULL,
	email VARCHAR(255) NOT NULL,
	nome VARCHAR(255) NOT NULL,
	senha VARCHAR(32) NOT NULL,
	bloqueado BOOLEAN NOT NULL DEFAULT false,
	motivo TEXT,
	tokenRedeSocial VARCHAR(32),
	tokenAutenticacao VARCHAR(32),
	dataHoraExpiracaoToken TIMESTAMP,
	tipoUsuario_fk BIGINT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE tipoUsuario(
	id BIGSERIAL NOT NULL,
	nome VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE criterioAvaliacao(
	id BIGSERIAL NOT NULL,
	nome VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE estande(
	id BIGSERIAL NOT NULL,
	curso VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	periodo BIGINT NOT NULL,
	nome VARCHAR(255) NOT NULL,
	areaTematica VARCHAR(255) NOT NULL,
	numero BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE informacoesParaAvaliacao(
	id BIGSERIAL NOT NULL,
	criterioAvaliacao_fk BIGINT NOT NULL,
	usuario_fk BIGINT NOT NULL,
	estande_fk BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE avaliacaoJurado(
	id BIGSERIAL NOT NULL,
	nota BIGINT NOT NULL,
	opiniao TEXT NOT NULL,
	informacoesParaAvaliacao_fk BIGINT NOT NULL UNIQUE,
	PRIMARY KEY(id)
);

CREATE TABLE avaliacaoVisitante(
	id BIGSERIAL NOT NULL,
	nota BIGINT NOT NULL,
	comentario TEXT,
	usuario_fk BIGINT NOT NULL,
	estande_fk BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE integranteEquipe(
	id BIGSERIAL NOT NULL,
	usuario_fk BIGINT NOT NULL,
	estande_fk BIGINT NOT NULL,
	responsavel BOOLEAN DEFAULT FALSE,
	PRIMARY KEY(id)
);

CREATE TABLE imagem(
	id BIGSERIAL NOT NULL,
	uri VARCHAR(255) NOT NULL,
	estande_fk BIGINT,
	PRIMARY KEY(id)
);

CREATE TABLE promocao(
	id BIGSERIAL NOT NULL,
	nome VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	dataHora TIMESTAMP NOT NULL,
	estande_fk BIGINT NOT NULL,
	imagem_fk BIGINT,
	PRIMARY KEY(id)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_tipoUsuario_fk FOREIGN KEY (tipoUsuario_fk) REFERENCES tipoUsuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE informacoesParaAvaliacao ADD CONSTRAINT info_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE informacoesParaAvaliacao ADD CONSTRAINT info_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoJurado ADD CONSTRAINT avaliacao_informacoesParaAvaliacao_fk FOREIGN KEY (informacoesParaAvaliacao_fk) REFERENCES informacoesParaAvaliacao(id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE avaliacaoVisitante ADD CONSTRAINT avaliacaoVisitante_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoVisitante ADD CONSTRAINT avaliacaoVisitante_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE integranteEquipe ADD CONSTRAINT integranteEquipe_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE integranteEquipe ADD CONSTRAINT integranteEquipe_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE imagem ADD CONSTRAINT imagem_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE promocao ADD CONSTRAINT promocao_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE promocao ADD CONSTRAINT promocao_imagem_fk FOREIGN KEY (imagem_fk) REFERENCES imagem(id) ON UPDATE CASCADE ON DELETE NO ACTION;
