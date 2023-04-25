package org.example.Model;

public class Task
{
    private int ID;
    private static int aux;
    private int arrivalTime;
    private int serviceTime;

    public Task(int arrivalTime, int serviceTime)
    {
        this.arrivalTime=arrivalTime;
        this.serviceTime=serviceTime;
        this.ID=aux;
        aux++;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public static int getAux() {
        return aux;
    }

    public int getID() {
        return ID;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public static void setAux(int aux) {
        Task.aux = aux;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }



}


