package org.roda_project.commons_ip2.validator.handlers;

import org.roda_project.commons_ip2.validator.metsHelpers.Agent;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsHdrAgentComponentHandler extends DefaultHandler {
    private List<Agent> agents;

    public MetsHdrAgentComponentHandler(List<Agent> agents) {
        this.agents = agents;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equals("agent")) {
            Agent agent = new Agent();
            for(int i=0; i<attributes.getLength(); i++){
                setAgent(agent,attributes.getLocalName(i), attributes.getValue(i));
            }
            agents.add(agent);
        }
    }

    private void setAgent(Agent agent,String name,String value) {
        if(name.equals("ROLE")){
            agent.setRole(value);
        }
        if(name.equals("TYPE")){
            agent.setType(value);
        }
        if(name.equals("OTHERTYPE")){
            agent.setOtherType(value);
        }
        if(name.equals("OTHERROLE")){
            agent.setOtherRole(value);
        }
    }
}
