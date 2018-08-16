package day11.behavioralPattern.mediator;

public class Colleague {
    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
       this.name = name;
       this.mediator = mediator;
    }
}
