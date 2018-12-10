package org.chtijbug.kie.rest.backend;

import org.kie.api.io.ResourceType;


/**
 * Created by nheron on 23/01/15.
 */
public class RestTypeDefinition {


    public boolean accept(String fileName) {
        boolean result = false;

        if (fileName.startsWith(".") == false) {

            if (fileName.endsWith("." + ResourceType.DRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.GDRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.RDRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.XDRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DSL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DSLR.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.RDSLR.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DRF.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.BPMN2.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.CMMN.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DTABLE.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.BRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.XSD.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.PMML.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DESCR.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.JAVA.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.PROPERTIES.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.SCARD.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.TDRL.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.BAYES.getDefaultExtension())
                    //  ||fileName.endsWith("." + ResourceType.JAVA.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.TEMPLATE.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DRT.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.GDST.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.SCGD.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.SOLVER.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.DMN.getDefaultExtension())
                    || fileName.endsWith("." + ResourceType.FEEL.getDefaultExtension())


            ) {
                result = true;
            }
        }
        return result;
    }
}

