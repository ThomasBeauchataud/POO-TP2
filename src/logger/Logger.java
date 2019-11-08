package logger;

import entity.*;
import managers.ConfigManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.FileWriter;
import java.util.Date;

@Aspect
public class Logger {

    @Before("execution(public void entity.Pigeon.rushTo(entity.PositionInterface))")
    public void tracePigeonRush (JoinPoint joinPoint) {
        Position newPosition = (Position) joinPoint.getArgs()[0];
        Pigeon pigeon = (Pigeon) joinPoint.getTarget();
        logPigeon(pigeon.toString()+" rushing from "+pigeon.getPosition().toString()+" to "+newPosition.toString());
    }

    @Before("execution(public void entity.Pigeon.moveTo(entity.PositionInterface))")
    public void tracePigeonMovement (JoinPoint joinPoint) {
        Position newPosition = (Position) joinPoint.getArgs()[0];
        Pigeon pigeon = (Pigeon) joinPoint.getTarget();
        logPigeon(pigeon.toString()+" moving from "+pigeon.getPosition().toString()+" to "+newPosition.toString());
    }

    @Before("execution(private void thread.PigeonLifeCycle.eatenTrigger(entity.PigeonInterface, entity.FoodInterface))")
    public void tracePigeonFeeding (JoinPoint joinPoint) {
        Pigeon pigeon = (Pigeon) joinPoint.getArgs()[0];
        Food food = (Food) joinPoint.getArgs()[1];
        logPigeon(pigeon.toString()+" has eaten "+food.toString());
    }

    @Before("execution(public void database.ClassDatabase.updateFear(entity.FearInterface))")
    public void traceFear (JoinPoint joinPoint) {
        Fear fear = (Fear) joinPoint.getArgs()[0];
        if(fear.isExpired()) {
            logFear(fear.toString() + " disappeared");
        } else {
            logFear(fear.toString() + " appeared at " + fear.getPosition());
        }
    }

    @Before("execution(public void database.ClassDatabase.addFood(entity.FoodInterface) )")
    public void traceFoodApparition(JoinPoint joinPoint) {
        Food food = (Food) joinPoint.getArgs()[0];
        logFood(food.toString() + " dropped at " + food.getPosition().toString());
    }

    @Before("execution(public void entity.Food.gettingOld())")
    public void traceFoodLifeCycle(JoinPoint joinPoint) {
        Food food = (Food) joinPoint.getTarget();
        if(food.getDurability() != 0) {
            logFood(food.toString() + " getting old: " + food.getDurability() + "/" + ConfigManager.getInt("defaultDurability"));
        } else {
            logFood(food.toString() + " expired");
        }
    }

    private void logPigeon(String content) {
        this.persistConsole(content);
        this.persistFile("pigeon", content);
    }

    private void logFood(String content) {
        this.persistConsole(content);
        this.persistFile("food", content);
    }

    private void logFear(String content) {
        this.persistConsole(content);
        this.persistFile("fear", content);
    }

    private void persistConsole(String content) {
        System.out.println(content);
    }

    private void persistFile(String fileName, String content) {
        String filename = System.getProperty("user.dir")+"/logs/"+fileName+".log";
        try {
            FileWriter fileWriter = new FileWriter(filename,true);
            fileWriter.write(new Date().toString()+ " - " +content + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
