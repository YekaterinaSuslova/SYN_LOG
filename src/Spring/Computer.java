package Spring;

public class Computer {

    private int id;
    private String producer;
    private String processor;
    private int cores;
    

    public Computer() {
        this.id = 0;
        this.producer = "";
        this.processor = "";
        this.cores = 0;
    }

    public Computer(String producer, String processor, int cores) {
        this.id = 0;
        this.producer = producer;
        this.processor = processor;
        this.cores = cores;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return getProducer();
    }

    public String getProcessor() {
        return processor;
    }

    public int getCores() {
        return cores;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return String.format("Производитель=%s, Процессор=%s, Ядра=%d", getProducer(), getProcessor(), getCores());
    }

    /**
     * @return the producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * @param producer the producer to set
     */
}
