CREATE TABLE IF NOT EXISTS forma_pagamento (
	id SERIAL NOT NULL,
	descricao varchar(60) not null,
	primary key (id)
);

CREATE TABLE IF NOT EXISTS grupo (
	id SERIAL NOT NULL,
	nome varchar(60) not null,
	
	primary key (id)
);

CREATE TABLE IF NOT EXISTS grupo_permissao (
	grupo_id bigint,
	permissao_id bigint not null,
	
	primary key (grupo_id, permissao_id)
);

CREATE TABLE IF NOT EXISTS permissao (
	id SERIAL NOT NULL,
	descricao varchar(60) not null,
	nome varchar(100) not null,
	
	primary key (id)
);

CREATE TABLE IF NOT EXISTS produto (
	id SERIAL NOT NULL,
	restaurante_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo char(1) not null,
	
	primary key (id)
);

CREATE TABLE IF NOT EXISTS  restaurante (
	id SERIAL NOT NULL,
	cozinha_id bigint not null,
	nome varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	data_atualizacao timestamp not null,
	data_cadastro timestamp not null,
	
	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	primary key (id)
);

CREATE TABLE IF NOT EXISTS  restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	
	primary key (restaurante_id, forma_pagamento_id)
);

CREATE TABLE IF NOT EXISTS  usuario (
	id SERIAL NOT NULL,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro timestamp not null,
	
	primary key (id)
);

CREATE TABLE IF NOT EXISTS  usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,
	
	primary key (usuario_id, grupo_id)
);




alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);