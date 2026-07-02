-- Script que crea la tabla Category en el esquema public
-- Autor: Henry Wong
-- Correo: hwongu@gmail.com

CREATE TABLE public.Category (
    id_category SERIAL PRIMARY KEY,    -- Campo autoincremental y clave primaria
    description VARCHAR(500) NOT NULL, -- Campo de descripción, no nulo
    enabled BOOLEAN NOT NULL,          -- Campo booleano, no nulo
    name VARCHAR(50) NOT NULL          -- Campo nombre, no nulo
);
