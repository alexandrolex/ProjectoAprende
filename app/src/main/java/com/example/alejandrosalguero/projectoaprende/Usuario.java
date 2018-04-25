package com.example.alejandrosalguero.projectoaprende;

public class Usuario {
    public String correo;
    public String contraseña;
    public String rol;

    public Usuario(String correo, String contraseña, String rol) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Usuario() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
