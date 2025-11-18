/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercitodeprueba.Entidades;



import com.mycompany.ejercitodeprueba.Dao.OficialDAo;
import java.util.Scanner;
import java.util.Date;
import java.io.File;

public class Oficial extends Usuario  {
    private Scanner scanner;

    public Oficial(int codigo) {
        super(codigo);
    }

    public Oficial() {
    }
    
    //public Usuario(String nombre, String apellido, String tipoUser, Cuerpo tipoCuerpo, Compania comp, Cuartel cuart, String Contrasenia, String email, int codigo)
    public Oficial(String nombre, String apellido, String tipoUser, Cuerpo tipoCuerpo, Compania comp, Cuartel cuart, String con,String email,int codigo) {
        super(nombre, apellido, tipoUser, tipoCuerpo, comp, cuart,con,email, codigo);
    }

    public void mostrarDatos() {
        System.out.println("---Datos Oficial---");
        System.out.println("Id Soldado: " + getCodigo() + "\n");
        System.out.println("Nombre: " + getNombre() + "\n");
        System.out.println("Apellido: " + getApellido() + "\n");
        System.out.println("Tipo de Usuario: " + getTipoUser() + "\n");
        System.out.println("Tipo de Cuerpo: " + getTipoCuerpo().getDenominacion_cuerpo() + "\n");
        System.out.println("Compania: " + getComp().getDenominacion_compania() + "\n");
        System.out.println("Cuartel Asignado: " + getCuart().getNombre_cuartel() + "\n");
    }

    public void mostrarMenu() {
        Scanner escaner = new Scanner(System.in);
        OficialDAo ofiDao=new OficialDAo();
        System.out.println("Elige que quiere acceder:\n1)Ver mis datos\n2)Ver Servicios Asignado\n3)Asignar Sercicio a soldado\n4)Asignar Usuario a Cuartel\n5)Asignar Usuario a compania\n6)Asignar Usuario Cuerpo\n7)Crear Cuerpo\n8)Crear Compania\n9)Crear Cuartel\n10)Crear Servicio\n11)Salir");
        int opcion = escaner.nextInt();
        switch (opcion) {
            case 1:
                //Mostrar Datos
                mostrarDatos();
                break;
            case 2:
                System.out.println("Ingrese el id del usuario que queres ver sus servicios ");
                int idUser=escaner.nextInt();
                System.out.println("El id ingresado fue: "+ idUser);
                //ofiDao.obtenerServicios();
                //Ver Servicios Asignados
                
                break;
            case 3:
                
                //Servicios serv=new Servicios();
                System.out.println("---Asignando Servicio a Soldado---");
                System.out.println("Su id de usuario es: "+ getCodigo());
                System.out.println("Ingrese el Id del servicio que quiere asignar: \n");
                int serId=escaner.nextInt();
                System.out.println("El id de servicio ingresado es: "+ serId);
                //ofiDao.asignarServicioASoldado();
                //asignarServicio(Servicio serv);
                break;
            case 4:
                //Asignar Usuario Cuartel
                System.out.println("Ingrese el id del usuario que quiere agregar:");
                int cod_user=escaner.nextInt();
                System.out.println("Ingrese el id del Cuartel que quiere agregar:");
                int cod_cuart=escaner.nextInt();
                
                ofiDao.ReasignarUsuarioCuartel(codigo, codigo);
                //ofi.mostrarMenu();
                break;
            case 5:
                System.out.println("Ingrese el id del usuario que quiere agregar:");
                cod_user=escaner.nextInt();
                System.out.println("Ingrese el id del Compania que quiere agregar:");
                int cod_compania=escaner.nextInt();
                ofiDao.ReasignarUsuarioCompania(cod_user, cod_compania);
                break;
            case 6:
                System.out.println("Ingrese el id del usuario que quiere agregar:");
                cod_user=escaner.nextInt();
                System.out.println("Ingrese el id del Cuerpo que quiere agregar:");
                int cod_cuer=escaner.nextInt();
                ofiDao.ReasignarUsuarioCuerpo(cod_user, cod_cuer);
                break;
            case 7:
                //Crear Cuerpo;
                System.out.println("---Agregando Cuerpo:---");
                System.out.println("Ingrese el nombre del cuerpo: ");
                escaner.nextLine();
                String nombre_cuerpo=escaner.nextLine();                
                Cuerpo cuerpo1=new Cuerpo(1,nombre_cuerpo);
                ofiDao.CrearCuerpo(cuerpo1);
                break;
            case 8:
                System.out.println("---Agregando Compania:---");
                System.out.println("Ingrese el nombre del cuerpo: ");
                escaner.nextLine();
                String nombre_compania=escaner.nextLine();                
                Compania comp=new Compania(nombre_compania,1);
                ofiDao.CrearCompania(comp);
                break;
            case 9:
                System.out.println("---Agregando Cuartel:---");
                System.out.println("Ingrese el nombre del cuartel: ");
                escaner.nextLine();
                String nombre_cuartel=escaner.nextLine();                
                Cuartel cuart=new Cuartel(nombre_cuartel,1);
                ofiDao.CrearCuartel(cuart);
                break;
            case 10:
                //Salir;
                System.out.println("---Creando Servicio---");
                System.out.println("Ingrese su nombre de servicio: \n");
                escaner.nextLine();
                String nomSer=escaner.nextLine();
                System.out.println("Nombre servicio ingresado: "+ nomSer);

                System.out.println("Ingrese la descripcion del servicio: \n");
                //escaner.nextLine();
                String desSer=escaner.nextLine();
                System.out.println("Descripcion ingresada: "+ desSer);
                Servicios serv=new Servicios(nomSer,desSer);
                ofiDao.crearServicio(serv);
                break;
            case 11:
                System.out.println("---Salir---");
       
                break;
        }

    }
    
    
  
    
    

    
    
    
}
