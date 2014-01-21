SELECT
    af.cedula,
    af.nombre,
    ar.papa AS cedula_padre,
    ar.Nivel
FROM t_afiliados af
    JOIN t_arbol ar ON af.cedula=ar.papa
WHERE ar.Descendiente ='43210158-A'
#    AND af.activo = true
UNION

SELECT
    af.cedula,   
    af.nombre,
    ar.papa AS cedula_padre,
    ar.Nivel
FROM t_afiliados af
    JOIN  t_arbol ar ON af.cedula=ar.Descendiente
WHERE ar.papa='43210158-A'
#     AND af.activo = true