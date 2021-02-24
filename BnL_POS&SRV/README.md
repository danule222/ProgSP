<img src="../.github/readme-images/BnL_Logo.png" />
<h1>Buy n Large - Terminal Punto de Venta & Servidor</h1>

<p>
  Software para la gestión de stock y compras que se producen en tienda a bordo de la AXIOM. Porque que nuestro planeta se esté muriendo, no nos impide seguir gastando.
</p>

<center>
  <img src="../.github/readme-images/AXIOM.png" />
</center>

<h2>Servidor</h2>
<h3>Stock</h3>
<p>
  Gestión de almacén para cada tienda en la nave, enlazada a su respectivo almacén. Incluye un sistema de aviso por correo para que, en caso de falta de Stock, se envíe un aviso a la dirección indicada, con la cuenta de GMail que se indique en el archivo de configuración.
</p>
<h3>Empleados</h3>
<p>
  Cada empleado contará con un código privado con el que inciará sesión. Todas las ventas realizadas por el empleado se le serán asociadas. A la hora de cerrar sesión, se actualizará la información del empleado con la fecha y hora de su última sesión.
</p>
<h2>Cliente</h2>
<p>
  El cliente tiene la simple función de obtener datos del servidor, como información del empleado que está inciando sesión, o la lista de productos para vender. De igual manera el cliente envía comandos al servidor para que ejecute acciones, como realizar una compra y obtener la caja del día.
</p>
