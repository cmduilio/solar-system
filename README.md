# solar-system by Mauro Duilio Candotti
 Para iniciar el sistema se es necesario tener MySql 
 con un usuario root / root (modificable desde el application properties
 y una database llamada ML.
 
 Una vez que el sistema esté corriendo, va a estar escuchando via GET en:
 
 http://localhost:8080/weather?day=721 cuya respuesta será {"day":721,"weather":"Sequia"}

 El repositorio del proyecto está en: https://github.com/cmduilio/solar-system
 
# ACLARACIONES 
 
 Si bien el sistema solo funciona con 3 planetas (por ejemplo, al armar ún triángulo al no estar alineados
 está armado de forma tal que sea simple su ampliación a más planetas. 
 Además todos empezarán en 0º como posición inicial
 Se tomó como "año" a un ciclo del sistema, es decir, cuando los planetas vuelven a la posición original. 

 Para base de datos se usó MySQL ya que es el que más usé.
 
 Lamentablemente no pude terminiar el deploy a cloudfoundry, estuve buscando como hacer para subirlo
 ya que no lo conocía y no supe como hacer para configurar MySQL, levantar la base de datos, crear 
 el usuario necesario, etc. Como se estaba terminando la semana estimada, decidí no subirlo y aclararlo.

# EXPLICACIONES 

 Para armar SolarSystemJob se creó un método que ejecuta X cantidad de ciclos y se corre 10 veces usando
 runForTenCycles(). Una vez terminado se logean los resultados.
 A la vez, por cada movimiento del día en runForCycles se chequean los 4 posibles climas
 Como default el sistema está en Weather.NORMAL

 Sequia (Weather.DROUGHT) será cuando están alineados todos los planetas con el sol, es decir,
 todos tienen el mismo ángulo en el primer cuadrante.

 Si aún sigue siendo Weather.NORMAL se evalua si es están alineados pero no con el sol.
 como ya se que no están alineados con el sol (sería sequía en ese caso) verifico si el area del triangulo
 formado por sus posiciones es 0, si el area es 0 significa que no hay area y la única forma
 que un triangulo no tenga area es que estén alineados.

 Si aún sigue siendo Weather.NORMAL se hace lo mismo que en el paso anterior pero verificando si el 
 area es mayor que 0, si lo es, se verifica que el sol (0,0) esté dentro del triangulo.

 Si no pasa ninguno de los anteriores, el clima queda con el default (Weather.NORMAL)
