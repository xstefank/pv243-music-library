package cz.muni.fi.pv243.musiclib.batching.controller;

import cz.muni.fi.pv243.musiclib.logging.LogMessages;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

/**
 * @author Ondrej Oravcok
 * @version 18.5.2017
 */
@ApplicationScoped
public class CommentaryBatchControllerImpl implements CommentaryBatchController {

    private String commentaryJobName = "commentary-job";

    @Override
    public void startCommentaryFillingJob() {
        Properties jobParams = new Properties();

        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long jobId = jobOperator.start(commentaryJobName, jobParams);
        LogMessages.LOGGER.logBatchJobRunning(jobId);
    }

}
