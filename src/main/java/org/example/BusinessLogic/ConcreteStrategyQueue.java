package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;

public class ConcreteStrategyQueue  implements Strategy
{

    @Override
    public void addTask(List<Server> servers, Task t)
    {
        Server minim = servers.get(0);
        for(Server i : servers)
        {
            if(minim.getClienti().size()>i.getClienti().size())
            {
                minim = i;
            }
        }
        minim.adaugareClient(t);
    }
}
