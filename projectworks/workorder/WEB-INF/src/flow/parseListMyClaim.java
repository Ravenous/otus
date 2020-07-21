package flow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avaya.sce.runtimecommon.IVariable;

/**
 * A basic servlet which allows a user to define their code, generate
 * any output, and to select where to transition to next.
 * Last generated by Orchestration Designer at: 20 ИЮЛЯ 2020 Г. 19:11:47 MSK
 */
public class parseListMyClaim extends com.avaya.sce.runtime.BasicServlet {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 20 ИЮЛЯ 2020 Г. 19:11:47 MSK
	 */
	public parseListMyClaim() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * This method allows for custom integration with other Java components.
	 * You may use Java for sophisticated logic or to integrate with custom
	 * connectors (i.e. JMS, custom web services, sockets, XML, JAXB, etc.)
	 *
	 * Any custom code added here should work as efficiently as possible to prevent delays.
	 * It's important to design your callflow so that the voice browser (Experienve Portal/IR)
	 * is not waiting too long for a response as this can lead to a poor caller experience.
	 * Additionally, if the response to the client voice browser exceeds the configured
	 * timeout, the platform may throw an "error.badfetch". 
	 *
	 * Using this method, you have access to all session variables through the 
	 * SCESession object.
	 *
	 * The code generator will *** NOT *** overwrite this method in the future.
	 * Last generated by Orchestration Designer at: 20 ИЮЛЯ 2020 Г. 19:11:47 MSK
	 */
	public void servletImplementation(com.avaya.sce.runtimecommon.SCESession mySession) {
		
		IVariable var = mySession.getVariable(IProjectVariables.CLAIM);
		var.addCollection();
		if (var.isCollection() == true) {
			var.getCollection().removeAll();
		} else {
			var.addCollection();
		}		
		var.getCollection().reset();
		
		String jsonString = mySession.getVariableField(IProjectVariables.RESPONSE, IProjectVariables.RESPONSE_FIELD_ALL).getStringValue();
		if (jsonString.isEmpty()) {
			return;
		}
		jsonString = jsonString.replace("{\"[", "[{").replace("\"\"", "\"");

		JSONArray jsonArr = null;
		try {
			jsonArr = new JSONArray(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //List<Data> dataList = new ArrayList<Data>();
	    for (int i = 0; i < jsonArr.length(); i++) {
	        try {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				var.getComplexVariable().getField(IProjectVariables.CLAIM_FIELD_ID).setValue(jsonObj.getInt("id"));
				var.getComplexVariable().getField(IProjectVariables.CLAIM_FIELD_TOPIC).setValue(jsonObj.getString("topic"));
				var.getComplexVariable().getField(IProjectVariables.CLAIM_FIELD_DESCRIPTION).setValue(jsonObj.getString("description"));
				var.getComplexVariable().getField(IProjectVariables.CLAIM_FIELD_STATUS).setValue(jsonObj.getString("statusName"));
				var.getComplexVariable().getField(IProjectVariables.CLAIM_FIELD_CRITICAL).setValue(jsonObj.getString("criticalName"));
				var.getCollection().append();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		var.getCollection().reset();	
		var.getCollection().next();	

	}
	/**
	 * Builds the list of branches that are defined for this servlet object.
	 * This list is built automatically by defining Goto nodes in the call flow editor.
	 * It is the programmer's responsibilty to provide at least one enabled Goto.<BR>
	 *
	 * The user should override updateBranches() to determine which Goto that the
	 * framework will activate.  If there is not at least one enabled Goto item, 
	 * the framework will throw a runtime exception.<BR>
	 *
	 * This method is generated automatically and changes to it may
	 * be overwritten next time code is generated.  To modify the list
	 * of branches for the flow item, override:
	 *     <code>updateBranches(Collection branches, SCESession mySession)</code>
	 *
	 * @return a Collection of <code>com.avaya.sce.runtime.Goto</code>
	 * objects that will be evaluated at runtime.  If there are no gotos
	 * defined in the Servlet node, then this returns null.
	 * Last generated by Orchestration Designer at: 21 ИЮЛЯ 2020 Г. 12:14:05 MSK
	 */
	public java.util.Collection getBranches(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		com.avaya.sce.runtime.Goto aGoto = null;
		list = new java.util.ArrayList(1);

		aGoto = new com.avaya.sce.runtime.Goto("chkMyListClaim", 0, true, "Default");
		list.add(aGoto);

		return list;
	}
}
