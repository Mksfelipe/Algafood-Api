alter table restaurante add ativo char(1) not null;
update restaurante set ativo = 'Y';