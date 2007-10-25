package sos.scheduler.editor.conf;

import sos.scheduler.editor.app.IDataChanged;

public interface ISchedulerUpdate extends IDataChanged {
    public void updateJobs();

  	public void updateJob();
  	
  	public void expandJob(String job);


    public void updateJob(String s);


    public void updateCommand(String s);


    public void updateCommands();


    public void updateOrder(String s);


    public void updateOrders();


    public void updateDays(int type);
    
    public void updateDays(int type, String name);    
    
    public void updateSpecificWeekdays();
    
    public void updateJobChains();
    
    public void updateJobChain(String newName, String oldName);
    
    
    
}
