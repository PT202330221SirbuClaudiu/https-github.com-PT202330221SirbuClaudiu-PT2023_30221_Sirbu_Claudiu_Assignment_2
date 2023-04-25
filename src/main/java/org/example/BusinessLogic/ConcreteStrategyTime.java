package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy
{

    @Override
    public void addTask(List<Server> servers, Task t)
    {
        Server minim = servers.get(0);
        for(Server i : servers)
        {
            if(minim.getWaitingTime().get()>i.getWaitingTime().get())
            {
                minim = i;
            }
        }
        minim.adaugareClient(t);
    }
}
