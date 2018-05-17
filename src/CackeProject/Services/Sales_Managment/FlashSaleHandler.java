package CackeProject.Services.Sales_Managment;


import CackeProject.Entities.FlashSale;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class FlashSaleHandler implements org.quartz.Job {


    public FlashSaleHandler() {
    }



    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        FlashSale flashSale = (FlashSale) dataMap.get("flashsale");
        flashSale.setState("OFF");
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        crudFlashSale.updateFlashSale(flashSale,flashSale.getId());
    }
}