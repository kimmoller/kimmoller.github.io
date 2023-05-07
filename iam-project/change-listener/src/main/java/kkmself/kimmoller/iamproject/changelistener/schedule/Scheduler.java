package kkmself.kimmoller.iamproject.changelistener.schedule;

import kkmself.kimmoller.iamproject.changelistener.action.AccountProvisioningTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class Scheduler {

  private final AccountProvisioningTask accountProvisioningTask;

  @Scheduled(fixedDelay = 5000, initialDelay = 10000)
  public void scheduler() {
    accountProvisioningTask.provisionAccounts();
  }
}
