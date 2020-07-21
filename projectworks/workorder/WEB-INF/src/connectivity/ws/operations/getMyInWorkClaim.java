package connectivity.ws.operations;

/**
 * This class is generated automatically.  Manual edits must be outside of the tagged
 * areas (for example, "START:CLASS:..." and "END:CLASS:...").  Changes within the
 * tag areas will be overwritten when the web service operation is regenerated.
 * Last generated by Orchestration Designer at: 21 ИЮЛЯ 2020 Г. 6:55:33 MSK
 */
public class getMyInWorkClaim extends com.avaya.sce.runtime.connectivity.restws.CallService {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

    /**
    * Constructor for closeMyClaim.
    */
    public getMyInWorkClaim( com.avaya.sce.runtimecommon.IRuntimeSession mySession ) {
		//{{START:CLASS:CONSTRUCTOR
        super(mySession);

        //Web Service
        setServiceName("closeMyClaim");
		setHttpMethod("GET");
		setOutputFormat("String");
		setUseQuery("true");
		setUseJavaObject("true");
		setUseBody("false");
        setTimeoutSecondsFromParameter("listMyClaim_1595303733321TimeoutSeconds");
        setEndpointFromParameter("listMyClaim_1595303733321EndpointUrl");
        setAuthenticationFromParameter("listMyClaim_1595303733321Authentication");
        setPasswordFromParameter("listMyClaim_1595303733321Password");
        setUsernameFromParameter("listMyClaim_1595303733321Username");
        setPreemptiveFromParameter("listMyClaim_1595303733321Preemptive");
        setSecurityFromParameter("listMyClaim_1595303733321Security");
        setPortFromParameter("listMyClaim_1595303733321Port");

        //Operation
        setOperation(new javax.xml.namespace.QName("", "listClaim_OP"));

        //Input parameters
        {
            com.avaya.sce.runtime.connectivity.restws.WsParam param1 = new com.avaya.sce.runtime.connectivity.restws.WsParam(new javax.xml.namespace.QName("msisdn"));
            param1.setWrapped(false);
            param1.setParamType(1);
            param1.setDDVariableName("request:msisdn");
            addInParameter(param1);
        }
        {
            com.avaya.sce.runtime.connectivity.restws.WsParam param1 = new com.avaya.sce.runtime.connectivity.restws.WsParam(new javax.xml.namespace.QName("filter"));
            param1.setWrapped(false);
            param1.setParamType(1);
            param1.setDDVariableName("request:filter");
            addInParameter(param1);
        }

        //Output parameters
        {
            com.avaya.sce.runtime.connectivity.restws.WsParam param1 = new com.avaya.sce.runtime.connectivity.restws.WsParam(new javax.xml.namespace.QName("(ALL)"));
            param1.setWrapped(false);
            param1.setParamType(0);
            param1.setDDVariableName("response:all");
            addOutParameter(param1);
        }
		setMessageBody("");
        //}}END:CLASS:CONSTRUCTOR




    }

}
