ALTER TABLE IF EXISTS algafood.restaurante DROP COLUMN IF EXISTS aberto;

ALTER TABLE IF EXISTS algafood.restaurante
    ADD COLUMN aberto character(1) DEFAULT 'Y';
    
UPDATE algafood.restaurante SET aberto = 'Y';