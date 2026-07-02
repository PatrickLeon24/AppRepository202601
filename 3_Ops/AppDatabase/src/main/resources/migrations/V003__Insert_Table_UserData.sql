-- Script que crea la tabla user_data
-- Autor: Henry Wong
-- Correo: hwongu@gmail.com

CREATE TABLE public.user_data (
    id_user SERIAL PRIMARY KEY,      -- Campo autoincremental y clave primaria
    enabled BOOLEAN NOT NULL,        -- Campo booleano, no nulo
    password VARCHAR(250) NOT NULL,  -- Campo para contraseña, máximo 250 caracteres, no nulo
    username VARCHAR(50) NOT NULL    -- Campo para nombre de usuario, máximo 50 caracteres, no nulo
);
