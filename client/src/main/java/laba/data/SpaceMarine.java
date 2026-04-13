package laba.data;

import java.time.LocalDate;
import java.util.ArrayList;

import laba.exceptions.IncorrectCommandException;
import laba.exceptions.IncorrectIDException;
/**
 * Class of collection items
 */
public class SpaceMarine implements Comparable<SpaceMarine>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле не может быть null

    private static ArrayList<Integer> listID = new ArrayList<>();
    private static Integer count = 0;

    {
        id = count++;
        while (true) {
            id++;
            if (!listID.contains(id)) {
                listID.add(id);
                break;
            } 
        }

        health = 1; 
        name = "";
        coordinates = new Coordinates(0, 0);
        category = AstartesCategory.APOTHECARY;
        weaponType = Weapon.COMBI_FLAMER;
        meleeWeapon = MeleeWeapon.CHAIN_AXE;
        chapter = new Chapter();
    }


    public boolean validate() {
        if (name == null || coordinates == null || creationDate == null
            || category == null || weaponType == null || meleeWeapon == null 
            || chapter == null || id == null
        ) return false;
        if (health <= 0 || !coordinates.validate() || !chapter.validate()) return false;
        if (creationDate.isAfter(LocalDate.now())) return false;
        return true;
    }

    @Override
    public int compareTo(SpaceMarine sm) {
        return name.compareTo(sm.getName());
    }

    public SpaceMarine() {
    }
    

    public static Integer getCount() {
        return count;
    }

    public static ArrayList<Integer> getListID() {
        return listID;
    }
    
    public static void addID(Integer ID) throws IncorrectIDException{
        for (Integer i : listID) {
            
            if (i.equals(ID)) {
                throw new IncorrectIDException();
            }
        }
        listID.add(ID);
    }
    
    public static void removeID(Integer ID) {
        listID.remove(ID);
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setId(Integer id) throws IncorrectIDException{
        for (Integer i : listID) {
            if (i == id) {
                throw new IncorrectIDException();
            }
        }
        this.id = id;
    }

    public void setHealth(long health) throws IncorrectCommandException{
        if (health <= 0) throw new IncorrectCommandException();
        this.health = health;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

     public void setCategory(String line) throws IncorrectCommandException{
        switch (line.toUpperCase()) {
            case "INCEPTOR": 
                category = AstartesCategory.INCEPTOR;
                break;
            case "SUPPRESSOR": 
                category = AstartesCategory.SUPPRESSOR; 
                break;
            case "TERMINATOR": 
                category = AstartesCategory.TERMINATOR;
                break;
            case "CHAPLAIN": 
                category = AstartesCategory.CHAPLAIN;
                break;
            case "APOTHECARY": 
                category = AstartesCategory.APOTHECARY;
                break;
            default: throw new IncorrectCommandException(); 
        }
    }

    public void setMeleeWeapon(String line) throws IncorrectCommandException{
        
        switch (line.toUpperCase()) {
            case "CHAIN_SWORD": 
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case "CHAIN_AXE": 
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case "MANREAPER": 
                meleeWeapon = MeleeWeapon.MANREAPER;
            case "POWER_FIST": 
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
            default: throw new IncorrectCommandException();
        }
    
    }

    public void setWeaponType(String line) throws IncorrectCommandException{
        switch (line.toUpperCase()) {
            case "COMBI_FLAMER":
                weaponType = Weapon.COMBI_FLAMER; 
                break;
            case "COMBI_PLASMA_GUN":
                weaponType = Weapon.COMBI_PLASMA_GUN;
                break;
            case "FLAMER": 
                weaponType = Weapon.FLAMER;
                break;
            default:
                throw new IncorrectCommandException();
        }
    }

    public Integer getId() {
        return id;
    }

    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public long getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

   

    @Override
    public String toString() {
        return "ID: " + id +
        "\nИмя: " + name + 
        "\nКоординаты: " + coordinates.toString() + 
        "\nЗдоровье: " + health +
        "\nChapter {" + 
        "\n   name: "+chapter.getName() +
        "\n   world: "+chapter.getWorld() + "\n}" + 
        "\nДата создания элемента: " + creationDate +
        "\nТип AstartesCategory: " + category + 
        "\nТип Weapon: " + weaponType + 
        "\nТип MeleeWeapon: " + meleeWeapon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpaceMarine sm = (SpaceMarine) obj;
        return name == sm.getName() &&
         weaponType == sm.getWeaponType() &&
          category == sm.getCategory() && 
          meleeWeapon == getMeleeWeapon() &&
          coordinates.getX() == sm.getCoordinates().getX() &&
          coordinates.getY() == sm.getCoordinates().getY() && 
          meleeWeapon.equals(sm.getMeleeWeapon()) &&
          weaponType.equals(sm.getWeaponType()) &&
          category.equals(sm.getCategory());
    }

}
