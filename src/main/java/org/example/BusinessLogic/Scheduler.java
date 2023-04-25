package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{

    private List<Server> servers;
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler (int maxNoServers)
    {
        this.strategy = new ConcreteStrategyTime();
        this.maxNoServers = maxNoServers;
        this.servers = new ArrayList<>(maxNoServers);

        for (int i = 0; i < maxNoServers; i++) {
            Server aux = new Server();
            servers.add(aux);
        }
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        //apply strategy patter to instantiate the strategy with the concrete
        //strategy corresponding to policy
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t)
    {
        if(strategy.equals(SelectionPolicy.SHORTEST_TIME))
        {
            ConcreteStrategyTime x = new ConcreteStrategyTime();
            x.addTask(this.servers, t);
        }
        else
        {
            ConcreteStrategyQueue y = new ConcreteStrategyQueue();
            y.addTask(this.servers, t);
        }
    }

    public List<Server> getServers()
    {
        return servers;
    }

}
