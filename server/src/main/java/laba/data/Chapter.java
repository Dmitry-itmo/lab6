package laba.data;

public class Chapter implements Comparable<Chapter>{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле не может быть null

    {
        name = "";
        world = "";
    }

    @Override
    public int compareTo(Chapter chapter) {
        return name.compareTo(chapter.getName());
    }

    public boolean validate() {
        if (name == null || world == null) return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nWorld: " + world;
    }
}
