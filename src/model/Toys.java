package model;


public class Toys{
    private int id;
    private String name;
    private int dropFrequency;
    private int count;
    
    public Toys(int id, String name, int dropFrequency, int count) {
        this.id = id;
        this.name = name;
        this.dropFrequency = dropFrequency;
        this.count = count;
    }
    
    @Override
    public String toString()
    {
        return "toy id = " + id + 
        ", toy name = " + name + 
        ", dropFrequency = " + dropFrequency + 
        ", count = " + count;
    }
    public String toFile(){
        return id + "/" +
        name + "/" + 
        dropFrequency + "/" +
        count;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDropFrequency() {
        return dropFrequency;
    }

    public void setDropFrequency(int dropFrequency) {
        if(dropFrequency< 0) {
            System.out.println("You entered the wrong value! " + dropFrequency + "\nSet value " + 0);
            dropFrequency = 0;
        }
        if(dropFrequency > 100) {
            System.out.println("You entered the wrong value! " + dropFrequency + "\nSet value " + 100);
            dropFrequency = 100;
        }
        this.dropFrequency = dropFrequency;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count < 0) {
            System.out.println("Quantity cannot be negative! Set value 0 !");
            count = 0;
        }
        this.count = count;
    }

    


  
    
}
