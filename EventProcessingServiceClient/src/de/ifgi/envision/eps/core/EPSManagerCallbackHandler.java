
/**
 * EPSManagerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package de.ifgi.envision.eps.core;

    /**
     *  EPSManagerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EPSManagerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EPSManagerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EPSManagerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for registerRuleMLStatement method
            * override this method for handling normal response from registerRuleMLStatement operation
            */
           public void receiveResultregisterRuleMLStatement(
                    de.ifgi.envision.eps.core.EPSManagerStub.RegisterRuleMLStatementResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerRuleMLStatement operation
           */
            public void receiveErrorregisterRuleMLStatement(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerStatement method
            * override this method for handling normal response from registerStatement operation
            */
           public void receiveResultregisterStatement(
                    de.ifgi.envision.eps.core.EPSManagerStub.RegisterStatementResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerStatement operation
           */
            public void receiveErrorregisterStatement(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for start method
            * override this method for handling normal response from start operation
            */
           public void receiveResultstart(
                    de.ifgi.envision.eps.core.EPSManagerStub.StartResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from start operation
           */
            public void receiveErrorstart(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerJSONStatement method
            * override this method for handling normal response from registerJSONStatement operation
            */
           public void receiveResultregisterJSONStatement(
                    de.ifgi.envision.eps.core.EPSManagerStub.RegisterJSONStatementResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerJSONStatement operation
           */
            public void receiveErrorregisterJSONStatement(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restart method
            * override this method for handling normal response from restart operation
            */
           public void receiveResultrestart(
                    de.ifgi.envision.eps.core.EPSManagerStub.RestartResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restart operation
           */
            public void receiveErrorrestart(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for stop method
            * override this method for handling normal response from stop operation
            */
           public void receiveResultstop(
                    de.ifgi.envision.eps.core.EPSManagerStub.StopResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from stop operation
           */
            public void receiveErrorstop(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerService method
            * override this method for handling normal response from registerService operation
            */
           public void receiveResultregisterService(
                    de.ifgi.envision.eps.core.EPSManagerStub.RegisterServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerService operation
           */
            public void receiveErrorregisterService(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registerServiceForHistoricalData method
            * override this method for handling normal response from registerServiceForHistoricalData operation
            */
           public void receiveResultregisterServiceForHistoricalData(
                    de.ifgi.envision.eps.core.EPSManagerStub.RegisterServiceForHistoricalDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registerServiceForHistoricalData operation
           */
            public void receiveErrorregisterServiceForHistoricalData(java.lang.Exception e) {
            }
                


    }
    