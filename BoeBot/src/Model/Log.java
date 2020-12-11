package Model;

public class Log {
    private Integer id;
    private String text;

    public Log(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
