package me.jy.lang.junit;

import lombok.extern.java.Log;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@Log
public class JUnitLauncherTest {

    private static LauncherDiscoveryRequest getLauncherRequests() {

        return LauncherDiscoveryRequestBuilder
            .request()
            .selectors(selectPackage("core.junit"),
                selectClass(JUnitScratches.class))
            .build();
    }

    public static void main(String[] args) {

        Launcher launcher = LauncherFactory.create();

        TestPlan testPlan = launcher.discover(getLauncherRequests());

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(testPlan);

    }
}
