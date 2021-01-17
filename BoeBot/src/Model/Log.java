package Model;

public class Log {
    private Integer id;
    private String boebot, text;

    public Log(Integer id, String boebot, String text) {
        this.id = id;
        this.boebot = boebot;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoebot() {return this.boebot;}

    public void setBoebot(String boebot){this.boebot = boebot;}

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
