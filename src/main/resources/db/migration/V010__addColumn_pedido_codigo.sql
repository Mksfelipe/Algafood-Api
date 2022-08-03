ALTER TABLE IF EXISTS algafood.pedido
    ADD COLUMN codigo character varying(36);
ALTER TABLE IF EXISTS algafood.pedido
    ADD CONSTRAINT unique_uuid_pedido UNIQUE (codigo);