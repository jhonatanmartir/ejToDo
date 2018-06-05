package myclass;

import java.util.Calendar;

public class ToDo {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        this.fecha = Calendar.getInstance().getTime().toString();
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
