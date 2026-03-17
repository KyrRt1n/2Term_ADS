package ua.voloschenko.theme9;

public class Car {

    private String model;

    public Car(String model){
        this.model = model;
    }
    public static class Engine{
        int horsepower;

        public Engine(int horsepower){
            this.horsepower = horsepower; }
        public int getHorsepower() {
            return horsepower; }
    }
    public Engine spec(int horsepower){
        return new Engine(horsepower);
    }

    public String getModel() {
        return model;
    }
}
