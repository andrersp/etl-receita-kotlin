
CREATE table history (
    id SERIAL not null,
    status integer not null,
    start_in TIMESTAMP not null  default now(),
    finished_in TIMESTAMP
);

CREATE table empresas (
    cnpj_basico integer not null,
    razao_social varchar(300),
    natureza_juridica integer,
    qualificacao_responsavel integer,
    capital_social_da_empresa numeric(20,2),
    porte_empresa integer,
    ente_federativo_responsavel varchar(255)

);

CREATE TABLE simples (
    cnpj_basico integer not null,
    opcao_simples BOOLEAN not null,
    data_opcao_simples DATE,
    data_exclusao_simples DATE,
    opcao_mei BOOLEAN not null,
    data_opcao_mei DATE,
    data_exclusao_mei DATE
);

CREATE table socios (
    cnpj_basico integer not null,
    identificador_socio integer,
    nome_socio varchar(255),
    cpf_cnpj_socio varchar(14),
    qualificacao_socio integer,
    data_entrada_sociedade DATE,
    codigo_pais integer,
    representante_legal varchar(255),
    nome_representante_legal varchar(255),
    qualificacao_representante_legal integer,
    faixa_etaria integer
);

CREATE table estabelecimentos (
        cnpj_basico integer not null,
        cnpj_ordem integer not null,
        cnpj_dv integer not null,
        identificador_matriz_filial integer not null,
        nome_fantasia varchar(300),
        situacao_cadastral integer,
        data_situacao_cadastral DATE,
        motivo_situacao_cadastral integer,
        nome_cidade_exterior varchar(255),
        cod_pais integer,
        data_inicio_atividade DATE,
        cnae_fiscal varchar(10),
        tipo_logradouro varchar(250),
        logradouro varchar(255),
        numero varchar(60),
        complemento varchar(255),
        bairro varchar(255),
        cep varchar(15),
        uf varchar(4),
        codigo_municipio integer,
        ddd1 varchar(4),
        telefone1 varchar(15),
        ddd2 varchar(4),
        telefone2 varchar(15),
        ddd_fax varchar(4),
        fax varchar(15),
        correio_eletronico varchar(255),
        situacao_especial varchar(255),
        data_situacao_especial DATE
);

CREATE TABLE paises (
    codigo integer not null,
    descricao varchar(60) not null
);

CREATE TABLE cnaes (
    codigo varchar(12) not null,
    descricao varchar(255) not null
);
CREATE TABLE qualificacoes_socios (
    codigo integer not null,
    descricao varchar(100) not null
);

CREATE TABLE municipios (
    codigo integer not null,
    descricao varchar(100) not null
);

CREATE TABLE naturezas_juridicas (
    codigo integer not null,
    descricao varchar(100) not null
);

CREATE TABLE motivo_situacao_cadastral (
    codigo integer not null,
    descricao varchar(100) not null
);
