package mejorandome.mejorandome.SeekBarClasses;


import java.math.BigDecimal;

public class Mood {

    private int felicidad;
    private int paz;
    private int satisfaccion;
    private int angustia;
    private int culpa;
    private int ansiedad;
    private double peso;
    private BigDecimal peso2;

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public int getPaz() {
        return paz;
    }

    public void setPaz(int paz) {
        this.paz = paz;
    }

    public int getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(int satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public int getAngustia() {
        return angustia;
    }

    public void setAngustia(int angustia) {
        this.angustia = angustia;
    }

    public int getCulpa() {
        return culpa;
    }

    public void setCulpa(int culpa) {
        this.culpa = culpa;
    }

    public int getAnsiedad() {
        return ansiedad;
    }

    public void setAnsiedad(int ansiedad) {
        this.ansiedad = ansiedad;
    }

    public double getPeso() {
        return peso;
    }

    public BigDecimal getPeso2(){ return peso2; }

    public void setPeso(double peso) {
        this.peso = peso;
        peso2 = BigDecimal.valueOf(peso);
    }


    public boolean AllReady()
    {
        if(felicidad == -1) return false;
        if(paz == -1) return false;
        if(satisfaccion == -1) return false;
        if(angustia == -1) return false;
        if(culpa == -1) return false;
        if(ansiedad == -1) return false;
        if(peso < 30) return false;

        return true;
    }

    public Mood()
    {
        felicidad = -1;
        paz = -1;
        satisfaccion = -1;
        angustia = -1;
        culpa = -1;
        ansiedad = -1;
        peso = -1;
    }

}
