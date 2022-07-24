CREATE TABLE IF NOT EXISTS algafood.restaurante_usuario_responsavel
(
    restaurante_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    CONSTRAINT restaurante_usuario_responsavel_pkey PRIMARY KEY (restaurante_id, usuario_id),
    CONSTRAINT fk_restaurante_usuario_restaurante FOREIGN KEY (restaurante_id)
        REFERENCES algafood.restaurante (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_restaurante_usuario_usuario FOREIGN KEY (usuario_id)
        REFERENCES algafood.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS algafood.restaurante_usuario_responsavel
    OWNER to postgres;