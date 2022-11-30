package classes;
public abstract class Aeromodelo {
    
    private int id;
    private String marca;
    private String modelo;

    protected Aeromodelo(int id, String marca, String modelo) throws Exception {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Aeromodelo: " + "ID: " + id + ", Marca: " + marca + ", Modelo: " + modelo;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Aeromodelo)) {
            return false;
        }
        final Aeromodelo other = (Aeromodelo) object;

        return this.id == other.id;
    }

}

