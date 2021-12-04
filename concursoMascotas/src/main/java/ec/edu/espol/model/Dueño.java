/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;

/**
 *
 * @author Gonzàlez
 */
public class Dueño extends Persona
{
    private String direccion;
    private ArrayList<Mascota> mascotas;
    
    public Dueño(int id, String nombres,String apellidos, String telefono, String email,String direccion )
    {
        super(id,nombres,apellidos,telefono,email);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    @Override
    public String toString()
    {
        return "Dueño("+"id:"+this.id+", nombres:"+this.nombre+", apellidos:"+this.apellidos+", telefono:"+this.telefono+", email:"+this.email+", direccion:"+this.direccion+")";
       
    }
}
