package fi.metatavu.soteapi.server.integrations.management.tasks;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Singleton
@ApplicationScoped
@Startup
public class PageUpdater {

  @Inject
  private Instance<PageUpdateJob> finvoiceUpdateJob;
  
  @Resource
  private ManagedScheduledExecutorService managedScheduledExecutorService;
  
  @PostConstruct
  public void postConstruct() {
    startTimer(1000, 1000);
  }
  
  private void startTimer(long warmup, long delay) {
    managedScheduledExecutorService.scheduleWithFixedDelay(finvoiceUpdateJob.get(), warmup, delay, TimeUnit.MILLISECONDS);
  }

}
