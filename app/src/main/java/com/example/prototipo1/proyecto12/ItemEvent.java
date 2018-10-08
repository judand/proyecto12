package com.example.prototipo1.proyecto12;

public class ItemEvent {
    private String mtitle;
    private String mimageurl;
    private String mlugar;
    private String mfecha;
    private String mhora;
    private int mcostoboleta;
    private int mcapacidad;
    private String mdescripcion;

    public ItemEvent(String title, String imageurl, String lugar, int capacidad, String descripcion, String fecha,String hora, int costoboleta){
        mtitle=title;
        mimageurl=imageurl;
        mlugar=lugar;
        mcapacidad=capacidad;
        mdescripcion=descripcion;
        mfecha=fecha;
        mhora=hora;
        mcostoboleta=costoboleta;

    }

    public String getMimageurl() {
        return mimageurl;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getMlugar() {
        return mlugar;
    }

    public int getMcapacidad() {
        return mcapacidad;
    }

    public String getMdescripcion() {
        return mdescripcion;
    }

    public String getMfecha() {
        return mfecha;
    }

    public String getMhora() {
        return mhora;
    }

    public int getMcostoboleta() {
        return mcostoboleta;
    }
}
