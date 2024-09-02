public class SubTask extends Task{
    protected int epicId;

    public SubTask(int id, String name, String description, TaskProgress status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public SubTask(String name, String description, TaskProgress status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId(int index) {
        return epicId;
    }
    public int getEpicId() {
        return epicId;
    }
}
