package tralafarlaw.miguel.button;

import java.util.List;

public class Conductor {
    private String Nombre;
    private double lat;
    private double lon;
    private List<control> ruta;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public List<control> getRuta() {
        return ruta;
    }

    public void setRuta(List<control> ruta) {
        this.ruta = ruta;
    }

    private class control{
        private double lat;
        private double lon;
        private int hour;
        private int min;
        private int seg;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getSeg() {
            return seg;
        }

        public void setSeg(int seg) {
            this.seg = seg;
        }
    }
}
