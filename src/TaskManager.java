import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subtasks = new HashMap<>();
    private int id = 0;

    public HashMap<Integer, SubTask> getSubtasks() {
        return subtasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }
    //Добавляем задачи по категориям
    public int addNewTask(Task task) {
        id += 1;
        tasks.put(id, task);
        return id;
    }

    public int addNewEpic(Epic epic) {
        id += 1;
        epics.put(id, epic);
        return id;
    }

    public int addNewSubtask(SubTask subtask) {
        for (int index : epics.keySet()){
            if(subtask.getEpicId() == index){
                id += 1;
                subtasks.put(id, subtask);
            }
        }
        return id;
    }

    //Получение списка задач по категориям

    public ArrayList<Task> allTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task : tasks.values()) {
            tasksList.add(task);
        }
        return tasksList;
    }

    public ArrayList<Epic> allEpics() {
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic : epics.values()) {
            epicsList.add(epic);
        }
        return epicsList;
    }

    public ArrayList<SubTask> allSubtasks() {
        ArrayList<SubTask> subTasksList = new ArrayList<>();
        for (SubTask subTask : subtasks.values()) {
            subTasksList.add(subTask);
        }
        return subTasksList;
    }

    //Удаление всех задач по категориям

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpicsAndSubtasks() {
        subtasks.clear();
        epics.clear();
    }

    //Получение задачи по идентификатору

    public Task getTaskById(int index) {
        Task task = null;
        if (tasks.isEmpty()) {
            return task;
        }
        for (int i : tasks.keySet()) {
            if (i == index) {
                task = tasks.get(index);
            } else {
                return task;
            }
        }
        return task;
    }

    public Epic getEpicById(int index) {
        Epic epic = null;
        if (epics.isEmpty()) {
            return epic;
        }
        for (int i : epics.keySet()) {
            if (i == index) {
                epic = epics.get(index);
            } else {
                return epic;
            }
        }
        return epic;
    }

    public SubTask getSubtaskByEpicId(int epicId) {
        SubTask Subtask = null;
        if (subtasks.isEmpty()){
            return Subtask;
        }
        for (int i : subtasks.keySet()) {
            if (i == epicId) {
                Subtask = subtasks.get(epicId);
            } else {
                return Subtask;
            }
        }
        return Subtask;
    }

    //Удаление задачи по идентификатору

    public void deleteTaskById(int index) {
        if (tasks.isEmpty()){
            return;
        }
        for (int i : subtasks.keySet()) {
            if(i == index) {
                subtasks.remove(index);
            }
        }
    }

    public void deleteEpicById(int index) {
        if (epics.isEmpty()){
            return;
        }
        for (int i : epics.keySet()) {
            if(i == index) {
                epics.remove(index);
            }
        }
    }

    public void deleteSubtaskById(int epicId) {
        if (subtasks.isEmpty()){
            return;
        }
        for (int i : subtasks.keySet()) {
            if(i == epicId) {
                subtasks.remove(epicId);
            }
        }
    }

    //Обновление задачи

    public void updateTask(int index, Task task) {
        if (tasks.isEmpty()){
            return;
        }
        for (int i : tasks.keySet()) {
            if (i == index) {
                tasks.remove(index);
                tasks.put(index, task);
            } else {
                return;
            }
        }
    }

    public TaskProgress updateEpicStatus(int epicId) {
        TaskProgress status = null;
        if (epics.isEmpty()){
            return status;
        }
        for (int i : subtasks.keySet()) {
            SubTask subtask = subtasks.get(i);
            if (epicId == subtask.getEpicId()) {
                if (subtask.getStatus().equals(TaskProgress.NEW)){
                    status = TaskProgress.NEW;
                } else if (subtask.getStatus().equals(TaskProgress.DONE)) {
                    status = TaskProgress.DONE;
                }else{
                    return TaskProgress.IN_PROGRESS;
                }
            }
        }
        return status;
    }

    public void updateSubtask(int SubtaskId, SubTask subtask) {
        if (subtasks.isEmpty()) {
            return;
        }
        for (int i : subtasks.keySet()) {
            if (i == SubtaskId) {
                subtasks.put(SubtaskId, subtask);
                subtask = subtasks.get(i);
                updateEpicStatus(subtask.getEpicId());
            }
        }
    }

    //Получение сабтасков определенного эпика
    public ArrayList<SubTask> getSubtasksFromEpic(int epicId) {
        ArrayList<SubTask> subtasksFromEpic = new ArrayList<>();
        for (int i : subtasks.keySet()){
            SubTask subtask = subtasks.get(i);
            if (epicId == subtask.getEpicId()) {
                subtasksFromEpic.add(subtasks.get(i));
            }
        }
        return subtasksFromEpic;
    }
}
