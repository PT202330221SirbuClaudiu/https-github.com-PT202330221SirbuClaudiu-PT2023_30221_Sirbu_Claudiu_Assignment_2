package org.example.BusinessLogic;

import org.example.GUI.SimulationFrame;
import org.example.Model.Server;
import org.example.Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable
{
    public int timeLimit = 100;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int numberOfClients = 100;

    public int minArrival;

    public int maxArrival;

    public int nrServers;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;

    private Scheduler scheduler;

    private SimulationFrame frame;
    private List<Task> generatedTasks;

    public SimulationManager()
    {
        this.frame=new SimulationFrame(this);
        this.generatedTasks = new ArrayList<>();
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public SimulationFrame getFrame() {
        return frame;
    }

    public void setFrame(SimulationFrame frame) {
        this.frame = frame;
    }

    public void setNrServers(int nrServers) {
        this.nrServers = nrServers;
    }

    public void setGeneratedTasks(List<Task> generatedTasks) {
        this.generatedTasks = generatedTasks;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMaxArrival() {
        return maxArrival;
    }

    public int getMinArrival() {
        return minArrival;
    }

    public void setMaxArrival(int maxArrival) {
        this.maxArrival = maxArrival;
    }

    public void setMinArrival(int minArrival) {
        this.minArrival = minArrival;
    }

    public void generateNRandomTasks()
    {
        for(int i=0; i<this.numberOfClients; i++)
        {
            Random r = new Random();
            int a = r.nextInt(this.maxArrival-this.minArrival) + this.minArrival;
            int w = r.nextInt(this.maxProcessingTime-this.minProcessingTime) + this.minProcessingTime;
            Task aux=new Task(a,w);
            this.generatedTasks.add(aux);
        }
    }

    @Override
    public void run()
    {
        int currentTime = 0;
        while (currentTime < timeLimit)
        {
            int ok = 0;
            if(!this.generatedTasks.isEmpty())
            {
                ok=1;
            }
            for(Server s : this.getScheduler().getServers())
            {
                if(!s.eGoala())
                {
                    ok=1;
                    break;
                }
            }
            if (ok == 1)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Server s : this.getScheduler().getServers()) {
                    synchronized (s.getLacat()) {
                        s.getLacat().notifyAll();
                    }
                }

                List<Task> aux = new ArrayList<>();

                for (Task t : this.generatedTasks) {
                    if (t.getArrivalTime() == currentTime) {
                        this.scheduler.dispatchTask(t);
                        aux.add(t);
                    }
                }

                for (Task t : aux) {
                    this.generatedTasks.remove(t);
                }

                this.frame.getTextArea1().setText(this.frame.getTextArea1().getText() + afisare(currentTime));
                currentTime++;
            }
            else
            {
                currentTime=this.timeLimit+1;
            }
        }


    }

    public String afisare(int timp)
    {
        String rez=" \n";
        rez += "Momentul: " + timp + "\n";
        rez += "Clientii din magazin: ";
        for(Task t : this.generatedTasks)
        {
            rez += "Client " + (t.getID()+1) + "(" + t.getArrivalTime() + ", " + t.getServiceTime() + ") ";
        }
        rez += "\n";
        for(Server s : this.getScheduler().getServers())
        {
            rez += "Casa nr " + s.getId() + ": ";
            for(Task t : s.getClienti())
            {
                rez += "Client " + (t.getID()+1) + "(" + t.getArrivalTime() + ", " + t.getServiceTime() + ") ";
            }
            rez += "\n";
        }
        return rez;
    }

}
