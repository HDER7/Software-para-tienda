 package Acceso;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Conectar {
    //variable para establecer conexion
    private Connection con=null;
   
    //variable cadena de conexion
    String cadenaConexion="jdbc:sqlserver://;database=FINAL;integratedSecurity=true;";
    String SQL="";//almacena la instrucciones en sql a ejercer
   
    //Variables del Empleado
    private int CodEmpleado;
    private String NomEmpleado;
    private String ApeEmpleado;
    private int EdadEmpleado ;
    private long TelEmpleado;
    private String FnEmpleado;
    private String passEm;
   
   //Variables para el cliente
    
    private int CodCliente;
    private String NomCliente;
    private String ApeCliente;
    private int EdadCliente;
    private long TelCliente;
    private String FnCliente; 

    //Variables para el articulo
    
    private int CodAritculo;
    private String NomArticulo;
    private String CatArticulo;
    private int Precio;
    private int Cantidad;

    public Connection getCon() {
        return con;
    }

    public String getCadenaConexion() {
        return cadenaConexion;
    }

    public String getSQL() {
        return SQL;
    }

    public int getCodEmpleado() {
        return CodEmpleado;
    }

    public String getNomEmpleado() {
        return NomEmpleado;
    }

    public String getApeEmpleado() {
        return ApeEmpleado;
    }

    public int getEdadEmpleado() {
        return EdadEmpleado;
    }

    public long getTelEmpleado() {
        return TelEmpleado;
    }

    public String getFnEmpleado() {
        return FnEmpleado;
    }
    
    public String getpassEm() {
        return passEm;
    }
    
    public int getCodCliente() {
        return CodCliente;
    }

    public String getNomCliente() {
        return NomCliente;
    }

    public String getApeCliente() {
        return ApeCliente;
    }

    public int getEdadCliente() {
        return EdadCliente;
    }

    public long getTelCliente() {
        return TelCliente;
    }

    public String getFnCliente() {
        return FnCliente;
    }

    public int getCodAritculo() {
        return CodAritculo;
    }

    public String getNomArticulo() {
        return NomArticulo;
    }

    public String getCatArticulo() {
        return CatArticulo;
    }

    public int getPrecio() {
        return Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }
    
    public Connection getConecxion(){
        try{
            con=(Connection) DriverManager.getConnection(cadenaConexion);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"ERROR"+ ex.getMessage());
        }
        return con;
    }
    public void inicio(File archivo) throws IOException{
        archivo=new File("./src/Recursos/usuarios.txt");
        archivo.delete();
        PrintWriter pw=null;  
        try{
            SQL="SELECT * FROM Empleados";
            archivo=new File("./src/Recursos/usuarios.txt");
            FileWriter escritor= new FileWriter(archivo,true); 
            pw=new PrintWriter(escritor);
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                pw.println(rs.getString("codigoEmpleados")+ "," + rs.getString("contraseña") );
            }
            stmt.close(); 
            pw.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void insertaArticulo(int id,String nom,String cat,double pre,int can){
        try{
            SQL="INSERT INTO ARTICULOS VALUES("+id+",'"+nom+"','"+cat+"',"+pre+","+can+");";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El Articulo ha sido registrado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void insertaEmpleado(int id,String nom,String ape,int edad,String fn,long tel,String pass){
        try{
            SQL="INSERT INTO EMPLEADOS VALUES("+id+",'"+nom+"','"+ape+"',"+edad+","+tel+",'"+fn+"','"+pass+"');";
            //crear variable tipo sentecia (statement)
            Statement stmt=con.createStatement();
            //Ejecutar sentencia
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El empleado ha sido registrado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void insertaCliente(int id,String nom,String ape,int edad,String dir,long tel){
        try{
            SQL="INSERT INTO CLIENTES VALUES("+id+",'"+nom+"','"+ape+"',"+edad+","+tel+",'"+dir+"');";
            //crear variable tipo sentecia (statement)
            Statement stmt=con.createStatement();
            //Ejecutar sentencia
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El cliente ha sido registrado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
     public void buscarArticulo(int id){
        try{
            SQL="SELECT * FROM ARTICULOS WHERE codigoArticulos ="+id+";";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            if(rs.next()){
                id=rs.getInt("codigoArticulos");
                NomArticulo=rs.getString("nombreA");
                CatArticulo=rs.getString("categoria");
                Precio=rs.getInt("precio");
                Cantidad=rs.getInt("cantidadBodega");
            }else{
                JOptionPane.showMessageDialog(null,"El Articulo no esta registrado");
            }
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void buscarEmpleado(int id){
        try{
            SQL="SELECT * FROM EMPLEADOS WHERE codigoEmpleados ="+id+";";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            if(rs.next()){
                id=rs.getInt("codigoEmpleados");
                NomEmpleado=rs.getString("nombreE");
                ApeEmpleado=rs.getString("apellido");
                EdadEmpleado=rs.getInt("edad");
                TelEmpleado=rs.getLong("telefono");
                FnEmpleado=rs.getString("fechaNacimiento");
                passEm=rs.getString("contraseña");
            }else{
                JOptionPane.showMessageDialog(null,"El empleado no esta registrado");
            }
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void buscarCliente(int id){
        try{
            SQL="SELECT * FROM CLIENTES WHERE codigoClientes ="+id+";";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            if(rs.next()){
                id=rs.getInt("codigoClientes");
                NomCliente=rs.getString("nombreC");
                ApeCliente=rs.getString("apellido");
                EdadCliente=rs.getInt("edad");
                TelCliente=rs.getLong("telefono");
                FnCliente=rs.getString("direccion");
            }else{
                JOptionPane.showMessageDialog(null,"El cliente no esta registrado");
            }
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void actualizarArticulo(int id,String nom,String cat,double pre,int can){
        try{
            SQL="UPDATE ARTICULOS SET cantidadBodega = "+can+", nombreA = '"+nom+"' , categoria = '"+cat+"',precio = "+pre+" WHERE codigoArticulos = "+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos del articulo");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void actualizarEmpleado(int id,String nom,String ape,int edad,String fn,long tel,String pass){
        try{
            SQL="UPDATE EMPLEADOS SET edad = "+edad+", nombreE = '"+nom+"' , apellido = '"+ape+"',FechaNacimiento = '"+fn+"', telefono ="+tel+",contraseña ='"+pass+"' WHERE codigoEmpleados = "+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos del empleado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void actualizarCliente(int id,String nom,String ape,int edad,String dir,long tel){
        try{
            SQL="UPDATE CLIENTES SET edad = "+edad+", nombreC = '"+nom+"' , apellido = '"+ape+"',direccion = '"+dir+"', telefono ="+tel+"WHERE codigoClientes = "+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos del cliente");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void eliminarArticulo(int id){
        try{
            SQL="DELETE FROM ARTICULOS WHERE codigoArticulos ="+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El articulo ha sido eliminado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void eliminarEmpleado(int id){
        try{
            SQL="DELETE FROM EMPLEADOS WHERE codigoEmpleados ="+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El empleado ha sido eliminado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void eliminarCliente(int id){
        try{
            SQL="DELETE FROM CLIENTES WHERE codigoClientes ="+id+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"El cliente ha sido eliminado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    
    public void tblClientes(JTable tblclientes){
        String[] titulos={"Codigo","Nombre","Apellido","Edad","Telefono","Direccion"};
        String[] registro= new String[6];
        DefaultTableModel modeloclientes = new DefaultTableModel(null,titulos);
        try{
            SQL="SELECT * FROM CLIENTES";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0] = rs.getString("codigoClientes");
                registro[1]=rs.getString("nombreC");
                registro[2]=rs.getString("apellido");
                registro[3]=rs.getString("edad");
                registro[4]=rs.getString("telefono");
                registro[5]=rs.getString("direccion");
                modeloclientes.addRow(registro);
            }
            tblclientes.setModel(modeloclientes);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
        
    }
    public void Ventas(int codigoArticulo,int codigoEmpleado, int codigoCliente, int cantidad,int ValorVenta,String fechaVenta, String horaVenta ){
         try{
            SQL="INSERT INTO VENTAS VALUES("+codigoArticulo+","+codigoEmpleado+","+codigoCliente+","+cantidad+","+ValorVenta+",'"+fechaVenta+"','"+horaVenta+"');";
            //crear variable tipo sentecia (statement)
          
            Statement stmt=con.createStatement();
            //Ejecutar sentencia
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"La venta se ha registrado");
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
         
    }
    public void bodega(int codigo){
        try{
            SQL="SELECT * FROM ARTICULOS WHERE codigoArticulos ="+codigo+";";
            
 
            //crear variable tipo sentecia (statement)
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
             if(rs.next()){
                codigo=rs.getInt("codigoArticulos");
                Cantidad=rs.getInt("cantidadBodega");
                Precio=rs.getInt("precio");
                NomArticulo=rs.getString("nombreA");
                
            }else{
                JOptionPane.showMessageDialog(null,"El Articulo no esta registrado");
            }
             

          
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR "+ ex.getMessage());
        }
        
     
    }
    public void ActualizarBodega(int can, int codigo){
         try{
            SQL="UPDATE ARTICULOS SET cantidadBodega = "+can+" WHERE codigoArticulos = "+codigo+";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos del articulo");
            stmt.close();
                
            }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }
    }
    public void tblArticulos(JTable tblclientes){
        String[] titulos={"Codigo","Nombre","Categoria","Precio","Cantidad"};
        String[] registro= new String[5];
        DefaultTableModel modeloarticulo = new DefaultTableModel(null,titulos);
        try{
            SQL="SELECT * FROM ARTICULOS";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0] = rs.getString("codigoArticulos");
                registro[1]=rs.getString("nombreA");
                registro[2]=rs.getString("categoria");
                registro[3]=rs.getString("precio");
                registro[4]=rs.getString("cantidadBodega");
                modeloarticulo.addRow(registro);
            }
            tblclientes.setModel(modeloarticulo);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }    
    }
   public void tblVentas(JTable tblventas){
        String[] titulos={"Nombre del Empleado", "Nombre del Cliente", "Nombre del articulo", "Codigo del articulo", "Codigo del Empleado", "Codigo del Cliente", "Cantidad comprado", "Valor de la compra", "Fecha de compra", "Hora de la compra"};
        String[] registro= new String[10];
        DefaultTableModel modeloarticulo = new DefaultTableModel(null,titulos);
        try{
            SQL="SELECT EMPLEADOS.nombreE, CLIENTES.nombreC, ARTICULOS.nombreA, VENTAS.codigoArticulo,VENTAS.codigoEmpleado, VENTAS.codigoCliente, VENTAS.cantidad, VENTAS.valorVenta,VENTAS.fechaVenta,VENTAS.horaVenta from EMPLEADOS, CLIENTES, VENTAS, ARTICULOS WHERE EMPLEADOS.codigoEmpleados=VENTAS.codigoEmpleado AND CLIENTES.codigoClientes=VENTAS.codigoCliente;";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0]=rs.getString("nombreE");
                registro[1]=rs.getString("nombreC");
                registro[2]=rs.getString("nombreA");
                registro[3]=rs.getString("codigoArticulo");
                registro[4]=rs.getString("codigoEmpleado");
                registro[5]=rs.getString("codigoCliente");
                registro[6]=rs.getString("cantidad");
                registro[7]=rs.getString("valorVenta");
                registro[8]=rs.getString("fechaVenta");
                registro[9]=rs.getString("horaVenta");
                modeloarticulo.addRow(registro);
            }
            tblventas.setModel(modeloarticulo);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }    
    }
   public void tblEmpleados(JTable tblclientes){
        String[] titulos={"Codigo","Nombre","Apellido","Edad","Telefono","Fecha de nacimiento"};
        String[] registro= new String[6];
        DefaultTableModel modeloarticulo = new DefaultTableModel(null,titulos);
        try{
            SQL="SELECT * FROM EMPLEADOS";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0] = rs.getString("codigoEmpleados");
                registro[1]=rs.getString("nombreE");
                registro[2]=rs.getString("apellido");
                registro[3]=rs.getString("edad");
                registro[4]=rs.getString("telefono");
                registro[5]=rs.getString("fechaNacimiento");
                modeloarticulo.addRow(registro);
            }
            tblclientes.setModel(modeloarticulo);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }    
    }
   public void VendedorX(JTable tblVdendedorX, int codigo){
        String[] titulos={"Codigo Articulo","Codigo empleado","Codigo cliente","Cantidad","Valor venta","Fecha venta"," Hora venta"};
        String[] registro= new String[7];
        DefaultTableModel modeloarticulo = new DefaultTableModel(null,titulos);
        try{
            SQL="Select * from VENTAS WHERE codigoEmpleado = "+codigo+";";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0] = rs.getString("codigoArticulo");
                registro[1]=rs.getString("codigoEmpleado");
                registro[2]=rs.getString("codigoCliente");
                registro[3]=rs.getString("cantidad");
                registro[4]=rs.getString("valorVenta");
                registro[5]=rs.getString("fechaVenta");
                registro[6]=rs.getString("horaVenta");
                modeloarticulo.addRow(registro);
            }
            tblVdendedorX.setModel(modeloarticulo);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }    
   }
   public void QuienVendio(JTable tblQuienVendio, int codigo){
        String[] titulos={"Nombre Empleado","Codigo Articulo"};
        String[] registro= new String[2];
        DefaultTableModel modeloarticulo = new DefaultTableModel(null,titulos);
        try{
            SQL="SELECT distinct nombreE, codigoArticulo FROM VENTAS, EMPLEADOS  WHERE codigoArticulo="+codigo+";";
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(SQL);
            while(rs.next()){
                registro[0] = rs.getString("nombreE");
                registro[1]=rs.getString("codigoArticulo");
               
                modeloarticulo.addRow(registro);
            }
            tblQuienVendio.setModel(modeloarticulo);
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ ex.getMessage());
        }    
   }
}
    
    
