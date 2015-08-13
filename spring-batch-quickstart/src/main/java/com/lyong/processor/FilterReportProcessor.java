package com.lyong.processor;

import com.lyong.module.Report;
import org.springframework.batch.item.ItemProcessor;

//run before writing
public class FilterReportProcessor implements ItemProcessor<Report, Report> {

    @Override
    public Report process(Report item) throws Exception {

        //filter object which age = 30
        if (item.getAge() == 30) {
            return null; // null = ignore this object
        }
        return item;
    }

}