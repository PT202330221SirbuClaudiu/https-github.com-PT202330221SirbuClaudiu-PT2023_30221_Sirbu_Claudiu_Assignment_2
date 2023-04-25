package org.example.Controller;

import org.example.BusinessLogic.Scheduler;
import org.example.BusinessLogic.SimulationManager;
import org.example.GUI.SimulationFrame;
import org.example.Model.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller
{
    public ActionListener ButtonStart(SimulationManager m)
    {
        ActionListener apasare = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                m.setMinProcessingTime(Integer.parseInt(m.getFrame().getTextField1().getText()));
                m.setMaxProcessingTime(Integer.parseInt(m.getFrame().getTextField2().getText()));
                m.setMinArrival(Integer.parseInt(m.getFrame().getTextField3().getText()));
                m.setMaxArrival(Integer.parseInt(m.getFrame().getTextField4().getText()));
                m.setNumberOfClients(Integer.parseInt(m.getFrame().getTextField6().getText()));
                m.setTimeLimit(Integer.parseInt(m.getFrame().getTextField7().getText()));
                m.setNrServers(Integer.parseInt(m.getFrame().getTextField5().getText()));
                m.generateNRandomTasks();
                m.setScheduler(new Scheduler(Integer.parseInt(m.getFrame().getTextField5().getText())));
                for(Server s: m.getScheduler().getServers())
                {
                    Thread taux = new Thread(s);
                    taux.start();
                }

                Thread t = new Thread(m);
                t.start();

            }
        };
        return apasare;

    }

}
