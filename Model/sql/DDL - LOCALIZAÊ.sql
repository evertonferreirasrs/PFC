CREATE TABLE usuario(
	id BIGSERIAL NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	nome VARCHAR(255) NOT NULL,
	senha VARCHAR(32) NOT NULL,
	situacao VARCHAR(50) NOT NULL DEFAULT 'ativo',
	motivo TEXT NULL,
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
        peso bigint NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE estande(
	id BIGSERIAL NOT NULL,
	curso VARCHAR(255) NOT NULL,
	descricao TEXT NULL,
	periodo BIGINT NOT NULL,
	titulo VARCHAR(255) NOT NULL UNIQUE,
	areaTematica VARCHAR(255) NOT NULL,
	numero BIGINT NOT NULL,
        evento_fk BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE avaliacaoJurado(
	id BIGSERIAL NOT NULL,
	nota BIGINT NOT NULL,
	opiniao TEXT NOT NULL,
	status VARCHAR(50) NOT NULL DEFAULT 'aberta',
        dataHoraFechamento TIMESTAMP NULL,
        dataHoraAbertura TIMESTAMP NULL,
        usuario_fk BIGINT NOT NULL,
        criterioAvaliacao_fk BIGINT NOT NULL,
        estande_fk BIGINT NOT NULL,
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
	usuario_fk BIGINT NOT NULL,
	estande_fk BIGINT NOT NULL,
	responsavel BOOLEAN DEFAULT FALSE,
	PRIMARY KEY(usuario_fk, estande_fk)
);

CREATE TABLE imagem(
	id BIGSERIAL NOT NULL,
	uri VARCHAR(255) NOT NULL UNIQUE,
	estande_fk BIGINT,
        promocao_fk BIGINT,
	PRIMARY KEY(id)
);

CREATE TABLE promocao(
	id BIGSERIAL NOT NULL,
	nome VARCHAR(255) NOT NULL,
	descricao TEXT NOT NULL,
	dataHora TIMESTAMP NOT NULL,
	estande_fk BIGINT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE evento(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(255) NOT NULL,
    dataHoraEventoInicio TIMESTAMP NOT NULL,
    dataHoraEventoFim TIMESTAMP NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE criterioJurado(
    usuario_fk BIGINT NOT NULL,
    criterioAvaliacao_fk BIGINT NOT NULL,
    estande_fk BIGINT NOT NULL,
    PRIMARY KEY(usuario_fk, criterioAvaliacao_fk, estande_fk)
);

create table estatistica(
	id bigserial not null,
	posicaoX bigint not null,
	posicaoY bigint not null,
	dataHora timestamp not null,
	usuario_fk bigint not null,
	primary key(id)
);

create table beacon(
    id BIGSERIAL NOT NULL,
    xCoordiante FLOAT NOT NULL,
    yCoordinate FLOAT NOT NULL,
    mac VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

alter table estatistica add constraint usuario_fk_estatistica foreign key (usuario_fk) references usuario(id) on update cascade on delete no action;
ALTER TABLE usuario ADD CONSTRAINT usuario_tipoUsuario_fk FOREIGN KEY (tipoUsuario_fk) REFERENCES tipoUsuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoVisitante ADD CONSTRAINT avaliacaoVisitante_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoVisitante ADD CONSTRAINT avaliacaoVisitante_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE integranteEquipe ADD CONSTRAINT integranteEquipe_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE integranteEquipe ADD CONSTRAINT integranteEquipe_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE imagem ADD CONSTRAINT imagem_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE imagem ADD CONSTRAINT imagem_promocao_fk FOREIGN KEY (promocao_fk) REFERENCES promocao(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE promocao ADD CONSTRAINT promocao_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE criterioJurado ADD CONSTRAINT criterioJurado_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE criterioJurado ADD CONSTRAINT criterioJurado_criterioAvaliacao_fk FOREIGN KEY (criterioAvaliacao_fk) REFERENCES criterioAvaliacao(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoJurado ADD CONSTRAINT avaliacaoJurado_usuario_fk FOREIGN KEY (usuario_fk) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE avaliacaoJurado ADD CONSTRAINT avaliacaoJurado_criterioAvaliacao_fk FOREIGN KEY (criterioAvaliacao_fk) REFERENCES criterioAvaliacao(id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE criterioJurado ADD CONSTRAINT criterioJurado_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE avaliacaoJurado ADD CONSTRAINT avaliacaoJurado_estande_fk FOREIGN KEY (estande_fk) REFERENCES estande(id) ON UPDATE CASCADE ON DELETE CASCADE;