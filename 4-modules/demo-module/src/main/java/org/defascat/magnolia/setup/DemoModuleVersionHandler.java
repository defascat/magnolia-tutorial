package org.defascat.magnolia.setup;

import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.AbstractTask;
import info.magnolia.module.delta.CreateNodePathTask;
import info.magnolia.module.delta.Delta;
import info.magnolia.module.delta.DeltaBuilder;
import info.magnolia.module.delta.SetPropertyTask;
import info.magnolia.module.delta.Task;
import info.magnolia.module.delta.TaskExecutionException;
import info.magnolia.repository.RepositoryConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.jackrabbit.core.config.RepositoryConfig;

/**
 * This class is optional and lets you manager the versions of your module,
 * by registering "deltas" to maintain the module's configuration, or other type of content.
 * If you don't need this, simply remove the reference to this class in the module descriptor xml.
 */
public class DemoModuleVersionHandler extends DefaultModuleVersionHandler {
    @Override
    protected List<Task> getExtraInstallTasks(InstallContext installContext) {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new CreateNodePathTask("Create config path", "/modules/demo-module/config", "mgnl:content"));
        tasks.add(new SetPropertyTask(RepositoryConstants.CONFIG, "/modules/demo-module/config", "greeting", "Hello!"));
        return tasks;
    }

    public DemoModuleVersionHandler() {
        register(DeltaBuilder.update("0.0.2", "Update to 0.0.2").addTask(
                new SetPropertyTask(RepositoryConstants.CONFIG, "/modules/demo-module/config", "greeting", "Дабрыдзень!")));
    }
}