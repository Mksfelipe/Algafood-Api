	create table algafood.estado (
		id SERIAL NOT NULL,
	    nome varchar(80) not null,
	    primary key(id)
	);

insert into algafood.estado(nome) select distinct nome_estado from algafood.cidade;

alter table algafood.cidade add column estado_id bigint not null;

update algafood.cidade cidade set estado_id = (select e.id from algafood.estado e where e.nome= cidade.nome_estado);

alter table algafood.cidade add constraint fk_cidade_estado foreign key(estado_id) references estado(id);

alter table algafood.cidade drop column nome_estado;


alter table algafood.cidade RENAME COLUMN nome_cidade TO nome;


