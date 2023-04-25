package org.example.Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{

    private BlockingQueue<Task> clienti;

    private AtomicInteger waitingTime;

    private Object lacat;

    private Integer id=0;
    private static int aux;
    public Server()
    {
        this.clienti=new ArrayBlockingQueue<>(200);
        this.waitingTime=new AtomicInteger(0);
        this.lacat=new Object();
        aux++;
        id=aux;

    }

    public void adaugareClient(Task t)
    {
        this.clienti.add(t);
        this.waitingTime = new AtomicInteger(this.waitingTime.get()+ t.getServiceTime());
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(this.eGoala()==false)
            {
                Task aux = this.clienti.peek();
                if(aux.getServiceTime()==1)
                    this.clienti.remove(aux);
                else aux.setServiceTime(aux.getServiceTime()-1);
                this.waitingTime=new AtomicInteger(this.waitingTime.get()-1);
            }
            synchronized (lacat)
            {
                try
                {
                    lacat.wait();
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }


            }
        }

    }

    public static int getAux() {
        return aux;
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public BlockingQueue<Task> getClienti() {
        return clienti;
    }

    public Integer getId() {
        return id;
    }

    public Object getLacat() {
        return lacat;
    }
    public boolean eGoala()
    {
        if(this.clienti.isEmpty()==true)
            return true;
        else return false;
    }
}
