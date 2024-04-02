# Práctica 1 Sistemas Distribuidos 
## Grupo 11 - Andrés Martín Esteban, Carlos Casado Suela, Lucas San Gregorio Dominguez
La práctica que hemos realizado se trata de una página de compra-venta de instrumentales de canción o beats llamada FroggyBeats.

Para el desarrollo de la práctica hemos usado Spring Boot con el uso de Mustache para generación de html, CSS para diseño de los html, y JavaScript para programar la interacción con el usuario.

Las entidades elegidas para la realización de la práctica han sido: __Usuario, Beat y Licencia__. Del usuario guardamos su nombre, email y contraseña.
Del beat guardamos su genero, titulo, una breve descripción, una URL que no será de ayuda para escuchar los beats en la página, una serie de tokens
que nos serán de ayuda al buscar luego los beats en la barra de búsqueda de la página, y el identificador del productor, que hace referencia a un usuario,
estableciendo relación entre estas dos entidades. Por último, de las licencias guardamos los identificadores del beat y del usuario asociados a la licencia (es decir que usuario compra que beat), 
la fecha de compra y el tipo de licencia.

Cabe recalcar que en esta práctica _no se ha distinguido entre usuarios en la práctica_, y además que la información implementada en ella es __volátil y no persistente__ (Implementada en un _ConcurrentHashMap_)

Las operaciones CRUD sobre la API REST están programadas en los controladores XAPIController, donde X es una entidad de la práctica. Además, hemos incluido una colección Postan de estas operaciones en
la carpeta raíz del proyecto /src.

Las operaciones CRUD sobre formularios están repartidas de la siguiente manera:
* __Usuario__
  * Create:
     Cuando te registras en la página web, en el formulario pensado para ello, estás creando un usuario.
  * Read:
    Para obtener todos los usuarios, hemos creado un botón en el índice que no lleva a una página que nos da información de todos los usuarios.

    Para obtener información de un usuario especifico, se puede acceder a la página de dicho usuario desde la página de todos los usuarios o desde cualquier parte si le 
    das click a tu nombre en la barra de navegación (una vez hayas iniciado sesión).
 * Update:
   A todos los usuarios se les permita cambiar su nombre de usuario desde la página de su perfil.
 * Delete:
   Al igual que con la operación update, desde el perfil de usuario se puede borrar su cuenta. Esto implicaría borrar todas sus licencias asociadas y, además, se le da la opción al usuario
   de transferir todos sus beats a otro usuario de la aplicación o eliminarlos también. Esto se debe a que si no nsería imposible eliminarlos de la web, ya que no podemos distinguir entre usuarios, por lo que no
   hay un administrador que lo pudiera borrar.
* __Beat__
  * Create:
    Cuando le das a vender beat, y añades todos los datos necesarios para crearlo, se crea un beat en el mapa de beats. Al introducir  los tags hay que destacar que
     __se deben introducir en minúscula y separados por un espacio__ para evitar problemas con la función de busqueda - ¡Cuanos más tags, más probabilidades de que se encuentre tu beat!
  * Read:
    Desde la página de comprar beats se pueden ver todos los beats guardados en la web

    Además, desde esa misma página se puede acceder a la página especifica de un beat, donde quien esté marcado como productor de dicho beat, puede editar su precio o borrarlo junto con las licencias asociadas a el.

    La función de búsqueda, que también cumple con la operación get, funciona de tal manera que divide la cadena buscada en tokens (generados de separar la cadena cada vez que se encuentre un espacio)
    y compara estos tokens con los tags de cada beat. Para encontrar un beat especificado aunque la cadena de búsqueda contenga errores, hemos implementado una función que computa la _distancia de Levenshtein_
    entre cada token con cada tag, con una distancia máxima de 3.

  * Update:
    Desde la página específica de un beat, un productor puede editar el precio del beat.
  * Delete:
    Desde la mísma página se pueden borrar el beat y las licencias asociadas a el.
    
* __Licencia__
  * Create:
    Cuando un usuario que no es el productor de un beat compra ese mismo beat, se genera una licencia. El usuario puede especificar que tipo de licencia quiere.
  * Read:
    Se ha creado una página donde se pueden ver todas las compras de beats, o lo que es lo mismo, todas las licencias generadas (accesible desde el índice).

    Además desde el perfil del usuario se pueden ver todas las licencias asociadas a un usuario y desde la página de todas las licencias se puede entrar a ver información detallada de cada licencia.
  * Update:
    Dentro de la página de cada licencia específica, si eres el dueño de dicha licencia, puedes cambiar el tipo de licencia.
  * Delete:
    Desde la misma página se puede eliminar una licencia, también solo si eres el dueño de la misma.

Para facilitar las pruebas con la página, se han creado una buena base de 24 beats, 4 de cada género seleccionado, 3 usuarios (Uno productor de todos los beats
, y otros 2 que han comprado varios beats: 
>Usuarios:  Juanito  Pepito  Tainy
>
>Contraseñas: Juanito8080  PepitoPerez512  Tainy1234

Se facilita aquí el lin al repositorio de github para que se pueda comprobar su uso: https://github.com/ccasado02/Grupo11_SD/


    
    
