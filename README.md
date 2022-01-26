<div align="center">
<h3 align="center">Empresa POP</h3>
<p align="center">
Proyecto final de AAD <br>
Creado por Ángel Andrade, Miguel Jaimes, Javier Miralles y Jacobo Rodríguez
<br />
</p>
</div>

# Directrices de trabajo del repositorio
Definición de como se ha de trabajar en el repositorio, para conseguir un entorno de trabajo limpio y poder crear a partir de ahí un workflow/flujo de trabajo decente y lo más real posible.

## Branches

- **`master`** >> Rama de **producción**, restringida a cambios solo mediante pull/merge request. A su vez, la pull/merge request tendrá que ser aprobada por algún desarrollador diferente.
- **`client`** >> Rama de **desarrollo** de la parte cliente. Restringida a cambios solo mediante pull/merge request. Como en la anterior, la pull/merge request ha de ser aprobada por algún desarrollador diferente.
- **`server`** >> Rama de **desarrollo** de la parte servidor. Restringida a cambios solo mediante pull/merge request. Como en las dos anteriores, la pull/merge request ha de ser aprobado por algún desarrollador diferente.
	
- **`nombre_desarrollador`** >> Cada desarrollador tiene asignada una rama dónde podrá **trabajar libremente**.

<div align="center">
Ramas y asignaciones:

| Rama | Desarrollador |
|:----------:|:-------------:|
| aandrade | Ángel Andrade |
| mjaimes | Miguel Jaimes |
| jmiralles | Javier Miralles |
| jrodriguez | Jacobo Rodriguez |
</div>

## Milestones y Issues

Para poder organizarnos y ver que tareas/problemas resuelve cada uno, utilizaremos los milestones y issues de GitHub. De esta forma cada desarrollador podrá asignarse un `issue` y trabajar específicamente en él. Pasos a seguir a la hora de empezar a desarrollar:

- Ir al apartado Issues:

![](https://imgur.com/HFDbvyH.png)

- Asignarte uno, el que prefieras:

![](https://imgur.com/KUPNLD8.png)

- Empezar a trabajar en tu rama personal
- Cuando esté listo, creas un `pull request` a la rama correspondiente (**client** si es del apartado cliente, **server** si es de la parte del servidor).